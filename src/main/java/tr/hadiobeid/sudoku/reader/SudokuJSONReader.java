package tr.hadiobeid.sudoku.reader;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class SudokuJSONReader extends GenericSudokuReader implements SudokuGridReader {
    public static final String BEAN_ID = "sudokuJSONReader";

    SudokuJSONReader() { super(); }

    SudokuJSONReader(String filename) {
        super(filename);
    }

    @Override
    public char[][] readFromFile() throws IOException, InvalidGridDataException {

        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new IOException(filename + " not found");
            } else {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    char[][][] grids = mapper.readValue(input, char[][][].class);
                    return grids[0];
                } catch (Exception e) {
                    throw new InvalidGridDataException(filename);
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public ArrayList<char[][]> readAllFromFile() throws IOException, InvalidGridDataException {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                throw new IOException(filename + " not found");
            } else {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    ArrayList<char[][]> grids = new ArrayList<>();
                    Collections.addAll(grids, mapper.readValue(input, char[][][].class));
                    return grids;
                } catch (Exception e) {
                    throw new InvalidGridDataException(filename);
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

}
