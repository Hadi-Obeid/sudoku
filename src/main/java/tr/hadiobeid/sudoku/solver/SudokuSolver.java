package tr.hadiobeid.sudoku.solver;

import tr.hadiobeid.sudoku.grid.SudokuGrid;

/**
 * Interface for solving a sudoku grid by using various algorithms.
 */
public abstract class SudokuSolver {
    public abstract SudokuGrid solve(SudokuGrid grid);
    SudokuSolver() {}
}
