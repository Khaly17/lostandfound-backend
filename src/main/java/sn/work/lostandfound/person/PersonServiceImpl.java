package sn.work.lostandfound.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import sn.work.lostandfound.UserInfo.UserInfo;
import sn.work.lostandfound.UserInfo.UserInfoService;
import sn.work.lostandfound.myException.NotFoundException;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final UserInfoService userInfoService;


    public PersonServiceImpl(
            PersonRepository personRepository,
            PersonConverter personConverter,
            UserInfoService userInfoService
    ) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
        this.userInfoService = userInfoService;
    }

    @Override
    public ResponseEntity<?> addPerson(PersonDto personDto) {
        if (personRepository.existsByEmail(personDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cet email est déjà utilisé.");
        }
        if (personRepository.existsByPhoneNumber(personDto.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cet numéro est déjà utilisé.");
        }

        Person person = personConverter.convertToEntity(personDto);
        Person personSaved = personRepository.save(person);

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(personDto.getEmail());
        userInfo.setName(personDto.getEmail());
        userInfo.setPassword(personDto.getPassword());
        userInfo.setRoles("NO_VERIFY");
        userInfo.setPerson(personSaved);

        userInfoService.addUser(userInfo);

        PersonDto savedPersonDto = personConverter.convertToDto(personSaved);
        return ResponseEntity.ok(savedPersonDto);
    }

    @Override
    public ResponseEntity<?> updatePerson(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not found with id: " + id));

        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setPhoneNumber(person.getPhoneNumber());

        Person updatedPerson = personRepository.save(person);

        PersonDto updatedPersonDto = personConverter.convertToDto(updatedPerson);
        return ResponseEntity.ok(updatedPersonDto);
    }
//    public PersonDto updatePerson(Long id, PersonDto personDto) {
//        Person person = personRepository.findById(id)
//        .orElseThrow(() -> new NotFoundException("Person not found with id: " + id));
//
//        person.setFirstName(personDto.getFirstName());
//        person.setLastName(personDto.getLastName());
//        person.setEmail(personDto.getEmail());
//
//        Person updatePerson = personRepository.save(person);
//
//        return this.personConverter.convertToDto(updatePerson);
//    }

    @Override
    public void deletePerson(Long id) {
        Person person = personRepository.findById(id)
                                    .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
        personRepository.delete(person);
    }

    @Override
    public List<PersonDto> findAllPersons() {
        List<Person> people = personRepository.findAll();
        List<PersonDto> personDtoList = new ArrayList<>();

        if(!people.isEmpty()){
            for (Person person : people) {
                PersonDto personDto = personConverter.convertToDto(person);
                personDtoList.add(personDto);
            }
            return personDtoList;
        }
        return null;
    }

    @Override
    public Optional<Person> findPersonById(Long id) {

        return this.personRepository.findById(id);
    }

    @Override
    public Optional<Person> findPersonByEmail(String email) {
        return this.personRepository.findByEmail(email);
    }

}
