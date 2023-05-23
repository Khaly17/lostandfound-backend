package sn.work.lostandfound.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/find")
public class PaymentController {
    private final PaymentServiceImpl paymentService;

    public PaymentController(PaymentServiceImpl paymentService){
        this.paymentService = paymentService;
    }
    @PostMapping("/payment/add")
    public PaymentResponse addPayment(@RequestBody Payment payment){
        return paymentService.addPayment(payment);
    }
    @GetMapping("/payment/confirm/{token}")
    public ConfirmPayment verifyPayment(@PathVariable String token){
        return paymentService.verifyPayment(token);
    }
}
