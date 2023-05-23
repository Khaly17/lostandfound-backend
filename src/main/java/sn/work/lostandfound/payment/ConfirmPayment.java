package sn.work.lostandfound.payment;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfirmPayment {
    private String response_code;
    private String response_text;
    private String hash;
    private String mode;
    private String status;
    private String fail_reason;
    private Action action;
}
