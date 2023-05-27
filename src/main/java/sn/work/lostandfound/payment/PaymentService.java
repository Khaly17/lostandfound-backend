package sn.work.lostandfound.payment;

import org.springframework.http.ResponseEntity;

public interface PaymentService {
    PaymentResponse addPayment(Payment payment);
    ConfirmPayment verifyPayment(String token);
    void payment(String token);
}
