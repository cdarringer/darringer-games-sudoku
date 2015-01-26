package com.darringer.games.sudoku;

import static com.darringer.games.sudoku.SudokuModel.SIZE;
import static com.darringer.games.sudoku.SudokuRegion.COLUMN;
import static com.darringer.games.sudoku.SudokuRegion.ROW;
import static com.darringer.games.sudoku.SudokuRegion.SUBSQUARE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 
 * @author cdarringer
 *
 */
public class SudokuNakedPairsStrategy implements SudokuStrategy {
	
	private Logger log = Logger.getLogger(SudokuNakedPairsStrategy.class);
	private long elapsedTime = 0l;

	@Override
	public SudokuModel applyStrategy(SudokuModel model, int x, int y) {
		long startTime = System.currentTimeMillis();
		
		// is this square a row "anchor" square?
		if (SudokuModel.isRowAnchor(x, y)) {
			log.info(String.format("Strategy being applied for row at %d, %d...", x, y));
			return checkForNakedPairsInRegion(model, x, y, ROW);
		} 
		if (SudokuModel.isColumnAnchor(x, y)) {
			log.info(String.format("Strategy being applied for column at %d, %d...", x, y));
			return checkForNakedPairsInRegion(model, x, y, COLUMN);			
		}
		if (SudokuModel.isSubSquareAnchor(x, y)) {
			log.info(String.format("Strategy being applied for subsquare at %d, %d...", x, y));
			return checkForNakedPairsInRegion(model, x, y, SUBSQUARE);			
		}

		elapsedTime += (System.currentTimeMillis() - startTime);
		return model;
	}

	/**
	 * 
	 * @param model
	 * @param x
	 * @param y
	 * @param region
	 * @return
	 */
	private SudokuModel checkForNakedPairsInRegion(SudokuModel model, int x, int y, SudokuRegion region) {
	
		// build a map of pairs to their location(s) in this region
		Map<Set<Integer>, List<Integer>> pairMap = new HashMap<Set<Integer>, List<Integer>>(SIZE);

		// we are interested in the set of pairs that appear twice in the given row, column, or subsquare
		Set<Set<Integer>> pairsThatAppearTwice = new HashSet<Set<Integer>>(SIZE);

		// for each cell in this region...
		for (int i=0; i < SIZE; i++) {
			Set<Integer> currentSet = model.getSetForRegionIndex(x, y, i, region);  
			if (currentSet.size() == 2) {
				// it's a pair - have we seen this pair before?
				List<Integer> currentPairLocations = pairMap.get(currentSet);
				if (currentPairLocations == null) {
					// we have not seen this one before
					currentPairLocations = new ArrayList<Integer>(2);
				} else {
					// we have seen this one before
					log.info("Found naked pair: " + currentSet);
					pairsThatAppearTwice.add(currentSet);
				}
				// add to main map in any case
				currentPairLocations.add(i);
				pairMap.put(currentSet, currentPairLocations);
			}
		}
		
		// did we find any naked pairs?
		// if so, remove their values from all cells in this region 
		// except the pair locations themselves
		for (Set<Integer> currentPair : pairsThatAppearTwice) {
			List<Integer> currentPairLocations = pairMap.get(currentPair);
			for (int i=0; i < SIZE; i++) {
				if (currentPairLocations.contains(i)) {
					// this was a pair location, leave it alone
				} else {
					Set<Integer> currentValues = model.getSetForRegionIndex(x, y, i, region);
					for (int currentValue : currentPair) {
						if (currentValues.remove(currentValue)) {
							log.info("Removed value: " + currentValue);
						}
					}
				}
			}
		}
		return model;
	}
	
	
	@Override
	public String getName() {
		return "Naked Pair Strategy";
	}

	@Override
	public long getElapsedTimeInStrategy() {
		return elapsedTime;
	}

}
