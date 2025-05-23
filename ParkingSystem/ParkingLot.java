package ParkingSystem;

import PaymentStrategy.CreditCardPayment;
import PaymentStrategy.DebitCardPayment;
import PaymentStrategy.PayPalPayment;
import PaymentStrategy.PaymentContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class ParkingLot {

    private String name;
    private String address;
    private ArrayList<Floor> floors;
    private ArrayList<Ticket> tickets;

    static Scanner sc = new Scanner(System.in);

    ParkingLot(String name, String address) {
        this.name = name;
        this.address = address;
        this.floors = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void showParkingLotDetails() {
        System.out.println("Name: " + name + " Address: " + address);
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
            if (ticket.getTicketId() == ticketId) {
                double ammount = ticket.calculateAmount();

                String paymentId = payParkingFee(ammount);

                if (paymentId != null) {

                    ticket.markExit(paymentId);

                    for (Floor floor : floors) {
                        for (Spot spot : floor.spots) {
                            if (spot.getSpotId() == ticket.getParkingSpot()) {
                                spot.leave();
                            }
                        }
                    }

                    return ticket;
                }
            }
        }

        return null;
    }

    public String payParkingFee(double ammount) {
        PaymentContext paymentContext = new PaymentContext();
        int paymentMethod = -1;

        System.out.println("Your Parking Fee: " + ammount + "$");
        System.out.println("Select Payment ethod");

        System.out.println("1 - Paypal");
        System.out.println("2 - Credit Card");
        System.out.println("3 - Debit Card");

        paymentMethod = sc.nextInt();

        switch (paymentMethod) {
            case 1:
                paymentContext.setPaymentStrategy(new PayPalPayment());
                break;
            case 2:
                paymentContext.setPaymentStrategy(new CreditCardPayment());
                break;
            case 3:
                paymentContext.setPaymentStrategy(new DebitCardPayment());
                break;
            default:
                System.out.println("Opps something went wrong");
                return null;
        }

        paymentContext.processPayment(ammount);

        return UUID.randomUUID().toString();
    }
}
