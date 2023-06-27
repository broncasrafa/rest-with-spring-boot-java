package com.rsfrancisco.restwithspringbootjava.unittests.services;

import com.rsfrancisco.restwithspringbootjava.application.exceptions.RequiredObjectIsNullException;
import com.rsfrancisco.restwithspringbootjava.application.services.PersonService;
import com.rsfrancisco.restwithspringbootjava.application.valueobjects.PersonVO;
import com.rsfrancisco.restwithspringbootjava.domain.entities.Person;
import com.rsfrancisco.restwithspringbootjava.domain.interfaces.repositories.IPersonRepository;
import com.rsfrancisco.restwithspringbootjava.unittests.mocks.PersonMock;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    PersonMock input;

    @InjectMocks
    private PersonService service;

    @Mock
    IPersonRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new PersonMock();
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testFindById() {
        Person person = input.mockEntity();
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.getById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
    }

    @Test
    void testFindAll() {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var result = service.getAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    void testInsert() {
        Person person = input.mockEntity();
        Person persisted = person;
        persisted.setId(1L);

        PersonVO vo = input.mockVO();
        vo.setKey(1L);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.insert(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
    }
    @Test
    void testInsertWithNullPersonObject() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.insert(null);
        });
        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() {
        Person person = input.mockEntity();
        Person persisted = person;
        persisted.setId(1L);

        PersonVO vo = input.mockVO();
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(1L, vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
    }
    @Test
    void testUpdateWithNullPersonObject() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(1L, null);
        });
        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void testDelete() {
        Person person = input.mockEntity();
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        doNothing().when(repository).delete(isA(Person.class));

        service.delete(1L);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(person);
    }
}
