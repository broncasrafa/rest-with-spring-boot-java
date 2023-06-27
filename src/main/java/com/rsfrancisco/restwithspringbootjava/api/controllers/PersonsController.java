package com.rsfrancisco.restwithspringbootjava.api.controllers;

import com.rsfrancisco.restwithspringbootjava.application.services.PersonService;
import com.rsfrancisco.restwithspringbootjava.application.valueobjects.PersonVO;
import com.rsfrancisco.restwithspringbootjava.domain.utils.MediaTypeUtil;
import com.rsfrancisco.restwithspringbootjava.domain.utils.UrlUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/persons",
                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaTypeUtil.APPLICATION_YAML_VALUE })
@Tag(name="Persons", description = "Endpoints for managing person")
public class PersonsController {
    @Autowired
    private PersonService _service;

    @GetMapping
    @Operation(
            summary = "List all persons",
            description = "List all persons",
            tags={"Persons"},
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))),
                    @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "401", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            }
    )
    public ResponseEntity<List<PersonVO>> findAll() {
        var persons = _service.getAll();
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonsController.class).findById(p.getKey())).withSelfRel()));
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<PersonVO> findById(@PathVariable Long id) {
        var person = _service.getById(id);
        // adicionando o hateoas
        person.add(linkTo(methodOn(PersonsController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok().body(person);
    }

    @PostMapping
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO model) {
        var person = _service.insert(model);
        return ResponseEntity.created(UrlUtil.getURI("/{id}", person.getKey())).build();
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
