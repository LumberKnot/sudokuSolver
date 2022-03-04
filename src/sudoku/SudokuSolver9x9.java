package sudoku;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SudokuSolver9x9 implements SudokuSolver {

    private int[][] board = new int[9][9];

    @Override
    public boolean solve() {
        if (isValid()){
            return solve(0,0);
        }
        return false;
    }

    /*
    helper function that recursively calls itself
     */
    private boolean solve(int x, int y) {
        //base case
        if (y > 8) {
            //whole board has been filled without issues
            return true;
        }

        //normal case

        // Player input
        if (get(x,y) != 0) {
            return solve((x == 8) ? 0 : x + 1, (x == 8) ? y + 1 : y);
        }

        //else : empty box
        for (int test = 1; test <=9; test ++ ){
            add(x,y,test);
            if (isValid(x,y)) {
                if (solve((x == 8)? 0 : x + 1, (x == 8)? y + 1 : y))
                    return true;
            }
        }
        remove(x,y);
        return false;

    }

    @Override
    public void add(int x, int y, int digit) {
        if (x < 0 || x > 8) {
            throw new IllegalArgumentException("illegal x input");
        } else if (y < 0 || y > 8) {
            throw new IllegalArgumentException("illegal y input");
        } else if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("illegal digit input");
        }
        board[x][y] = digit;
    }

    @Override
    public void add(int x, int y, @NotNull String digit) {
        if(digit.equals("")) {
            add(x, y, 0);
        }
        else
            try {
                add(x,y,Integer.parseInt(digit));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Input is not a number");
            }
    }

    @Override
    public void addAll(JTextField[][] textFields) {
        for(int x = 0; x < board.length;x++)
            for(int y = 0; y < board[x].length;y++)
                add(x,y, textFields[x][y].getText());
    }

    @Override
    public void remove(int x, int y) {
        if (x < 0 || x > 8) {
            throw new IllegalArgumentException("illegal x input");
        } else if (y < 0 || y > 8) {
            throw new IllegalArgumentException("illegal y input");
        }
        board[x][y] = 0;
    }

    @Override
    public int get(int x, int y) {
        if (x < 0 || x > 8) {
            throw new IllegalArgumentException("illegal row input");
        } else if (y < 0 || y > 8) {
            throw new IllegalArgumentException("illegal col input");
        }

        return board[x][y];
    }

    @Override
    public boolean isValid() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (!isValid(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isValid(int x, int y) {
        if (x < 0 || x > 8) {
            throw new IllegalArgumentException("illegal x input");
        } else if (y < 0 || y > 8) {
            throw new IllegalArgumentException("illegal y input");
        }

        if (board[x][y] == 0) return true;

        //row
        int[] row = getBelongingRow(y);
        if (twoOccurrences(row, board[x][y])) return false;

        //column
        int[] col = getBelongingColumn(x);
        if (twoOccurrences(col, board[x][y])) return false;

        //region
        int[] region = getBelongingRegion(x, y);
        return (!twoOccurrences(region, board[x][y]));


    }

    @Override
    public void clear() {
        board = new int[9][9];
    }

    @Override
    public void setMatrix(int[][] m) {
        //Check size-------------------
        if(m.length != 9){
            throw new IllegalArgumentException("Matrix has incorrect amount of columns");
        }
        for (int[] ar: m) {
            if (ar.length != 9) throw new IllegalArgumentException("Matrix has incorrect amount of rows");
        }

        //checks content
        for(int[] ar : m){
            for(int i : ar){
                if (i < 0 || i > 9) {
                    throw new IllegalArgumentException("Matrix has incorrect values");}
            }
        }

        board = m;
    }

    @Override
    public int[][] getMatrix() {
        return board;
    }

    @Override
    public int[] getBelongingRegion(int x, int y) {
        if (x < 0 || x > 8) {
            throw new IllegalArgumentException("illegal x input");
        } else if (y < 0 || y > 8) {
            throw new IllegalArgumentException("illegal y input");
        }

        int[] region = new int[9];

        //coordinates of the first square in the region
        int firstBoxX = (x / 3) * 3;
        int firstBoxY = (y / 3) * 3;

        //loops through the
        for (int xPos = 0; xPos < 3; xPos++) {
            for (int yPos = 0; yPos < 3; yPos++) {
                region[yPos * 3 + xPos] = board[xPos + firstBoxX][yPos + firstBoxY];
            }
        }

        return region;
    }

    @Override
    public int[] getBelongingRow(int y) {
        if (y < 0 || y > 8) {
            throw new IllegalArgumentException("illegal y input");
        }
        int[] row = new int[9];

        for (int x = 0; x < 9; x++) {
            row[x] = board[x][y];
        }
        return row;
    }

    @Override
    public int[] getBelongingColumn(int x) {
        if (x < 0 || x > 8) {
            throw new IllegalArgumentException("illegal x input");
        }

        return board[x];
    }

    /**
     * Checks if there are 2 or more occurrences of testCase in array.
     *
     * @param array    array that is tested
     * @param testCase value searched for
     * @return true if there are 2 or more occurrences of testCase
     */
    private boolean twoOccurrences(int[] array, int testCase) {
        int oc = 0;
        for (int j : array) {
            if (j == testCase) {
                oc++;
            }
        }
        return (oc >= 2);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x <= 8; x++) {
            for (int y = 0; y <= 8; y++) {
                builder.append(get(x,y));builder.append(" ");
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}

