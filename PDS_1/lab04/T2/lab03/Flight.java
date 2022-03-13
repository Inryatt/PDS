

import java.util.Arrays;

public class Flight {
   
    String flightCode;
    Airplane airplane;
    
    int [][] executive_seats;
    int [][] tourist_seats;
    int n_reservation = 0; 

    public Flight(String flightCode, Airplane airplane){
        this.flightCode = flightCode;
        this.airplane = airplane;
        this.n_reservation = 0;
        this.executive_seats = new int [airplane.getLinesExec()][airplane.getRowsExec()]; //built matrix executive
        this.tourist_seats = new int [airplane.getLinesTourist()][airplane.getRowsTourist()];  //built matrix tourist

        for (int [] v: executive_seats) { 
            Arrays.fill(v,0); // it starts with 0, no places booked
        }

        for (int [] w: tourist_seats) {
            Arrays.fill(w,0);
        }

    
    }

    public int getNReservation() {
        return ++this.n_reservation; // everytime a reservation is made, the number increments
    }

    public int getCurrentReservation() {
        return this.n_reservation;  //get the exact number of the reservation
    }

    public String getFlightCode() {
        return flightCode;
    }
    
    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode; 
    }
    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public int[][] getExecSeats() {
        return executive_seats;
    }
    public int[][] getTouristSeats() {
        return tourist_seats;
    }
    public void setExecSeats(int [][] exec_seats) {
        this.executive_seats = exec_seats;
    }
    public void setTouristSeats(int [][] tourist_seats) {
        this.tourist_seats = tourist_seats;
    }
}