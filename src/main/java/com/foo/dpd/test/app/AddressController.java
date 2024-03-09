package com.foo.dpd.test.app;

import com.foo.dpd.test.dto.AddressDto;
import com.foo.dpd.test.entity.Address;
import com.foo.dpd.test.entity.Person;
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
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/address/{personId}/{id}")
    public AddressDto getAddress(@PathVariable String personId, @PathVariable Long id) {

        Optional<Address> address = addressService.getAddress(personId, id);
        if (address.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found");
        }
        return mapEntityToDto(address.get());
    }

    @PostMapping("/address/{personId}")
    public void addAddress(@PathVariable String personId, @RequestBody AddressDto addressDto) {

        Optional<Person> personOpt = personService.getPerson(personId);
        if (personOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found");
        }

        Person person = personOpt.get();

        Address address = Address.builder()
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .postalCode(addressDto.getPostalCode())
                .person(person)
                .build();
        person.getAdresses().add(address);

        personService.createUpdatePerson(person);

    }

    @PutMapping("/address/{personId}")
    public void updateAddress(@PathVariable String personId, @RequestBody AddressDto addressDto) {

        Optional<Address> addressOpt = addressService.getAddress(personId, addressDto.getId());
        if (addressOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found");
        }
        Address address = addressOpt.get();

        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setPostalCode(addressDto.getPostalCode());

        Address updatedAddress = addressService.updateAddress(address);
        log.info("updated address: {}", updatedAddress);
    }

    private AddressDto mapEntityToDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }

}
