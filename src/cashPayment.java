import java.time.LocalDateTime;

public class cashPayment implements Payment {
    private String paymentId;
    private String orderId;
    private double paymentAmount;
    private String paymentMethod;
    private LocalDateTime paymentDateTime;
    private final double cashAmount;

    public cashPayment(String paymentId, String orderId, LocalDateTime paymentDateTime, double paymentAmount, double cashAmount, String paymentMethod) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentAmount = paymentAmount;
        this.paymentDateTime = paymentDateTime;
        this.cashAmount = cashAmount;
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
        return "=== Cash Payment ===\n"
                + "Payment ID: " + getPaymentId() + "\n"
                + "Order ID: " + getOrderId() + "\n"
                + "Amount: $" + getPaymentAmount() + "\n"
                + "Cash Given: $" + cashAmount + "\n"
                + "Payment Method: " + getPaymentMethod();
    }
}
