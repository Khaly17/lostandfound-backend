package sn.work.lostandfound.objet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.work.lostandfound.person.Person;

/**
 * @author DIENG Khaly (MS2E)
 */
@Repository
public interface ObjetRepository extends JpaRepository<Objet, Long> {
    /**
     *
     * @param objetNumber
     * @return
     */
    Optional<Objet> findByObjetNumber(String objetNumber);

    /**
     *
     * @param objetNumber
     * @param status
     * @return
     */
    @Query("SELECT COUNT(o) > 0 FROM Objet o WHERE o.objetNumber = :objetNumber AND o.status = :status")
    boolean existsByObjetNumberAndStatus(@Param("objetNumber") String objetNumber, @Param("status") Boolean status);

    /**
     *
     * @param objetId
     * @return
     */
    @Query("SELECT o.person FROM Objet o WHERE o.id = :objetId")
    Optional<Person> findPersonByObjetId(@Param("objetId") Long objetId);


}

