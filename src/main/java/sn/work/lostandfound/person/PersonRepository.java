package sn.work.lostandfound.person;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    @Query("SELECT COUNT(p) > 0 FROM Person p WHERE p.email = :email")
    boolean existsByEmail(@Param("email") String email);
    @Query("SELECT COUNT(p) > 0 FROM Person p WHERE p.phoneNumber = :phoneNumber")
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}

