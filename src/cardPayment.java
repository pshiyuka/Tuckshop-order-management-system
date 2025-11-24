import javax.swing.*;
import java.time.LocalDateTime;

public class cardPayment implements Payment {
    private String paymentId;
    private String orderId;
    private double paymentAmount;
    private String paymentMethod;
    private LocalDateTime paymentDateTime;
    private String transactionId;
    private String cardType;
    private String cardMask;
    private String cardExpiry;
    private String cardholderName;
    private String paymentGateway;
    private String paymentConfirmationId;

    public cardPayment(String paymentId, String orderId, double paymentAmount, LocalDateTime paymentDateTime,
                       String transactionId, String cardType, String cardMask, String cardExpiry, String cardholderName,
                       String paymentGateway, String paymentConfirmationId, String paymentMethod) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentAmount = paymentAmount;
        this.paymentDateTime = paymentDateTime;
        this.transactionId = transactionId;
        this.cardType = cardType;
        this.cardMask = cardMask;
        this.cardExpiry = cardExpiry;
        this.cardholderName = cardholderName;
        this.paymentGateway = paymentGateway;
        this.paymentConfirmationId = paymentConfirmationId;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String getPaymentId() {
        return paymentId;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public double getPaymentAmount() {
        return paymentAmount;
    }

    @Override
    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }

    @Override
    public String displayPaymentInfo() {
        return "=== Card Payment ===\n"
                + "Payment ID: " + getPaymentId() + "\n"
                + "Order ID: " + getOrderId() + "\n"
                + "Amount: $" + getPaymentAmount() + "\n"
                + "Transaction ID: " + transactionId + "\n"
                + "Card Type: " + cardType + "\n"
                + "Cardholder: " + cardholderName;

    }
}
