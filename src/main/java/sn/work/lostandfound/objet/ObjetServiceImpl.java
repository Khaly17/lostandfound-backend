package sn.work.lostandfound.objet;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sn.work.lostandfound.myException.NotFoundException;
import sn.work.lostandfound.myException.ObjectAlreadyExistsException;
import sn.work.lostandfound.person.Person;
import sn.work.lostandfound.person.PersonConverter;
import sn.work.lostandfound.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObjetServiceImpl implements ObjetService{
    private final ObjetRepository objetRepository;
    private final ObjetConverter objetConverter;
    private final PersonConverter personConverter;

    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedDate = currentDate.format(formatter);

    public ObjetServiceImpl(
            ObjetRepository objetRepository,
            ObjetConverter objetConverter,
            PersonConverter personConverter) {
        this.objetRepository = objetRepository;
        this.objetConverter = objetConverter;
        this.personConverter = personConverter;
    }

    @Override
    public ObjetDto addObjet(ObjetDto objetDto) {
        objetDto.setCreatedDate(formattedDate);
        Objet objet = objetConverter.convertToEntity(objetDto);
        try {
//            String filename = Utils.fileUpload(file);
//            if(filename != null)
//                objet.setPhoto(filename);
            Objet objetSaved = objetRepository.save(objet);
            return objetConverter.convertToDto(objetSaved);
        } catch (DataIntegrityViolationException e) {
            throw new ObjectAlreadyExistsException("Objet with number " + objetDto.getObjetNumber() + " already exists.");
        }
    }

    @Override
    public ObjetDto updateObjet(Long id, ObjetDto objetDto) {
        Objet objetToUpdate = objetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Objet not found with id " + id));

        objetToUpdate.setObjetNumber(objetDto.getObjetNumber());
        objetToUpdate.setObjetName(objetToUpdate.getObjetName());
        objetToUpdate.setStatus(objetDto.getStatus());
        objetToUpdate.setPhoto(objetDto.getPhoto());
        objetToUpdate.setUpdatedDate(formattedDate);
        objetToUpdate.setDescription(objetDto.getDescription());

        Objet objetSaved = objetRepository.save(objetToUpdate);
        return objetConverter.convertToDto(objetSaved);
    }
    @Override
    public void deleteObjet(Long id) {
        objetRepository.deleteById(id);
    }

    @Override
    public List<ObjetDto> findAllObjets() {
        List<Objet> objets = objetRepository.findAll();
        return objets.stream().map(objetConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ObjetDto findObjetById(Long id) {
        return null;
    }

    @Override
    public List<ObjetDto> findObjetByStatus(Boolean status) {
        return null;
    }

    @Override
    public List<ObjetDto> findObjetsByPerson(Long personId) {
        return null;
    }

    @Override
    public Optional<ObjetDto> findObjetByNumber(String objetNumber) {
        return Optional.empty();
    }

    public Person getPersonByObjetNumber(String objetNumber) {
        Optional<Objet> objectOptional = objetRepository.findByObjetNumber(objetNumber);
        if (objectOptional.isPresent()) {
            Objet objet = objectOptional.get();
            return objet.getPerson();
        }
        return null;
    }
}
