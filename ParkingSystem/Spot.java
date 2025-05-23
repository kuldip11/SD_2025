package ParkingSystem;

public class Spot {
    int spotId;
    VehicleType spotType;
    boolean isReserved;
    boolean isAvailable;

    Spot(int spotId, VehicleType spotType, boolean isReserved) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.isReserved = isReserved;
        this.isAvailable = true;
    }

    Spot(int spotId, VehicleType spotType, boolean isReserved, boolean isAvailable) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.isReserved = isReserved;
        this.isAvailable = isAvailable;
    }

    public boolean park() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public void leave() {
        isAvailable = true;
    }

    public int getSpotId() {
        return spotId;
    }

    public VehicleType getSpotType() {
        return spotType;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}
