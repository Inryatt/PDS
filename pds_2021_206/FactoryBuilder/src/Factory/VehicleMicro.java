package Ex1;

public class VehicleMicro implements Vehicle {
    public static final int maxVolume = 250;
    public static final int maxPassengers=1;

    @Override
    public  int getMaxVolume() {
        return this.maxVolume;
    }

    @Override
    public int getMaxPassengers() {
        return this.maxPassengers;
    }
}
