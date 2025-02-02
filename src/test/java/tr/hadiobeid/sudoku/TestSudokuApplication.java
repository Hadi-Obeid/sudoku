package tr.hadiobeid.sudoku;

import org.springframework.boot.SpringApplication;

public class TestSudokuApplication {

    public static void main(String[] args) {
        SpringApplication.from(SudokuApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
