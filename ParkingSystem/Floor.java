package ParkingSystem;

import java.util.ArrayList;

public class Floor {
    int floorId;
    ArrayList<Spot> spots;

    public Floor(int floorId) {
        this.floorId = floorId;
        this.spots = new ArrayList<>();
    }

     public void addSpot(Spot spot) {
        spots.add(spot);
    }

    public int getFloorId() {
        return floorId;
    }

    public Spot findAvailableSpot(VehicleType type) {
        for (Spot spot : spots) {
            if (spot.isAvailable() && !spot.isReserved() && spot.getSpotType() == type) {
                return spot;
            }
        }
        return null;
    }

    public Spot findAvailableSpot(VehicleType type, boolean isReserved) {
        for (Spot spot : spots) {
            if (spot.isAvailable() && !spot.isReserved() && spot.getSpotType() == type) {
                return spot;
            }
        }
        return null;
    }


}
