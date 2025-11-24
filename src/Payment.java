import java.time.LocalDateTime;

public interface Payment {

    String getPaymentId();

    String getOrderId();

    double getPaymentAmount();

    String getPaymentMethod();

    LocalDateTime getPaymentDateTime();

    String displayPaymentInfo();
}
