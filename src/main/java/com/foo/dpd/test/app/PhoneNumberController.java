package com.foo.dpd.test.app;

import com.foo.dpd.test.dto.PhoneNumberDto;
import com.foo.dpd.test.entity.Person;
import com.foo.dpd.test.entity.PhoneNumber;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class PhoneNumberController {

    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/phonenumber/{personId}/{id}")
    public PhoneNumberDto getPhoneNumber(@PathVariable String personId, @PathVariable Long id) {

        Optional<PhoneNumber> phoneNumber = phoneNumberService.getPhoneNumber(personId, id);
        if (phoneNumber.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "phoneNumber not found");
        }
        return mapEntityToDto(phoneNumber.get());
    }

    @PostMapping("/phonenumber/{personId}")
    public void addPhoneNumber(@PathVariable String personId, @RequestBody PhoneNumberDto phoneNumberDto) {

        Optional<Person> personOpt = personService.getPerson(personId);
        if (personOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found");
        }

        Person person = personOpt.get();

        PhoneNumber phoneNumber = PhoneNumber.builder().phoneNumber(phoneNumberDto.getPhoneNumber()).person(person).build();
        person.getPhoneNumbers().add(phoneNumber);

        personService.createUpdatePerson(person);

    }

    @PutMapping("/phonenumber/{personId}")
    public void updatePhoneNumber(@PathVariable String personId, @RequestBody PhoneNumberDto phoneNumberDto) {

        Optional<PhoneNumber> phoneNumberOpt = phoneNumberService.getPhoneNumber(personId, phoneNumberDto.getPhoneNumberId());
        if (phoneNumberOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "phoneNumber not found");
        }
        PhoneNumber phoneNumber = phoneNumberOpt.get();
        phoneNumber.setPhoneNumber(phoneNumberDto.getPhoneNumber());

        PhoneNumber updatedPhoneNumber = phoneNumberService.updatePhoneNumber(phoneNumber);
        log.info("updated phoneNumber: {}", updatedPhoneNumber);
    }

    private PhoneNumberDto mapEntityToDto(PhoneNumber phoneNumber) {
        return modelMapper.map(phoneNumber, PhoneNumberDto.class);
    }

}
