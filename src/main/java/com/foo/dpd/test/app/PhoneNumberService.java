package com.foo.dpd.test.app;

import com.foo.dpd.test.entity.PhoneNumber;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public Optional<PhoneNumber> getPhoneNumber(Long id) {
        return phoneNumberRepository.findById(id);
    }

    public Optional<PhoneNumber> getPhoneNumber(String personId, Long id) {
        return phoneNumberRepository.findByPhoneNumberIdAndPersonPersonId(id, personId);
    }

    public PhoneNumber updatePhoneNumber(@Valid PhoneNumber phoneNumber) {
        phoneNumberRepository.save(phoneNumber);
        return phoneNumber;
    }

}
