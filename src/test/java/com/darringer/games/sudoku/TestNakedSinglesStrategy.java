package com.darringer.games.sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author cdarringer
 *
 */
public class TestNakedSinglesStrategy {

	@Test
	public void testNakedSingle() {
		SudokuStrategy strategy = new SudokuNakedSinglesStrategy();
		String inputString = 
				"- - - - - - - - -\n" +
				"- - - - 1 - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n";
		SudokuModel inputModel = new SudokuModel(inputString);
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 4, 1);

		// current cell check
		assertTrue("Current cell should still have only one value", answerModel.getSet(4, 1).size() == 1);		
		assertTrue("Current cell should still only contain 1", answerModel.getSet(4, 1).contains(1));		
		
		// outside check
		assertTrue("Single should NOT be remove from other row, column, or sunsquare set", answerModel.getSet(0, 0).contains(1));
		
		// same row check
		assertFalse("Single should be removed from all same row sets", answerModel.getSet(0, 1).contains(1));
		assertFalse("Single should be removed from all same row sets", answerModel.getSet(1, 1).contains(1));
		assertFalse("Single should be removed from all same row sets", answerModel.getSet(2, 1).contains(1));
		assertFalse("Single should be removed from all same row sets", answerModel.getSet(3, 1).contains(1));
		assertFalse("Single should be removed from all same row sets", answerModel.getSet(5, 1).contains(1));
		assertFalse("Single should be removed from all same row sets", answerModel.getSet(6, 1).contains(1));
		assertFalse("Single should be removed from all same row sets", answerModel.getSet(7, 1).contains(1));
		assertFalse("Single should be removed from all same row sets", answerModel.getSet(8, 1).contains(1));

		// same column check
		assertFalse("Single should be removed from all same column sets", answerModel.getSet(4, 0).contains(1));
		assertFalse("Single should be removed from all same column sets", answerModel.getSet(4, 2).contains(1));
		assertFalse("Single should be removed from all same column sets", answerModel.getSet(4, 3).contains(1));
		assertFalse("Single should be removed from all same column sets", answerModel.getSet(4, 4).contains(1));
		assertFalse("Single should be removed from all same column sets", answerModel.getSet(4, 5).contains(1));
		assertFalse("Single should be removed from all same column sets", answerModel.getSet(4, 6).contains(1));
		assertFalse("Single should be removed from all same column sets", answerModel.getSet(4, 7).contains(1));
		assertFalse("Single should be removed from all same column sets", answerModel.getSet(4, 8).contains(1));

		// same subsquare check
		assertFalse("Single should be removed from all same subsquare sets", answerModel.getSet(3, 0).contains(1));
		assertFalse("Single should be removed from all same subsquare sets", answerModel.getSet(4, 0).contains(1));
		assertFalse("Single should be removed from all same subsquare sets", answerModel.getSet(5, 0).contains(1));
		assertFalse("Single should be removed from all same subsquare sets", answerModel.getSet(3, 1).contains(1));
		assertFalse("Single should be removed from all same subsquare sets", answerModel.getSet(5, 1).contains(1));
		assertFalse("Single should be removed from all same subsquare sets", answerModel.getSet(3, 2).contains(1));
		assertFalse("Single should be removed from all same subsquare sets", answerModel.getSet(4, 2).contains(1));
		assertFalse("Single should be removed from all same subsquare sets", answerModel.getSet(5, 2).contains(1));
	}
}
