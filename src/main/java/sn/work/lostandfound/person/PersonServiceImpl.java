package sn.work.lostandfound.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
    public PersonDto addPerson(PersonDto personDto) {

        Person person = personConverter.convertToEntity(personDto);
        Person personSaved = personRepository.save(person);

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(personDto.getEmail());
        userInfo.setName(personDto.getUsername());
        userInfo.setPassword(personDto.getPassword());
        userInfo.setRoles("NO_VERIFY");
        userInfo.setPerson(personSaved);

        userInfoService.addUser(userInfo);

        return personConverter.convertToDto(personSaved);
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Person not found with id: " + id));

        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setEmail(personDto.getEmail());

        Person updatePerson = personRepository.save(person);
        
        return this.personConverter.convertToDto(updatePerson);
    }

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
    public PersonDto findPersonById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<PersonDto> findPersonByEmail(String email) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

}
