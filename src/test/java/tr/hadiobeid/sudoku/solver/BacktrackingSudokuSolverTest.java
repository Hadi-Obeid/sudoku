package tr.hadiobeid.sudoku.solver;


import org.junit.jupiter.api.Test;
import tr.hadiobeid.sudoku.grid.SudokuState;
import tr.hadiobeid.sudoku.reader.InvalidGridDataException;
import tr.hadiobeid.sudoku.reader.SudokuGridReader;
import tr.hadiobeid.sudoku.reader.SudokuGridReaderFactory;
import tr.hadiobeid.sudoku.grid.SudokuGrid;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {
    // Some factory stuff here
    SudokuSolverFactory factory = new SudokuSolverFactory();
    SudokuSolver solver = factory.getSudokuSolver("backtracking");

    SudokuGridReaderFactory gridReaderFactory = new SudokuGridReaderFactory();
    SudokuGridReader reader = gridReaderFactory.getSudokuGridReaderForFile("data/gridtestdata.json");
    ArrayList<char[][]> grids = reader.readAllFromFile();

    BacktrackingSudokuSolverTest() throws IOException, InvalidGridDataException {
    }

    @Test
    void solverShouldExist() {
        assertNotNull(solver);
    }

    @Test
    void solverSolvesSolvedPuzzle() {
        SudokuGrid easy_grid = new SudokuGrid(grids.get(2));

        System.out.println("Grid to solve: ");
        System.out.println(easy_grid);

        SudokuGrid solution = solver.solve(easy_grid);

        assertTrue(solution.isSolved());

    }

    @Test
    void solverSolvesTrivialPuzzle() {
        SudokuGrid easy_grid = new SudokuGrid(grids.get(1));
        System.out.println("Grid to solve: ");
        System.out.println(easy_grid);

        SudokuGrid solution = solver.solve(easy_grid);
        System.out.println(solution);
        assertTrue(solution.isSolved());

    }

    @Test
    void solverSolvesTwoMoves() {
        SudokuGrid easy_grid = new SudokuGrid(grids.get(1));
        easy_grid.setCell(8,7,'0');

        System.out.println("Grid to solve: ");
        System.out.println(easy_grid);
        SudokuGrid solution = solver.solve(easy_grid);

        assertTrue(solution.isSolved());

    }

    @Test
    void solverSolvesThreeMoves() {
        SudokuGrid easy_grid = new SudokuGrid(grids.get(1));
        easy_grid.setCell(0,0,'0');
        easy_grid.setCell(0,1,'0');

        System.out.println("Grid to solve: ");
        System.out.println(easy_grid);

        SudokuGrid solution = solver.solve(easy_grid);
        System.out.println(solution);

        assertTrue(solution.isSolved());

    }

    @Test
    void solverSolvesRealGrid() {
        SudokuGrid easy_grid = new SudokuGrid(grids.get(3));

        System.out.println("Grid to solve: ");
        System.out.println(easy_grid);

        SudokuGrid solution = solver.solve(easy_grid);
        System.out.println(solution);

        assertTrue(solution.isSolved());

    }


    @Test
    void solverSolvesHardGrid() {
        SudokuGrid grid = new SudokuGrid(grids.get(4));

        System.out.println("Grid to solve: ");
        System.out.println(grid);

        SudokuGrid solution = solver.solve(grid);
        System.out.println(solution);

        assertTrue(solution.isSolved());

    }
}