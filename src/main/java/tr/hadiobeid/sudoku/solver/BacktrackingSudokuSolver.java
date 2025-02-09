package tr.hadiobeid.sudoku.solver;

import tr.hadiobeid.sudoku.grid.SudokuGrid;
import tr.hadiobeid.sudoku.grid.SudokuState;


/**
 * Sudoku Solving algorithm based on recursive backtracking. Simple but inefficient
 */
public class BacktrackingSudokuSolver extends SudokuSolver {

    @Override
    public SudokuGrid solve(SudokuGrid grid) {
        SudokuGrid copy = new SudokuGrid(grid.getGrid());

        solve(copy, '1');

        return copy;
    }

    private static int[] getEmptyCell(SudokuGrid grid) {
        char[][] cells = grid.getGrid();
        int[] answer = new int[2];
        for (int i = 0; i < grid.rows; i++) {
            for (int j = 0; j < grid.cols; j++) {
                if (cells[i][j] == '0') {
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                }

            }
        }
        return null;
    }
    /**
     * @param grid sudoku grid
     * @param row y coordinate of next move
     * @param col x coordinate of next move
     * @return grid if solved (base case), otherwise do recursion
     */
    public boolean solve(SudokuGrid grid, char move) {
        int[] next_move_pos = getEmptyCell(grid);
        if (next_move_pos == null) {
            return true;

        } else {
            int row = next_move_pos[0], col = next_move_pos[1];
            for (char i = '1'; i <= '9'; i++) {
                grid.setCell(row, col, i);
                if (grid.validateGrid() == SudokuState.VALID) {
                    if (solve(grid, move)) {
                        return true;
                    }
                }
                grid.setCell(row, col, '0');

            }

        }
        return false;

    }

    private void getSolution(char[][] board) {

    }
}
