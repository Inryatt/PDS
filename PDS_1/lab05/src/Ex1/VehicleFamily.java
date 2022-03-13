package Ex1;

public class VehicleFamily implements Vehicle {
    public static final int maxVolume = 600;
    public static final int maxPassengers=4;

    @Override
    public  int getMaxVolume() {
        return this.maxVolume;
    }

    @Override
    public int getMaxPassengers() {
        return  this.maxPassengers;
    }
}
