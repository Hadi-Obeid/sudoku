package tr.hadiobeid.sudoku;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import tr.hadiobeid.sudoku.reader.SudokuGridReaderFactory;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SudokuApplicationTests {

    @Test
    void contextLoads() {
    }

}
