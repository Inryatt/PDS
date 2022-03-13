import javax.swing.text.AttributeSet;
import java.io.*;
import java.util.*;

public class WSGenerator {
    static List<String> wordList = new ArrayList<>();
    static char[][] grid;
    static String[] directions = {"UpLeft", "Up", "UpRight", "Right", "DownRight", "Down", "DownLeft", "Left"};

    public static void main(String[] args) throws IOException {
        String fname = "";
        String fout = "out1_1.txt";
        int numOfArgs = 0;
        int size = 0;
        boolean flagI = false;
        boolean flagS = false;
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-i")){
                fname = args[i + 1];
                flagI = true;
                numOfArgs += 2;
            }
            if(args[i].equals("-s")){
                size = Integer.parseInt(args[i + 1]);
                flagS = true;
                numOfArgs += 2;
            }

            if(args[i].equals("-o")){
                fout = args[i + 1];
                numOfArgs += 2;
            }
        }

        if(!flagI || !flagS || numOfArgs != args.length){
            System.err.println("Mandatory arguments are not met");
            System.exit(1);
        }
        
        getWordsFromFile(fname);
        grid = generatePuzzle(size);
        saveToFile(fout);


    }

    /**
     * prints to the file the generated word puzzle and it's words
     * @param nameF -> file name
     * @throws IOException
     */
    private static void saveToFile(String nameF) throws IOException{

        PrintWriter pw = new PrintWriter(new File(nameF));
        for(int i = 0; i < grid.length; i++){
            for(char c : grid[i]){
                pw.print(c);
            }
            pw.println();
        }
        for(String word : wordList){
            pw.print(word + ";");
        }
        pw.close();

    }

    /**generates a puzzle based on a random direction and a random position (x, y)
     *
     * @param size
     * @return
     */
    private static char[][] generatePuzzle(int size) { // procura para sempre
        grid = new char[size][size];
        for(String word: wordList) {
            int randWord = 0;
            String direction = "";
            int xpos = 0;
            int ypos = 0;
            do{
                randWord = randomRange(0, direction.length()-1); // originalmente não tinha o -1, ficava out of bounds
                direction = directions[randWord];
                xpos = randomRange(0, size);
                ypos = randomRange(0, size);
            }while(!isValidPosition(word, xpos, ypos, direction));

            addToPuzzle(word, xpos, ypos, direction);
        }

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid[i][j] == '\u0000')
                    grid[i][j] = (char) (randomRange(65,95)); // 65 -> ascii code for capital 'A' and 95 for capital 'Z'

            }
        }


        return grid;
    }

    /**
     *inserts the correspondent letters of a word in the starting valid position (x, y) following a direction
     * @param word
     * @param xpos
     * @param ypos
     * @param direction
     */
    private static void addToPuzzle(String word, int xpos, int ypos, String direction) {
        int len = word.length();
        word = word.toUpperCase();
        grid[xpos][ypos] = word.charAt(0);
        int width = grid.length;
        switch (direction){
            case "UpLeft":
                for (int i = 1; i < len; i++) {
                    grid[xpos - i][ypos - i] = word.charAt(i);
                }
                break;
            case "Up":

                for (int i = 1; i < len; i++) {
                    grid[xpos - i][ypos] = word.charAt(i);
                }

                break;
            case "UpRight":

                for(int i = 1; i < len; i++) {
                    grid[xpos - i][ypos + i] = word.charAt(i);
                }

                break;
            case "Right":

                for (int i = 1; i < len; i++) {
                    grid[xpos][ypos + i] = word.charAt(i);

                }

                break;
            case "DownRight":

                for (int i = 1; i < len; i++)
                    grid[xpos + i][ypos + i] = word.charAt(i) ;


                break;
            case "Down":

                for (int i = 1; i < len ; i++)
                    grid[xpos + i][ypos] = word.charAt(i);


                break;
            case "DownLeft":

                for (int i = 1; i < len ; i++)
                    grid[xpos + i][ypos - i] = word.charAt(i);

                break;
            case "Left":

                for (int i = 1; i < len; i++)
                    grid[xpos][ypos - i] = word.charAt(i);


                break;
        }
    }

    /**
     * Checks if a given position (x, y) and a given direction is valid to include on the puzzle
     * @param word
     * @param x -> x position
     * @param y -> y position
     * @param direction
     * @return
     */
    private static boolean isValidPosition(String word, int x, int y, String direction) {
        int len = word.length();
        int width = grid.length;
        switch (direction){
            case "UpLeft":
                if(x+1 - len >= 0 && y+1 - len >= 0){
                    for (int i = 1; i < len; i++) {
                        if (grid[x - i][y - i] != '\u0000')
                            return false;
                    }
                }
                else
                    return false;
                break;
            case "Up":
                if(x+1 - len >= 0){
                    for (int i = 1; i < len; i++) {
                        if (grid[x - i][y] != '\u0000')
                            return false;
                    }
                }
                else
                    return false;

                break;
            case "UpRight":
                if(x+1 - len >= 0 && y + len <= width){
                    for(int i = 1; i < len; i++) {
                        if (grid[x - i][y + i] != '\u0000')
                            return false;
                    }
                }
                else
                    return false;

                break;
            case "Right":
                if (y + len <= width){
                    for (int i = 1; i < len; i++) {
                        if (grid[x][y + i] != '\u0000')
                            return false;
                    }
                }
                else
                    return false;
                break;
            case "DownRight":
                if (x + len <= width && y + len <= width){
                    for (int i = 1; i < len; i++) {
                        if (grid[x + i][y + i] != '\u0000')
                            return false;
                    }
                }
                else
                    return false;
                break;
            case "Down":
                if (x + len <= width){
                    for (int i = 1; i < len ; i++) {
                        if (grid[x + i][y] != '\u0000')
                            return false;
                    }
                }
                else
                    return false;
                break;
            case "DownLeft":
                if(x + len <= width && y+1 - len >= 0){
                    for (int i = 1; i < len ; i++) {
                        if (grid[x + i][y - i] != '\u0000')
                            return false;
                    }

                }
                else
                    return false;
                break;
            case "Left":
                if (y+1 - len >= 0){
                    for (int i = 1; i < len; i++) {
                        if (grid[x][y - i] != '\u0000')
                            return false;
                    }
                }
                else
                    return false;
                break;
        }

        return true;
    }

    /**
     *Adds to the list of words the words saved in the given file
     * @param fname
     * @throws IOException
     */
    private static void getWordsFromFile(String fname) throws IOException{
        File fin = new File(fname);
        Scanner rf = new Scanner(fin);

        while(rf.hasNextLine()){
            String[] lines = rf.nextLine().split("\\s|;|,");
            for(String l : lines)
                wordList.add(l);

        }
        rf.close();
    }

    /**
     *
     * @param min
     * @param max
     * @return random integer in [min, max[
     */
    private static int randomRange(int min, int max){
        return (int)(Math.random() * (max - min) + min);
    }
}
