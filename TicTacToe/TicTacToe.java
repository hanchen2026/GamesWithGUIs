import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.*;


public class TicTacToe {

    private JFrame f;


    public TicTacToe() {
        f = new JFrame("Lets Play TicTacToe!");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        grid.setLayout(new GridLayout(3, 3));
        grid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //add button listeners:
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                grid.addMouseListener(new MouseListener() ) {
                    
                }
            }
        }


        f.add(grid);
    }

    public void playGame() {

    }

}
