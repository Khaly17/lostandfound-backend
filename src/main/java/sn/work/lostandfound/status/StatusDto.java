package sn.work.lostandfound.status;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatusDto {
    private Long id;
    private String code;
    private String value;
}
