package Ex1;

public class VehicleScooter implements Vehicle {

    public static final int maxVolume = 0;
    public static final int maxPassengers=1;

    @Override
    public int getMaxVolume() {
        return maxVolume;
    }

    @Override
    public int getMaxPassengers() {
        return maxPassengers;
    }
}
