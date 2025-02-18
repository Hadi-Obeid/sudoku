package tr.hadiobeid.sudoku.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LatinSquareTest {

    @Test
    void shouldBeNdimensional() {
        var sqr = new LatinSquare(5);
        assertEquals(5, sqr.grid.getFirst().size());
        assertEquals('0', sqr.grid.getFirst().getFirst());
    }

    @Test
    void shouldGenerate2x2ConstraintMatrix() {
        var twosquare = new LatinSquare(2);
        var matrix = twosquare.getMatrix();
        assertEquals(12, matrix.columns.size());
        assertEquals(2, matrix.columns.getFirst().size);
        assertEquals(2, matrix.columns.get(3).size);
        assertEquals(matrix.columns.getFirst().down, matrix.columns.get(4).down.left);
        assertEquals(matrix.columns.getFirst().down.left, matrix.columns.get(4).down.right.right);
    }

}