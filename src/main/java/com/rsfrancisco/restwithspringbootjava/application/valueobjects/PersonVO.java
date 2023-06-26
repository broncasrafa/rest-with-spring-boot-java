package com.rsfrancisco.restwithspringbootjava.application.valueobjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@JsonPropertyOrder({ "id", "firstName", "lastName", "birthDate", "gender", "address" })
public class PersonVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty(value="first_name")
    private String firstName;

    @JsonProperty(value="last_name")
    private String lastName;
    private String address;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    @JsonProperty(value="birth_date")
    private Date birthDate;



    public PersonVO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO personVO = (PersonVO) o;
        return Objects.equals(id, personVO.id) &&
                Objects.equals(firstName, personVO.firstName) &&
                Objects.equals(lastName, personVO.lastName) &&
                Objects.equals(address, personVO.address) &&
                Objects.equals(gender, personVO.gender) &&
                Objects.equals(birthDate, personVO.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, gender, birthDate);
    }
}
