package sn.work.lostandfound.payment;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentResponse {
    private String response_code;
    private String response_text;
    private String description;
    private String token;
}
