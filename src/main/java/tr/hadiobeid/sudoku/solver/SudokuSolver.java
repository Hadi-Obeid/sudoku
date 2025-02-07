package tr.hadiobeid.sudoku.solver;

import tr.hadiobeid.sudoku.grid.SudokuGrid;

/**
 * Interface for solving a sudoku grid by using various algorithms.
 */
public interface SudokuSolver {
    public SudokuGrid solve(SudokuGrid grid);
}
