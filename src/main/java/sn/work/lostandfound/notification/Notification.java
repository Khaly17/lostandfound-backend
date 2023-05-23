package sn.work.lostandfound.notification;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "notification")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "objId")
    private Long objId;
    @Column(name = "motif")
    private String motif;
    @Column(name = "description")
    private String description;
    @Column(name = "createdAt")
    private Date createdAt;
}
