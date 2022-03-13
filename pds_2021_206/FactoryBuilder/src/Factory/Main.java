package Ex1;

public class Main {
    public static void main(String[] args) {
        int[] luggage;
        Vehicle v;

        // Get vehicle for 1 passenger without luggage
        v = VehicleFactory.getVehicle(1);

        // Get vehicle for 1 passenger with two items of luggage
        luggage = new int[]{100, 140}; // two bags with a total volume of 240
        v = VehicleFactory.getVehicle(1, luggage);

        //Aditional Test: Ex1.Vehicle for 3 passengers with 490 cargo -> Van
        luggage = new int[]{50,200,240};
        v = VehicleFactory.getVehicle(3, luggage);

        // Get vehicle for 3 passengers with two items of luggage
        luggage = new int[]{50,100};
        v = VehicleFactory.getVehicle(2, luggage);

        // Get vehicle for 2 passengers and wheelchair
        v = VehicleFactory.getVehicle(2, true);

        //Aditional Test: No available vehicle (too much cargo)
        luggage = new int[]{50,300,240,90,40,500};
        v = VehicleFactory.getVehicle(2, luggage, true);

        //Aditional Test: No available vehicle (too many passengers)
        v = VehicleFactory.getVehicle(10);


    }
}
