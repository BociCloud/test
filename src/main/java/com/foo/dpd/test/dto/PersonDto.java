package com.foo.dpd.test.dto;

import java.util.Calendar;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonDto {
    
    private String id;    
    private String name;
    private Calendar birthDate;
    private String birthPlace;
    private String mothersName;
    private String tajNumber;
    private String taxid;
    private String email;    
    private List<AddressDto> adresses;
    private List<PhoneNumberDto> phoneNumbers;

}
