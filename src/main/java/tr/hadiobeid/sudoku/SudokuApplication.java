package tr.hadiobeid.sudoku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tr.hadiobeid.sudoku.grid.SudokuGrid;
import tr.hadiobeid.sudoku.reader.SudokuGridReader;

@SpringBootApplication
public class SudokuApplication {


    public SudokuApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(SudokuApplication.class, args);
    }

}
