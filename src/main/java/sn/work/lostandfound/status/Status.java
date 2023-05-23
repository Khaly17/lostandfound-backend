package sn.work.lostandfound.status;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "status")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "value")
    private String value;
}
