package com.rsfrancisco.restwithspringbootjava.unittests.mapper.mocks;

import com.rsfrancisco.restwithspringbootjava.application.valueobjects.PersonVO;
import com.rsfrancisco.restwithspringbootjava.domain.entities.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonMock {

    private Person mockEntity(Integer number) {
        Person person = new Person();
        person.setId(number.longValue());
        person.setFirstName("First Name Test" + number);
        person.setLastName("Last Name Test" + number);
        person.setAddress("Address Test" + number);
        person.setGender(((number % 2) == 0) ? "Male" : "Female");
        return person;
    }

    private PersonVO mockVO(Integer number) {
        PersonVO vo = new PersonVO();
        vo.setId(number.longValue());
        vo.setFirstName("First Name Test" + number);
        vo.setLastName("Last Name Test" + number);
        vo.setAddress("Address Test" + number);
        vo.setGender(((number % 2) == 0) ? "Male" : "Female");
        return vo;
    }

    public Person mockEntity() {
        return mockEntity(0);
    }
    public PersonVO mockVO() {
        return mockVO(0);
    }
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }
    public List<PersonVO> mockVOList() {
        List<PersonVO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }

}
