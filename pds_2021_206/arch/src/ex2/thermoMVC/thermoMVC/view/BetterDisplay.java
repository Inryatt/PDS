package ex2.thermoMVC.thermoMVC.view;

import ex2.thermoMVC.thermoMVC.model.Thermometer;
import ex2.thermoMVC.thermoMVC.model.ThermometerListener;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ex2.thermoMVC.thermoMVC.model.Thermometer;
import ex2.thermoMVC.thermoMVC.model.ThermometerListener;

public class BetterDisplay extends JPanel implements ThermometerListener {

    // The label displaying the temperature
    private JLabel tempLabel;

    // The Unicode symbol for degrees
    private static  String DEGREE_SYMBOL = "MILD";
    private static  Color BACKGROUND = Color.yellow;


    // The Unicode symbol for degrees

    /*private static final String VERY_COLD = "🥶"; // -10 a 0
    private static final String COLD = "🤧"; // 0 a 15
    private static final String MILD = "😐"; // 15 a 25
    private static final String HOT = "🥵"; // 25 a 35
    private static final String VERY_HOT = "🔥"; // 35+ :D*/

    private static final String VERY_COLD = "V COLD \uD83E\uDD76"; // -10 a 0
    private static final String COLD = "COLD \uD83E\uDD27"; // 0 a 15
    private static final String MILD = "MILD \uD83D\uDE10"; // 15 a 25
    private static final String HOT = "HOT \uD83E\uDD75"; // 25 a 35
    private static final String VERY_HOT = "FAYA \uD83D\uDD25"; // 35+ :D

    /** The thermometer whose temperature is being displayed */
    protected Thermometer thermometer;

    /**
     * Creates a digital thermometer
     * @param t the thermometer whose temperature is displayed
     */
    public BetterDisplay(Thermometer t) {
        thermometer = t;

        JPanel tempPanel = new JPanel();
        tempLabel = new JLabel(getDisplayString());


        tempLabel.setFont(tempLabel.getFont().deriveFont(20.0f));
        tempPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        tempPanel.add(tempLabel);
        add(tempPanel);
    }

    /**
     * Create the string to display in the thermometer
     * @return the string to display in the thermometer
     */
    private String getDisplayString() {
        return "" + DEGREE_SYMBOL;
    }

    /**
     * Change the temperature displayed
     */
    @Override
    public void temperatureChanged() {

        int temp=thermometer.getTemperature();

         if (temp<0) {
             DEGREE_SYMBOL = VERY_COLD;
             BACKGROUND = Color.white;
         }
        else if (temp<15){
            DEGREE_SYMBOL=COLD;
            BACKGROUND = Color.blue;
        }
        else if(temp<25){
            DEGREE_SYMBOL=MILD;
            BACKGROUND = Color.yellow;
        }
        else if(temp<35){
            DEGREE_SYMBOL=HOT;
            BACKGROUND = Color.orange;
        }
        else {
            DEGREE_SYMBOL=VERY_HOT;
            BACKGROUND = Color.red;
         }
        tempLabel.setText(getDisplayString());
        this.setBackground(BACKGROUND);
        repaint();
    }
}
