package tr.hadiobeid.sudoku.solver;

/**
 * Factory to generate sudoku solvers based on different algorithms
 */
public class SudokuSolverFactory {

    SudokuSolver getSudokuSolver(String id) {
        return new BacktrackingSudokuSolver();
    }
}
