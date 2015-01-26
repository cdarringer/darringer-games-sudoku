package com.darringer.games.sudoku;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TestSudokuNakedPairsStrategy {

	@Test
	public void testNakedPairInColumn() {
		SudokuStrategy strategy = new SudokuNakedPairsStrategy();
		SudokuModel inputModel = new SudokuModel();		
	
		// 2,3 is a naked pair - remove from rows 3, 4
		inputModel.setNumbersAtLocation(2, 0, 4, 7);
		inputModel.setNumbersAtLocation(2, 1, 6, 9);
		inputModel.setNumbersAtLocation(2, 2, 8);
		inputModel.setNumbersAtLocation(2, 3, 2, 3, 7, 9);
		inputModel.setNumbersAtLocation(2, 4, 3, 7, 9);
		inputModel.setNumbersAtLocation(2, 5, 2, 3);
		inputModel.setNumbersAtLocation(2, 6, 1);
		inputModel.setNumbersAtLocation(2, 7, 5);
		inputModel.setNumbersAtLocation(2, 8, 2, 3);		
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 2, 0);
		assertFalse("2 and 3 were a naked pair in this column", answerModel.getSet(2, 3).contains(2));	
		assertFalse("2 and 3 were a naked pair in this column", answerModel.getSet(2, 3).contains(3));	
		assertFalse("2 and 3 were a naked pair in this column", answerModel.getSet(2, 4).contains(3));	
		assertTrue("2 and 3 were a naked pair in this column", answerModel.getSet(2, 5).contains(2));	
		assertTrue("2 and 3 were a naked pair in this column", answerModel.getSet(2, 5).contains(3));	
		assertTrue("2 and 3 were a naked pair in this column", answerModel.getSet(2, 8).contains(2));	
		assertTrue("2 and 3 were a naked pair in this column", answerModel.getSet(2, 8).contains(3));	

		// 7, 9 is now a naked pair, remove from rows 0, 1
		inputModel = answerModel;
		answerModel = strategy.applyStrategy(inputModel, 2, 0);
		assertFalse("7 and 9 were a naked pair in this column", answerModel.getSet(2, 0).contains(7));	
		assertFalse("7 and 9 were a naked pair in this column", answerModel.getSet(2, 1).contains(9));			
	}
	
	@Test
	public void testNakedPairInRow() {
		SudokuStrategy strategy = new SudokuNakedPairsStrategy();
		SudokuModel inputModel = new SudokuModel();		
	
		// 2,3 is a naked pair - remove from columns 3, 4
		inputModel.setNumbersAtLocation(0, 2, 4, 7);
		inputModel.setNumbersAtLocation(1, 2, 6, 9);
		inputModel.setNumbersAtLocation(2, 2, 8);
		inputModel.setNumbersAtLocation(3, 2, 2, 3, 7, 9);
		inputModel.setNumbersAtLocation(4, 2, 3, 7, 9);
		inputModel.setNumbersAtLocation(5, 2, 2, 3);
		inputModel.setNumbersAtLocation(6, 2, 1);
		inputModel.setNumbersAtLocation(7, 2, 5);
		inputModel.setNumbersAtLocation(8, 2, 2, 3);		
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 0, 2);
		assertFalse("2 and 3 were a naked pair in this row", answerModel.getSet(3, 2).contains(2));	
		assertFalse("2 and 3 were a naked pair in this row", answerModel.getSet(3, 2).contains(3));	
		assertFalse("2 and 3 were a naked pair in this row", answerModel.getSet(4, 2).contains(3));	
		assertTrue("2 and 3 were a naked pair in this row", answerModel.getSet(5, 2).contains(2));	
		assertTrue("2 and 3 were a naked pair in this row", answerModel.getSet(5, 2).contains(3));	
		assertTrue("2 and 3 were a naked pair in this row", answerModel.getSet(8, 2).contains(2));	
		assertTrue("2 and 3 were a naked pair in this row", answerModel.getSet(8, 2).contains(3));	

		// 7, 9 is now a naked pair, remove from columns 0, 1
		inputModel = answerModel;
		answerModel = strategy.applyStrategy(inputModel, 0, 2);
		assertFalse("7 and 9 were a naked pair in this row", answerModel.getSet(0, 2).contains(7));	
		assertFalse("7 and 9 were a naked pair in this row", answerModel.getSet(1, 2).contains(9));	
	}
	
	@Test
	public void testNakedPairInSubSquare() {
		SudokuStrategy strategy = new SudokuNakedPairsStrategy();
		SudokuModel inputModel = new SudokuModel();		
		
		// 2, 3 is a naked pair - remove from 3, 4 and 4, 4
		inputModel.setNumbersAtLocation(3, 3, 4, 7);
		inputModel.setNumbersAtLocation(4, 3, 6, 9);
		inputModel.setNumbersAtLocation(5, 3, 8);
		inputModel.setNumbersAtLocation(3, 4, 2, 3, 7, 9);
		inputModel.setNumbersAtLocation(4, 4, 3, 7, 9);
		inputModel.setNumbersAtLocation(5, 4, 2, 3);
		inputModel.setNumbersAtLocation(3, 5, 1);
		inputModel.setNumbersAtLocation(4, 5, 5);
		inputModel.setNumbersAtLocation(5, 5, 2, 3);		
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 3, 3);
		assertFalse("2 and 3 were a naked pair in this subsquare", answerModel.getSet(3, 4).contains(2));	
		assertFalse("2 and 3 were a naked pair in this subsquare", answerModel.getSet(3, 4).contains(3));	
		assertFalse("2 and 3 were a naked pair in this subsquare", answerModel.getSet(4, 4).contains(3));	
		assertTrue("2 and 3 were a naked pair in this subsquare", answerModel.getSet(5, 4).contains(2));	
		assertTrue("2 and 3 were a naked pair in this subsquare", answerModel.getSet(5, 4).contains(3));	
		assertTrue("2 and 3 were a naked pair in this subsquare", answerModel.getSet(5, 5).contains(2));	
		assertTrue("2 and 3 were a naked pair in this subsquare", answerModel.getSet(5, 5).contains(3));	
		
		// 7, 9 is now a naked pair, remove from 3, 3 and 4, 3 
		inputModel = answerModel;
		answerModel = strategy.applyStrategy(inputModel, 3, 3);
		assertFalse("7 and 9 were a naked pair in this subsquare", answerModel.getSet(3, 3).contains(7));	
		assertFalse("7 and 9 were a naked pair in this subsquare", answerModel.getSet(4, 3).contains(9));	
	}
}