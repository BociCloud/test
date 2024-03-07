package com.foo.dpd.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;

    private String city;
    private String street;
    private String postalCode;

    @ManyToOne
    @JsonIgnore
    private Person person;

}
