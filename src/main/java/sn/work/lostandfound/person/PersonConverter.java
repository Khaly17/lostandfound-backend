package sn.work.lostandfound.person;

import org.springframework.stereotype.Component;
import sn.work.lostandfound.objet.Objet;
import sn.work.lostandfound.objet.ObjetConverter;
import sn.work.lostandfound.objet.ObjetDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonConverter {
    public ObjetConverter objetConverter;
    public PersonDto convertToDto(Person person) {
        PersonDto personDto = new PersonDto();
        objetConverter = new ObjetConverter();
        personDto.setId(person.getId());
        personDto.setLastName(person.getLastName());
        personDto.setFirstName(person.getFirstName());
        personDto.setPhoneNumber(person.getPhoneNumber());
        personDto.setEmail(person.getEmail());
        personDto.setAddress(person.getAddress());
        if(person.getObjetList() != null) {
            List<ObjetDto> objetDtoList = new ArrayList<>();
            for (Objet objet: person.getObjetList()) {
                objetDtoList.add(objetConverter.convertToDto(objet));
            }
            personDto.setObjetDtoList(objetDtoList);
        }
        return personDto;
    }

    public Person convertToEntity(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setLastName(personDto.getLastName());
        person.setFirstName(personDto.getFirstName());
        person.setPhoneNumber(personDto.getPhoneNumber());
        person.setEmail(personDto.getEmail());
        person.setAddress(personDto.getAddress());
        return person;
    }
}

