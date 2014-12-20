package com.darringer.games.sudoku;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author cdarringer
 *
 */
public class TestPointedStrategy {

	@Ignore
	@Test
	public void testPointedPairInRow() {
		SudokuStrategy strategy = new SudokuPointedStrategy();
		SudokuModel inputModel = new SudokuModel();		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 4, 1);		
	}

	@Ignore
	@Test
	public void testPointedPairInColumn() {
		SudokuStrategy strategy = new SudokuPointedStrategy();
		SudokuModel inputModel = new SudokuModel();		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 4, 1);		
	}
	
}
