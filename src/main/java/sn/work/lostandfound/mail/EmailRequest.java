package sn.work.lostandfound.mail;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailRequest {
    private String to;
    private String subject;
    private String text;
}
