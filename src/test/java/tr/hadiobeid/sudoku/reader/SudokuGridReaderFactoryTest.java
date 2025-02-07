package tr.hadiobeid.sudoku.reader;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridReaderFactoryTest {
    private final SudokuGridReaderFactory factory = new SudokuGridReaderFactory();

    @Test
    void getSudokuGridReader() {
        SudokuGridReader reader = factory.getSudokuGridReaderForFile("data/basicFile.txt");
        assertNotNull(reader);
    }

    @Test
    void shouldReadFromFile() throws IOException, InvalidGridDataException {
        SudokuGridReader reader = factory.getSudokuGridReaderForFile("data/basicFile.txt");
        char[][] grid = reader.readFromFile();
        assertNotNull(grid);
        assertEquals('1', grid[0][0]);
    }

    @Test
    void shouldReadAllFromFile() throws IOException, InvalidGridDataException {
        SudokuGridReader reader = factory.getSudokuGridReaderForFile("data/multiple.txt");
        var grids = reader.readAllFromFile();
        assertNotNull(grids);
        assertEquals('1', grids.get(0)[0][0]);
        assertEquals('2', grids.get(1)[0][0]);

    }
  
}