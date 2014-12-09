package com.darringer.games.sudoku;


/**
 * Contains logic for solving a Sudoku puzzle
 * 
 * @author cdarringer
 * @see SudokuModel
 *
 */
public class SudokuSolver {

	private SudokuStrategy nakedSinglesStrategy = new SudokuNakedSinglesStrategy();
	private SudokuStrategy hiddenSinglesStrategy = new SudokuHiddenSinglesStrategy();

	
	/**
	 * Return the solution for a given {@link SudokuModel} or throw a 
	 * {@link IllegalArgumentException} if the given puzzle cannot be solved.
	 * 
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 */
	public SudokuModel solve(SudokuModel model) throws IllegalArgumentException {
		int solvedOptionCount = (SudokuModel.SIZE * SudokuModel.SIZE);
		int previousOptionCount = getTotalOptionCount(model);
		while (previousOptionCount > solvedOptionCount) {	
			// for each cell..
			for (int x=0; x < SudokuModel.SIZE; x++) {
				for (int y=0; y < SudokuModel.SIZE; y++) {
					model = nakedSinglesStrategy.applyStrategy(model, x, y);
					model = hiddenSinglesStrategy.applyStrategy(model, x, y);
				}
			}
			int currentOptionCount = getTotalOptionCount(model);
			if (currentOptionCount == previousOptionCount) {
				throw new IllegalArgumentException("The given model appears to be unsolvable.  Stuck at option count: " + currentOptionCount);
			} else {
				previousOptionCount = currentOptionCount;
			}
		}
		return model;
	}
		
	/**
	 * Return the total size of the option sets in the given {@link SudokuModel} 
	 * 
	 * @param model
	 * @return
	 */
	private int getTotalOptionCount(SudokuModel model) {
		int optionCount = 0;
		for (int x=0; x < SudokuModel.SIZE; x++) {
			for (int y=0; y < SudokuModel.SIZE; y++) {
				optionCount += model.getSet(x, y).size();
			}
		}
		return optionCount;
	}		
}
