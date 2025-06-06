import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WordleGame {

    private JFrame f;
    private ArrayList<String> validWordList;
    private ArrayList<String> actualValidList;
    private String[][] guessArr;
    private static int count;


    public WordleGame() {
        f = new JFrame("Welcome to Wordle -- Made by Han Chen");
        validWordList = new ArrayList<>();
        actualValidList = new ArrayList<>();
        guessArr = new String[6][5];
        count = 0;
    }

    public void start() {
        initUI();
        readFiles();
        testWordle();
    }

    public void initUI() {
        f.setBounds(0,0, 1600, 1300);
        //work on gui later. set this to true once game logic works.
        f.setVisible(false);
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
        catch (IndexOutOfBoundsException e){
            System.out.println("Trying to access an index out of bounds");
        }
        catch (Exception e) {
            System.out.println("Something else went wrong");
        }
    }

    //testing wordle without a gui
    public void testWordle() {
        Boolean correct = false;
        Random rand = new Random();
        String ans = actualValidList.get(rand.nextInt(actualValidList.size() - 1));

        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < guessArr.length; i++) {

            String guess = scan.nextLine();

            //prompt to ensure valid guess
            while (guess.length() != 5 || !validWordList.contains(guess.toLowerCase())) {
                if (guess.length()!= 5) {
                    System.out.println("Your guess has to be 5 letters to play wordle. Try again.");
                }
                else {
                    System.out.println("\'" + guess + "\' is not a valid word. Try again. ");
                }

                guess = scan.nextLine();
            }
            checkWordle(guess, ans, correct);
        
            if (correct) {
                System.out.println("The answer was: " + ans + ". Great job guessing right!");
                return;
            } 

        }

        scan.close();
        System.out.println("The answer was: " + ans);

    }

    //verifying wordle logic:
    public void checkWordle(String guess, String ans, boolean didGuessRight) {
        //if exactly matches, 
        ans = "route";
        if (guess.equals(ans)) {
            didGuessRight = true;

            return;
        }

        //'⬜'
        //'🟨'
        //'🟩'
        String copy = ans.toLowerCase();
        System.out.println("Answer is: " + copy);


        for (int i = 0; i < ans.length(); i++) {
            char Gch = guess.charAt(i);
            System.out.println("Gch is: " + Gch);
            for (int j = 0; j < ans.length(); j++) {
                char Ach = copy.charAt(j);
                System.out.println("Ach is: " + Ach);
                if (!ans.contains(Gch+"")) {
                    guessArr[count][i] = "⬜w";
                    System.out.println("Not found in word.");
                    break;
                }

                if (Gch == Ach && i == j) {
                    guessArr[count][i] = "🟩g";
                    copy.replaceFirst(Ach+"", "#");
                    System.out.println("Found Directly");

                    break;
                }

                if (Ach == Gch) {
                    guessArr[count][i] = "🟨y";
                    copy.replaceFirst(Ach+"", "#");
                    System.out.println("Found yellow");
                    break;
                }

            }
        }

        count++;
        printArr();
    }

    public void printArr() {
        boolean allNull = true;
        for (int i = 0; i < guessArr.length; i++) {
            System.out.println(Arrays.toString(guessArr[i]));
        }
    }

}
