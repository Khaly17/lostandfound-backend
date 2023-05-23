package sn.work.lostandfound.objet;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author DIENG Khaly (MS2E)
 */
public interface ObjetService {
    /**
     *
     * @param objetDto
     * @return
     */
    ResponseEntity<?> addObjet(ObjetDto objetDto);

    /**
     *
     * @param id
     * @param objetDto
     * @return
     */
    ObjetDto updateObjet(Long id, ObjetDto objetDto);

    /**
     *
     * @param id
     */
    void deleteObjet(Long id);

    /**
     *
     * @return
     */
    List<ObjetDto> findAllObjets();

    /**
     *
     * @param id
     * @return
     */
    ObjetDto findObjetById(Long id);

    /**
     *
     * @param status
     * @return
     */
    List<ObjetDto> findObjetByStatus(Boolean status);

    /**
     *
     * @param personId
     * @return
     */
    List<ObjetDto> findObjetsByPerson(Long personId);

    /**
     *
     * @param objetNumber
     * @return
     */
    Optional<ObjetDto> findObjetByNumber(String objetNumber);
}

