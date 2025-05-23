package tr.hadiobeid.sudoku.sudoku;

import tr.hadiobeid.sudoku.grid.SudokuGrid;
import tr.hadiobeid.sudoku.solver.SudokuSolver;

import java.util.ArrayList;

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
    private final SudokuGrid initial;
    private ArrayList<SudokuGrid> solution;

    private SudokuSolver solver;

    public Sudoku(SudokuGrid initial, SudokuSolver solver) throws IllegalArgumentException {
        this.initial = initial;
        this.solver = solver;
        if (solver.solve(initial).size() > 1) throw new IllegalArgumentException("Grid has too many solutions");
        this.solution = solver.solve(initial);
    }

    @Override
    public String toString() {
        return "Initial state: " +
                initial.toString() +
                "\n" +
                "Solution: " +
                solution.toString();
    }
}
