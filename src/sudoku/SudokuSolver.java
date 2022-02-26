package sudoku;

public class SudokuSolver implements Solver{

    private int[] board = new int[81];

    @Override
    public boolean solve() {
        return false;
    }

    @Override
    public void add(int row, int col, int digit) {
        if (row < 0 || row > 8){
            throw new IllegalArgumentException("illegal row input");
        }
        else if(col < 0 || col > 8 ){
            throw new IllegalArgumentException("illegal col input");
        }
        else if(digit < 0 || digit > 9 ){
            throw new IllegalArgumentException("illegal digit input");
        }

        board[coordsToBoard(row,col)] = digit;
    }

    @Override
    public void remove(int row, int col) {
        if (row < 0 || row > 8){
            throw new IllegalArgumentException("illegal row input");
        }
        else if(col < 0 || col > 8 ){
            throw new IllegalArgumentException("illegal col input");
        }

        board[coordsToBoard(row,col)] = 0;
    }

    @Override
    public int get(int row, int col) {
        if (row < 0 || row > 8){
            throw new IllegalArgumentException("illegal row input");
        }
        else if(col < 0 || col > 8 ){
            throw new IllegalArgumentException("illegal col input");
        }

        return board[coordsToBoard(row,col)];
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void clear() {
        board = new int[81];
    }

    @Override
    public void setBoard(int[] board) {

    }

    @Override
    public int[] getBoard() {
        return board;
    }

    @Override
    public int[][] getMatrix() {
        return new int[0][];
    }

    @Override
    public int coordsToBoard(int row, int col) {
        return ((9 * row) + col);
    }
}
