package sn.work.lostandfound.payment;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Action {
    @Value("${payment.confirm_payment.CANCEL_URL}")
    private String cancel_url;
    @Value("${payment.confirm_payment.CALLBACK_URL}")
    private String callback_url;
    @Value("${payment.confirm_payment.RETURN_URL}")
    private String return_url;
}
