package Ex1;

public class VehicleVan implements Vehicle {
    public static final int maxVolume = 1000;
    public static final int maxPassengers=4;

    @Override
    public  int getMaxVolume() {
        return this.maxVolume;
    }

    @Override
    public int getMaxPassengers() {
        return 3;
    }
}
