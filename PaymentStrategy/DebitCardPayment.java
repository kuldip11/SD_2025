package PaymentStrategy;

public class DebitCardPayment implements PaymentStrategy {
    public void pay(double amount){
        System.out.println("Paid " + amount + " using Debit Card.");
    }
    
}
