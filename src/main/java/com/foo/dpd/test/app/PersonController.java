package com.foo.dpd.test.app;

import com.foo.dpd.test.dto.PersonDto;
import com.foo.dpd.test.entity.Person;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModelMapper modelMapperWithoutChild;

    @GetMapping("/person/{id}")
    public PersonDto getPerson(@PathVariable String id) {

        Optional<Person> person = personService.getPerson(id);
        if (person.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found");
        }
        return mapEntityToDto(person.get());
    }

    @PostMapping("/person")
    public PersonDto createPerson(@RequestBody PersonDto personDto) {

        Person person = mapDtoToEntity(personDto);
        person.setPersonId(null);

        Person createdPerson = personService.createUpdatePerson(person);
        log.info("created person: {}", createdPerson);

        return mapEntityToDto(createdPerson);
    }

    @PutMapping("/person")
    public PersonDto updatePerson(@RequestBody PersonDto personDto) {

        Optional<Person> personOpt = personService.getPerson(personDto.getId());
        if (personOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found");
        }
        Person person = personOpt.get();
        modelMapperWithoutChild.map(personDto, person);

        Person updatedPerson = personService.createUpdatePerson(person);
        log.info("updated person: {}", updatedPerson);

        return mapEntityToDto(updatedPerson);
    }

    @DeleteMapping("/person/{id}")
    public PersonDto depersonalizePerson(@PathVariable String id) {

        Optional<Person> personOpt = personService.getPerson(id);
        if (personOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found");
        }
        Person person = personOpt.get();

        Person updatedPerson = personService.updatePersonWithAnonymizedContent(person);
        log.info("updated person: {}", updatedPerson);

        return mapEntityToDto(updatedPerson);
    }

    private Person mapDtoToEntity(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    private PersonDto mapEntityToDto(Person createdPerson) {
        return modelMapper.map(createdPerson, PersonDto.class);
    }

}
