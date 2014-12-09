package com.darringer.games.sudoku;

import java.util.Set;

import org.apache.log4j.Logger;

/**
 * For each cell whose set of possible values contains a single number, 
 * remove that number from all possible column cell sets, all row cell 
 * sets, and all subsquare cell sets.
 * 
 * @author cdarringer
 *
 */
public class SudokuNakedSinglesStrategy implements SudokuStrategy {

	private Logger log = Logger.getLogger(SudokuNakedSinglesStrategy.class);
	
	@Override
	public SudokuModel applyStrategy(SudokuModel model, int x, int y) {
		Set<Integer> currentSet = model.getSet(x, y);
		if (currentSet.size() == 1) {
			// this cell has a "naked" single value
			// remove it from all cells in the same row...
			log.debug(String.format("SudokuNakedSingles Strategy being applied at %d, %d...", x, y));
			for (int i=0; i < SudokuModel.SIZE; i++) {
				if (i != x) {
					if (model.getSet(i, y).removeAll(currentSet)) {
						log.debug(String.format("Removed row value at %d, %d...", i, y));						
					}
				}
			}
		
			// remove this number as an option from all cells in the same column...
			for (int i=0; i < SudokuModel.SIZE; i++) {
				if (i != y) {
					if (model.getSet(x, i).removeAll(currentSet)) {
						log.debug(String.format("Removed column value at %d, %d...", x, i));						
					}
				}
			}

			// remove this number as an option from same subsquare cells...
			int xRoot = (x / 3) * 3;
			int yRoot = (y / 3) * 3;
			for (int xDelta=0; xDelta <3; xDelta++) {
				for (int yDelta=0; yDelta < 3; yDelta++) {
					int xSubSquare = xRoot + xDelta;
					int	ySubSquare = yRoot + yDelta;
					if ((xSubSquare != x) || (ySubSquare != y)) {
						if (model.getSet(xSubSquare, ySubSquare).removeAll(currentSet)) {
							log.debug(String.format("Removed subsquare value at %d, %d...", xSubSquare, ySubSquare));													
						}
					}
				}
			}
		}
		return model;
	}
}
