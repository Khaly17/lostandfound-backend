package sn.work.lostandfound.correspondence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "correspondence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Correspondence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "objetLostId")
    private Long objetLostId;
    @Column(name = "objetFoundId")
    private Long objetFoundId;
    @Column(name = "correspondenceScore")
    private double correspondenceScore;
    @Column(name = "correspondenceDate")
    private Date correspondenceDate;
    @Column(name = "correspondenceStatus")
    private String correspondenceStatus;
    @Column(name = "userIdFound")
    private Long userIdFound;
    @Column(name = "userIdLost")
    private Long userIdLost;
}

