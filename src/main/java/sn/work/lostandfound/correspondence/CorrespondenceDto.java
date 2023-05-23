package sn.work.lostandfound.correspondence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorrespondenceDto {

    private Long id;
    private Long objetLostId;
    private Long objetFoundId;
    private double correspondenceScore;
    private Date correspondenceDate;
    private String correspondenceStatus;
    private Long userIdFound;
    private Long userIdLost;

}
