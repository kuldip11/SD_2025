package ParkingSystem;

import PaymentStrategy.PaymentStrategy;
import java.time.LocalDateTime;

public class Ticket {

    private int ticketId;
    private int parkingSpot;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private String paymentId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(int ticketId, int parkingSpot, String vehicleNumber, VehicleType vehicleType, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.parkingSpot = parkingSpot;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
    }

    public void markExit(String paymentId) {
        this.paymentId = paymentId;
        this.exitTime = LocalDateTime.now();
    }

    public double calculateAmount() {
        long durationInHours = java.time.Duration.between(this.entryTime, LocalDateTime.now()).toHours();

        if (durationInHours <= 0) durationInHours = 1;

        double amount = 0;
        long remaining = durationInHours;

        long firstTier = Math.min(remaining, 3);
        amount += firstTier * 10;
        remaining -= firstTier;

        long secondTier = Math.min(remaining, 2);
        amount += secondTier * 5;
        remaining -= secondTier;

        amount += remaining * 2;

        return amount;
    }

    public void pay(PaymentStrategy paymentStrategy){
        double ammount = this.calculateAmount();
        paymentStrategy.pay(ammount);
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
        System.out.println("Your Ticket");
        System.out.println("Ticket Id: " + ticketId);
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Payment Id: " + paymentId);
        System.out.println("Entry Time: " + entryTime);
        System.out.println("Exit Time: " + exitTime);
    }

}
