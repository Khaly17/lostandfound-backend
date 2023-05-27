package sn.work.lostandfound.objet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private final ObjetServiceImpl objetService;

    private final PersonConverter personConverter;
    public ObjetController(
            ObjetServiceImpl objetService,
            PersonConverter personConverter
    ){
        this.objetService = objetService;
        this.personConverter = personConverter;
    }
    @PostMapping("/objet/create")
    public ResponseEntity<?> addObjet(
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
    public ResponseEntity<?> getPersonByObjetNumber(@PathVariable("objetNumber") String objetNumber) {
        try {
            ResponseEntity<?> responseEntity = objetService.getPersonByObjetNumber(objetNumber);
            return generateResponse(responseEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/objet/person/{objetId}")
    public ResponseEntity<?> getPersonByObjetId(@PathVariable("objetId") Long objetId) {
        try {
            ResponseEntity<?> responseEntity = objetService.getPersonByObjetId(objetId, false);
            return generateResponse(responseEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private ResponseEntity<?> generateResponse(ResponseEntity<?> responseEntity) {
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            PersonDto personDto = personConverter.convertToDto((Person) responseEntity.getBody());
            return ResponseEntity.ok(personDto);
        } else if (responseEntity.getStatusCode() == HttpStatus.PRECONDITION_FAILED) {
            String errorMessage = "Vous devez d'abord finaliser le paiement.";
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errorMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
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
