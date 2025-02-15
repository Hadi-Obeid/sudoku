package tr.hadiobeid.sudoku.algorithm;

import lombok.Getter;

/**
 * Class representing the constraint matrix node header
 * Based (copied) on Sudopedia's implementation of the constraint matrix
 */
@Getter
public class MatrixNodeHeader extends MatrixNode{
    public Integer size;

    @Override
    public String toString() {
        return size.toString();
    }

}
