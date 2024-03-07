package com.foo.dpd.test.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "person")
public class PhoneNumber {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long phoneNumberId;

    private String phoneNumber;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable=false, updatable=false)
    private Person person;
    
}
