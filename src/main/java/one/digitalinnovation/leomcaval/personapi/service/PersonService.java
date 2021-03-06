package one.digitalinnovation.leomcaval.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.leomcaval.personapi.dto.request.PersonDTO;
import one.digitalinnovation.leomcaval.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.leomcaval.personapi.entities.Person;
import one.digitalinnovation.leomcaval.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.leomcaval.personapi.mapper.PersonMapper;
import one.digitalinnovation.leomcaval.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

//    @Autowired
//    public PersonService(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfexists(id);
        return personMapper.toDTO(person);
    }



    public void delete(Long id) throws PersonNotFoundException {
        verifyIfexists(id);

        personRepository.deleteById(id);
    }




    public MessageResponseDTO updateByID(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfexists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID");
    }

    private Person verifyIfexists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
