package com.darringer.games.sudoku;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 
 * @author cdarringer
 * 
 */
public class SudokuHiddenStrategy implements SudokuStrategy {

	private Logger log = Logger.getLogger(SudokuHiddenStrategy.class);
	long elapsedTime = 0l;

	/**
	 * 
	 */
	@Override
	public SudokuModel applyStrategy(SudokuModel model, int x, int y) {

		// this strategy only applies to cells with more than one possible value
		long startTime = System.currentTimeMillis();
		if (model.getSet(x, y).size() > 1) {

			// build a set of all values in this row
			Set<Integer> rowSet = new HashSet<Integer>();
			for (int i = 0; i < SudokuModel.SIZE; i++) {
				if (i != x) {
					rowSet.addAll(model.getSet(i, y));
				}
			}

			// if there is a single item in the current cell set that is not in
			// the row set then we know the cell value
			Set<Integer> currentCellSet = new HashSet<Integer>(model.getSet(x, y));
			currentCellSet.removeAll(rowSet);
			if (currentCellSet.size() == 1) {
				log.info(String.format("Found row hidden single at %d, %d", x, y));
				model.setSet(x, y, currentCellSet);
				return model;
			}

			// build a set of all values in this column
			Set<Integer> columnSet = new HashSet<Integer>();
			for (int i = 0; i < SudokuModel.SIZE; i++) {
				if (i != y) {
					columnSet.addAll(model.getSet(x, i));
				}
			}

			// if there is a single item in the current cell set that is not in
			// the column set then we know the cell value
			currentCellSet = new HashSet<Integer>(model.getSet(x, y));
			currentCellSet.removeAll(columnSet);
			if (currentCellSet.size() == 1) {
				log.info(String.format("Found column hidden single at %d, %d", x, y));
				model.setSet(x, y, currentCellSet);
				return model;
			}

			// build a set of all values in this subsquare
			Set<Integer> subSquareSet = new HashSet<Integer>();
			int xRoot = (x / 3) * 3;
			int yRoot = (y / 3) * 3;
			for (int xDelta = 0; xDelta < 3; xDelta++) {
				for (int yDelta = 0; yDelta < 3; yDelta++) {
					int xSubSquare = xRoot + xDelta;
					int ySubSquare = yRoot + yDelta;
					if ((xSubSquare != x) || (ySubSquare != y)) {
						subSquareSet.addAll(model.getSet(xSubSquare, ySubSquare));
					}
				}
			}

			// if there is a single item in the current cell set that is not in
			// the subsquare set then we know the cell value
			currentCellSet = new HashSet<Integer>(model.getSet(x, y));
			currentCellSet.removeAll(subSquareSet);
			if (currentCellSet.size() == 1) {
				log.info(String.format("Found subsquare hidden single at %d, %d", x, y));
				model.setSet(x, y, currentCellSet);
				return model;
			}

			// if we made it here, there were no hidden singles
			log.debug(String.format("No hidden singles at %d, %d", x, y));

		}
		elapsedTime += (System.currentTimeMillis() - startTime);
		return model;
	}

	@Override
	public String getName() {
		return "Hidden Strategy";
	}

	@Override
	public long getElapsedTimeInStrategy() {
		return elapsedTime;
	}
}
