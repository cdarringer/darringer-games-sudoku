package com.darringer.games.sudoku;

import static com.darringer.games.sudoku.SudokuModel.SIZE;
import static com.darringer.games.sudoku.SudokuModel.SOLVED_OPTION_COUNT;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * Contains logic for solving a Sudoku puzzle
 * 
 * @author cdarringer
 * @see SudokuModel
 *
 */
public class SudokuSolver {

	private Logger log = Logger.getLogger(SudokuSolver.class);
	private List<SudokuStrategy> strategies = new ArrayList<SudokuStrategy>();

	/**
	 * 
	 */
	public SudokuSolver() {
		strategies.add(0, new SudokuNakedSinglesStrategy());
		strategies.add(1, new SudokuHiddenSingleStrategy());
		strategies.add(2, new SudokuPointedStrategy());
		strategies.add(3, new SudokuNakedPairsStrategy());
		strategies.add(4, new SudokuNakedTriplesStrategy());
	}
	
	
	/**
	 * Return the solution for a given {@link SudokuModel} or throw a 
	 * {@link IllegalArgumentException} if the given puzzle cannot be solved.
	 * 
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 */
	public SudokuModel solve(SudokuModel model) throws IllegalArgumentException {
		long startTime = System.currentTimeMillis();
		int currentStrategyIndex = 0;
		int previousOptionCount = getTotalOptionCount(model);
		while (previousOptionCount > SOLVED_OPTION_COUNT) {	
			// for each cell..
			for (int x=0; x < SIZE; x++) {
				for (int y=0; y < SIZE; y++) {
					SudokuStrategy currentStrategy = strategies.get(currentStrategyIndex);
					model = currentStrategy.applyStrategy(model, x, y);				}
			}
			int currentOptionCount = getTotalOptionCount(model);
			if (currentOptionCount == previousOptionCount) {
				currentStrategyIndex++;
				if (currentStrategyIndex == strategies.size()) {
					// we are out of things to try!
					log.error("Could not solve puzzle with existing strategies");
					log.error("\n" + model);
					throw new IllegalArgumentException("The given model appears to be unsolvable with current strategies.  Stuck at option count: " + currentOptionCount);
				} else {
					log.info("We are stuck, trying the next strategy...");
				}
			} else {
				currentStrategyIndex = 0;
				previousOptionCount = currentOptionCount;
			}
		}
		log.info(String.format("Model solved in %d ms:", System.currentTimeMillis() - startTime));
		for (SudokuStrategy currentStrategy : strategies) {
			log.info(String.format("   %d ms spent in %s", 
					currentStrategy.getElapsedTimeInStrategy(), 
					currentStrategy.getName()));
		}
		return model;
	}
		
	/**
	 * Return the total size of the option sets in the given {@link SudokuModel} 
	 * 
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 */
	private int getTotalOptionCount(SudokuModel model) throws IllegalArgumentException {
		int optionCount = 0;
		for (int x=0; x < SIZE; x++) {
			for (int y=0; y < SIZE; y++) {
				int currentOptionCount = model.getSet(x, y).size();
				if (currentOptionCount == 0) {
					String message = String.format("Square at %d, %d unexpectedly went to 0 options", x, y);
					log.error(message);
					throw new IllegalArgumentException(message);
				}
				optionCount += currentOptionCount;
			}
		}
		return optionCount;
	}		
}
