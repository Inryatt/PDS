package Ex1;

public class VehicleFactory {

    public static Vehicle getVehicle(int passengers, int[] luggage) {
        int totalCargo = getTotalCargo(luggage);
        String output;
        if(totalCargo > 0) {
            output = "Ex1.Vehicle for " + passengers + " with " + luggage.length + " items of luggage: ";
        }
        else {
            output = "Ex1.Vehicle for " + passengers + ": ";
        }
        if (passengers == VehicleScooter.maxPassengers) {
            //Even if the number of passenger's below the maximum for the vehicle, if
            //the luggage volume exceeds the vehicle's limit, the user will get a larger vehicle.
            if (totalCargo ==  VehicleScooter.maxVolume) {
                //The user will get a Scooter
                System.out.println(output+"Use a Scooter");
                return new VehicleScooter();
            }
            if(totalCargo <= VehicleMicro.maxVolume){
                //The user will get a Micro Car
                System.out.println(output+"Use a Micro car");
                return new VehicleMicro();
            }
        }
        if (passengers <= VehicleCity.maxPassengers) {
            if (totalCargo <= VehicleCity.maxVolume){
                //The user will get a City Car
                System.out.println(output + "Use a City car");
                return new VehicleCity();
            }
        }
        if (passengers <= VehicleFamily.maxPassengers) {
            if (totalCargo <= VehicleFamily.maxVolume) {
                //The user will get a Family Car
                System.out.println(output + "Use a Family car");
                return new VehicleFamily();
            }
            if(totalCargo<=VehicleVan.maxVolume){
                //The user will get a Van
                System.out.println(output+"Use a Van");
                return new VehicleVan();
            }
        }
        System.out.println(output + "No vehicle available that meets those requirements");
        return null;
    }

    public static Vehicle getVehicle(int passengers, boolean wheelchair){
        int[] luggage = new int[]{};
        return getVehicle(passengers,luggage ,wheelchair); // run the other factory methods with empty luggage!
    }

    public static Vehicle getVehicle(int passengers){
        int[] luggage = new int[]{};
        return getVehicle(passengers,luggage); // run the other factory methods with empty luggage!
    }

    public static Vehicle getVehicle(int passengers, int[] luggage, boolean wheelchair) {
        int totalCargo=getTotalCargo(luggage);
        String output = "Ex1.Vehicle for " + passengers + ((totalCargo > VehicleScooter.maxVolume)?(" with " + luggage.length + " items of luggage"):"") + ((wheelchair)?" and wheelchair":"") + ": ";
        if(wheelchair) { // need wheelchair
            if (totalCargo <= VehicleVan.maxVolume && passengers <= VehicleVan.maxPassengers) { // it fits in a van
                System.out.println(output + "Use a Van");
                return new VehicleVan();
            } else { // doesnt fit in van, doesnt fit nowhere
                System.out.println(output + "No vehicle available that meets those requirements");
                return null;
            }
        }
        else {
            return getVehicle(passengers,luggage); // If no wheelchair is needed this is a "normal" search for vehicle
        }
    }

    //Returns the sum of all cargo volumes
    private static int getTotalCargo(int[] luggage) {
        int totalCargo=0;
        for (int bag : luggage) {
            totalCargo = totalCargo + bag;
        }
        return totalCargo;
    }

}
