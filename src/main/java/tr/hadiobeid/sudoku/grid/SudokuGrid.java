package tr.hadiobeid.sudoku.grid;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;

/**
 * Represents a grid state of the 9x9 sudoku board
 * <p>
 *     Provided methods:
 *     <li>
 *         <ul>Evaluate the state of a grid, whether it is solved, unsolved (Valid), or Invalid </ul>
 *     </li>
 * </p>
 */
public class SudokuGrid {
    private char[][] grid;
    // Grid must be 9x9
    final int rows = 9;
    final int cols = 9;

    /**
     * Constructor that generates a grid with default value 0
     * @return a sudoku grid where each value is '0', representing an empty cell
     */
    SudokuGrid() {
       this.grid = new char[9][9];
       for (int row = 0; row < 9; row++) {
           Arrays.fill(this.grid[row], '0');
       }

    }

    /**
     * Getter method for SudokuGrid
     * @param row row in grid from top to bottom
     * @param col col in grid from left to right
     * @throws IndexOutOfBoundsException if row or col are invalid
     * @return value of grid cell at row, col
     */
    public char getCell(int row, int col) throws IndexOutOfBoundsException {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Row or col out of bounds");
        }
        return grid[row][col];
    }

    /**
     * Setter method for SudokuGrid
     * @param row row in grid from top to bottom
     * @param col col in grid from left to right
     * @throws IndexOutOfBoundsException if row or col are invalid
     * @throws IllegalArgumentException if attempting to assign a nonsensical value to the grid
     */
    public void setCell(int row, int col, char value) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Row or col out of bounds");
        } else if (value <= '0' || value >= '9') {
            throw new IllegalArgumentException("Attempting to assign invalid value");
        }
        grid[row][col] = value;
    }

    /**
     * Get a corresponding 3x3 region of a cell
     * @return A corresponding 3x3 region for a cell at row, col (including cell at row, col)
     * @throws IndexOutOfBoundsException if values of row and col would lead to invalid index
     */
    public char[][] getRegion(int row, int col) throws IndexOutOfBoundsException {
        // get coordinates of region (top left corner)
        int region_x = (col / 3) * 3;
        int region_y = (row / 3) * 3;

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Row or col out of bounds");
        }

        if ((region_y < 0 || 2 + region_y >= rows) || (region_x < 0 || 2 + region_x >= cols)) {
            throw new IndexOutOfBoundsException("Region would contain out of bounds");
        }

        char[][] region = new char[3][3];
        for (int r = 0; r < 3; r++) {
            System.arraycopy(grid[r + region_y], region_x, region[r], 0, 3);
        }
        return region;
    }

    /**
     * @return SudokuState enum for the following states
     *    <ul>
     *        <li>SudokuState.VALID if a grid has empty space with all cells abiding by Sudoku rules</li>
     *        <li>SudokuState.INVALID if there are invalid cells (such as two of the same number in a 3x3 area)</li>
     *        <li>SudokuState.Solved if the grid is valid and has no empty space, therefore it is basically solved</li>
     *    </ul>
     */
    SudokuState ValidateGrid() {
       return SudokuState.VALID;
    }

    public char[][] getGrid() {
        return grid;
    }
}
