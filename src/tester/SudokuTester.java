package tester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import sudoku.SudokuSolver9x9;

public class SudokuTester {
    SudokuSolver9x9 sudoku;

    @BeforeEach
    public void setup(){
        sudoku = new SudokuSolver9x9();
    }

    @Test
    public void testClear(){
        sudoku.setMatrix(new int[][]{{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1}});
        sudoku.clear();

        for (int i = 0; i < 9; i++) {
            assertArrayEquals(new int[9], sudoku.getMatrix()[i], "Clear does not work");
        }
    }

    @Test
    public void testAddGetRemove(){
        sudoku.add(5,7, "5");
        assertEquals(5, sudoku.get(5,7), "Wrong get");
        sudoku.remove(5,7);
        assertEquals(0,sudoku.get(5,7),"Remove does not work");

        assertThrows(IllegalArgumentException.class ,() -> sudoku.add(1,7,10), "Incorrect exception/ no exception");
        assertThrows(IllegalArgumentException.class ,() -> sudoku.add(9,7,1),  "Incorrect exception/ no exception");
        assertThrows(IllegalArgumentException.class ,() -> sudoku.add(1,9,1),  "Incorrect exception/ no exception");
    }

    @Test
    public void testIsValid(){
        sudoku.add(5,5,9);
        sudoku.add(5,6,9);

        assertFalse(sudoku.isValid(), "Whole board valid doesn't work");
        assertFalse(sudoku.isValid(5,6), "Single cell valid doest work");

        sudoku.clear();

        sudoku.add(0,0,9);
        sudoku.add(1,1,9);

        assertFalse(sudoku.isValid(), "Region doesn't work");
        assertFalse(sudoku.isValid(5,6), "Region doesn't work");

        sudoku.clear();

        sudoku.add(0,0,9);
        sudoku.add(1,1,8);

        assertTrue(sudoku.isValid(), "Region doesn't work");

    }






}
