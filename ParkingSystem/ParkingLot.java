package ParkingSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ParkingLot {
    private String name;
    private String address;
    private ArrayList<Floor> floors;
    private ArrayList<Ticket> tickets;

    ParkingLot(String name, String address) {
        this.name = name;
        this.address = address;
        this.floors = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void showParkingLotDetails(){
        System.err.println("Name: " + name + " Address: " + address);
    }

    public Ticket getTicket(VehicleType vehicleType, String vehicleNumber) {
        for (Floor floor : floors) {
            Spot spot = floor.findAvailableSpot(vehicleType);

            if (spot != null) {
                spot.park();
                LocalDateTime entryTime = LocalDateTime.now();
                Ticket newTicket = new Ticket(tickets.size(), spot.getSpotId(), vehicleNumber, vehicleType, entryTime);
                tickets.add(newTicket);
                return newTicket;
            }
        }
        return null;
    }

    public Ticket leaveParking(int ticketId) {
        for (Ticket ticket : tickets) {
            if(ticket.getTicketId() == ticketId){
                ticket.markExit();

                for (Floor floor : floors) {
                    for (Spot spot : floor.spots) {
                        if (spot.getSpotId() == ticket.getParkingSpot()) {
                            spot.leave();
                            break;
                        }
                    }
                }

                return ticket;
            }
        }
        
        return null;
    }
}
