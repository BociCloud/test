package com.foo.dpd.test.app;

import com.foo.dpd.test.dto.PersonDto;
import com.foo.dpd.test.entity.Person;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/person/{id}")
    public PersonDto getPerson(@PathVariable String id) {

        Optional<Person> person = personService.getPerson(id);
        if (person.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found");
        }
        return modelMapper.map( person.get(), PersonDto.class);
    }

    @PostMapping("/person")
    public PersonDto createPerson(@RequestBody PersonDto personDto) {

        Person person = modelMapper.map(personDto, Person.class);
        log.info("person: {}", person);
        Person createdPerson = personService.createPerson(person);

        return modelMapper.map(createdPerson, PersonDto.class);
    }

}
