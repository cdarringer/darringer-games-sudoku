package com.darringer.games.sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Maintain the state of a Sudoku board and provide some basic 
 * functions for getting the set of possible values for each 
 * square.
 * 
 * @author cdarringer
 *
 */
class SudokuModel {
	
	static final int SIZE = 9;
	static final int SOLVED_OPTION_COUNT = SIZE * SIZE;
	static final String EMPTY_VALUE = "-";
	
	List<Set<Integer>> model = new ArrayList<Set<Integer>>(SIZE * SIZE);
	
	/**
	 * Construct an empty Sudoku model
	 */
	public SudokuModel() {
		for (int y=0; y < SIZE; y++) {
			for (int x=0; x < SIZE; x++) {
				model.add(getFullSet());
			}
		}			
	}
	
	
	/**
	 * Construct a model from a string containing rows separated 
	 * by newline characters.
	 * 
	 * @param input
	 */
	public SudokuModel(String input) {
		Scanner scanner = new Scanner(input);
		
		for (int y=0; y < SIZE; y++) {
			String currentRow = scanner.nextLine();
			String[] currentRowValues = currentRow.split(" ");
			for (int x=0; x < SIZE; x++) {
				String currentValue = currentRowValues[x];
				if (currentValue.equals(EMPTY_VALUE)) {
					model.add(getFullSet());
				} else {
					Integer currentIntValue = Integer.parseInt(currentValue);
					Set<Integer> singleValueSet = new HashSet<Integer>(1);
					singleValueSet.add(currentIntValue);
					model.add(singleValueSet);
				}
			}
		}
		scanner.close();
	}
	
	/**
	 * Return a set containing all possible values for a single
	 * Sudoku square
	 * 
	 * @return
	 */
	private Set<Integer> getFullSet() {
		Set<Integer> fullSet = new HashSet<Integer>();
		for (int i=0; i < SIZE; i++) {
			fullSet.add(i+1);
		}
		return fullSet;
	}
	
	/**
	 * Get the set of possible values for the Sudoku square at the 
	 * given index
	 * 
	 * @param x
	 * @param y
	 * @return
	 */	
	public Set<Integer> getSet(int x, int y) {
		return model.get((y * SIZE) + x);
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param i
	 * @param region
	 * @return
	 */
	public Set<Integer> getSetForRegionIndex(int x, int y, int i, SudokuRegion region) {
		switch (region) {
		case ROW:
			return getSet(i, y);
		case COLUMN:
			return getSet(x, i);
		case SUBSQUARE:
			int xDelta = i % 3;
			int yDelta = i / 3;
			return getSet(x + xDelta, y + yDelta);
		default:
			return null;
		}	
	}
	

	/**
	 * Set the set of possible values for the Sudoku square at the 
	 * given index
	 * 
	 * @param x
	 * @param y
	 * @param set
	 */
	public void setSet(int x, int y, Set<Integer> set) {
		model.set((y * SIZE) + x, set);
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param values
	 */
	public void setNumbersAtLocation(int x, int y, int... values) {
		Set<Integer> options = new HashSet<Integer>();
		for (int value : values) {
			options.add(value);
		}
		setSet(x, y, options);
	}
	
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SudokuModel other = (SudokuModel) obj;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}

	/**
	 * Print the Sudoku board, displaying actual values for a square
	 * (when known) or the number of options in brackets when not 
	 * known.
	 */
	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		for (int y=0; y < SIZE; y++) {
			for (int x=0; x < SIZE; x++) {
				Set<Integer> currentValues = getSet(x, y);
				if (currentValues.size() == 1) {
					output.append(" " + currentValues.iterator().next() + " ");
				} else {
					output.append("[" + currentValues.size() + "]");
				}
			}
			output.append("\n");
		}
		return output.toString();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isSubSquareAnchor(int x, int y) {
		return (((x % 3) == 0) && ((y % 3) == 0)); 
	}
	
	public static boolean isRowAnchor(int x, int y) {
		return (x == 0); 		
	}
	
	public static boolean isColumnAnchor(int x, int y) {
		return (y == 0); 
	}
	
	
}
