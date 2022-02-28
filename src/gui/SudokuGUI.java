package gui;
import sudoku.SudokuSolver9x9;

import javax.swing.*;
import java.awt.*;


public class SudokuGUI {
    int regionSide = 3;
    SudokuSolver9x9 sudoku = new SudokuSolver9x9();


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
        gridPanel.setLayout(new GridLayout(9,9));
        JTextField[][] board = new JTextField[9][9];

        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[x].length; y++){
                JTextField field = new JTextField(1);
                field.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
                field.setHorizontalAlignment(JTextField.CENTER);
                if(isColoredRegion(x, y))
                    field.setBackground(Color.ORANGE);
                field.setSize(width/9,  height/9);
                int finalX = x;
                int finalY = y;

                field.addActionListener(e-> sudoku.add(finalX,finalY, Integer.parseInt(((JTextField)e.getSource()).getText()))
                );
                gridPanel.add(field);



            }
        }
        frame.setPreferredSize(new Dimension(width, height));
        frame.add(gridPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private boolean isColoredRegion(int x, int y){
        x/=3; y/=3;
        return (y*regionSide + x)%2 == 1;
    }
}
