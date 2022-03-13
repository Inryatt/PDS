package ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // Color coding some error messages
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    // _________________________________
    public static ArrayList<Flight> flights = new ArrayList<>(); // Array of recorded flights
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean keepItRunning = true;
        while (keepItRunning) {
            System.out.print(ANSI_BLUE + "Select your option (H for help): " + ANSI_RESET);
            String[] input = sc.nextLine().split(" ");
            input[0] = input[0].toUpperCase(); // Options are case-insensitive
            menu:
            switch(input[0]) {
                case "Q": // Quit the program
                    keepItRunning = false;
                    System.out.print("Exiting. See you soon!");
                    break;
                case "H": // Show the user some help
                    System.out.print("Usage:\n  - Q (exit)\n  - I <file_name> (read flight from file)\n  - M <flight_code> (display flight seats and their availability)\n" +
                            "  - F <flight_code> <exec_seats> <tourist_seats> (create a new flight)\n  - R <flight_code> <class> <number_seats> (create reservation)\n  - C <reservation_code> (cancel a reservation)\n");
                    break;
                case "I": // Read flight from file
                    readFromFile(input);
                    break;
                case "M":                   // Display the Seat Chart for a specific flight
                                            // identified by its flight code
                    if(input.length!=2){
                        //There can be only two parameters: the command itself and the flight code
                        System.out.println(ANSI_RED+"Error: Invalid parameters! Usage: M flight_code"+ANSI_RESET);
                        break;
                    }

                    //Search for the correct flight to print
                    for(Flight flight : flights){
                        if(input[1].equals(flight.getFlightCode())){
                            flight.printChart();
                            break;
                        }
                    }
                    break;
                case "F":
                    //There can be either 3 or 4 arguments, depending on if the plane has executive class seating
                    if(input.length<3 || input.length>4){
                        System.out.println((ANSI_RED+"Error: Invalid parameters! Usage: F flight_code Exec_num_seats Tourist_num_seats, num_seats in format AxB"+ANSI_RESET));
                        break;
                    }
                    else{
                        int[] Tseats=new int[2]; //Rows,Cols of seats
                        int[] Eseats=new int[2];
                        for (int i=0;i<2;i++){
                            if(input.length==3){ //There is no Executive class seating
                                try {
                                    //Parsing the number of seats from the user input
                                    Integer tmp = Integer.parseInt((input[2].split("x")[i]));
                                    if (isNegative(tmp)){
                                        break menu;
                                    }
                                    Tseats[i]=tmp;
                                }catch(NumberFormatException ex){
                                    System.out.println(ANSI_RED+"ERROR: Invalid number of tourist seats. Please use format AxB"+ANSI_RESET);
                                    break menu;
                                }
                            }
                            else{ //There is Executive class seating
                                try {
                                    //Parsing the number of seats from the user input
                                    Integer tmp = Integer.parseInt((input[3].split("x")[i]));
                                    if (isNegative(tmp)){
                                        break menu;
                                    }
                                    Tseats[i]=tmp;

                                }catch(NumberFormatException ex){
                                    System.out.println(ANSI_RED+"ERROR: Invalid number of tourist seats. Please use format AxB"+ANSI_RESET);
                                    break menu;
                                }
                                try {
                                    Integer tmp = Integer.parseInt((input[2].split("x")[i]));
                                    if (isNegative(tmp)){
                                        break menu;
                                    }
                                    Eseats[i]=tmp;
                                }catch(NumberFormatException ex){
                                    System.out.println(ANSI_RED+"ERROR: Invalid number of executive seats. Please use format AxB"+ANSI_RESET);
                                    break menu;
                                }
                            }
                        }
                        if(Tseats[0]*Tseats[1]<Eseats[0]*Eseats[1]){
                            System.out.println(ANSI_RED+"ERROR: Executive class larger than touristic class."+ANSI_RESET);
                            break;
                        }
                        addFlight(input[1],Eseats,Tseats); //adds a flight with the specified parameters
                        System.out.println(flights+"\b");
                    }
                    break;
                case "R": // Create new booking
                    if(input.length != 4) { // Different number of arguments passed than those expected
                        System.out.println((ANSI_RED+"Error: Invalid parameters! Usage: R <flight_code> <class> <number_seats>"+ANSI_RESET));
                        break;
                    }

                    //Get the flight from the provided flight code, if it exists
                    boolean foundFlight = false;
                    Flight flight = null;
                    for(Flight tmpFlight : flights) { // look up flight code in our array of flights
                        if(tmpFlight.getFlightCode().equals(input[1])) {
                            foundFlight = true;
                            flight = tmpFlight;
                            break;
                        }
                    }
                    if(!foundFlight) { // Didn't find flights
                        System.out.println((ANSI_RED+"Error: The specified flight code doesn't seem to exist."+ANSI_RESET));
                        break;
                    }
                    else {
                        //Get number of seats from user input
                        int seatNum;
                        try {
                            //Check if number is valid
                            int tmp = Integer.parseInt(input[3]);
                            if (isNegative(tmp)){
                                break;
                            }
                            else seatNum=tmp;
                        } catch(NumberFormatException e) {
                            System.out.println((ANSI_RED+"Error: Invalid parameters! Usage: R <flight_code> <class> <number_seats> (numbers must be integers)"+ANSI_RESET));
                            break;
                        }
                        boolean isExec = false;
                        boolean valid = true;
                        //Get the Seat Class from user input
                        switch(input[2].toUpperCase()) {
                            case "E":
                                isExec = true;
                                break;
                            case "T":
                                break;
                            default:
                                valid = false;
                                System.out.println((ANSI_RED+"Error: Invalid parameters! Usage: R <flight_code> <class> (T/E) <number_seats>"+ANSI_RESET));
                                break;
                        }
                        if(!valid) break; //User input wasn't correct.
                        else {
                            if(flight.makeReservation(seatNum,isExec)) //Make the reservation
                                 System.out.print(((isExec)?"Executive ":"Touristic ")+"booking of "+seatNum+" seats successful!\n");
                            else System.out.print(((isExec)?"Executive ":"Touristic ")+"booking of "+seatNum+" seats unsuccessful!\n");
                        }
                    }
                    break;
                case "C": //Cancel a previously made reservation
                    if(input.length!=2){
                        System.out.println(ANSI_RED+"Error: Invalid arguments. Usage: C booking_code");
                    }
                    else {
                        //Read User Input
                        String[] resCode=input[1].split(":");

                        for(Flight fl : flights){
                            if (fl.getFlightCode().equals(resCode[0])){ //Find the correct Flight
                                for(String res : fl.getReservations()){
                                    if(res.split(":")[1].equals(resCode[1])){ // Find the correct booking
                                        fl.deleteReservation(Integer.parseInt(resCode[1])); //Delete booking
                                        System.out.println("Booking " + Arrays.toString(resCode) + " deleted successfully.");
                                        break menu; // Breaks out of all loops back into menu

                                    }
                                }
                            }
                        }
                        System.out.println(ANSI_RED+"Error: Booking "+input[1]+" not found."+ANSI_RESET+" \nValid bookings:");
                        // Show the existing bookings via terminal
                        for(Flight fl:flights){
                            System.out.println("Flight "+fl.getFlightCode());
                            if(fl.getReservations().size()==0){
                                System.out.println("No Bookings.");
                                break;
                            }
                            for(String book : fl.getReservations()){
                                System.out.println(book);
                            }
                        }
                    }
                    break;
                default:
                    System.err.println("Error: Invalid option. Please use H to get help.");
                    break;
            }
        }
    }
    // Adds a flight to our array and verifies if its duplicated
    // numSeatE = {0,0} for plane without Executive wing
    public static void addFlight(String flightCode, int[] numSeatE, int[] numSeatT){
        for(Flight fl : flights) {
            if (fl.getFlightCode().equals(flightCode)){
                System.out.println(ANSI_RED+"Error: Flight already exists!"+ANSI_RESET);
                return;
            }
        }
        int[] compare = {0,0};
        if(Arrays.equals(numSeatE, compare)) {
            flights.add(new Flight(flightCode, new Plane(numSeatT)));
        }
        else {
            flights.add(new Flight(flightCode, new Plane(numSeatE, numSeatT)));
        }
    }

    public static void readFromFile(String[] input) {
        if(input.length != 2) { // Wrong number of arguments passed than those expected
            System.err.println("Usage: I <file_name>");
        }
        else {
            // Preparing file for read
            File file;
            Scanner read;
            try {
                file = new File(".\\lab03\\" + input[1]);
                read = new Scanner(file);
            } catch(FileNotFoundException e) { // Error reading file/File non existent
                System.out.println(ANSI_RED + "Could not read the file!" + ANSI_RESET);
                return;
            }

            // Plane args
            boolean hasExec = false;
            int rowsT = 0, colsT = 0, rowsE = 0, colsE = 0;
            String code;

            // Handle the first line
            if(!read.hasNext()) { // No first line
                System.err.println("Bad file format! (File is empty)");
                return;
            }
            else {
                String firstLine = read.nextLine();
                if(firstLine.charAt(0) != '>') { // No command character
                    System.err.println("Bad file format! (No command line, missing '>')");
                    return;
                }
                else {
                    firstLine = firstLine.substring(1);
                    String[] arguments = firstLine.split(" ");
                    if(arguments.length == 2) {
                        if(!arguments[1].contains("x")) { // No row/column separator
                            System.err.println("Bad file format! (Seats must be specified as ROWSxCOLS)");
                            return;
                        }
                        else {
                            try {

                                rowsT = Integer.parseInt(arguments[1].split("x")[0]);
                                colsT = Integer.parseInt(arguments[1].split("x")[1]);

                                if(isNegative(rowsT)||isNegative(colsT)){
                                    return;
                                }
                            } catch(NumberFormatException e) { // No valid seat numbers
                                System.err.println("Bad file format! (Seats must be specified as integers)");
                                return;
                            }
                            finally {
                                code = arguments[0]; // get the flight code
                            }
                        }
                    }
                    else if(arguments.length == 3) {
                        hasExec = true;
                        if(!arguments[1].contains("x") || !arguments[2].contains("x") ) { // No row/column separator
                            System.err.println("Bad file format! (Seats must be specified as ROWSxCOLS)");
                            return;
                        }
                        else {
                            try {
                                rowsT = Integer.parseInt(arguments[2].split("x")[0]);
                                colsT = Integer.parseInt(arguments[2].split("x")[1]);
                                rowsE = Integer.parseInt(arguments[1].split("x")[0]);
                                colsE = Integer.parseInt(arguments[1].split("x")[1]);
                                if(isNegative(rowsT)||isNegative(colsT)||isNegative(rowsE)||isNegative(colsE)){
                                    return;
                                }
                            } catch(NumberFormatException e) { // No valid seat numbers
                                System.err.println("Bad file format! (Seats must be specified as integers)");
                                return;
                            }
                            finally {
                                code = arguments[0]; // get the flight code
                            }
                        }
                    }
                    else { // wrong number of arguments passed than those expected
                        System.err.println("Bad file format! (Command is wrong. Usage: >FLIGHT_CODE ROWS_TxCOLS_T (optional)ROWS_ExCOLS_E)");
                        return;
                    }
                }
            }

            // Create flight
            Plane plane;
            int[] T = {colsT, rowsT};
            if(hasExec) {
                int[] E = {colsE, rowsE};
                plane = new Plane(E, T);
            }
            else {
                plane = new Plane(T);
            }
            Flight flight = new Flight(code, plane);

            // Print the flight
            System.out.println("Flight code "+flight.getFlightCode()+". Available seats: "+flight.getPlane().getNumSeatsT()+" touristic seats"+((flight.getPlane().hasExecutive())?("; "+flight.getPlane().getNumSeatsE()+" executive seats."):(".")));

            // Read and register the reservations (if they exist)
            while(read.hasNextLine()) {
                String[] reservationArgs = read.nextLine().split(" ");
                if(reservationArgs.length != 2) {
                    System.err.println("Bad file format! (Wrong reservation format. Usage: E/T <num_of_seats>");
                    return;
                }
                // Type of reservation
                boolean isExec = false;
                switch(reservationArgs[0]) {
                    case "E":
                        isExec = true;
                        break;
                    case "T":
                        break;
                    default:
                        System.err.println("Bad file format! (Wrong reservation format. Usage: E/T <num_of_seats>");
                        return;
                }
                // Number of seats
                int seatNum;
                try {
                    seatNum = Integer.parseInt(reservationArgs[1]);
                    if(isNegative(seatNum)){
                        return;
                    }
                } catch(NumberFormatException e) {
                    System.err.println("Bad file format! (Wrong reservation format. Usage: E/T <num_of_seats> (numbers must be integers)");
                    return;
                }
                if(flight.makeReservation(seatNum,isExec)) System.out.print(((isExec)?"Executive ":"Touristic ")+"booking of "+seatNum+" seats successful!\n");
                else System.out.print(((isExec)?"Executive ":"Touristic ")+"booking of "+seatNum+" seats unsuccessful!\n");
            }

            flights.add(flight);

        }
    }
    //Quick, small function to check if a number is greater than zero
    //To avoid repeating code
    public static boolean isNegative(Integer number){
        if(number<=0){
            System.out.println(ANSI_RED+"ERROR: Please insert a number above zero!"+ANSI_RESET);
            return true;
        }
        else return false;
    }
}


