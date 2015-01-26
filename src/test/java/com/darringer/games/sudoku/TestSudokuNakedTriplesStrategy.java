package com.darringer.games.sudoku;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TestSudokuNakedTriplesStrategy {

	@Test
	public void testNakedTripleInColumn() {
		SudokuStrategy strategy = new SudokuNakedTriplesStrategy();
		SudokuModel inputModel = new SudokuModel();		
	
		// 2,4,7 is a naked triple - remove from rows 0, 1
		inputModel.setNumbersAtLocation(4, 0, 3, 2);
		inputModel.setNumbersAtLocation(4, 1, 8, 4, 7);
		inputModel.setNumbersAtLocation(4, 2, 1);
		inputModel.setNumbersAtLocation(4, 3, 2, 4, 7);
		inputModel.setNumbersAtLocation(4, 4, 2, 4, 7);
		inputModel.setNumbersAtLocation(4, 5, 2, 4, 7);
		inputModel.setNumbersAtLocation(4, 6, 9);
		inputModel.setNumbersAtLocation(4, 7, 6);
		inputModel.setNumbersAtLocation(4, 8, 5);		
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 4, 0);
		assertFalse("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 0).contains(2));	
		assertFalse("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 1).contains(4));	
		assertFalse("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 1).contains(7));	
		assertTrue("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 3).contains(2));	
		assertTrue("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 3).contains(4));	
		assertTrue("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 3).contains(7));	
		assertTrue("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 4).contains(2));	
		assertTrue("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 4).contains(4));	
		assertTrue("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 4).contains(7));	
		assertTrue("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 5).contains(2));	
		assertTrue("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 5).contains(4));	
		assertTrue("2, 4, and 7 were a naked triple in this column", answerModel.getSet(4, 5).contains(7));	
	}
	
	@Test
	public void testNakedTripleInRow() {
	}
	
	@Test
	public void testNakedTripleInSubSquare() {
	}
}