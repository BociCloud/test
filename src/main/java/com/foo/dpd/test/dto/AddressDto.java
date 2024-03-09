package com.foo.dpd.test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDto {

    private long id;

    private String city;
    private String street;
    private String postalCode;

}
