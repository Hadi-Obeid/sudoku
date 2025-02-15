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


}