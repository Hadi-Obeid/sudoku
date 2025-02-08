package tr.hadiobeid.sudoku.solver;

public class SudokuSolverFactory {

    SudokuSolver getSudokuSolver(String id) {
        return new BacktrackingSudokuSolver();
    }
}
