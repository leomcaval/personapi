package one.digitalinnovation.leomcaval.personapi.service;

import one.digitalinnovation.leomcaval.personapi.dto.request.PersonDTO;
import one.digitalinnovation.leomcaval.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.leomcaval.personapi.entities.Person;
import one.digitalinnovation.leomcaval.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){

        Person personToSave = Person.builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .birthDate(personDTO.getBirthDate())
                .phones(personDTO.getPhones())
                .build();

        Person savedPerson = personRepository.save(personDTO);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID" + savedPerson.getId())
                .build();
    }

}
