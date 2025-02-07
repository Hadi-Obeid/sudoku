package tr.hadiobeid.sudoku.grid;

import org.junit.jupiter.api.Test;
import tr.hadiobeid.sudoku.reader.InvalidGridDataException;
import tr.hadiobeid.sudoku.reader.SudokuGridReader;
import tr.hadiobeid.sudoku.reader.SudokuGridReaderFactory;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridTest {
    SudokuGridReaderFactory sudokuGridReaderFactory = new SudokuGridReaderFactory();

    @Test
    void createSudokuGrid() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertNotNull(sudokuGrid);
    }

    @Test
    void defaultValueIsZero() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                assertEquals('0', sudokuGrid.getCell(row, col));
            }
        }
    }

    @Test
    void shouldSetCell() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        sudokuGrid.setCell(0, 0, '1');
        sudokuGrid.setCell(1, 0, '0');
        sudokuGrid.setCell(2, 0, '9');
        assertEquals('1', sudokuGrid.getCell(0, 0));
        assertEquals('0', sudokuGrid.getCell(1, 0));
        assertEquals('9', sudokuGrid.getCell(2, 0));
    }

    @Test
    void shouldGetCell() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        sudokuGrid.setCell(4, 3, '1');
        assertEquals('1', sudokuGrid.getCell(4, 3));
    }

    @Test
    void shouldThrowIfGetInvalidRow() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.getCell(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.getCell(9, 0));
    }

    @Test
    void shouldThrowIfGetInvalidCol() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.getCell(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.getCell(0, 9));
    }

    @Test
    void shouldThrowIfSetInvalidRow() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.setCell(-1, 0, '1'));
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.setCell(9, 0, '1'));
    }

    @Test
    void shouldThrowIfSetInvalidCol() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.setCell(0, -1, '1'));
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.setCell(0, 9, '1'));
    }

    @Test
    void shouldThrowIfSetInvalidValue() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertThrows(IllegalArgumentException.class, () -> sudokuGrid.setCell(0, 0, 'a'));
        assertThrows(IllegalArgumentException.class, () -> sudokuGrid.setCell(0, 0, '@'));
        assertThrows(IllegalArgumentException.class, () -> sudokuGrid.setCell(0, 0, '\0'));
        assertThrows(IllegalArgumentException.class, () -> sudokuGrid.setCell(0, 0, '?'));
    }

    @Test
    void regionShouldReturnSomething() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        char[] region = sudokuGrid.getRegion(0, 0);
        assertNotNull(region);
    }

    @Test
    void regionShouldThrowOnOutOfBoundsRow() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.getRegion(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.getRegion(9, 0));
    }

    @Test
    void regionShouldThrowOnOutOfBoundsCol() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.getRegion(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> sudokuGrid.getRegion(0, 9));
    }

    @Test
    void regionShouldReturnProperRegion() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        char[] region = sudokuGrid.getRegion(0, 0);
        assertArrayEquals(new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0'}, region);

        sudokuGrid.setCell(4, 4, '1');
        region = sudokuGrid.getRegion(4, 4);
        assertArrayEquals(new char[] {'0', '0', '0', '0', '1', '0', '0', '0', '0'}, region);

        sudokuGrid.setCell(3, 4, '1');
        region = sudokuGrid.getRegion(4, 4);
        assertArrayEquals(new char[] {'0', '1', '0', '0', '1', '0', '0', '0', '0'}, region);

        sudokuGrid.setCell(5, 4, '1');
        sudokuGrid.setCell(5, 5, '1');
        region = sudokuGrid.getRegion(4, 4);
        assertArrayEquals(new char[]{'0', '1', '0', '0', '1', '0', '0', '1', '1'}, region);

        sudokuGrid = new SudokuGrid();
        sudokuGrid.setCell(0, 0, '7');
        sudokuGrid.setCell(0, 1, '7');
        sudokuGrid.setCell(0, 2, '7');
        sudokuGrid.setCell(1, 0, '7');
        region = sudokuGrid.getRegion(0, 0);
        assertArrayEquals(new char[]{'7', '7', '7', '7', '0', '0', '0', '0', '0'}, region);
    }

    @Test
    void emptyGridIsIncomplete() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertFalse(sudokuGrid.isComplete());
    }

    @Test
    void partialGridIsIncomplete() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 8; j++) {
                sudokuGrid.setCell(i, j, '1');
            }
        }
        assertFalse(sudokuGrid.isComplete());

    }

    @Test
    void fullGridIsComplete() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuGrid.setCell(i, j, '1');
            }
        }
        assertTrue(sudokuGrid.isComplete());
    }

    @Test
    void getRegionsReturnsNineRegions() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertEquals(9, sudokuGrid.getRegions().length);
    }

    @Test
    void getRegionsReturnsInProperOrder() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        sudokuGrid.setCell(0, 0, '1');

        sudokuGrid.setCell(8, 8, '1');
        sudokuGrid.setCell(4, 4, '1');
        sudokuGrid.setCell(6, 6, '1');
        char[][] regions = sudokuGrid.getRegions();
        assertArrayEquals(new char[]{'1', '0', '0', '0', '0', '0', '0', '0', '0'}, regions[0]);
        assertArrayEquals(new char[]{'0', '0', '0', '0', '1', '0', '0', '0', '0'}, regions[4]);
        assertArrayEquals(new char[]{'1', '0', '0', '0', '0', '0', '0', '0', '1'}, regions[8]);

    }

    @Test
    void shouldValidateEmptyGrid() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        assertEquals(SudokuState.VALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldValidateRow() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int i = 0; i <= 8; i++) {
            // Set row to 1 2 3 4 5 6 7 8 9
            char c = (char) ('1' + i);
            sudokuGrid.setCell(0, i, c);
        }
        assertEquals(SudokuState.VALID, sudokuGrid.validateGrid());

    }


    @Test
    void shouldInvalidateRow() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int i = 0; i <= 8; i++) {
            // Set row to 1 2 3 4 5 6 7 8 9
            char c = (char) ('1' + i);
            sudokuGrid.setCell(0, i, c);
        }
        sudokuGrid.setCell(0,0, '2');
        assertEquals(SudokuState.INVALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldValidateColumn() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int i = 0; i <= 8; i++) {
            char c = (char) ('1' + i);
            sudokuGrid.setCell(i, 0, c);
        }
        assertEquals(SudokuState.VALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldInvalidateColumn() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int i = 0; i <= 8; i++) {
            char c = (char) ('1' + i);
            sudokuGrid.setCell(i, 0, c);
        }
        sudokuGrid.setCell(0, 0, '2');
        assertEquals(SudokuState.INVALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldValidateColumns() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int i = 0; i <= 8; i++) {
            char c = (char) ('1' + i);
            char d = (char) ('9' - i);
            if (i != 4) {
                sudokuGrid.setCell(i, 0, c);
                sudokuGrid.setCell(i, 3, d);
            }
        }
        assertEquals(SudokuState.VALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldInvalidateColumns() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int i = 0; i <= 8; i++) {
            char c = (char) ('1' + i);
            char d = (char) ('9' - i);
            if (i != 4) {
                sudokuGrid.setCell(i, 0, c);
                sudokuGrid.setCell(i, 3, d);
            }
        }
        sudokuGrid.setCell(8, 0, '2');
        assertEquals(SudokuState.INVALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldValidateRows() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int i = 0; i <= 8; i++) {
            char c = (char) ('1' + i);
            char d = (char) ('9' - i);
            if (i != 4) {
                sudokuGrid.setCell(0, i, c);
                sudokuGrid.setCell(3, i, d);
            }
        }

        assertEquals(SudokuState.VALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldInvalidateRows() {
        SudokuGrid sudokuGrid = new SudokuGrid();

        for (int i = 0; i <= 8; i++) {
            char c = (char) ('1' + i);
            char d = (char) ('9' - i);
            if (i != 4) {
                sudokuGrid.setCell(0, i, c);
                sudokuGrid.setCell(3, i, d);
            }
        }
        sudokuGrid.setCell(0, 8, '2');
        sudokuGrid.setCell(2, 8, '3');
        assertEquals(SudokuState.INVALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldValidateRegion() {
        SudokuGrid sudokuGrid = new SudokuGrid();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = (char) (('1' + j) + i * 3);
                sudokuGrid.setCell(i, j, c);
                System.out.print(c + " ");
            }
            System.out.println();
        }
        assertEquals(SudokuState.VALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldInvalidateRegion() {
        SudokuGrid sudokuGrid = new SudokuGrid();

        // 3x3 region
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = (char) (('1' + j) + i * 3);
                sudokuGrid.setCell(i, j, c);
            }
        }

        // Add an extra 2 where it shouldn't be
        sudokuGrid.setCell(2,0, '2');
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = sudokuGrid.getCell(i, j);
                System.out.print(c + " ");
            }
            System.out.println();
        }

        assertEquals(SudokuState.INVALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldValidateRegions() {
        SudokuGrid sudokuGrid = new SudokuGrid();

        // 3 diagonal regions so there are no row/column clashes
        for (int q = 0; q < 3; q++)
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char c = (char) (('1' + j) + i * 3);
                    sudokuGrid.setCell(i + (q * 3), j + (q * 3), c);
                }
            }
        assertEquals(SudokuState.VALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldInvalidateRegions() {
        SudokuGrid sudokuGrid = new SudokuGrid();

        // 3 diagonal regions so there are no row/column clashes
        for (int q = 0; q < 3; q++)
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char c = (char) (('1' + j) + i * 3);
                    sudokuGrid.setCell(i + (q * 3), j + (q * 3), c);
                }
            }
        sudokuGrid.setCell(8,8,'1');
        assertEquals(SudokuState.INVALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldValidateSudoku() throws IOException, InvalidGridDataException {
        // TODO: Write SudokuGridReader
        SudokuGridReader reader = sudokuGridReaderFactory.getSudokuGridReaderForFile("data/gridtestdata.json");
        SudokuGrid sudokuGrid = new SudokuGrid(reader.readFromFile());

        assertNotNull(sudokuGrid);
        assertEquals(SudokuState.VALID, sudokuGrid.validateGrid());
    }

    @Test
    void shouldValidateSolved() throws IOException, InvalidGridDataException {
        SudokuGridReader reader = sudokuGridReaderFactory.getSudokuGridReaderForFile("data/gridtestdata.json");
        var sudokuGrids = reader.readAllFromFile();
        assertNotNull(sudokuGrids);
        SudokuGrid solved = new SudokuGrid(sudokuGrids.get(2));
        assertEquals(SudokuState.SOLVED, solved.validateGrid());

    }


}