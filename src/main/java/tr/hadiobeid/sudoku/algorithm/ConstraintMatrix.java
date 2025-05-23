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

    MatrixNode createDetail(int index) throws IndexOutOfBoundsException {
        // Easier to work with, appends to the "bottom end" of a column
        MatrixNodeHeader head = columns.get(index);

        MatrixNode curNode = head.up;
        if (curNode == head)
            return createDetail(null, head);
        else {
            while (curNode.down != head) {
                if (curNode.down == head) {
                    return createDetail(curNode, head);
                }
                curNode = curNode.down;
            }
            return createDetail(curNode, head);
        }
    }


    /**
     * Covers the references to a row in a constraint matrix, disabling it
     * @param rowIndex index of row to disable
     */
    public void disable(int rowIndex) {
        MatrixNode curNode = rows.get(rowIndex);
        do {
            // Prevent disabling the same node twice
            if (curNode.down != curNode) {
                curNode.down.up = curNode;
                curNode.up.down = curNode.down;
                curNode.down = curNode;
                curNode.up = curNode;
                curNode.head.size -= 1; // Decrement size of column header
            }
            curNode = curNode.right;
        } while (curNode != rows.get(rowIndex));
    }

    /**
     * Uncovers the references to a row in a constraint matrix, enabling it
     * @param rowIndex index of row to enable
     */
    public void enable(int rowIndex) {
        MatrixNode curNode = rows.get(rowIndex);
        do {
            // Prevent disabling the same node twice
            if (curNode.down == curNode) {
                curNode.down = curNode.head;
                curNode.up = curNode.head.up;
                curNode.down.up = curNode;
                curNode.up.down = curNode;
                curNode.head.size += 1; // Decrement size of column header
            }
            curNode = curNode.right;
        } while (curNode != rows.get(rowIndex));
    }

    MatrixNode getNode(int rowIndex, int colIndex) throws IndexOutOfBoundsException {
        try {
            MatrixNode curNode = rows.get(rowIndex);
            do {
                if (curNode.head == columns.get(colIndex)) {
                    return curNode;
                }
                curNode = curNode.right;
            } while (curNode != rows.get(rowIndex));

            return null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    MatrixNode getRoot() {
        return root;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rows.size(); r++) {
            for (int c = 0; c < columns.size(); c++) {
                MatrixNode curNode = getNode(r, c);
                if (curNode != null) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
