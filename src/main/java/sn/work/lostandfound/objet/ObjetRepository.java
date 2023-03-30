package sn.work.lostandfound.objet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetRepository extends JpaRepository<Objet, Long> {
//    List<Objet> findByStatus(Boolean status);
//    List<Objet> findByPersonId(Long personId);
//    Optional<Objet> findByNumero(String numero);
    Optional<Objet> findByObjetNumber(String objetNumber);
}

