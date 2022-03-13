package Ex3;

import Ex3.startypes.*;

import java.awt.*;
import java.util.HashMap;
import static java.lang.Math.random;

public class StarFactory {

    private static final HashMap<Character,Star> createdStars=new HashMap<>();

    private static final Color BColor = new Color(230, 252, 252);
    private static final Color OColor =  new Color(225, 250, 250);
    private static final Color AColor =  new Color(230, 252, 252);
    private static final Color GColor = new Color(245, 250, 250);
    private static final Color KColor = new Color(230, 160, 10);
    private static final Color FColor =  new Color(255, 255, 245);

    private static final StarType B = new StarType(5, BColor,"This is a long description of the B type star....");
    private static final StarType O = new StarType(5, OColor,"This is a long description of the O type star....");
    private static final StarType A = new StarType(5, AColor,"This is a long description of the B type star....");
    private static final StarType G = new StarType(1, GColor,"This is a long description of the G type star....");
    private static final StarType K = new StarType(1, KColor,"This is a long description of the K type star....");
    private static final StarType M = new StarType(1, Color.RED, "This is a long description of the M type star....");
    private static final StarType F = new StarType(2, FColor, "This is a long description of the F type star....");

    public static void createStar(char type, int CANVAS_SIZE,Graphics g) {
        short x =(short)( random()*(CANVAS_SIZE+1));
        short y =(short)( random()*(CANVAS_SIZE+1));
        Star star=null;

        if(createdStars.containsKey(type)){
            createdStars.get(type).move(x,y);
             createdStars.get(type).draw(g);
        }
        else
        switch (type) {
            case 'O' :{
                star = new Star(O, x, y);
                createdStars.put(type,star);
                star.draw(g);
                break;
            }
            case 'A' :{
                star = new Star(A, x, y);
                createdStars.put(type,star);
                star.draw(g);
                break;
            }
            case 'B' :{
                star = new Star(B, x, y);
                createdStars.put(type,star);
                star.draw(g);
                break;
            }
            case 'F' :{
                star = new Star(F, x, y);
                createdStars.put(type,star);
                break;
            }
            case 'G' :{
                star = new Star(G, x, y);
                createdStars.put(type,star);
                star.draw(g);
                break;
            }
            case 'K' :{
                star = new Star(K, x, y);
                createdStars.put(type,star);
                star.draw(g);
                break;
            }
            case 'M' :{
                star = new Star(M, x, y);
                createdStars.put(type,star);
                star.draw(g);
                break;
            }
        }
    }
}
