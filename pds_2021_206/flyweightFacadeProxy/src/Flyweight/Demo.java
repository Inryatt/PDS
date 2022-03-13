package Ex3;
import java.awt.*;

import Ex3.startypes.*;

public class Demo {
    static short CANVAS_SIZE = 1200;
    static int STARS_TO_DRAW = 1000000;
    
    public static void main(String[] args) {
        Sky sky = new Sky();

        // https://astrobackyard.com/wp-content/uploads/2020/03/types-of-stars.jpg
        char[] starTypes = {'O', 'B', 'A', 'F', 'G', 'K', 'M'};
        char type;

		Runtime runtime = Runtime.getRuntime();
		long before = runtime.totalMemory() - runtime.freeMemory();
        sky.setSize(CANVAS_SIZE, CANVAS_SIZE);
        sky.setBackground(Color.BLACK);
        sky.setVisible(true);
        Graphics c = sky.getGraphics();

        for (int i = 0; i < STARS_TO_DRAW; i++) {
            type = starTypes[random((byte)0, (byte) (starTypes.length-1))];
            StarFactory.createStar(type,CANVAS_SIZE,c);
        }

        long after = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory: " + (after - before) / 1024 / 1024 + " MB");

    }



	private static byte random(byte min, byte max) {
        return (byte) (min + (byte) (Math.random() * ((max - min) + 1)));
    }
}