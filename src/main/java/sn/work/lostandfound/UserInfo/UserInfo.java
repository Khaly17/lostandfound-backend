package sn.work.lostandfound.UserInfo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.work.lostandfound.person.Person;

@Entity
@Table(name = "userInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String name;
    @Column(name = "email")
    private String email;
    private String password;
    private String roles;
    @OneToOne
    @JoinColumn(name = "personId")
    private Person person;
}
