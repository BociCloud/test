package com.foo.dpd.test.app;

import com.foo.dpd.test.entity.Person;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(String id) {
        return personRepository.findById(id);
    }

    public Person createPerson(@Valid Person person) {
        return personRepository.saveAndFlush(person);
    }
}
