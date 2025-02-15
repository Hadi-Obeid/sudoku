package tr.hadiobeid.sudoku.grid;

import lombok.Getter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    @Getter
    private final char[][] grid;

    private static final Map<Character, Integer> counter = new HashMap<>();

    /**
     * Utility method to iterate through a unit in Sudoku.
     * @param unit A unit of the sudoku puzzle (a row, column, or region) that should contain 9 distinct elements
     * @return False if unit is valid (i.e. no repeating digits), True if invalid
     */
    private static boolean isUnitInvalid(char[] unit) {
        counter.clear();
        for (char c : unit) {
            if (c != '0')
                if (counter.containsKey(c)) {
                    return true;
                } else {
                    counter.put(c, counter.getOrDefault(c, 1));
                }
        }
        return false;
    }
    // Grid must be 9x9
    public final int rows = 9;
    public final int cols = 9;

    /**
     * Constructor that generates a grid with default value of 0
     */
    public SudokuGrid() {
       this.grid = new char[9][9];
       for (int row = 0; row < rows; row++) {
           Arrays.fill(this.grid[row], '0');
       }
    }

    public SudokuGrid(char[][] grid) {
        this.grid = grid;
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
        } else if (value < '0' || value > '9') {
            throw new IllegalArgumentException("Attempting to assign invalid value");
        }
        grid[row][col] = value;
    }



    /**
     * Get a corresponding 3x3 region of a cell
     * @return A corresponding 3x3 region for a cell at row, col (including cell at row, col) as a 1D array
     * @throws IndexOutOfBoundsException if values of row and col would lead to invalid index
     */
    public char[] getRegion(int row, int col) throws IndexOutOfBoundsException {
        // get coordinates of region (top left corner)
        int region_x = (col / 3) * 3;
        int region_y = (row / 3) * 3;

        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Row or col out of bounds");
        }

        if ((region_y < 0 || 2 + region_y >= rows) || (region_x < 0 || 2 + region_x >= cols)) {
            throw new IndexOutOfBoundsException("Region would contain out of bounds");
        }

        char[] region = new char[9];
        for (int r = 0; r < 3; r++) {
            System.arraycopy(grid[r + region_y], region_x, region, r * 3, 3);
        }
        return region;
    }

    /**
     * Gets all 9 3x9 regions in sudoku board
     * @return Array of 3x3 regions
     */
    public char[][] getRegions() {
        return new char[][] {
                getRegion(0,0), getRegion(0, 3), getRegion(0, 6),
                getRegion(3,0), getRegion(3, 3), getRegion(3, 6),
                getRegion(6,0), getRegion(6, 3), getRegion(6, 6),
        };
    }



    /**
     * Checks if a grid is complete (no unfilled spaces)
     * @return If there exists any unfilled spaces ('0') in grid
     */
    public boolean isComplete() {
        return Arrays.stream(grid).noneMatch(row -> new String(row).indexOf('0') != -1);
    }

    /**
     *
     * @return True If a grid contains only valid numbers, false otherwise
     */
    private boolean gridIsValid() {
        // Get regions
        char[][] regions = getRegions();

        // Validate rows ensuring that no two numbers in the range 1-9 repeat twice

        //Map<Character, Integer> counter = new HashMap<>();

        for (char[] row : grid) {
            if (isUnitInvalid(row)) return false;
        }

        //counter = new HashMap<>();

        // It is slow to iterate through columns
        for (int col = 0; col < cols; col++) {
            char[] column = new char[9];

            for (int row = 0; row < rows; row++) {
                column[row] = grid[row][col];

            }
            if (isUnitInvalid(column)) return false;
        }

        // Check regions
        for (char[] region : regions) {
            if (isUnitInvalid(region)) return false;
        }

        return true;
    }

    /**
     * @return SudokuState enum for the following states
     *    <ul>
     *        <li>SudokuState.VALID if a grid has empty space with all cells abiding by Sudoku rules</li>
     *        <li>SudokuState.INVALID if there are invalid cells (such as two of the same number in a 3x3 area)</li>
     *    </ul>
     */
    public SudokuState validateGrid() {
        if (!this.gridIsValid()) {
            return SudokuState.INVALID;
        }

        return SudokuState.VALID;
    }

    // The usual
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean isSolved() {
        return isComplete() && (validateGrid() == SudokuState.VALID);
    }
}
