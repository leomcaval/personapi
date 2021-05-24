package one.digitalinnovation.leomcaval.personapi.repository;

import one.digitalinnovation.leomcaval.personapi.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
