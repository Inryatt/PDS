
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

public class main2 {
    public static void main(String args[]) throws IOException  {
        Scanner reader;
        if (args.length == 1){ //read the file
            reader = new Scanner(new File(args[0]));
        }

        else{
            reader = new Scanner(System.in);
        }
        Manager manager = new Manager();
        printMenu();
        String op = "";
        String flight_code;

        while (op != "q" ) { 
            String line = reader.nextLine();

            String[] arguments = line.split(" ");
            op = arguments[0].toLowerCase(); //the first argument is always the option

            switch (op) {
                case "h": // shows menu
                    printMenu();
                    break;

                case "i": // reads the file
                    String fileName = arguments[1]; 
                    flightFile(fileName, manager); 
                    break;

                case "m": //show the reservation map
                    String flightCode = arguments[1];
                    Flight flight = manager.flights.get(flightCode); 
                    manager.printPlaneMap(flight); 
                    break;

                case "f":
                    int lins_exec = 0; 
                    int cols_exec = 0;
                    int lins_tourist = 0;
                    int cols_tourist = 0;
                    flight_code = "";
                    
                    if (arguments.length == 4) { //if the flight has executive class
                        
                        flight_code = arguments[1];
                        String [] lins_cols_exec = arguments[2].split("x");
                        String[] lins_cols_tourist = arguments[3].split("x");
                        lins_exec = Integer.parseInt(lins_cols_exec[0]);
                        cols_exec = Integer.parseInt(lins_cols_exec[1]);
                        lins_tourist = Integer.parseInt(lins_cols_tourist[0]);
                        cols_tourist = Integer.parseInt(lins_cols_tourist[1]);
                    }   

                    else if (arguments.length == 3) { //if the flight doesnt have executive class
                        flight_code = arguments[1];
                        String[] lins_cols = arguments[2].split("x");
                        lins_tourist = Integer.parseInt(lins_cols[0]);
                        cols_tourist = Integer.parseInt(lins_cols[1]);
                        
                    }

                    manager.addNewFlight(flight_code, lins_exec, cols_exec, lins_tourist, cols_tourist); //update all of our reservations with the file the program read
                    break;

                case "r": // add a new reservation
                    flight_code = arguments[1]; 
                    String classe = arguments[2];
                    int n_seats = Integer.parseInt(arguments[3]);
                    manager.addReservation(flight_code, classe, n_seats);
                    break;

                case "c": // cancel a reservation
                    String reservation_code = arguments[1];
                    String [] code = reservation_code.split(":");
                    flight_code = code[0];
                    int reservation_number = Integer.parseInt(code[1]);
                    flight = manager.flights.get(flight_code);
                    manager.cancelReservation(flight, reservation_number);
                    break;

                case "q": //exit 
                    System.out.println("Exiting ...");
                    System.out.println("------------");
                    System.exit(0);
                    break;
                default:
                    System.out.println(" Please select a letter from here: ");
                    printMenu();
                    break;
            }

            if (reader.hasNextLine() == false){
                break;
            }      
        }
        reader.close();
        
    }
    public static void printMenu() {
        System.out.println(" ----------------- MENU ----------------- ");
        System.out.println("H : Show menu; \nI [filename] : Reads info about a flight; \nM [flight_code]: Show the reservation map;");
        System.out.println("F [flight_code, num_seats_executive (ex: 3x2), num_seats_tourist (ex: 5x3)] : Add a new flight;");
        System.out.println("R [flight_code, class number_seats ] : Add a new reservation; \nC [reservation_code]: Cancel a reservation; \nQ : Exit.");
    }


    public static void flightFile (String fileName, Manager manager) {
        System.out.println("Reading your information...");

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            String firstLine = reader.nextLine();
            String [] values = firstLine.split("[> ]");
            if (firstLine.charAt(0)=='>') { //it has to start with a '>'
                String flightCode = values[1]; 
                Airplane airplane;
                Flight flight;

                if (values.length == 3) { // if it doesnt have executive class with will only have 3 arguments (>, the flight code and the number of seats)
                    String [] vals = values[2].split("x"); //it breaks the '3x2' into '3' 'x' '2'
                    
                    int rows = Integer.parseInt(vals[0]); // rows (lines in the matrix) = 3
                    int seatsPerRow = Integer.parseInt(vals[1]); // seats per row(collumn in the matrix = 2)
                    int totalSeats = rows * seatsPerRow; //total of seats, 3x2 = 6
                    
                    airplane = new Airplane(rows,seatsPerRow);  //create a new airplane
                    flight = new Flight(flightCode, airplane); //create a new flight
                    manager.flights.put(flightCode,flight); //add to our HashMap 
    

                    //while reading the reservations on the file
                    while (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        if (line.charAt(0) == 'E') { //if it beggins with 'E' we know that is executive class
                            
                            System.out.println("We are sorry to inform you that this airplane does not have an executive class.");
                            System.out.printf("Therefore we can not book this reservation: %s \n", line);
                            
                        }
                        else if (line.charAt(0) == 'T') {  //if it beggins with 'E' we know that is tourist class
                            String [] reservations = line.split("T ");
                            int numberBookings = Integer.parseInt(reservations[1]);  
                            totalSeats = totalSeats - numberBookings; //decrement the number of seats available 
                            if (totalSeats < 0) { //if it's lower than zero it doesnt have more seats to book
                                totalSeats = totalSeats + numberBookings; //restore the number of seats 
                                System.out.printf("Não foi possível obter lugares para a reserva: T %d", numberBookings);
                            }
                            else { //if it has seats available
                                ArrayList<String> reservationsList = manager.addReservation(flightCode, "T", numberBookings).getValue(); //add reservation
                                printReservationResult(flightCode, flight, reservationsList);
                            } 
                        }
                    }

                }
                else if (values.length == 4) { //if it has 4, then the plane will have executive class
                    //the same thing as before
                    String [] executiveVals = values[2].split("x");
                    String [] touristVals = values[3].split("x");

                    int executiveRows = Integer.parseInt(executiveVals[0]);
                    int executiveSeatsPerRow = Integer.parseInt(executiveVals[1]);
                    int totalExecutiveSeats = executiveRows*executiveSeatsPerRow;

                    int touristRows = Integer.parseInt(touristVals[0]);
                    int touristSeatsPerRow = Integer.parseInt(touristVals[1]);
                    int totalTouristSeats = touristRows*touristSeatsPerRow;


                    airplane = new Airplane(executiveRows,executiveSeatsPerRow,touristRows,touristSeatsPerRow); 

                    flight = new Flight(flightCode, airplane);
                    manager.flights.put(flightCode,flight);
                    while (reader.hasNextLine()) {

                        String line = reader.nextLine();
                        if (line.charAt(0) == 'E') { 
                            String [] executiveReservations = line.split("E ");
                            int numberBookings = Integer.parseInt(executiveReservations[1]); 

                            totalExecutiveSeats = totalExecutiveSeats - numberBookings;
                            if (totalExecutiveSeats < 0) {
                                totalExecutiveSeats = totalExecutiveSeats + numberBookings; 
                                System.out.printf("Can't book seats for this reservation: E %d\n", numberBookings);
                            }
                            else {
                                ArrayList<String> reservations = manager.addReservation(flightCode, "E", numberBookings).getValue();
                                printReservationResult(flightCode, flight, reservations);
                            }   

                        }
                        else if (line.charAt(0) == 'T') {
                            String [] touristReservations = line.split("T ");
                            int numberBookings = Integer.parseInt(touristReservations[1]); 

                            totalTouristSeats = totalTouristSeats - numberBookings;
                            if (totalTouristSeats < 0) {
                                totalTouristSeats = totalTouristSeats + numberBookings; 
                                System.out.printf("Can't book seats for this reservation: T %d\n", numberBookings);
                            }
                            else {
                                ArrayList<String> reservations = manager.addReservation(flightCode, "T", numberBookings).getValue();
                                printReservationResult(flightCode, flight, reservations);
                            }  
                        }
                    }

                }
                
            }
            else {
                System.out.println("We are sorry to inform you that the file does not follow the rules.");
                System.exit(0);
            }
           
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("There was an error processing file");
            e.printStackTrace();
        }
    }
    

    public static void printReservationResult(String flight_code, Flight flight, ArrayList<String> reservations) {
        if (reservations == null) {
            return;
        }
        else {
            String resev = flight_code;
            System.out.print(resev + ":" + flight.getCurrentReservation() + " = ");
            for (int i = 0; i < reservations.size(); i++) {
                System.out.print(reservations.get(i));
                if (i != reservations.size()-1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

}

