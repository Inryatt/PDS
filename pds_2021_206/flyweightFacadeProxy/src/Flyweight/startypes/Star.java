package Ex3.startypes;
import java.awt.*;

public class Star {

    private short x,y;
    private StarType type;

    public Star(StarType type, short x, short y) {
        this.x = x;
        this.y = y;
        this.type=type;
    }

    public void move(short x, short y){
        this.x=x;
        this.y=y;
        
    }

    public void draw(Graphics g){
        this.type.draw(g, this.x, this.y); //is dumb, works :)
    }
    
}
