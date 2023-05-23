package sn.work.lostandfound.objet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.work.lostandfound.person.Person;

@Repository
public interface ObjetRepository extends JpaRepository<Objet, Long> {
    Optional<Objet> findByObjetNumber(String objetNumber);
    @Query("SELECT COUNT(o) > 0 FROM Objet o WHERE o.objetNumber = :objetNumber AND o.status = :status")
    boolean existsByObjetNumberAndStatus(@Param("objetNumber") String objetNumber, @Param("status") Boolean status);

    @Query("SELECT o.person FROM Objet o WHERE o.id = :objetId")
    Optional<Person> findPersonByObjetId(@Param("objetId") Long objetId);


}

