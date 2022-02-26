package sudoku;

import utils.Point;

public interface SudokuSolver {
    /**
     * To be done
     */
    boolean solve();

    /**
     * Puts digit in the box at point
     *
     * @param point coordinates where you want to insert
     * @param digit The digit to insert
     * @throws IllegalArgumentException if x, y or digit is outside the range [0,9]
     */
    void add(Point point, int digit);

    /**
     * Removes digit in box at point
     *
     * @param point the point in the board
     * @throws IllegalArgumentException if x or y is outside the range [0,9]
     */
    void remove(Point point);

    /**
     * Returns the value of the box at point. 0 if empty.
     *
     * @param point the point in the board
     * @return the value of the box at point. 0 if empty
     * @throws IllegalArgumentException if x or y is outside the range [0,9]
     */
    int get(Point point);

    /**
     * Checks that all filled in digits follows the sudoku rules.
     *
     * @return true if all boxes comply to the rules
     */
    boolean isValid();

    /**
     * Checks that given box follows the sudoku rules.
     *
     * @param point coordinates of the box
     * @return true if box at x,y complies with the rules
     */
    boolean isValid(Point point);

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
     * @param point The point
     * @return an array with all values in the region
     */
    int[] getBelongingRegion(Point point);

    /**
     * Returns an array with all the values in the row.
     *
     * @param y The column
     * @return an array with all the values in the row
     */
    int[] getBelongingRow(int y);

    /**
     * Returns an array with all the values in the column.
     *
     * @param x The row
     * @return an array with all the values in the row
     */
    int[] getBelongingColumn(int x);


}
