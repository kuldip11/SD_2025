package PaymentStrategy;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(double amount){
        if(paymentStrategy == null){
            System.out.println("Please select the payment method");
        }else{
            paymentStrategy.pay(amount);
        }
    }
}
