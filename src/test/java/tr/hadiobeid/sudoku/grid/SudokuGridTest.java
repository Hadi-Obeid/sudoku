package tr.hadiobeid.sudoku.grid;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridTest {

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
        assertEquals('1', sudokuGrid.getCell(0, 0));
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
        char[][] region = sudokuGrid.getRegion(0, 0);
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
        char[][] region = sudokuGrid.getRegion(0, 0);
        assertTrue(Arrays.deepEquals(new char[][]{{'0', '0', '0'}, {'0', '0', '0'}, {'0', '0', '0'}}, region));

        sudokuGrid.setCell(4, 4, '1');
        region = sudokuGrid.getRegion(4, 4);
        assertTrue(Arrays.deepEquals(new char[][]{{'0', '0', '0'}, {'0', '1', '0'}, {'0', '0', '0'}}, region));

        sudokuGrid.setCell(3, 4, '1');
        region = sudokuGrid.getRegion(4, 4);
        assertTrue(Arrays.deepEquals(new char[][]{{'0', '1', '0'}, {'0', '1', '0'}, {'0', '0', '0'}}, region));

        sudokuGrid.setCell(5, 4, '1');
        sudokuGrid.setCell(5, 5, '1');
        region = sudokuGrid.getRegion(4, 4);
        assertTrue(Arrays.deepEquals(new char[][]{{'0', '1', '0'}, {'0', '1', '0'}, {'0', '1', '1'}}, region));

        sudokuGrid = new SudokuGrid();
        sudokuGrid.setCell(0, 0, '7');
        sudokuGrid.setCell(0, 1, '7');
        sudokuGrid.setCell(0, 2, '7');
        sudokuGrid.setCell(1, 0, '7');
        region = sudokuGrid.getRegion(0, 0);
        assertTrue(Arrays.deepEquals(new char[][]{{'7', '7', '7'}, {'7', '0', '0'}, {'0', '0', '0'}}, region));
    }
}