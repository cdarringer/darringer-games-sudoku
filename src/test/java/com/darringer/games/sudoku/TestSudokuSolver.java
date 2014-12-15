package com.darringer.games.sudoku;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * @author cdarringer
 * @author fdarringer
 *
 */
public class TestSudokuSolver {
	
	/**
	 * Requires naked singles only
	 */
	@Test
	public void testEasyPuzzle1() {
		SudokuSolver solver = new SudokuSolver();
		String inputString = 
				"- - - 2 - 8 6 - -\n" +
				"- - - 5 3 7 - - -\n" +
				"9 5 7 - - - - - 8\n" + 
				"- - - 3 8 2 1 - -\n" +
				"- 4 8 - - - 5 3 -\n" +
				"- - 1 7 5 4 - - -\n" +
				"2 - - - - - 8 9 4\n" + 
				"- - - 8 2 6 - - -\n" +
				"- - 3 9 - 5 - - -\n"; 
		SudokuModel inputModel = new SudokuModel(inputString);
		String answerString = 
				"1 3 4 2 9 8 6 7 5\n" + 
				"6 8 2 5 3 7 4 1 9\n" + 
				"9 5 7 4 6 1 3 2 8\n" +
				"5 9 6 3 8 2 1 4 7\n" + 
				"7 4 8 6 1 9 5 3 2\n" + 
				"3 2 1 7 5 4 9 8 6\n" + 
				"2 6 5 1 7 3 8 9 4\n" + 
				"4 1 9 8 2 6 7 5 3\n" + 
				"8 7 3 9 4 5 2 6 1\n"; 
		SudokuModel answerModel = new SudokuModel(answerString);
		SudokuModel resultModel = solver.solve(inputModel);
		assertEquals(answerModel, resultModel);	
	}

	/**
	 * Requires naked singles only
	 */
	@Test
	public void testEasyPuzzle2() {
		SudokuSolver solver = new SudokuSolver();
		String inputString = 
				"3 - 7 2 - - - 8 -\n" +
				"- - - 4 5 7 - 2 -\n" +
				"9 - 4 - - - - 5 -\n" + 
				"7 9 2 - 8 - - - -\n" +
				"4 - - - 3 - - - 2\n" +
				"- - - - 6 - 1 4 9\n" +
				"- 7 - - - - 2 - 6\n" + 
				"- 4 - 5 2 9 - - -\n" +
				"- 1 - - - 3 5 - 4\n"; 
		SudokuModel inputModel = new SudokuModel(inputString);
		String answerString = 
				"3 5 7 2 9 6 4 8 1\n" + 
				"1 8 6 4 5 7 9 2 3\n" + 
				"9 2 4 3 1 8 6 5 7\n" +
				"7 9 2 1 8 4 3 6 5\n" + 
				"4 6 1 9 3 5 8 7 2\n" + 
				"8 3 5 7 6 2 1 4 9\n" + 
				"5 7 9 8 4 1 2 3 6\n" + 
				"6 4 3 5 2 9 7 1 8\n" + 
				"2 1 8 6 7 3 5 9 4\n"; 
		SudokuModel answerModel = new SudokuModel(answerString);
		SudokuModel resultModel = solver.solve(inputModel);
		assertEquals(answerModel, resultModel);	
	}

	/**
	 * 
	 */
	@Test
	public void testEasyPuzzle3() {
		SudokuSolver solver = new SudokuSolver();
		String inputString = 
				"- - - - - - 3 4 5\n" +
				"4 7 2 5 - - - - -\n" +
				"- - - 8 4 9 - - 6\n" + 
				"- 4 - - - - 8 3 1\n" +
				"- - - 4 2 1 - - -\n" +
				"6 5 1 - - - - 9 -\n" +
				"1 - - 3 7 6 - - -\n" + 
				"- - - - - 2 4 1 3\n" +
				"9 3 8 - - - - - -\n"; 
		SudokuModel inputModel = new SudokuModel(inputString);
		String answerString = 
				"8 9 6 2 1 7 3 4 5\n" + 
				"4 7 2 5 6 3 1 8 9\n" + 
				"5 1 3 8 4 9 7 2 6\n" +
				"2 4 7 6 9 5 8 3 1\n" + 
				"3 8 9 4 2 1 5 6 7\n" + 
				"6 5 1 7 3 8 2 9 4\n" + 
				"1 2 4 3 7 6 9 5 8\n" + 
				"7 6 5 9 8 2 4 1 3\n" + 
				"9 3 8 1 5 4 6 7 2\n"; 
		SudokuModel answerModel = new SudokuModel(answerString);
		SudokuModel resultModel = solver.solve(inputModel);
		assertEquals(answerModel, resultModel);	
	}
	
	/**
	 * This puzzle required naked single, hidden singles, 
	 * and pointed pair techniques
	 */
	@Test
	public void testHardLevelPuzzle1() {
		SudokuSolver solver = new SudokuSolver();
		String inputString = 
				"- - - 2 - - - 6 3\n" +
				"3 - - - - 5 4 - 1\n" +
				"- - 1 - - 3 9 8 -\n" + 
				"- - - - - - - 9 -\n" +
				"- - - 5 3 8 - - -\n" +
				"- 3 - - - - - - -\n" +
				"- 2 6 3 - - 5 - -\n" + 
				"5 - 3 7 - - - - 8\n" +
				"4 7 - - - 1 - - -\n"; 
		SudokuModel inputModel = new SudokuModel(inputString);
		String answerString = 
				"8 5 4 2 1 9 7 6 3\n" + 
				"3 9 7 8 6 5 4 2 1\n" + 
				"2 6 1 4 7 3 9 8 5\n" +
				"7 8 5 1 2 6 3 9 4\n" + 
				"6 4 9 5 3 8 1 7 2\n" + 
				"1 3 2 9 4 7 8 5 6\n" + 
				"9 2 6 3 8 4 5 1 7\n" + 
				"5 1 3 7 9 2 6 4 8\n" + 
				"4 7 8 6 5 1 2 3 9\n"; 
		SudokuModel answerModel = new SudokuModel(answerString);
		SudokuModel resultModel = solver.solve(inputModel);
		assertEquals(answerModel, resultModel);	
	}
	
	
		
	@Test
	public void testUnsolvablePuzzle() {
		SudokuSolver solver = new SudokuSolver();
		String inputString = 
				"- - - - - - - - -\n" +
				"- - - 1 2 3 - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n" +
				"- - - - - - - - -\n";
		SudokuModel inputModel = new SudokuModel(inputString);
		try {
			solver.solve(inputModel);
			assertFalse("This puzzle is NOT solvable", true);
		} catch (IllegalArgumentException iae) {
			assertTrue("This puzzle is NOT solvable", true);
		}
	}
}
