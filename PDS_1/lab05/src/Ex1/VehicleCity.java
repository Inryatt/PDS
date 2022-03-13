package Ex1;

public class VehicleCity implements Vehicle {

    public static final int maxVolume = 250;
    public static final int maxPassengers=3;

    @Override
    public  int getMaxVolume() {
        return this.maxVolume;
    }

    @Override
    public int getMaxPassengers() {
        return 3;
    }
}
