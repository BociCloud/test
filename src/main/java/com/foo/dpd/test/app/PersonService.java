package com.foo.dpd.test.app;


import com.foo.dpd.test.entity.Person;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(String id) {
        return personRepository.findById(id);
    }
    
    public Person createPerson(Person person) {
        Person saved = personRepository.save(person);
        return saved;
    }
}