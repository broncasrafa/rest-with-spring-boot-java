package com.rsfrancisco.restwithspringbootjava.application.services;

import com.rsfrancisco.restwithspringbootjava.application.exceptions.ResourceNotFoundException;
import com.rsfrancisco.restwithspringbootjava.application.mappers.Mapper;
import com.rsfrancisco.restwithspringbootjava.application.valueobjects.PersonVO;
import com.rsfrancisco.restwithspringbootjava.domain.entities.Person;
import com.rsfrancisco.restwithspringbootjava.domain.interfaces.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private IPersonRepository _repository;

    public PersonVO getById(Long id) {
        var person = _repository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Object not exists with id: "+id));
        return Mapper.mapper(person, PersonVO.class);
    }

    public List<PersonVO> getAll() {
        var persons = _repository.findAll();
        return Mapper.mapper(persons, PersonVO.class);
    }

    public PersonVO insert(PersonVO person) {
        var data = Mapper.mapper(person, Person.class);
        var obj = _repository.save(data);

        return Mapper.mapper(obj, PersonVO.class);
    }

    public PersonVO update(Long id, PersonVO person) {
        var db = _repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object not exists with id: "+id));
        db.setFirstName(person.getFirstName());
        db.setLastName(person.getLastName());
        db.setAddress(person.getAddress());
        db.setGender(person.getGender());
        db.setBirthDate(person.getBirthDate());
        var obj = _repository.save(db);

        return Mapper.mapper(obj, PersonVO.class);
    }

    public void delete(Long id) {
        var db = _repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object not exists with id: "+id));
        _repository.delete(db);
    }
}
