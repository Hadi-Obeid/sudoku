package tr.hadiobeid.sudoku.reader;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SudokuJSONReaderTest {

    @Test
    void shouldInitializeSudokuReader() {
        SudokuJSONReader sudokuJSONReader = new SudokuJSONReader();
        sudokuJSONReader.setFilename("data/sudokutest.json");
        assertNotNull(sudokuJSONReader);
    }

    @Test
    void returnExceptionIfFileDoesNotExist() {
        SudokuJSONReader sudokuJSONReader = new SudokuJSONReader();
        sudokuJSONReader.setFilename("meaning of life");
        assertThrows(IOException.class, sudokuJSONReader::readFromFile);
    }

    @Test
    void shouldReadSudoku() throws IOException, InvalidGridDataException {
        SudokuJSONReader sudokuJSONReader = new SudokuJSONReader();
        sudokuJSONReader.setFilename("data/sudokutest.json");
        char[][] grid = sudokuJSONReader.readFromFile();
        assertNotNull(grid);
    }

    @Test
    void gridShouldBeValid() throws IOException, InvalidGridDataException {
        SudokuJSONReader sudokuJSONReader = new SudokuJSONReader();
        sudokuJSONReader.setFilename("data/sudokutest.json");
        char[][] grid = sudokuJSONReader.readFromFile();
        assertEquals(9, grid.length);

    }

    @Test
    void shouldReadValues() throws IOException, InvalidGridDataException {
        SudokuJSONReader sudokuJSONReader = new SudokuJSONReader();
        sudokuJSONReader.setFilename("data/sudokutest.json");
        char[][] grid = sudokuJSONReader.readFromFile();
        assertEquals('9', grid[0][0]);
        assertEquals('9', grid[0][8]);
        assertEquals('9', grid[8][8]);

    }

    @Test
    void shouldThrowExceptionIfImproperlyFormed() throws IOException, InvalidGridDataException {
        SudokuJSONReader sudokuJSONReader = new SudokuJSONReader();
        sudokuJSONReader.setFilename("data/badfile.txt");
        assertThrows(InvalidGridDataException.class, sudokuJSONReader::readFromFile);
    }

    @Test
    void readAllReadsMultipleGrids() throws IOException, InvalidGridDataException {
        SudokuJSONReader sudokuJSONReader = new SudokuJSONReader();
        sudokuJSONReader.setFilename("data/sudokutest.json");
        ArrayList<char[][]> grids = sudokuJSONReader.readAllFromFile();
        assertNotNull(grids);
        assertEquals(3, grids.size());

        assertEquals('2', grids.get(1)[0][0]);
        assertEquals('3', grids.get(2)[0][0]);
        assertEquals('0', grids.get(2)[0][1]);

    }

}