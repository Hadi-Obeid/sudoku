package tr.hadiobeid.sudoku.reader;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBasicReaderTest {

    @Test
    void shouldInitializeSudokuReader() {
        SudokuBasicReader sudokuBasicReader = new SudokuBasicReader();
        sudokuBasicReader.setFilename("basicFile.txt");
        assertNotNull(sudokuBasicReader);
    }

    @Test
    void returnExceptionIfFileDoesNotExist() {
        SudokuBasicReader sudokuBasicReader = new SudokuBasicReader();
        sudokuBasicReader.setFilename("meaning of life");
        assertThrows(IOException.class, sudokuBasicReader::readFromFile);
    }

    @Test
    void shouldReadSudoku() throws IOException, InvalidGridDataException {
        SudokuBasicReader sudokuBasicReader = new SudokuBasicReader();
        sudokuBasicReader.setFilename("data/basicFile.txt");
        char[][] grid = sudokuBasicReader.readFromFile();
        assertNotNull(grid);
    }

    @Test
    void gridShouldBeValid() throws IOException, InvalidGridDataException {
        SudokuBasicReader sudokuBasicReader = new SudokuBasicReader();
        sudokuBasicReader.setFilename("data/basicFile.txt");
        char[][] grid = sudokuBasicReader.readFromFile();
        assertEquals(9, grid.length);

    }

    @Test
    void shouldReadValues() throws IOException, InvalidGridDataException {
        SudokuBasicReader sudokuBasicReader = new SudokuBasicReader();
        sudokuBasicReader.setFilename("data/basicFile.txt");
        char[][] grid = sudokuBasicReader.readFromFile();
        assertEquals('1', grid[0][0]);
        assertEquals('1', grid[0][8]);
        assertEquals('1', grid[4][4]);
        assertEquals('1', grid[8][0]);
        assertEquals('1', grid[8][8]);

    }

    @Test
    void shouldThrowExceptionIfImproperlyFormed() throws InvalidGridDataException {
        SudokuBasicReader sudokuBasicReader = new SudokuBasicReader();
        sudokuBasicReader.setFilename("data/badfile.txt");
        assertThrows(InvalidGridDataException.class, sudokuBasicReader::readFromFile);
        sudokuBasicReader.setFilename("data/badnumbers.txt");
        assertThrows(InvalidGridDataException.class, sudokuBasicReader::readFromFile);
        sudokuBasicReader.setFilename("data/brokengrid");
        assertThrows(InvalidGridDataException.class, sudokuBasicReader::readFromFile);
    }

    @Test
    void readAllReadsMultipleGrids() throws IOException, InvalidGridDataException {
        SudokuBasicReader sudokuBasicReader = new SudokuBasicReader();
        sudokuBasicReader.setFilename("data/multiple.txt");
        ArrayList<char[][]> grids = sudokuBasicReader.readAllFromFile();
        assertNotNull(grids);
        assertEquals(3, grids.size());

        assertEquals('2', grids.get(1)[0][0]);
        assertEquals('3', grids.get(2)[0][0]);
        assertEquals('0', grids.get(2)[0][1]);

    }


}