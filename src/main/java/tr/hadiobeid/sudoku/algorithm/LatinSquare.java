package tr.hadiobeid.sudoku.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Create an nxn Latin square
 */
public class LatinSquare {
    List<List<Character>> grid;
    int n;

    LatinSquare(int n) {
        this.n = n;
        if (n < 1){
            throw new IllegalArgumentException();
        } else {
            grid = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                var l = new ArrayList<Character>();
                for (int j = 0; j < n; j++) {
                    l.add('0');
                }
                grid.add(l);
            }
        }
    }

    ConstraintMatrix getMatrix() {
        var matrix = new ConstraintMatrix();

        for (int i = 0; i < n*n*3; i++) {
            matrix.createHeader();
        }

        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {
                for (int num = 0; num < n; num++) {
                    System.out.println((num+1) + " at (" + col + ", " + row + ")");
                    int constraint_location = (n * col) + row;
                    int constraint_row = (n * n) + row + (n * num);
                    var node = matrix.createDetail(null, matrix.columns.get((n * col) + row));
                    matrix.createDetail(node, matrix.columns.get(constraint_row));
                }
            }
        }



        return matrix;
    }

}
