package tr.hadiobeid.sudoku.solver;

import tr.hadiobeid.sudoku.grid.SudokuGrid;

import java.util.ArrayList;

/**
 * Interface for solving a sudoku grid by using various algorithms.
 */
public abstract class SudokuSolver {
    public abstract ArrayList<SudokuGrid> solve(SudokuGrid grid);
    SudokuSolver() {}
}
