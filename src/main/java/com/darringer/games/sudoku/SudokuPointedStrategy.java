package com.darringer.games.sudoku;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * @author cdarringer
 *
 */
public class SudokuPointedStrategy implements SudokuStrategy {
	
	Logger log = Logger.getLogger(SudokuPointedStrategy.class);

	@Override
	public SudokuModel applyStrategy(SudokuModel model, int x, int y) {
		// is this a subsquare "anchor" square?
		/**
		if (SudokuModel.isAnchor(x, y)) {
			log.debug("Anchor square detected, applying pointed strategy to current subsquare...");
				
			// count the instances each number can appear in the entire subsquare
			Map<Integer, Integer> subSquareCounts = countSubSquareInstances(model, x, y); 
		
			// for each subsquare row...
			for (int subSquareRowIndex = 0; subSquareRowIndex < 3; subSquareRowIndex++) {
				// does this subsquare row contain all of the instances?
				Map<Integer, Integer> currentSubSquareRowCounts = countSubSquareInstancesInRow(model, x, y, subSquareRowIndex); 
				for (int currentSubSquareRowValue : currentSubSquareRowCounts.keySet()) {
					int currentSubSquareRowCount = currentSubSquareRowCount.get(currentSubSquareRowValue);
					if (currentSubSquareRowCount > 1) {
						// there is more than one instance of this value in this row
						int currentSubSquareCount = subSquareCounts.get(currentSubSquareRowValue);
						if (currentSubSquareCount == currentSubSquareRowCount) {
							// all instances in the subsquare are in this row
							// remove this "pointed" number from the entire row...
							log.info(String.format("Pointed pair for %d found in subsquare at anchor %d, %d", currentSubSquareRowValue, x, y)); 
							model = removePointedPairFromRow(model, x, y, subSquareRowIndex, currentSubSquareRowValue);							
						}
					}
				}
			}
					
		
		// for each column...
			// does this subsquare column contain all of the instances?
		
			// it does, remove this number from the entire column

		}
		**/
		return model;
	}

}
