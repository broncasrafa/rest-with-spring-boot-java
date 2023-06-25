package com.rsfrancisco.restwithspringbootjava.unittests.mapper;

import com.rsfrancisco.restwithspringbootjava.application.mappers.Mapper;
import com.rsfrancisco.restwithspringbootjava.application.valueobjects.v1.PersonVO;
import com.rsfrancisco.restwithspringbootjava.domain.entities.Person;
import com.rsfrancisco.restwithspringbootjava.unittests.mapper.mocks.PersonMock;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapperTest {
    PersonMock personMock;

    @Before
    public void setUp() {
        personMock = new PersonMock();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO output = Mapper.mapper(personMock.mockEntity(), PersonVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("First Name Test0", output.getFirstName());
        Assert.assertEquals("Last Name Test0", output.getLastName());
        Assert.assertEquals("Address Test0", output.getAddress());
        Assert.assertEquals("Male", output.getGender());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputList = Mapper.mapper(personMock.mockEntityList(), PersonVO.class);
        PersonVO outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("First Name Test0", outputZero.getFirstName());
        Assert.assertEquals("Last Name Test0", outputZero.getLastName());
        Assert.assertEquals("Address Test0", outputZero.getAddress());
        Assert.assertEquals("Male", outputZero.getGender());

        PersonVO outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("First Name Test7", outputSeven.getFirstName());
        Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
        Assert.assertEquals("Address Test7", outputSeven.getAddress());
        Assert.assertEquals("Female", outputSeven.getGender());

        PersonVO outputTwelve = outputList.get(12);

        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("First Name Test12", outputTwelve.getFirstName());
        Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
        Assert.assertEquals("Address Test12", outputTwelve.getAddress());
        Assert.assertEquals("Male", outputTwelve.getGender());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = Mapper.mapper(personMock.mockVO(), Person.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("First Name Test0", output.getFirstName());
        Assert.assertEquals("Last Name Test0", output.getLastName());
        Assert.assertEquals("Address Test0", output.getAddress());
        Assert.assertEquals("Male", output.getGender());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputList = Mapper.mapper(personMock.mockVOList(), Person.class);
        Person outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("First Name Test0", outputZero.getFirstName());
        Assert.assertEquals("Last Name Test0", outputZero.getLastName());
        Assert.assertEquals("Address Test0", outputZero.getAddress());
        Assert.assertEquals("Male", outputZero.getGender());

        Person outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("First Name Test7", outputSeven.getFirstName());
        Assert.assertEquals("Last Name Test7", outputSeven.getLastName());
        Assert.assertEquals("Address Test7", outputSeven.getAddress());
        Assert.assertEquals("Female", outputSeven.getGender());

        Person outputTwelve = outputList.get(12);

        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("First Name Test12", outputTwelve.getFirstName());
        Assert.assertEquals("Last Name Test12", outputTwelve.getLastName());
        Assert.assertEquals("Address Test12", outputTwelve.getAddress());
        Assert.assertEquals("Male", outputTwelve.getGender());
    }
}
