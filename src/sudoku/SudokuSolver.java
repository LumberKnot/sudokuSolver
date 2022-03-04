package sudoku;

import javax.swing.*;

public interface SudokuSolver {
    /**
     * Solves the board if the input is valid.
     *
     * @return true if the board is solvable
     */
    boolean solve();

    /**
     * Puts digit in the box at point.
     *
     * @param x     The row
     * @param y     The column
     * @param digit The digit to insert
     * @throws IllegalArgumentException if x, y or digit is outside the range [0,9]
     */
    void add(int x, int y, int digit);

    /**
     * Puts digit in the box at point.
     *
     * @param x     The row
     * @param y     The column
     * @param digit String representing the digit to insert
     * @throws IllegalArgumentException if x, y or digit is outside the range [0,9], or if digit can't be parsed to int
     */
    void add(int x, int y, String digit);

    /**
     * Adds all numbers from a JTextField matrix.
     *
     * @param m The input matrix
     * @throws IllegalArgumentException if a field contains a number not in [1,9] or a string that can't be parsed to int
     */
    void addAll(JTextField[][] m);

    /**
     * Removes digit in box at point.
     *
     * @param x The row
     * @param y The column
     * @throws IllegalArgumentException if x or y is outside the range [0,8]
     */
    void remove(int x, int y);

    /**
     * Returns the value of the box at point. 0 if empty.
     *
     * @param x The row
     * @param y The column
     * @return the value of the box at point. 0 if empty
     * @throws IllegalArgumentException if x or y is outside the range [0,8]
     */
    int get(int x, int y);

    /**
     * Checks that all filled in digits follows the sudoku rules.
     *
     * @return true if all boxes comply to the rules
     */
    boolean isValid();

    /**
     * Checks that given box follows the sudoku rules.
     *
     * @param x The row
     * @param y The column
     * @return true if box at x,y complies with the rules
     * @throws IllegalArgumentException if x or y is outside the range [0,8]
     */
    boolean isValid(int x, int y);

    /**
     * Sets all boxes to 0
     */
    void clear();

    /**
     * Fills the grid with the digits in m. The digit 0 represents an empty box.
     *
     * @param m the matrix with the digits to insert
     * @throws IllegalArgumentException if board has the wrong size or contains values outside the range [0,9]
     */
    void setMatrix(int[][] m);

    /**
     * Returns the board as a matrix.
     *
     * @return the board as a matrix
     */
    int[][] getMatrix();

    /**
     * Returns an array with all values in the region.
     *
     * @param x The row
     * @param y The column
     * @return an array with all values in the region
     * @throws IllegalArgumentException if x or y is outside the range [0,8]
     */
    int[] getRegion(int x, int y);

    /**
     * Returns an array with all the values in the row.
     *
     * @param y The column
     * @return an array with all the values in the row
     * @throws IllegalArgumentException if y is outside the range [0,8]
     */
    int[] getRow(int y);

    /**
     * Returns an array with all the values in the column.
     *
     * @param x The row
     * @return an array with all the values in the row
     * @throws IllegalArgumentException if x is outside the range [0,8]
     */
    int[] getColumn(int x);


}
