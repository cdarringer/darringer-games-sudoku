package com.darringer.games.sudoku;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 
 * @author cdarringer
 *
 */
public class SudokuPointedStrategy implements SudokuStrategy {
	
	Logger log = Logger.getLogger(SudokuPointedStrategy.class);
	private long elapsedTime = 0l;

	@Override
	public SudokuModel applyStrategy(SudokuModel model, int x, int y) {
		long startTime = System.currentTimeMillis();

		// is this subsquare "anchor" square?
		if (SudokuModel.isAnchor(x, y)) {
			log.debug("Anchor square detected, applying pointed strategy to current subsquare...");
				
			// count the instances each number appears in the entire subsquare
			Map<Integer, Integer> subSquareCounts = countSubSquareInstances(model, x, y); 
		
			// for each subsquare row...
			for (int subSquareRowIndex = 0; subSquareRowIndex < 3; subSquareRowIndex++) {
				// does this subsquare row contain all of the instances?
				Map<Integer, Integer> subSquareRowCounts = countSubSquareRowInstances(model, x, y, subSquareRowIndex); 
				for (int currentSubSquareRowValue : subSquareRowCounts.keySet()) {
					int currentSubSquareRowCount = subSquareRowCounts.get(currentSubSquareRowValue);
					if (currentSubSquareRowCount > 1) {
						// there is more than one instance of this value in this row
						int currentSubSquareCount = subSquareCounts.get(currentSubSquareRowValue);
						if (currentSubSquareCount == currentSubSquareRowCount) {
							// all instances in the subsquare are in this row
							// remove this "pointed" number from the entire row...
							log.info(String.format("Pointed pair for %d found in subsquare at anchor %d, %d", currentSubSquareRowValue, x, y)); 
							model = removePointedNumberFromRow(model, x, y, subSquareRowIndex, currentSubSquareRowValue);							
						}
					}
				}
			}
					
		
		// for each column...
			// does this subsquare column contain all of the instances?
		
			// it does, remove this number from the entire column

		}
		elapsedTime += (System.currentTimeMillis() - startTime);
		return model;
	}
	
	/**
	 * For each number that appears in the current subsquare, 
	 * return that map of that number to its subsquare count.
	 * We assume the given point is a subsquare anchor point.
	 * 
	 * @param model
	 * @param x
	 * @param y
	 * @return
	 */
	private Map<Integer, Integer> countSubSquareInstances(SudokuModel model, int x, int y) {
		Map<Integer, Integer> subSquareInstanceCount = new HashMap<Integer, Integer>();
		for (int i=0; i < 3; i++) {
			for (int j=0; j < 3; j++) {
				Set<Integer> currentSquareInstances = model.getSet(x + i, y + j);
				for (int currentNumber : currentSquareInstances) {
					updateInstanceCount(subSquareInstanceCount, currentNumber);
				}
			}
		}
		return subSquareInstanceCount;
	}
	
	/**
	 * For each number that appears in the current subsquare row, 
	 * return that map of that number to its subsquare row count.
	 * We assume the given point is a subsquare row anchor point.
	 * 
	 * @param model
	 * @param x
	 * @param y
	 * @param rowIndex
	 * @return
	 */
	private Map<Integer, Integer> countSubSquareRowInstances(SudokuModel model, int x, int y, int rowIndex) {
		Map<Integer, Integer> subSquareRowInstanceCount = new HashMap<Integer, Integer>();
		for (int i=0; i < 3; i++) {
			Set<Integer> currentSquareInstances = model.getSet(x + i, y + rowIndex);
			for (int currentNumber : currentSquareInstances) {
				updateInstanceCount(subSquareRowInstanceCount, currentNumber);
			}
		}
		return subSquareRowInstanceCount;
	}	
	
	/**
	 * Update the number count contained in the given map
	 * to reflect the addition of the given number
	 * 
	 * @param count
	 * @param number
	 */
	private void updateInstanceCount(Map<Integer, Integer> count, Integer number) {
		Integer currentNumberCount = count.get(number);
		if (currentNumberCount == null) {
			currentNumberCount = 1;
		} else {
			currentNumberCount++;
		}
		count.put(number, currentNumberCount);		
	}
	

	/**
	 * 
	 * @param model
	 * @param x
	 * @param y
	 * @param rowIndex
	 * @param number
	 */
	private SudokuModel removePointedNumberFromRow(SudokuModel model, int x, int y, int rowIndex, int number) {
		for (int i=0; i < SudokuModel.SIZE; i++) {
			if ((i < x) || (i >= (x+3))) {
				// this is a row square outside our subsquare
				Set<Integer> currentSet = model.getSet(i, y + rowIndex);
				if (currentSet.remove(number)) {
					log.debug(String.format("Removed %d from row at %d, %d", number, i, y + rowIndex));
				}
			}
		}
		return model;
	}
	
	

	@Override
	public String getName() {
		return "Pointed Strategy";
	}

	@Override
	public long getElapsedTimeInStrategy() {
		return elapsedTime;
	}

}
