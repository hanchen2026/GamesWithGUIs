import java.awt.*;
import javax.swing.JFrame;


public class TicTacToe {

    private JFrame f;


    public TicTacToe() {
        f = new JFrame();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //start the game
    public void start() {
        setBounds();
        drawGrid();
        playGame();
    }

}
