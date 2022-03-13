package Ex1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] Args){

        BogoSort bestSort = new BogoSort();
        MiracleSort alsoAGoodSort = new MiracleSort();
        PanicSort doesTheJobSort = new PanicSort();
        ArrayList<String> toSort = new ArrayList<>();
        toSort.add("An item");
        toSort.add("Another item");
        toSort.add("Something goes here");
        toSort.add("Oh look, another one");
        toSort.add("Where are these coming from??");

        System.out.println("Which sorting mechanism should we use?");
        System.out.println("0-BogoSort");
        System.out.println("1-MiracleSort");
        System.out.println("2-PanicSort");

        Scanner sc = new Scanner(System.in);

        switch(sc.nextLine()){
            case("0"):{
                System.out.println(bestSort.sort(toSort));
                break;
            }
            case("1"):{
                System.out.println(alsoAGoodSort.sort(toSort));
                break;
            }
            case("2"):{
                System.out.println(doesTheJobSort.sort(toSort));
                break;
            }
        }
    }
}
