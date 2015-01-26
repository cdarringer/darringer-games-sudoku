package com.darringer.games.sudoku;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author cdarringer
 *
 */
public class TestSudokuHiddenSingleStrategy {

	private SudokuStrategy strategy = new SudokuHiddenSingleStrategy();

	
	@Test
	public void testHiddenSingleRow() {
		SudokuModel inputModel = new SudokuModel();
		
		inputModel.setNumbersAtLocation(0, 1, 3, 4, 7, 9);
		inputModel.setNumbersAtLocation(1, 1, 4, 7);
		inputModel.setNumbersAtLocation(2, 1, 3, 4, 9);
		inputModel.setNumbersAtLocation(3, 1, 4, 6, 9);
		inputModel.setNumbersAtLocation(4, 1, 2);
		inputModel.setNumbersAtLocation(5, 1, 4, 9);
		inputModel.setNumbersAtLocation(6, 1, 8);
		inputModel.setNumbersAtLocation(7, 1, 5);
		inputModel.setNumbersAtLocation(8, 1, 1);
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 3, 1);

		// current cell check
		assertTrue("Current cell should now have only one value", answerModel.getSet(3, 1).size() == 1);		
		assertTrue("Current cell should now only contain 6", answerModel.getSet(3, 1).contains(6));		
	}

	
	@Test
	public void testHiddenSingleColumn() {
		SudokuModel inputModel = new SudokuModel();
		
		inputModel.setNumbersAtLocation(1, 0, 3, 4, 7, 9);
		inputModel.setNumbersAtLocation(1, 1, 4, 7);
		inputModel.setNumbersAtLocation(1, 2, 3, 4, 9);
		inputModel.setNumbersAtLocation(1, 3, 4, 6, 9);
		inputModel.setNumbersAtLocation(1, 4, 2);
		inputModel.setNumbersAtLocation(1, 5, 4, 9);
		inputModel.setNumbersAtLocation(1, 6, 8);
		inputModel.setNumbersAtLocation(1, 7, 5);
		inputModel.setNumbersAtLocation(1, 8, 1);
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 1, 3);

		// current cell check
		assertTrue("Current cell should now have only one value", answerModel.getSet(1, 3).size() == 1);		
		assertTrue("Current cell should now only contain 6", answerModel.getSet(1, 3).contains(6));		
	}

	@Test
	public void testHiddenSingleSubSquare() {
		SudokuModel inputModel = new SudokuModel();
		
		inputModel.setNumbersAtLocation(0, 0, 3, 4, 7, 9);
		inputModel.setNumbersAtLocation(1, 0, 4, 7);
		inputModel.setNumbersAtLocation(2, 0, 3, 4, 9);
		inputModel.setNumbersAtLocation(0, 1, 4, 6, 9);
		inputModel.setNumbersAtLocation(1, 1, 2);
		inputModel.setNumbersAtLocation(2, 1, 4, 9);
		inputModel.setNumbersAtLocation(0, 2, 8);
		inputModel.setNumbersAtLocation(1, 2, 5);
		inputModel.setNumbersAtLocation(2, 2, 1);		
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 0, 1);

		// current cell check
		assertTrue("Current cell should now have only one value", answerModel.getSet(0, 1).size() == 1);		
		assertTrue("Current cell should now only contain 6", answerModel.getSet(0, 1).contains(6));		
	}
	

	
	
}
