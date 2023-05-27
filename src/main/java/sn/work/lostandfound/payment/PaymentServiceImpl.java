package sn.work.lostandfound.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sn.work.lostandfound.constant.Constant;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Value("${payment.MASTER_KEY}")
    private String masterKey;
    @Value("${payment.dev.PRIVATE_KEY}")
    private String privateKey;
    @Value("${payment.dev.TOKEN}")
    private String token;
    @Value("${payment.api.dev}")
    private String paymentUrl;
    @Value("${payment.confirm_payment.CONFIRM_TOKEN}")
    private String CONFIRM_TOKEN;
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public PaymentResponse addPayment(Payment payment) {
        HttpEntity<Payment> request = new HttpEntity<>(payment,getHeader());
        PaymentResponse paymentResponse = restTemplate.postForObject(paymentUrl,request, PaymentResponse.class);
        System.out.println(paymentResponse);
        return paymentResponse;
    }

    @Override
    public ConfirmPayment verifyPayment(String token) {
        HttpEntity request = new HttpEntity<>(getHeader());
        ResponseEntity<ConfirmPayment> response = restTemplate.exchange(
                CONFIRM_TOKEN + token,
                HttpMethod.GET,
                request,
                ConfirmPayment.class,
                1
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            ConfirmPayment paymentConfirm = response.getBody();
            Action action = new Action(
                                    Constant.ACTION_CANCEL_URL,
                                    Constant.ACTION_CALLBACK_URL,
                                    Constant.ACTION_RETURN_URL);
            paymentConfirm.setAction(action);
            return paymentConfirm;
        } else {
            throw new IllegalStateException("La requête de vérification du paiement a échoué.");
        }
    }

    @Override
    public void payment(String token) {
        HttpEntity request = new HttpEntity<>(getHeader());
        ResponseEntity<String> response = restTemplate.exchange(
                Constant.PAYMENT_URL + token,
                HttpMethod.GET,
                request,
                String.class,
                1
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            String paymentConfirm = response.getBody();
            System.out.println(paymentConfirm);
        } else {
            throw new IllegalStateException("La requête de paiement a échoué.");
        }

    }
    public HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("PAYDUNYA-MASTER-KEY", masterKey);
        headers.set("PAYDUNYA-PRIVATE-KEY", privateKey);
        headers.set("PAYDUNYA-TOKEN", token);
        return headers;
    }



}