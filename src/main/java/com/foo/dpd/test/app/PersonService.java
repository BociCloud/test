package com.foo.dpd.test.app;

import com.foo.dpd.test.entity.Person;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public Optional<Person> getPerson(String id) {
        return personRepository.findById(id);
    }

    public Person createPerson(Person person) {
        return personRepository.saveAndFlush(person);
    }
}
