package com.darringer.games.sudoku;

import org.junit.Test;

/**
 * 
 * @author cdarringer
 *
 */
public class TestPointedStrategy {

	@Test
	public void testNonAnchorSquare() {
		SudokuStrategy strategy = new SudokuPointedStrategy();
		SudokuModel inputModel = new SudokuModel();
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 4, 1);
		
	}
	
}
