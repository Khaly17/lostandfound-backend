package sn.work.lostandfound.notification;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationDto {
    private Long id;
    private Long userId;
    private Long objId;
    private String motif;
    private String description;
    private Date createdAt;
}
