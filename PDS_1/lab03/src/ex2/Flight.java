package ex2;

import java.util.*;

public class Flight {
    //Class Variables
    private String code;
    private Plane plane;
    private int[][] seatChart;
    private int resNum = 1;
    private ArrayList<String> reservations = new ArrayList<>();
    //Constructor
    public Flight(String code, Plane plane){
        this.code = code;
        this.plane = plane;
        int rows = Math.max(plane.getSeatDimensionsE()[0], plane.getSeatDimensionsT()[0]);
        int cols = (plane.getSeatDimensionsE()[1]+ plane.getSeatDimensionsT()[1]);
        this.seatChart = new int[rows][cols];
    }

    public int getResNum(){
        return resNum;
    } //Get booking number

    public boolean makeReservation(int seatNum, boolean isExec) {
        if (isExec) {
            // Check if there are enough free executive seats
            if (getFreeSeatsE() < seatNum) return false;
            // Search for empty rows
            boolean clearRow = false;
            int i;
            for (i = 0; i < plane.getSeatDimensionsE()[1]; i++) {
                clearRow = true;
                for (int j = 0; j < plane.getSeatDimensionsE()[0]; j++) {
                    if (!(seatChart[j][i] == 0)) clearRow = false;
                }
                if(clearRow) { // Fill empty row, if found
                    for (int j = 0; j < Math.min(plane.getSeatDimensionsE()[0],seatNum); j++) {
                        seatChart[j][i] = resNum;
                    }
                    break;
                }
            }
            if(clearRow) { // Insert the rest of the reservation on the following row, if found a clear row
                int left = seatNum-plane.getSeatDimensionsE()[0];
                for(int k = 0; k < left;) {
                    i = ((++i)%plane.getSeatDimensionsE()[1]);
                    for (int j = 0; j < plane.getSeatDimensionsE()[0]; j++) {
                        if (seatChart[j][i] == 0) {
                            seatChart[j][i] = resNum;
                            k++;
                            if(k == left) break;
                        }
                    }
                }
            }
            else { // Distribute the seats however we can
                for(int k = 0; k < seatNum;) {
                    for (i = 0; i < plane.getSeatDimensionsE()[1]; i++) {
                        for (int j = 0; j < plane.getSeatDimensionsE()[0]; j++) {
                            if (seatChart[j][i] == 0) {
                                seatChart[j][i] = resNum;
                                k++;
                                if(k == seatNum) break;
                            }
                        }
                        if(k == seatNum) break;
                    }
                }
            }
        }
        else {
            // Check if there are enough free executive seats
            if (getFreeSeatsT() < seatNum) return false;
            // Search for empty rows
            boolean clearRow = false;
            int i;
            for(i = plane.getSeatDimensionsE()[1]; i < plane.getSeatDimensionsT()[1]+plane.getSeatDimensionsE()[1]; i++) {
                clearRow = true;
                for (int j = 0; j < plane.getSeatDimensionsT()[0]; j++) {
                    if (!(seatChart[j][i] == 0)) clearRow = false;
                }
                if(clearRow) { // Fill empty row, if found
                    for (int j = 0; j < Math.min(plane.getSeatDimensionsT()[0],seatNum); j++) {
                        seatChart[j][i] = resNum;
                    }
                    break;
                }
            }
            if(clearRow) { // Insert the rest of the reservation on the following row, if found a clear row
                int left = seatNum-plane.getSeatDimensionsT()[0];
                for(int k = 0; k < left;) {
                    i++;
                    if(i >= plane.getSeatDimensionsT()[1]+plane.getSeatDimensionsE()[1]) {
                        i = plane.getSeatDimensionsE()[1];
                    }
                    for (int j = 0; j < plane.getSeatDimensionsT()[0]; j++) {
                        if (seatChart[j][i] == 0) {
                            seatChart[j][i] = resNum;
                            k++;
                            if(k == left) break;
                        }
                    }
                }
            }
            else { // Distribute the seats however we can
                for(int k = 0; k < seatNum;) {
                    for (i = plane.getSeatDimensionsE()[1]; i < plane.getSeatDimensionsT()[1] + plane.getSeatDimensionsE()[1]; i++) {
                        for (int j = 0; j < plane.getSeatDimensionsT()[0]; j++) {
                            if (seatChart[j][i] == 0) {
                                seatChart[j][i] = resNum;
                                k++;
                                if(k == seatNum) break;
                            }
                        }
                        if(k == seatNum) break;
                    }
                }
            }
        }
        String tmp=this.code + ":" + resNum;
        this.reservations.add(tmp);
        System.out.println("Reservation " + tmp + " added.");
        resNum++;
        return true;
    }

    public void deleteReservation(int resNum){
        //Deletes a booking for this flight
        boolean toRemove=false;
        for(String res : this.reservations){
            String[] tmp = res.split(":");
            //Find the correct Booking
            //Can't remove it here since we're iterating over it
            if (Integer.parseInt(tmp[1])==resNum){
                 toRemove=true;
            }
        }

        //Remove booking from seat chart
        for(int[] row : seatChart){
            for (int seat : row) {

                if (seat == resNum) {
                    seat = 0;
                }
            }
        }
        //Now we remove the booking
        if (toRemove)
        this.reservations.remove(resNum);
    }

    //Getters
    public ArrayList<String> getReservations(){
        return reservations;
    }

    public int getFreeSeatsE() {
        int ret = 0;
        for(int i = 0; i < plane.getSeatDimensionsE()[1]; i++) {
            for(int j = 0; j < plane.getSeatDimensionsE()[0]; j++) {
                if(seatChart[j][i] == 0) ret++;
            }
        }
        return ret;
    }

    public int getFreeSeatsT() {
        int ret = 0;
        for(int i = plane.getSeatDimensionsE()[1]; i < plane.getSeatDimensionsT()[1]+plane.getSeatDimensionsE()[1]; i++) {
            for(int j = 0; j < plane.getSeatDimensionsT()[0]; j++) {
                if(seatChart[j][i] == 0) ret++;
            }
        }
        return ret;
    }

    public String toString(){
        return String.format("\b\bCode: %10s Executive Seats: %5s Tourist Seats: %5s \n",code,plane.getNumSeatsE(),plane.getNumSeatsT());
    }

    //Prints the Seat Chart associated to this flight
    public void printChart() {
        System.out.print("   "); //For formatting purposes
        //Print the column number labels
        for( int i=1;i<seatChart[0].length+1;i++){
            System.out.print(i+"  ");
        }
        System.out.println();
        //Print the line Letter labels

        char toPrint='A'; //We're starting on capital 'A'
        for (int i = 0; i < seatChart.length; i++) {
            for (int j = 0; j < seatChart[i].length; j++) {
                if (j == 0) {
                    System.out.print((char) (toPrint + i) + "  ");  // To print the letters
                }
                // This is due to the plane being able to have different sized executive and touristic seats
                // Got to verify if we should print empty space or a seat
                if ( ( (j<this.plane.getSeatDimensionsE()[1]) && (i+1>this.plane.getSeatDimensionsE()[0]))  ||
                        ( (j+this.plane.getSeatDimensionsE()[1] < this.plane.getSeatDimensionsT()[1]+this.plane.getSeatDimensionsE()[1]) &&
                                (  i+1 > this.plane.getSeatDimensionsT()[0]))){
                    System.out.print("   ");             // Executive row > Tourist row and needs padding
                } else {
                    //To align the seats vertically
                    if(j<9) System.out.print(seatChart[i][j] + "  ");
                    else if(j<99) System.out.print(seatChart[i][j] + "   ");
                    else System.out.print(seatChart[i][j] + "    ");
                }
            }
            System.out.print("\n");
        }
    }

    public String getFlightCode() {
        return code;
    }

    public Plane getPlane() {
        return plane;
    }

}
