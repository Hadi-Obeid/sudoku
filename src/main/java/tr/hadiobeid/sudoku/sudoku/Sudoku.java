package tr.hadiobeid.sudoku.sudoku;

import tr.hadiobeid.sudoku.grid.SudokuGrid;
import tr.hadiobeid.sudoku.solver.SudokuSolver;

/**
 * Represents a 9x9 standard Sudoku board with a set of givens/clues and solutions
 * <p>
 *     This class provides methods to:
 *     <ul>
 *         <li>Generate a solvable board randomly of varying difficulty</li>
 *         <li>From the givens, return a solution state</li>
 *     </ul>
 * </p>
 */
public class Sudoku {
    // A SudokuGrid representing the base grid (given values)
    private final SudokuGrid givens;
    private SudokuGrid solution;

    private SudokuSolver solver;

    public Sudoku(SudokuGrid givens, SudokuSolver solver) {
        this.givens = givens;
        this.solver = solver;
        //this.solution = solver.solve(givens);
    }

}
