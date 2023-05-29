package sn.work.lostandfound.person;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.work.lostandfound.UserInfo.AuthRequest;
import sn.work.lostandfound.UserInfo.UserInfoService;
import sn.work.lostandfound.utils.UserResponse;

@RestController
@RequestMapping("/find")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {

    private final PersonServiceImpl personService;

    private final UserInfoService userInfoService;

    private final PersonConverter personConverter;

    public PersonController(
            PersonServiceImpl personService,
            UserInfoService userInfoService,
            PersonConverter personConverter
    ){
        this.personService = personService;
        this.userInfoService = userInfoService;
        this.personConverter = personConverter;
    }

    @PostMapping("/person/create")
    public ResponseEntity<?> addPerson(@RequestBody PersonDto personDto){
        return personService.addPerson(personDto);
    }

    @GetMapping("/person/all")
//    @PreAuthorize("hasAuthority('NO_VERIFY')")
    public List<PersonDto> findAllPerson() {
        return personService.findAllPersons();
    }

    @GetMapping("/person/{id}")
    public Optional<PersonDto> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.findPersonById(id);
        return person.map(personConverter::convertToDto);
    }

    @PutMapping("/person/update/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return personService.updatePerson(id, personDto);
    }

    @DeleteMapping("/person/delete/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
    }
    @PostMapping("/person/authenticate")
        public UserResponse login(@RequestBody AuthRequest authRequest) throws JsonProcessingException {
        return userInfoService.authenticateAndGetToken(authRequest, personService, personConverter);
    }
    @GetMapping("/person/email/{email}")
    public Optional<PersonDto> getPersonByEmail(@PathVariable String email) {
        Optional<Person> person = personService.findPersonByEmail(email);
        return person.map(personConverter::convertToDto);
    }
}


