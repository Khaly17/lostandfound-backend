package sn.work.lostandfound.payment;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    private Invoice invoice;
    private Store store;
}
