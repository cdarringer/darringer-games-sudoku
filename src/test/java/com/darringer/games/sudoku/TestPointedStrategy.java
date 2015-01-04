package com.darringer.games.sudoku;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author cdarringer
 *
 */
public class TestPointedStrategy {

	@Test
	public void testPointedPairInRow() {
		SudokuStrategy strategy = new SudokuPointedStrategy();
		SudokuModel inputModel = new SudokuModel();		
	
		inputModel.setNumbersAtLocation(0,  0, 1, 2, 3);
		inputModel.setNumbersAtLocation(1,  0, 1, 2, 3);
		inputModel.setNumbersAtLocation(2,  0, 4, 5, 6);
		inputModel.setNumbersAtLocation(0,  1, 3, 5, 6);
		inputModel.setNumbersAtLocation(1,  1, 5, 6);
		inputModel.setNumbersAtLocation(2,  1, 5, 6);
		inputModel.setNumbersAtLocation(0,  2, 5, 6);
		inputModel.setNumbersAtLocation(1,  2, 5, 6);
		inputModel.setNumbersAtLocation(2,  2, 5, 6);
		inputModel.setNumbersAtLocation(3,  0, 1, 2, 3, 5, 6);
		inputModel.setNumbersAtLocation(4,  0, 1, 2, 3, 5, 6);
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 0, 0);		
		assertFalse("1 and 2 were a pointed pair in this row", answerModel.getSet(3, 0).contains(1));		
		assertFalse("1 and 2 were a pointed pair in this row", answerModel.getSet(3, 0).contains(2));		
		assertFalse("1 and 2 were a pointed pair in this row", answerModel.getSet(4, 0).contains(1));		
		assertFalse("1 and 2 were a pointed pair in this row", answerModel.getSet(4, 0).contains(2));		
		assertTrue("3 was not a pointed pair in this row", answerModel.getSet(3, 0).contains(3));		
		assertTrue("3 was not a pointed pair in this row", answerModel.getSet(4, 0).contains(3));				
	}

	@Test
	public void testPointedPairInColumn() {
		SudokuStrategy strategy = new SudokuPointedStrategy();
		SudokuModel inputModel = new SudokuModel();		

		inputModel.setNumbersAtLocation(0, 0, 1, 2, 3);
		inputModel.setNumbersAtLocation(0, 1, 1, 2, 3);
		inputModel.setNumbersAtLocation(0, 2, 4, 5, 6);
		inputModel.setNumbersAtLocation(1, 0, 3, 5, 6);
		inputModel.setNumbersAtLocation(1, 1, 5, 6);
		inputModel.setNumbersAtLocation(1, 2, 5, 6);
		inputModel.setNumbersAtLocation(2, 0, 5, 6);
		inputModel.setNumbersAtLocation(2, 1, 5, 6);
		inputModel.setNumbersAtLocation(2, 2, 5, 6);
		inputModel.setNumbersAtLocation(0, 3, 1, 2, 3, 5, 6);
		inputModel.setNumbersAtLocation(0, 4, 1, 2, 3, 5, 6);
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 0, 0);		
		assertFalse("1 and 2 were a pointed pair in this column", answerModel.getSet(0, 3).contains(1));		
		assertFalse("1 and 2 were a pointed pair in this column", answerModel.getSet(0, 3).contains(2));		
		assertFalse("1 and 2 were a pointed pair in this column", answerModel.getSet(0, 4).contains(1));		
		assertFalse("1 and 2 were a pointed pair in this column", answerModel.getSet(0, 4).contains(2));	
		assertTrue("3 was not a pointed pair in this column", answerModel.getSet(0, 3).contains(3));		
		assertTrue("3 was not a pointed pair in this column", answerModel.getSet(0, 4).contains(3));				
	}
	
	@Test
	public void testPointedPairInSubSquare() {
		SudokuStrategy strategy = new SudokuPointedStrategy();
		SudokuModel inputModel = new SudokuModel();		

		inputModel.setNumbersAtLocation(0, 0, 4);
		inputModel.setNumbersAtLocation(1, 0, 2, 5, 7);
		inputModel.setNumbersAtLocation(2, 0, 2, 5, 6, 7);
		inputModel.setNumbersAtLocation(0, 1, 5, 7);
		inputModel.setNumbersAtLocation(1, 1, 3);
		inputModel.setNumbersAtLocation(2, 1, 2, 5, 7);
		inputModel.setNumbersAtLocation(3, 1, 4);
		inputModel.setNumbersAtLocation(4, 1, 2, 5);
		inputModel.setNumbersAtLocation(5, 1, 6);
		inputModel.setNumbersAtLocation(6, 1, 1);
		inputModel.setNumbersAtLocation(7, 1, 2, 5, 9);
		inputModel.setNumbersAtLocation(8, 1, 8);
		inputModel.setNumbersAtLocation(0, 2, 5, 6, 7);
		inputModel.setNumbersAtLocation(1, 2, 8);
		inputModel.setNumbersAtLocation(2, 2, 1);
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 0, 0);		
		assertFalse("7 was a pointed pair within this subsquare", answerModel.getSet(1, 0).contains(7));		
		assertFalse("7 was a pointed pair within this subsquare", answerModel.getSet(2, 0).contains(7));		
		assertTrue("7 was a pointed pair within this subsquare", answerModel.getSet(0, 1).contains(7));		
		assertTrue("7 was a pointed pair within this subsquare", answerModel.getSet(2, 1).contains(7));		
		assertFalse("7 was a pointed pair within this subsquare", answerModel.getSet(0, 2).contains(7));		
	}
}
