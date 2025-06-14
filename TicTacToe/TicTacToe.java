import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

    private JFrame f;
    private int[][] grid;
    private boolean isTurnP1;
    private JButton[][] buttonGrid;
    private JLabel turn;
    private boolean gameOver;

    public TicTacToe() {
        f = new JFrame("Lets Play TicTacToe!");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //tie in button events with a deterministic "int" grid.
        grid = new int[3][3];
        buttonGrid = new JButton[3][3];

        isTurnP1 = true;
        gameOver = false;
        turn = new JLabel("Player 1's turn");
        turn.setHorizontalAlignment(SwingConstants.CENTER);
        turn.setFont(new Font(Font.SERIF, Font.BOLD, 30));
    }

    //start the game
    public void start() {
        setBounds();
        drawGrid();
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
                buttonGrid[i][j] = new JButton();

                buttonGrid[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
                buttonGrid[i][j].addActionListener(this);

                grid.add(buttonGrid[i][j]);

            }
        }

        f.add(grid);
        f.add(turn, BorderLayout.SOUTH);

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

    public boolean notFull(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    return true;
                } 
            }
        }

        return false;
    }

    public void actionPerformed(ActionEvent e) {

        //loop over button grid, find the pressed button
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[i].length; j++) {
                if (e.getSource().equals(buttonGrid[i][j])){

                    //if game is over, stop:
                    if (gameOver) {
                        return;
                    }

                    if (isTurnP1) {
                        buttonGrid[i][j].setForeground(Color.blue);
                        buttonGrid[i][j].setFont(new Font(Font.SERIF, Font.BOLD, 100));
                        buttonGrid[i][j].setText("X");

                        //assign grid 1 for X
                        grid[i][j] = 1;
                        isTurnP1 = !isTurnP1;
                        turn.setText("Player 2's turn");


                    }
                    else {
                        buttonGrid[i][j].setForeground(Color.red);
                        buttonGrid[i][j].setFont(new Font(Font.SERIF, Font.BOLD, 100));
                        buttonGrid[i][j].setText("O");

                        //assign grid 2 for O
                        grid[i][j] = 2;
                        isTurnP1 = !isTurnP1;
                        turn.setText("Player 1's turn");

                    }

                    //check for win/tie condition here

                    //vertical
                    checkVertical();

                    //horizontal
                    checkHorizontal();

                    //diag
                    checkDiagonal();

                    //tie
                    checkTie();

                    //quick exit to save time
                    return;
                }
            }
        }
        
    }

    //hardcoding 8 checks, since this is a 3x3 board (3 horizontal, 3 vertical, 2 diagonal)

    public void checkVertical() {
        if ((grid[0][0] != 0) && (grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0])) {
            gameOver = true;
            drawLine(buttonGrid[0][0], buttonGrid[2][0], grid[0][0]);
            restartPrompt(grid[0][0]);
        }
        if ((grid[0][1] != 0) && (grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1])) {
            gameOver = true;
            drawLine(buttonGrid[0][1], buttonGrid[2][1], grid[0][1]);
            restartPrompt(grid[0][1]);
        }
        if ((grid[0][2] != 0) && (grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2])) {
            gameOver = true;
            drawLine(buttonGrid[0][2], buttonGrid[2][2], grid[0][2]);
            restartPrompt(grid[0][2]);
        }
    }

    public void checkHorizontal() {
        if ((grid[0][0] != 0) && (grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2])) {
            gameOver = true;
            drawLine(buttonGrid[0][0], buttonGrid[0][2], grid[0][0]);
            restartPrompt(grid[0][0]);

        }
        if ((grid[1][0] != 0) && (grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2])) {
            gameOver = true;
            drawLine(buttonGrid[1][0], buttonGrid[1][2], grid[1][0]);
            restartPrompt(grid[1][0]);

        }
        if ((grid[2][0] != 0) && (grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2])) {
            gameOver = true;
            drawLine(buttonGrid[2][0], buttonGrid[2][2], grid[2][0]);
            restartPrompt(grid[2][0]);

        }
    }

    public void checkDiagonal() {
        if ((grid[0][0] != 0) && (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])) {
            gameOver = true;
            drawLine(buttonGrid[0][0], buttonGrid[2][2], grid[1][1]);
            restartPrompt(grid[1][1]);

        }
        if ((grid[0][2] != 0) && (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])) {
            gameOver = true;
            drawLine(buttonGrid[0][2], buttonGrid[2][0], grid[1][1]);
            restartPrompt(grid[1][1]);

        }
    }

    public void checkTie() {
        if (notFull(grid) == false && gameOver == false) {
            gameOver = true;
            restartPrompt(0);
        }
    }

    public void restartPrompt(int result) {
        int choice;

        if (result == 0) {
            //there was a draw, restart?
            choice = JOptionPane.showConfirmDialog(f, "There was a tie. Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        }
        else if (result == 1) {
            //player 1 won, restart?
            choice = JOptionPane.showConfirmDialog(f, "Player " + result + "(X's) won. Play again?", "Game Over", JOptionPane.YES_NO_OPTION);

        }
        else {
            //player 2 won, restart?
            choice = JOptionPane.showConfirmDialog(f, "Player " + result + "(O's) won. Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        }

        if (choice == JOptionPane.YES_OPTION){
            reset();
        }
        else {
            f.dispose();
        }
    }

    //draws a line at victory
    //!
    public void drawLine(JButton a, JButton b, int winner){
        // 1) Compute each button’s center in glass‐pane coordinates:
        Point centerA = SwingUtilities.convertPoint(
            a, a.getWidth()/2, a.getHeight()/2, f.getGlassPane()
        );
        Point centerB = SwingUtilities.convertPoint(
            b, b.getWidth()/2, b.getHeight()/2, f.getGlassPane()
        );

        // 2) Create a one‐off transparent component whose paintComponent draws the line:
        JComponent lineOverlay = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setStroke(new BasicStroke(10f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                if (winner == 1) {
                    g2.setColor(Color.BLUE);
                }
                else {
                    g2.setColor(Color.RED);
                }
                
                g2.drawLine(centerA.x, centerA.y, centerB.x, centerB.y);
                g2.dispose();
            }
        };

        // 3) Make it transparent and size it to cover the entire frame:
        lineOverlay.setOpaque(false);
        lineOverlay.setBounds(0, 0, f.getWidth(), f.getHeight());

        // 4) Add it onto the glass pane (which is invisible by default):
        JComponent glass = (JComponent) f.getGlassPane();
        glass.setLayout(null);
        glass.add(lineOverlay);
        glass.setVisible(true);
        glass.repaint();
    }
    //!



    //reset button
    public void reset() {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }

        for (JButton[] b: buttonGrid) {
            for (JButton button: b) {
                button.setText("");
            }
        }

        //!
        JComponent glass = (JComponent) f.getGlassPane();
        glass.removeAll();
        glass.setVisible(false);
        glass.repaint();
        //!


        isTurnP1 = true;
        gameOver = false;
    }

}
