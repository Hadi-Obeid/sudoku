package tr.hadiobeid.sudoku.reader;

import jakarta.annotation.PostConstruct;
import lombok.Setter;

@Setter
class GenericSudokuReader {
    String filename;

    /*
    @PostConstruct
    void setUp(String filename) {
        this.filename = filename;
    }

     */

    GenericSudokuReader(String filename) {
        this.filename = filename;
    }

    public GenericSudokuReader() {

    }

    String getFilename() {
        return filename;
    }

}
