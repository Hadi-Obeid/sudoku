package tr.hadiobeid.sudoku.reader;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SudokuBasicReader extends GenericSudokuReader implements SudokuGridReader {
    public static final String BEAN_ID = "sudokuBasicReader";

    SudokuBasicReader() { super(); }

    SudokuBasicReader(String filename) {
        super(filename);
    }


    @Override
    public char[][] readFromFile() throws IOException, InvalidGridDataException {
        char[][] grid = new char[9][9];

        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new IOException(filename + " not found");
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                for (int row = 0; row < 9; row++) {
                    String line = reader.readLine();
                    if (line == null) {
                        throw new InvalidGridDataException(filename + " is not a valid sudoku grid");
                    }
                    if (!line.matches("\\d{9}")) {
                        throw new InvalidGridDataException(filename + " is not a valid sudoku grid");
                    }
                    grid[row] = line.toCharArray();
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        return grid;
    }

    @Override
    public ArrayList<char[][]> readAllFromFile() throws IOException, InvalidGridDataException {
        ArrayList<char[][]> grids = new ArrayList<>();
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new IOException(filename + " not found");
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line = reader.readLine();
                if (line == null) {
                    throw new InvalidGridDataException(filename + " is not a valid sudoku grid");
                }
                while (line != null) {
                    char[][] grid = new char[9][9];
                    for (int row = 0; row < 9; row++) {
                        if (!line.matches("\\d{9}")) {
                            throw new InvalidGridDataException(filename + " is not a valid sudoku grid");
                        }
                        grid[row] = line.toCharArray();
                        line = reader.readLine();
                    }
                    grids.add(grid);
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        return grids;
    }

}
