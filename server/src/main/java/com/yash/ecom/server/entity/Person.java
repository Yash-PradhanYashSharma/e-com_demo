package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Person {
    private String lastName;
    private String middleName;
    private String firstName;
    private String email;
    private String conatactNumber;
    private String personalTitle;
    private Party partyByPartyId;

    @Basic
    @Column(name = "lastname", nullable = true, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    @Basic
    @Column(name = "middlename", nullable = true, length = 30)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middlename) {
        this.middleName = middlename;
    }

    @Basic
    @Column(name = "firstname", nullable = true, length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    @Id
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "conatactnumber", nullable = true, length = 20)
    public String getConatactNumber() {
        return conatactNumber;
    }

    public void setConatactNumber(String conatactnumber) {
        this.conatactNumber = conatactnumber;
    }

    @Basic
    @Column(name = "personaltitle", nullable = true, length = 10)
    public String getPersonalTitle() {
        return personalTitle;
    }

    public void setPersonalTitle(String personaltitle) {
        this.personalTitle = personaltitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(lastName, person.lastName) &&
                Objects.equals(middleName, person.middleName) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(email, person.email) &&
                Objects.equals(conatactNumber, person.conatactNumber) &&
                Objects.equals(personalTitle, person.personalTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, middleName, firstName, email, conatactNumber, personalTitle);
    }

    @ManyToOne
    @JoinColumn(name = "partyid", referencedColumnName = "partyid")
    public Party getPartyByPartyId() {
        return partyByPartyId;
    }

    public void setPartyByPartyId(Party partyByPartyid) {
        this.partyByPartyId = partyByPartyid;
    }
}
