package sn.work.lostandfound.payment;

/**
 * @author DIENG Khaly (MS2E)
 */
public interface PaymentService {
    /**
     *
     * @param payment
     * @return
     */
    PaymentResponse addPayment(Payment payment);

    /**
     *
     * @param token
     * @return
     */
    ConfirmPayment verifyPayment(String token);

    /**
     *
     * @param token
     */
    void payment(String token);
}
