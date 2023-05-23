package sn.work.lostandfound.objet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sn.work.lostandfound.category.CategoryConverter;
import sn.work.lostandfound.person.PersonConverter;

@Component
public class ObjetConverter {

    @Autowired
    private PersonConverter personConverter = new PersonConverter();

    @Autowired
    private CategoryConverter categoryConverter = new CategoryConverter();

    public ObjetDto convertToDto(Objet objet) {
        ObjetDto objetDto = new ObjetDto();
        objetDto.setId(objet.getId());
        objetDto.setObjetName(objet.getObjetName());
        objetDto.setDescription(objet.getDescription());
        objetDto.setCreatedDate(objet.getCreatedDate());
        objetDto.setUpdatedDate(objet.getUpdatedDate());
        objetDto.setStatus(objet.getStatus());
        objetDto.setPhoto(objet.getPhoto());
        objetDto.setObjetNumber(objet.getObjetNumber());
        objetDto.setToken(objet.getToken());
        return objetDto;
    }

    public Objet convertToEntity(ObjetDto objetDto) {
        Objet objet = new Objet();
        objet.setId(objetDto.getId());
        objet.setObjetName(objetDto.getObjetName());
        objet.setDescription(objetDto.getDescription());
        objet.setCreatedDate(objetDto.getCreatedDate());
        objet.setUpdatedDate(objetDto.getUpdatedDate());
        objet.setStatus(objetDto.getStatus());
        if(objetDto.getPerson() != null)
            objet.setPerson(personConverter.convertToEntity(objetDto.getPerson()));
        if(objetDto.getCategory() != null)
            objet.setCategory(categoryConverter.convertToEntity(objetDto.getCategory()));
        objet.setPhoto(objetDto.getPhoto());
        objet.setObjetNumber(objetDto.getObjetNumber());
        objet.setToken(objetDto.getToken());
        return objet;
    }
}

