package com.foo.dpd.test.app;

import com.foo.dpd.test.entity.Address;
import com.foo.dpd.test.entity.Person;
import com.foo.dpd.test.entity.PhoneNumber;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    public Person createUpdatePerson(@Valid Person person) {
        return personRepository.save(person);
    }

    public Person updatePersonWithAnonymizedContent(@Valid Person person) {

        person.setBirthPlace(convert(person.getBirthPlace()));
        person.setEmail(convertEmail());
        person.setMothersName(convert(person.getMothersName()));
        person.setName(convert(person.getName()));
        person.setTajNumber(convert(person.getTajNumber()));
        person.setTaxid(convert(person.getTaxid()));

        for (Address address : person.getAdresses()) {
            address.setStreet(convert(address.getStreet()));
            address.setCity(convert(address.getCity()));
        }

        for (PhoneNumber phoneNumber : person.getPhoneNumbers()) {
            phoneNumber.setPhoneNumber(convert(phoneNumber.getPhoneNumber()));
        }
        log.info("converted: {}", person);

        return personRepository.save(person);
    }

    private String convert(String str) {
        if (null == str) {
            return null;
        }
        return StringUtils.repeat("*", str.length());
    }

    private String convertEmail() {
        return "foo@bar.baz";
    }

}
