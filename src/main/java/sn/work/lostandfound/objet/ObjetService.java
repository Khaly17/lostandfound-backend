package sn.work.lostandfound.objet;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ObjetService {
    ObjetDto addObjet(ObjetDto objetDto);
    ObjetDto updateObjet(Long id, ObjetDto objetDto);
    void deleteObjet(Long id);
    List<ObjetDto> findAllObjets();
    ObjetDto findObjetById(Long id);
    List<ObjetDto> findObjetByStatus(Boolean status);
    List<ObjetDto> findObjetsByPerson(Long personId);
    Optional<ObjetDto> findObjetByNumber(String objetNumber);
}

