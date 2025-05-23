package ParkingSystem;

import java.time.LocalDateTime;

public class Ticket {

    private int ticketId;
    private int parkingSpot;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(int ticketId, int parkingSpot, String vehicleNumber, VehicleType vehicleType, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.parkingSpot = parkingSpot;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
    }

    public void markExit() {
        this.exitTime = LocalDateTime.now();
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getParkingSpot() {
        return parkingSpot;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void printTicket() {
        System.err.println("Here is Your Ticket");
        System.err.println("Ticket Id: " + ticketId);
        System.err.println("Vehicle Number: " + vehicleNumber);
        System.err.println("Vehicle Type: " + vehicleType);
        System.err.println("Entry Time: " + entryTime);
        System.err.println("Exit Time: " + exitTime);
    }

}
