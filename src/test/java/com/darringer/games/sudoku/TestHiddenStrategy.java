package com.darringer.games.sudoku;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * @author cdarringer
 *
 */
public class TestHiddenStrategy {

	private SudokuStrategy strategy = new SudokuHiddenStrategy();

	
	@Test
	public void testHiddenSingleRow() {
		SudokuModel inputModel = new SudokuModel();
		
		// at 0, 1 we have 3, 4, 7, 9
		Set<Integer> options = new HashSet<Integer>();
		options.add(3);
		options.add(4);
		options.add(7);
		options.add(9);
		inputModel.setSet(0, 1, options);

		// at 1, 1 we have 4, 7
		options = new HashSet<Integer>();
		options.add(4);
		options.add(7);
		inputModel.setSet(1, 1, options);

		// at 2, 1 we have 3, 4, 9
		options = new HashSet<Integer>();
		options.add(3);
		options.add(4);
		options.add(9);
		inputModel.setSet(2, 1, options);

		// at 3, 1 we have 4, 6, 9
		options = new HashSet<Integer>();
		options.add(4);
		options.add(6);
		options.add(9);
		inputModel.setSet(3, 1, options);

		// at 4, 1 we have 2
		options = new HashSet<Integer>();
		options.add(2);
		inputModel.setSet(4, 1, options);
		
		// at 5, 1 we have 4, 9
		options = new HashSet<Integer>();
		options.add(4);
		options.add(9);
		inputModel.setSet(5, 1, options);
		
		// at 6, 1 we have 8
		options = new HashSet<Integer>();
		options.add(8);
		inputModel.setSet(6, 1, options);
		
		// at 7, 1 we have 5
		options = new HashSet<Integer>();
		options.add(5);
		inputModel.setSet(7, 1, options);
		
		// at 8, 1 we have 1
		options = new HashSet<Integer>();
		options.add(1);
		inputModel.setSet(8, 1, options);
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 3, 1);

		// current cell check
		assertTrue("Current cell should now have only one value", answerModel.getSet(3, 1).size() == 1);		
		assertTrue("Current cell should now only contain 6", answerModel.getSet(3, 1).contains(6));		
	}

	
	@Test
	public void testHiddenSingleColumn() {
		SudokuModel inputModel = new SudokuModel();
		
		// at 1, 0 we have 3, 4, 7, 9
		Set<Integer> options = new HashSet<Integer>();
		options.add(3);
		options.add(4);
		options.add(7);
		options.add(9);
		inputModel.setSet(1, 0, options);

		// at 1, 1 we have 4, 7
		options = new HashSet<Integer>();
		options.add(4);
		options.add(7);
		inputModel.setSet(1, 1, options);

		// at 1, 2 we have 3, 4, 9
		options = new HashSet<Integer>();
		options.add(3);
		options.add(4);
		options.add(9);
		inputModel.setSet(1, 2, options);

		// at 1, 3 we have 4, 6, 9
		options = new HashSet<Integer>();
		options.add(4);
		options.add(6);
		options.add(9);
		inputModel.setSet(1, 3, options);

		// at 1, 4 we have 2
		options = new HashSet<Integer>();
		options.add(2);
		inputModel.setSet(1, 4, options);
		
		// at 1, 5 we have 4, 9
		options = new HashSet<Integer>();
		options.add(4);
		options.add(9);
		inputModel.setSet(1, 5, options);
		
		// at 1, 6 we have 8
		options = new HashSet<Integer>();
		options.add(8);
		inputModel.setSet(1, 6, options);
		
		// at 1, 7 we have 5
		options = new HashSet<Integer>();
		options.add(5);
		inputModel.setSet(1, 7, options);
		
		// at 1, 8 we have 1
		options = new HashSet<Integer>();
		options.add(1);
		inputModel.setSet(1, 8, options);
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 1, 3);

		// current cell check
		assertTrue("Current cell should now have only one value", answerModel.getSet(1, 3).size() == 1);		
		assertTrue("Current cell should now only contain 6", answerModel.getSet(1, 3).contains(6));		
	}

	@Test
	public void testHiddenSingleSubSquare() {
		SudokuModel inputModel = new SudokuModel();
		
		// at 0, 0 we have 3, 4, 7, 9
		Set<Integer> options = new HashSet<Integer>();
		options.add(3);
		options.add(4);
		options.add(7);
		options.add(9);
		inputModel.setSet(0, 0, options);

		// at 1, 0 we have 4, 7
		options = new HashSet<Integer>();
		options.add(4);
		options.add(7);
		inputModel.setSet(1, 0, options);

		// at 2, 0 we have 3, 4, 9
		options = new HashSet<Integer>();
		options.add(3);
		options.add(4);
		options.add(9);
		inputModel.setSet(2, 0, options);

		// at 0, 1 we have 4, 6, 9
		options = new HashSet<Integer>();
		options.add(4);
		options.add(6);
		options.add(9);
		inputModel.setSet(0, 1, options);

		// at 1, 1 we have 2
		options = new HashSet<Integer>();
		options.add(2);
		inputModel.setSet(1, 1, options);
		
		// at 2, 1 we have 4, 9
		options = new HashSet<Integer>();
		options.add(4);
		options.add(9);
		inputModel.setSet(2, 1, options);
		
		// at 0, 2 we have 8
		options = new HashSet<Integer>();
		options.add(8);
		inputModel.setSet(0, 2, options);
		
		// at 1, 2 we have 5
		options = new HashSet<Integer>();
		options.add(5);
		inputModel.setSet(1, 2, options);
		
		// at 2, 2 we have 1
		options = new HashSet<Integer>();
		options.add(1);
		inputModel.setSet(2, 2, options);
		
		SudokuModel answerModel = strategy.applyStrategy(inputModel, 0, 1);

		// current cell check
		assertTrue("Current cell should now have only one value", answerModel.getSet(0, 1).size() == 1);		
		assertTrue("Current cell should now only contain 6", answerModel.getSet(0, 1).contains(6));		
	}
	

	
	
}
