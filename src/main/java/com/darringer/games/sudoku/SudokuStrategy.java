package com.darringer.games.sudoku;

public interface SudokuStrategy {

	SudokuModel applyStrategy(SudokuModel model, int x, int y);
	
}
