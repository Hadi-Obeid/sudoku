package tr.hadiobeid.sudoku.sudoku;

import org.junit.jupiter.api.Test;
import tr.hadiobeid.sudoku.reader.InvalidGridDataException;
import tr.hadiobeid.sudoku.reader.SudokuGridReader;
import tr.hadiobeid.sudoku.reader.SudokuGridReaderFactory;
import tr.hadiobeid.sudoku.solver.SudokuSolver;
import tr.hadiobeid.sudoku.solver.SudokuSolverFactory;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {
    SudokuSolverFactory factory = new SudokuSolverFactory();
    SudokuSolver solver = factory.getSudokuSolver("backtracking");

    SudokuGridReaderFactory gridReaderFactory = new SudokuGridReaderFactory();
    SudokuGridReader reader = gridReaderFactory.getSudokuGridReaderForFile("data/gridtestdata.json");
    ArrayList<char[][]> grids = reader.readAllFromFile();

    SudokuTest() throws IOException, InvalidGridDataException {
    }

    @Test
    void shouldInitializeSudoku() {

    }

}