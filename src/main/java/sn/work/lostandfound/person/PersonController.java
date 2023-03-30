package sn.work.lostandfound.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.work.lostandfound.UserInfo.AuthRequest;
import sn.work.lostandfound.UserInfo.UserInfoService;

@RestController
@RequestMapping("/find")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/person/create")
    public PersonDto addPerson(@RequestBody PersonDto personDto){
        return personService.addPerson(personDto);
    }

    @GetMapping("/person/all")
    @PreAuthorize("hasAuthority('NO_VERIFY')")
    public List<PersonDto> findAllPerson() {
        return personService.findAllPersons();
    }

    @GetMapping("/person/{id}")
    public PersonDto getPersonById(@PathVariable Long id) {
        return personService.findPersonById(id);
    }
    @PutMapping("/person/update/{id}")
    public PersonDto updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return personService.updatePerson(id, personDto);
    }

    @DeleteMapping("/person/delete/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
    }
    @PostMapping("/person/authenticate")
    public String login(@RequestBody AuthRequest authRequest) {
        return userInfoService.authenticateAndGetToken(authRequest);
    }
  
}


