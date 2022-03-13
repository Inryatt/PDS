package TextReader;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TextReader implements TextReaderInterface {
    private Scanner sc;

    public TextReader(String filename) {
        try{
            File f = new File(filename);
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.err.print("Could not read file.");
        }
    }

    @Override
    public boolean hasNext() {
        return sc.hasNext();
    }

    @Override
    public String next() {
        return sc.nextLine();
    }
}
