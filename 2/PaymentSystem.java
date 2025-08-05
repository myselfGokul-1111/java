import java.util.Scanner;

interface Payable {
    void pay();
    String getPaymentDetails();
    boolean validatePayment();
    void generateReceipt();
}

abstract class Payment implements Payable {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public boolean validatePayment() {
        return amount > 0;
    }
}

class CashPayment extends Payment {
    public CashPayment(double amount) {
        super(amount);
    }

    public void pay() {
        System.out.println("Cash Payment of " + amount + " received.");
    }

    public String getPaymentDetails() {
        return "Payment Type: Cash, Amount: " + amount;
    }

    public void generateReceipt() {
        System.out.println("\n\n=== Receipt ===");
        System.out.println("Payment Method: " + this.getClass().getSimpleName());
        System.out.println("Amount Paid: " + amount);
        System.out.println("================");
    }
}

class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardHolder;

    public CreditCardPayment(double amount, String cardNumber, String cardHolder) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    public void pay() {
        System.out.println("Credit Card Payment of " + amount + " received from " + cardHolder);
    }

    public String getPaymentDetails() {
        return "Payment Type: Credit Card, Card Holder: " + cardHolder + ", Card No: " + cardNumber;
    }

    public void generateReceipt() {
        System.out.println("=== Receipt ===");
        System.out.println("Payment Method: " + this.getClass().getSimpleName());
        System.out.println("Amount Paid: " + amount);
        System.out.println("================");
    }
}

class UPIPayment extends Payment {
    private String upiID;

    public UPIPayment(double amount, String upiID) {
        super(amount);
        this.upiID = upiID;
    }

    public void pay() {
        System.out.println("UPI Payment of " + amount + " sent to " + upiID);
    }

    public String getPaymentDetails() {
        return "Payment Type: UPI, UPI ID: " + upiID + ", Amount: " + amount;
    }

    public void generateReceipt() {
        System.out.println("\n\n=== Receipt ===");
        System.out.println("Payment Method: " + this.getClass().getSimpleName());
        System.out.println("Amount Paid: " + amount);
        System.out.println("================");
    }
}

class OverloadDemo {
    public void printDetails(String details) {
        System.out.println(details);
    }

    public void printDetails(String details, double amount) {
        System.out.println(details + " | Amount: " + amount);
    }

    public void printDetails(Payment payment) {
        System.out.println("Details: " + payment.getPaymentDetails());
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CashPayment cash = new CashPayment(500);
        CreditCardPayment card = new CreditCardPayment(1200, "1234567890123456", "Alice");
        UPIPayment upi = new UPIPayment(750, "alice@upi");

        Payment[] payments = {cash, card, upi};

        System.out.println("\n--- Payment Processing ---");
        for (Payment p : payments) {
            if (p.validatePayment()) {
                p.pay();
                p.generateReceipt();
                System.out.println(p.getPaymentDetails());
                System.out.println("----------------------");
            } else {
                System.out.println("Invalid Payment");
            }
        }

        System.out.println("\n--- Method Overloading Demo ---");
        OverloadDemo od = new OverloadDemo();
        od.printDetails("Simple message");
        od.printDetails("Payment Done", 1500);
        od.printDetails(card);
    }
}
