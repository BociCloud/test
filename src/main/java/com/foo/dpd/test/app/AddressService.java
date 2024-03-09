package com.foo.dpd.test.app;

import com.foo.dpd.test.entity.Address;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Optional<Address> getAddress(Long id) {
        return addressRepository.findById(id);
    }

    public Optional<Address> getAddress(String personId, Long id) {
        return addressRepository.findByAddressIdAndPersonPersonId(id, personId);
    }

    public Address updateAddress(@Valid Address address) {
        addressRepository.save(address);
        return address;
    }

}
