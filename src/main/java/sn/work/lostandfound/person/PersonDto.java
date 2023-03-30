package sn.work.lostandfound.person;

import lombok.*;
import sn.work.lostandfound.objet.ObjetDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonDto {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String address;
    private List<ObjetDto> objetDtoList;
}

