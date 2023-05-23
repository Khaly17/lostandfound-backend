package sn.work.lostandfound.payment;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Invoice {
    private double total_amount;
    private String description;
}
