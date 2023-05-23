package sn.work.lostandfound.person;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    ResponseEntity<?> addPerson(PersonDto personDto);
    ResponseEntity<?> updatePerson(Long id, PersonDto personDto);
    void deletePerson(Long id);
    List<PersonDto> findAllPersons();
    Optional<Person> findPersonById(Long id);
    Optional<Person> findPersonByEmail(String email);
}

