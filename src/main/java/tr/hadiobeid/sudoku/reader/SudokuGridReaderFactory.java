package tr.hadiobeid.sudoku.reader;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public class SudokuGridReaderFactory {


    /**
     * Generate Appropriate implementation for SudokuGridReader
     * @param filename Name of fil
     * @return SudokuGridReader implementation for file extension
     */
    public SudokuGridReader getSudokuGridReaderForFile(@NotNull String filename) {
        String extension = filename.split("\\.")[1];
        if (extension.equals("json")) {
            return new SudokuJSONReader(filename);
        } else {
            // If file extension is not recognised
            return new SudokuBasicReader(filename);
        }

    }


}
