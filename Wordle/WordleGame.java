import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class WordleGame {

    private JFrame f;
    private ArrayList<String> validWordList;
    private ArrayList<String> actualValidList;


    public WordleGame() {
        f = new JFrame("Welcome to Wordle -- Made by Han Chen");
        validWordList = new ArrayList<>();
        actualValidList = new ArrayList<>();
    }

    public void start() {
        initUI();
        readFiles();
    }

    public void initUI() {
        f.setBounds(0,0, 1600, 1300);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //this method reads two files and adds them to an arraylist. Not as fast as hashmap, but it should be fine.
    public void readFiles() {
        try {
            BufferedReader brValid = new BufferedReader(new InputStreamReader(new FileInputStream("validwords.txt")));
            BufferedReader brActual = new BufferedReader(new InputStreamReader(new FileInputStream("wordlist.txt")));

            String wordsValid = brValid.readLine();
            while(wordsValid != null) {
                validWordList.add(wordsValid);
                wordsValid = brValid.readLine();
            }
            brValid.close();

            String wordsActual = brActual.readLine();
            while(wordsActual != null) {
                actualValidList.add(wordsActual);
                wordsActual = brActual.readLine();
            }
            brActual.close();

        }
        catch (IOException e) {
            System.out.println("Something went wrong when trying to read files");
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Trying to access an index out of bounds");
        }
        catch (Exception e) {
            System.out.println("Something else went wrong");
        }
    }



}
