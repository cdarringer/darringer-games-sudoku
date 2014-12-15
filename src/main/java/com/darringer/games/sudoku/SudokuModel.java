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
	public static boolean isAnchor(int x, int y) {
		return (((x % 3) == 0) && ((y % 3) == 0)); 
	}
}
