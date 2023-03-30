package sn.work.lostandfound.objet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sn.work.lostandfound.person.Person;
import sn.work.lostandfound.person.PersonConverter;
import sn.work.lostandfound.person.PersonDto;

import java.util.List;

@RestController
@RequestMapping("/find")
public class ObjetController {
    @Autowired
    private ObjetServiceImpl objetService;
    @Autowired
    private PersonConverter personConverter;
    @PostMapping("/objet/create")
    public ObjetDto addObjet(
            @RequestBody ObjetDto objetDto
            //@RequestParam("file") MultipartFile file
    ){
        return objetService.addObjet(objetDto);
    }
    @GetMapping("/objet/all")
    public List<ObjetDto> findAllObjets() {
        return objetService.findAllObjets();
    }
    @GetMapping("/objet/{objetNumber}/person")
    public ResponseEntity<PersonDto> getPersonByObjetNumber(@PathVariable("objetNumber") String objetNumber) {
        Person person = objetService.getPersonByObjetNumber(objetNumber);
        if (person != null) {
            PersonDto personDto = personConverter.convertToDto(person);
            return ResponseEntity.ok(personDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/objet/update/{id}")
    public ObjetDto updateObjet(@PathVariable Long id, @RequestBody ObjetDto objetDto) {
        return objetService.updateObjet(id, objetDto);
    }
    @DeleteMapping("/objet/delete/{idObjet}")
    public void deleteObjet(@PathVariable("idObjet") Long idObjet){
        objetService.deleteObjet(idObjet);
    }
}
