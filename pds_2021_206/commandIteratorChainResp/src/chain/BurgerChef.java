package ex2;

import java.util.Locale;
import java.util.Random;

public class BurgerChef implements Handler {
    private Handler next = null;
    Random rand = new Random();

    public BurgerChef() {};

    @Override
    public void handle(String str) {
        if(str.toUpperCase(Locale.ROOT).contains("BURGER")) {
            System.out.println("Burger Chef: Starting to cook "+str+". Out in "+rand.nextInt(40)+" minutes!");
        }
        else if(next != null) {
            System.out.println("Burger Chef: I can't cook that!");
            next.handle(str);
        }
        else {
            System.out.println("We're sorry but that request can't be satisfied by our service!");
        }
    }

    @Override
    public void setNext(Handler h) {
        next = h;
    }
}
