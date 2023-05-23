package sn.work.lostandfound.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import sn.work.lostandfound.person.PersonDto;

import java.util.Optional;

@Data
@NoArgsConstructor
public class UserResponse {
    private String token;
    private Optional<PersonDto> person;
}
