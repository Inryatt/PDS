package Ex3.startypes;

import java.awt.*;

public class StarType {
    private int size;
    private Color color;
    protected String description;
    protected Float[] physicalProperties;

    public StarType( int size, Color color,  String description){
        this.physicalProperties=new Float[10];              //Not sure if they're supposed to be the same across all stars or randomized?
        this.color=color;
        this.size=size;
        this.description=description;
    }


    public void draw(Graphics g,short x,short y) {
        g.setColor(color);
        g.fillOval(x, y , size, size);
    }

    public Color getColor(){return color;}
}
