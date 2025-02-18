package tr.hadiobeid.sudoku.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstraintMatrixTest {

    @Test
    void shouldCreateRoot() {
        var matrix = new ConstraintMatrix();
        assertNotNull(matrix.getRoot());
        assertEquals(matrix.getRoot(), matrix.getRoot().right);
        assertEquals(matrix.getRoot(), matrix.getRoot().left);
        assertNull(matrix.getRoot().up);
        assertNull(matrix.getRoot().down);
    }

    @Test
    void shouldCreateHeader() {
        var matrix = new ConstraintMatrix();
        matrix.createHeader();
        assertNotEquals(matrix.getRoot(), matrix.getRoot().right);
        assertEquals(0, matrix.getRoot().right.getSize());
        assertEquals(matrix.getRoot(), matrix.getRoot().right.left);
        assertEquals(matrix.getRoot(), matrix.getRoot().right.right);

    }

    @Test
    void shouldCreateRow() {
        var matrix = new ConstraintMatrix();
        matrix.createHeader();
        matrix.createDetail(null, (MatrixNodeHeader) matrix.getRoot().right);
        matrix.createDetail(matrix.getRoot().right.down, (MatrixNodeHeader) matrix.getRoot().right);
        assertNotNull(matrix.getRoot().right.down);
        assertEquals(matrix.getRoot().right.down, matrix.rows.getFirst());
        assertNotNull(matrix.getRoot().right.down.right);

    }

    @Test
    void shouldCreateRows() {
        var matrix = new ConstraintMatrix();
        // Create 3 headers
        matrix.createHeader();
        matrix.createHeader();
        matrix.createHeader();

        // First row, first column
        matrix.createDetail(null, (MatrixNodeHeader) matrix.columns.getFirst());

        // Second row, first column
        matrix.createDetail(null, (MatrixNodeHeader) matrix.columns.getFirst());

        assertEquals(matrix.columns.getFirst().up, matrix.columns.getFirst().down.down);
    }

    @Test
    void shouldDisableRow() {
        var matrix = new ConstraintMatrix();
        for (int i = 0; i < 3; i++) {
            matrix.createHeader();
        }

        // Create rows 1 and 2, disable row 1

        /*
            R   A B C
            0   1 0 1
            1   0 1 0
         */
        var node = matrix.createDetail(null, (MatrixNodeHeader) matrix.columns.getFirst());
        matrix.createDetail(node, (MatrixNodeHeader) matrix.columns.getLast());
        matrix.createDetail(null, (MatrixNodeHeader) matrix.columns.get(1));
        assertEquals(matrix.columns.getFirst().down.left, matrix.columns.getLast().down);

        matrix.disable(0);
        assertEquals(0, matrix.columns.getFirst().getSize());
        assertEquals(0, matrix.columns.getLast().getSize());
    }

    @Test
    void shouldEnableRow() {
        var matrix = new ConstraintMatrix();
        for (int i = 0; i < 3; i++) {
            matrix.createHeader();
        }

        // Create rows 1 and 2, disable row 1

        /*
            R   A B C
            0   1 0 1
            1   0 1 0
         */
        var node = matrix.createDetail(null, (MatrixNodeHeader) matrix.columns.getFirst());
        matrix.createDetail(node, (MatrixNodeHeader) matrix.columns.getLast());
        matrix.createDetail(null, (MatrixNodeHeader) matrix.columns.get(1));

        matrix.disable(0);
        matrix.enable(0);
        assertEquals(1, matrix.columns.getFirst().getSize());
        assertEquals(1, matrix.columns.getLast().getSize());
    }


}