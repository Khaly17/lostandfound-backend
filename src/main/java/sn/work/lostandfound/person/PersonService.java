package sn.work.lostandfound.person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    PersonDto addPerson(PersonDto personDto);
    PersonDto updatePerson(Long id, PersonDto personDto);
    void deletePerson(Long id);
    List<PersonDto> findAllPersons();
    PersonDto findPersonById(Long id);
    Optional<PersonDto> findPersonByEmail(String email);
}

