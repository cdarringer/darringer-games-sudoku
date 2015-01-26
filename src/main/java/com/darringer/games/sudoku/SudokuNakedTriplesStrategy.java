package com.darringer.games.sudoku;

import static com.darringer.games.sudoku.SudokuModel.SIZE;
import static com.darringer.games.sudoku.SudokuRegion.COLUMN;
import static com.darringer.games.sudoku.SudokuRegion.ROW;
import static com.darringer.games.sudoku.SudokuRegion.SUBSQUARE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 
 * @author cdarringer
 *
 */
public class SudokuNakedTriplesStrategy implements SudokuStrategy {
	
	private Logger log = Logger.getLogger(SudokuNakedTriplesStrategy.class);
	private long elapsedTime = 0l;

	@Override
	public SudokuModel applyStrategy(SudokuModel model, int x, int y) {
		long startTime = System.currentTimeMillis();
		
		// is this square a row "anchor" square?
		if (SudokuModel.isRowAnchor(x, y)) {
			log.info(String.format("Strategy being applied for row at %d, %d...", x, y));
			return checkForNakedTriplesInRegion(model, x, y, ROW);
		} 
		if (SudokuModel.isColumnAnchor(x, y)) {
			log.info(String.format("Strategy being applied for column at %d, %d...", x, y));
			return checkForNakedTriplesInRegion(model, x, y, COLUMN);			
		}
		if (SudokuModel.isSubSquareAnchor(x, y)) {
			log.info(String.format("Strategy being applied for subsquare at %d, %d...", x, y));
			return checkForNakedTriplesInRegion(model, x, y, SUBSQUARE);			
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
	private SudokuModel checkForNakedTriplesInRegion(SudokuModel model, int x, int y, SudokuRegion region) {
		
		// build maps of triples to their location(s) and counts in this region
		Map<Set<Integer>, List<Integer>> tripleLocationMap = new HashMap<Set<Integer>, List<Integer>>(SIZE);
		Map<Set<Integer>, Integer> tripleCountMap = new HashMap<Set<Integer>, Integer>(SIZE);

		// for each cell in this region...
		for (int i=0; i < SIZE; i++) {
			Set<Integer> currentSet = model.getSetForRegionIndex(x, y, i, region);  
			if (currentSet.size() == 3) {
				// it's a triple - have we seen this triple before?
				List<Integer> currentTripleLocations = tripleLocationMap.get(currentSet);
				if (currentTripleLocations == null) {
					// we have not seen this triple before - create locations and set initial count
					log.info("Found triple for the first time: " + currentSet);
					currentTripleLocations = new ArrayList<Integer>(3);
					tripleCountMap.put(currentSet, 1);
				} else {
					// we have seen this one before - increment the count
					Integer currentTripleCount = tripleCountMap.get(currentSet);
					log.info(String.format("Found %d instances of triple %s", currentTripleCount + 1, currentSet));
					tripleCountMap.put(currentSet, ++currentTripleCount);
				}
				// update locations map
				currentTripleLocations.add(i);
				tripleLocationMap.put(currentSet, currentTripleLocations);
			}
		}
		
		// did we find any naked triples?
		// if so, remove their values from all cells in this region 
		// except the triple locations themselves
		for (Set<Integer> currentTriple : tripleLocationMap.keySet()) {
			Integer count = tripleCountMap.get(currentTriple);
			if (count == 3) {
				log.info(currentTriple + " appears to be a naked triple");
				List<Integer> currentTripleLocations = tripleLocationMap.get(currentTriple);
				for (int i=0; i < SIZE; i++) {
					if (currentTripleLocations.contains(i)) {
						// this was a triple location, leave it alone
					} else {
						Set<Integer> currentValues = model.getSetForRegionIndex(x, y, i, region);
						for (int currentValue : currentTriple) {
							if (currentValues.remove(currentValue)) {
								log.info("Removed value: " + currentValue);
							}
						}
					}
				}
			}
		}
		return model;
	}
	
	
	@Override
	public String getName() {
		return "Naked Triples Strategy";
	}

	@Override
	public long getElapsedTimeInStrategy() {
		return elapsedTime;
	}

}
