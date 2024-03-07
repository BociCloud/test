package com.foo.dpd.test.entity;

import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
public class Person {

    public static final int NAME_MAX_LENGTH = 100;
    public static final int PLACE_MAX_LENGTH = 100;
    public static final int TAJNUMBER_LENGTH = 9;
    public static final int TAXID_LENGTH = 10;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    private String id;

    @Column(length = NAME_MAX_LENGTH)
    private String name;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar birthDate;

    @Column(length = PLACE_MAX_LENGTH)
    private String birthPlace;

    @Column(length = NAME_MAX_LENGTH)
    private String mothersName;

    @Size(min = TAJNUMBER_LENGTH, max = TAJNUMBER_LENGTH)
    @Column(length = TAJNUMBER_LENGTH)
    private String tajNumber;

    @Size(min = TAXID_LENGTH, max = TAXID_LENGTH)
    @Column(length = TAXID_LENGTH)
    private String taxid;

    @Email
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> adresses;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;

}
