package tr.hadiobeid.sudoku.algorithm;

/**
 * Class representing the constraint matrix node
 * Based (copied) on Sudopedia's implementation of the constraint matrix
 */
public class MatrixNode {
    MatrixNode up, down, left, right;
    MatrixNodeHeader head;

    MatrixNode() {
    }

    public Integer getSize() {
        return null;
    }

    @Override
    public String toString() {
        return "1";
    }
}
