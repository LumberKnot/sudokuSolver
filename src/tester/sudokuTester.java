package tester;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import sudoku.SudokuSolver;
import sudoku.SudokuSolver9x9;

public class sudokuTester {
    SudokuSolver sudoku;

    @BeforeEach
    public void setup(){
        sudoku = new SudokuSolver9x9();
    }

    @AfterEach
    public void tearDown(){
        sudoku = null;
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

        sudoku.clear();

        sudoku.add(0,0,9);
        sudoku.add(1,1,8);

        assertTrue(sudoku.isValid(), "Region doesn't work");


    }

    @Test
    public void emptySolve(){
        assertTrue(sudoku.solve(), "Can't solve empty");
        assertTrue(sudoku.isValid());

        int[][] board = sudoku.getMatrix();

        for (int[] ar: board) {
            for(int i : ar){
                assertNotEquals(0, i, "ZERO IN SUDOKU!");
            }
        }

    }

    @Test
    public void solveGiven(){

        int[][] givenBoard = new int[][]{
                {0,0,1,0,0,6,4,8,0},
                {0,0,0,0,5,0,1,6,0},
                {8,0,2,0,0,0,0,0,0},
                {0,0,5,2,0,0,6,0,0},
                {0,0,0,1,0,0,0,3,0},
                {9,0,0,0,0,0,8,0,0},
                {0,0,0,0,6,0,0,1,4},
                {6,0,0,9,0,2,0,0,0},
                {2,5,0,0,0,8,0,0,0}
        };

        int[][] ansBoard = new int[][]{
                {5,3,1,7,2,6,4,8,9},
                {4,7,9,8,5,3,1,6,2},
                {8,6,2,4,9,1,5,7,3},
                {1,8,5,2,3,9,6,4,7},
                {7,2,6,1,8,4,9,3,5},
                {9,4,3,6,7,5,8,2,1},
                {3,9,8,5,6,7,2,1,4},
                {6,1,7,9,4,2,3,5,8},
                {2,5,4,3,1,8,7,9,6}
        };

        //safety-----------------------------------------------------
        SudokuSolver9x9 safetySudoku = new SudokuSolver9x9();
        safetySudoku.setMatrix(ansBoard);
        assertTrue(safetySudoku.isValid(), "Incorrectly hardcoded answer");

        //Actual test-----------------------------------------------
        sudoku.setMatrix(givenBoard);

        assertTrue(sudoku.solve(), "incorrectly written input");

        int[][] result = sudoku.getMatrix();

        for (int i = 0; i <= 8; i++) {
            assertArrayEquals(ansBoard[i],result[i], "does not match");
        }

    }






}
