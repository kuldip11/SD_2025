package ParkingSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String parkingName;
        String parkingAddress;

        String[] vehicleTypes = {"CAR", "BIKE", "TRUCK"};
        
        Scanner sc = new Scanner(System.in);
        
        System.err.println("Enter Parkinglot Name");
        parkingName = sc.nextLine();
        System.err.println("Enter Parkinglot Address");
        parkingAddress = sc.nextLine();

        ParkingLot rustumjeeParkingLot = new ParkingLot(parkingName, parkingAddress);

        System.err.println("Enter total floor " + parkingName + " have?");
        int numFloors = sc.nextInt();
        
        for (int i = 1; i <= numFloors; i++) {
            Floor floor = new Floor(i);

            System.out.print("Enter number of spots on Floor " + i + ": ");
            int numSpots = sc.nextInt();
            sc.nextLine();

            for (int j = 1; j <= numSpots; j++) {
                System.out.println("Spot " + j + ":");

                VehicleType type = null;
                while (type == null) {
                    System.out.print("Enter Vehicle Type (0 - CAR, 1 - BIKE, 2 - TRUCK): ");
                    int inputVehecleType = sc.nextInt();

                    type = VehicleType.valueOf(vehicleTypes[inputVehecleType]);
                    
                }

                System.out.print("Is this spot reserved? (true/false): ");
                boolean isReserved = sc.nextBoolean();

                Spot spot = new Spot((i*1000) + j, type, isReserved);
                floor.addSpot(spot);
            }

            rustumjeeParkingLot.addFloor(floor);
        }


        while(true){
            System.err.println("Enty or Exit - (0/1)");
            int task = sc.nextInt();

            if(task == 0){
                System.out.print("Enter Vehicle Type (0 - CAR, 1 - BIKE, 2 - TRUCK): ");
                int inputVehecleType = sc.nextInt();
                sc.nextLine();

                VehicleType vehicleType = VehicleType.valueOf(vehicleTypes[inputVehecleType]);

                System.err.println("Enter Vehicle Number");
                String vehicleNumber = sc.nextLine();

                Ticket ticket = rustumjeeParkingLot.getTicket(vehicleType, vehicleNumber);

                if(ticket == null){
                    System.err.println("Sorry Spots is Not Available");
                }else{
                    ticket.printTicket();
                }
            }else{
                System.out.println("Enter Your Ticket Id");
                int ticketId = sc.nextInt();

                Ticket updatedTicket = rustumjeeParkingLot.leaveParking(ticketId);

                if(updatedTicket != null){
                    updatedTicket.printTicket();
                }else{
                    System.out.println("Invalid Ticket ID");
                }
            }
        }
        
    }

}
