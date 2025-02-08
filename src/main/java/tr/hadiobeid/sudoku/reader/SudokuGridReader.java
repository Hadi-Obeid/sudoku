package tr.hadiobeid.sudoku.reader;

import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for implementing a class that reads Grid Data from files
 * Mostly intended for testing
 * Possible implementations:
 * <li>
 *     <ul> A basic reader based on whitespace separated values </ul>
 *     <ul> Read from JSON files </ul>
 * </li>
 *
 */

@Setter
public abstract class SudokuGridReader {

    String filename;

    /**
     * Read a single Sudoku Grid from a file
     * @return A single 2d sudoku grid array from file (1st if multiple exist)
     * @throws IOException if there are any problems with accessing the file.
     * @throws InvalidGridDataException if there exists an issue with the data being read.
     */
    public abstract char[][] readFromFile() throws IOException, InvalidGridDataException;

    /**
     * Read all Sudoku Grids from a file
     *
     * @return A list of grids (as found in file)
     * @throws IOException              If there are any problems with accessing the file.
     * @throws InvalidGridDataException If there exists an issue with the data being read.
     */
    public abstract ArrayList<char[][]> readAllFromFile() throws IOException, InvalidGridDataException;


    /*
    @PostConstruct
    void setUp(String filename) {
        this.filename = filename;
    }

     */

    SudokuGridReader(String filename) {
        this.filename = filename;
    }

    public SudokuGridReader() {

    }


    public void setFilename(String filename) {
    this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
