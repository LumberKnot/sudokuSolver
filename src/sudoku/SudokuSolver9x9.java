package sudoku;

public class SudokuSolver9x9 implements SudokuSolver {

    private int[][] board = new int[9][9];

    @Override
    public boolean solve() {
        return false;
    }

    @Override
    public void add(int x, int y, int digit) {
        if (x < 0 || x > 8) {
            throw new IllegalArgumentException("illegal row input");
        } else if (y < 0 || y > 8) {
            throw new IllegalArgumentException("illegal col input");
        } else if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("illegal digit input");
        }

        board[x][y] = digit;
    }

    @Override
    public void remove(int x, int y) {
        if (x < 0 || x > 8) {
            throw new IllegalArgumentException("illegal row input");
        } else if (y < 0 || y > 8) {
            throw new IllegalArgumentException("illegal col input");
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
        if (board[x][y] == 0) return true;

        //row
        int[] row = getBelongingRow(y);
        if (twoOccurrences(row, board[x][y])) return false;

        //column
        int[] col = getBelongingColumn(x);
        if (twoOccurrences(col, board[x][y])) return false;

        //region
        int[] region = getBelongingRegion(x,y);
        if (twoOccurrences(region, board[x][y])) return false;

        return true;

    }


    @Override
    public void clear() {
        board = new int[9][9];
    }

    @Override
    public void setMatrix(int[][] m) {
        board = m;
    }


    @Override
    public int[][] getMatrix() {
        return board;
    }

    @Override
    public int[] getBelongingRegion(int x, int y) {
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
        int[] row = new int[9];

        for (int x = 0; x < 9; x++) {
            row[x] = board[x][y];
        }
        return row;
    }

    @Override
    public int[] getBelongingColumn(int x) {
        return board[x];
    }


    /**
     * Checks if there are 2 or more occurrences of testCase in array.
     *
     * @param array array that is tested
     * @param testCase value searched for
     * @return true if there are 2 or more occurrences of testCase
     */
    public boolean twoOccurrences(int[] array, int testCase) {
        int oc = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == testCase) {
                oc++;
            }
        }
        return (oc >= 2);
    }
}

