package one.digitalinnovation.leomcaval.personapi.controllers;

import one.digitalinnovation.leomcaval.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.leomcaval.personapi.entities.Person;
import one.digitalinnovation.leomcaval.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID" + savedPerson.getId())
                .build();
    }


}
