package gui;

import sudoku.SudokuSolver;
import sudoku.SudokuSolver9x9;

import javax.swing.*;
import java.awt.*;


public class SudokuGUI {
    int regionSide = 3;
    SudokuSolver sudoku = new SudokuSolver9x9();
    public JTextField[][] textFieldBoard = new JTextField[9][9];


    public SudokuGUI(String title, int width, int height) {
        SwingUtilities.invokeLater(() -> createWindow(title, width, height));
    }

    /**
     * Private helper method, to confine all Swing-related work to
     * Swing's Event Dispatch Thread (EDT).
     */
    private void createWindow(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(9, 9));

        //create boxes -------------------------------------------------------------------------------------------------
        for (int x = 0; x < textFieldBoard.length; x++) {
            for (int y = 0; y < textFieldBoard[x].length; y++) {
                JTextField field = new JTextField(1);
                textFieldBoard[x][y] = field;
                field.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
                field.setHorizontalAlignment(JTextField.CENTER);
                if (isColoredRegion(x, y))
                    field.setBackground(Color.ORANGE);
                field.setSize(width / 9, height / 9);


                gridPanel.add(field);


            }
        }
        frame.setPreferredSize(new Dimension(width, height));
        frame.add(gridPanel, BorderLayout.CENTER);


        //Create Buttons -----------------------------------------------------------------------------------------------
        JPanel buttonPanel = new JPanel();

        //clear button
        JButton clearButton = new JButton("CLEAR");
        clearButton.addActionListener(e -> {
            sudoku.clear();
            fillBoard(sudoku.getMatrix());
        });

        //Solve button
        JButton solveButton = new JButton("SOLVE");
        solveButton.addActionListener(e -> {
            try {
                sudoku.addAll(textFieldBoard);

                if (sudoku.isValid()) {
                    if (sudoku.solve())
                        fillBoard(sudoku.getMatrix());
                    else {
                        JFrame failFrame = new JFrame("Failure");
                        JOptionPane.showMessageDialog(failFrame, "No solutions to this sudoku exists");
                    }

                } else {
                    JFrame failureFrame = new JFrame();
                    JOptionPane.showMessageDialog(failureFrame, "The inputted Sudoku is not valid");
                }

            } catch (IllegalArgumentException exception) {
                JFrame failureFrame = new JFrame();
                JOptionPane.showMessageDialog(failureFrame, "Illegal Input", "Failure", JOptionPane.WARNING_MESSAGE);
            }


        });

        buttonPanel.add(clearButton);
        buttonPanel.add(solveButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        //Displaying ---------------------------------------------------------------------------------------------------
        frame.pack();
        frame.setVisible(true);
    }

    private boolean isColoredRegion(int x, int y) {
        x /= 3;
        y /= 3;
        return (y * regionSide + x) % 2 == 1;
    }

    /*
    Fills the text fields with a given matrix m
    Empty string if a box has a 0
     */
    private void fillBoard(int[][] m) {
        for (int x = 0; x <= 8; x++) {
            for (int y = 0; y <= 8; y++) {
                if (m[x][y] != 0) {
                    textFieldBoard[x][y].setText(String.valueOf(m[x][y]));
                } else {
                    textFieldBoard[x][y].setText("");
                }

            }
        }
    }

    public void setText(int x, int y, int n) {
        textFieldBoard[x][y].setText(String.valueOf(n));
    }

    public void remove(int x, int y) {
        textFieldBoard[x][y].setText("");
    }


}
