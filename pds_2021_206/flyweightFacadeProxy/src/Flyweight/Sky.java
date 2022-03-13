package Ex3;

import javax.swing.*;

import Ex3.startypes.Star;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sky extends JFrame {
    private List<Star> stars = new ArrayList<>();

    public void placeStar(Star star) {
        stars.add(star);
    }

}