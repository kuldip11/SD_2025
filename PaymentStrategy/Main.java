package PaymentStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();
        int paymentMethod = 0;
        double amount;
        
        Scanner sc = new Scanner(System.in);
        

        while (paymentMethod < 1 || paymentMethod > 3) { 
            System.out.println("Select payment method");
            System.out.println("1 - Paypal");
            System.out.println("2 - Credit Card");
            System.out.println("3 - Debit Card");


            paymentMethod = sc.nextInt();

            if(paymentMethod < 1 || paymentMethod > 3){
                System.out.println("Invalid payment method selected");
                System.out.println("Please slect valid payment method");
            }else{
                break;
            }
        }

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
        }

        System.out.println("Enter ammount to pay");

        amount = sc.nextDouble();

        paymentContext.processPayment(amount);
    }
}
