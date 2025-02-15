package tr.hadiobeid.sudoku.algorithm;


import java.util.ArrayList;

/**
 * Class representing the constraint matrix that will be used for DLX
 * Based (copied) on Sudopedia's implementation of the constraint matrix
 */
public class ConstraintMatrix {
    private final MatrixNode root;

    public ArrayList<MatrixNodeHeader> columns = new ArrayList<>();
    public ArrayList<MatrixNode> rows = new ArrayList<>();

    ConstraintMatrix() {
        root = new MatrixNode();
        root.left = root;
        root.right = root;
    }

    /**
     * Create header column for constraint matrix and add it to the columns array
     */
    public void createHeader() {
        MatrixNodeHeader header = new MatrixNodeHeader();
        header.size = 0;
        // Cycle right to root
        header.right = root;
        // Set left to rightmost column
        header.left = root.left;
        // Set this column to rightmost column from root left
        header.right.left = header;
        // Connect rightmost column to this header
        header.left.right = header;

        // Set up cyclical connection for top down
        header.up = header;
        header.down = header;

        columns.add(header);
    }

    /**
     * Add an element on a row
     * @param last Previous node in row
     * @param head Head node of column
     */
    MatrixNode createDetail(MatrixNode last, MatrixNodeHeader head) {
        MatrixNode newNode = new MatrixNode();

        if (last != null) {
            newNode.left = last;
            newNode.right = last.right;
            newNode.left.right = newNode;
            newNode.right.left = newNode;
        } else {
            newNode.left = newNode;
            newNode.right = newNode;
        }
        if (head == columns.getFirst()) {
            rows.add(newNode);
        }
        newNode.head = head;
        head.size += 1;
        newNode.down = head;
        newNode.up = head.up;
        newNode.up.down = newNode;
        newNode.down.up = newNode;

        return newNode;

    }

    MatrixNode getRoot() {
        return root;
    }

    public boolean findAtIndex(int row, int col) {
        MatrixNode rowStart = rows.get(row);
        MatrixNodeHeader colHeader = (MatrixNodeHeader) columns.get(col);
        if (row < 0 || col < 0) return false;
        if (row > rows.size() - 1 || col > columns.size() - 1) return false;

        MatrixNode node = rowStart;
        for (int i = 0; i < col; i++) {
            if (node.head == columns.get(col)) {
                return true;
            }
            node = node.right;
        }
        return false;

    }

}
