import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;


public class TicTacToe {

    private JFrame f;
    private int[][] grid;

    public TicTacToe() {
        f = new JFrame("Lets Play TicTacToe!");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //tie in button events with a deterministic "int" grid.
        grid = new int[3][3];
    }

    //start the game
    public void start() {
        setBounds();
        drawGrid();
        playGame();
    }

    public void setBounds() {
        f.setBounds(0, 0, 1500, 1000);
    }

    public void drawGrid() {
        //create new panel to add grid to
        JPanel grid = new JPanel();
        initLogicGrid();

        grid.setLayout(new GridLayout(3, 3));

        //add button listeners:
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                JButton b = new JButton();

                b.setBorder(BorderFactory.createLineBorder(Color.black, 2));

                grid.add(b);

            }
        }

        f.add(grid);

    }

    public void initLogicGrid() {
        //just fill the grid with "0"s when first initiated. Then, fill with 1's (X's) or 2's (O's.)
        //Yes, i know it is more time efficient to not do this, but it's literally a 3x3 grid i can do whatever.

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }

    }

    public void playGame() {

    }

}
