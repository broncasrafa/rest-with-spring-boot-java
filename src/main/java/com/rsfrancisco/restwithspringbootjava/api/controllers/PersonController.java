package com.rsfrancisco.restwithspringbootjava.api.controllers;

import com.rsfrancisco.restwithspringbootjava.application.services.PersonService;
import com.rsfrancisco.restwithspringbootjava.application.valueobjects.v1.PersonVO;
import com.rsfrancisco.restwithspringbootjava.domain.utils.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
    @Autowired
    private PersonService _service;

    @GetMapping
    public ResponseEntity<List<PersonVO>> findAll() {
        var list = _service.getAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<PersonVO> findById(@PathVariable Long id) {
        var person = _service.getById(id);
        return ResponseEntity.ok().body(person);
    }

    @PostMapping
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO model) {
        var person = _service.insert(model);
        return ResponseEntity.created(UrlUtil.getURI("/{id}", person.getId())).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<PersonVO> update(@PathVariable Long id, @RequestBody PersonVO model) {
        var person = _service.update(id, model);
        return ResponseEntity.ok().body(person);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        _service.delete(id);
        return ResponseEntity.ok().body(true);
    }
}