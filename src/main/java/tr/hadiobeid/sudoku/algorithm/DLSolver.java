package tr.hadiobeid.sudoku.algorithm;

/**
 * Generic solver for exact cover problem
 */
public class DLSolver {
    ConstraintMatrix matrix;

    DLSolver(ConstraintMatrix m) {
        matrix = m;
    }

    boolean isSolved() {
        int s = 1;
        for (var c: matrix.columns) {
            s = c.size;
        }
        return s == 1;
    }

    MatrixNodeHeader getColumnHeuristic() {
        int size = matrix.rows.size();
        MatrixNodeHeader h = null;
        for (var column: matrix.columns) {
            if (column.size < size) {
                h = column;
                size = column.size;
            }
        }
        return h;
    }
    boolean solve() {
        if (isSolved()) {
            return true;
        } else {
            var header = getColumnHeuristic();
            if (header.size == 0) {
                return false;
            }


        }
    }
}
