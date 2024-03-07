package com.foo.dpd.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class PhoneNumber {
    
    @Id
    private String phoneNumber;
    
    @ManyToOne
    @JsonIgnore
    private Person person;
    
}
