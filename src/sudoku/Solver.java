package sudoku;

public interface Solver {
    /**
     * To be done
     */
    boolean solve();

    /**
     * Puts digit in the box row, col.
     *
     * @param row   The row
     * @param col   The column
     * @param digit The digit to insert in box row, col
     * @throws IllegalArgumentException if row, col or digit is outside the range [0,9]
     */
    void add(int row, int col, int digit);

    /**
     * Removes digit in box at row, col.
     * @param row The row
     * @param col Te column
     * @throws IllegalArgumentException if row or col is outside the range [0,9]
     */
    void remove(int row, int col);

    /**
     * Returns the value of the box at row, col. 0 if empty.
     * @param row The row
     * @param col The column
     * @return the value of the box at row, col. 0 if empty
     * @throws IllegalArgumentException if row or col is outside the range [0,9]
     */
    int get(int row, int col);

    /**
     * Checks that all filled in digits follows the the sudoku rules.
     */
    boolean isValid();

    /**
     * Sets all boxes to 0
     */
    void clear();

    /**
     * Fills the grid with the digits in board. The digit 0 represents an empty box.
     * @param board the vector with the digits to insert
     * @throws IllegalArgumentException if board has the wrong size or contains values outside the range [0,9]
     */
    void setBoard(int[] board);

    /**
     * Returns the board as a vector with 81 integers.
     * @return the board as a vector with 81 integers
     */
    int[] getBoard();

    /**
     * Returns the board as a 9X9 matrix.
     * @return the board as a 9X9 matrix
     */
    int[][] getMatrix();

    /**
     * Retuns the position on the board corresponding to the row , col input.
     * @param row The row
     * @param col The column
     * @return the position on the board corresponding to the row , col input
     */
    int coordsToBoard(int row, int col);

}
