package tr.hadiobeid.sudoku.grid;

import org.junit.jupiter.api.Test;

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

}