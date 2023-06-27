package com.rsfrancisco.restwithspringbootjava.api.controllers;

import com.rsfrancisco.restwithspringbootjava.application.services.BookService;
import com.rsfrancisco.restwithspringbootjava.application.valueobjects.BookVO;
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

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/books",
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaTypeUtil.APPLICATION_YAML_VALUE })
@Tag(name="Books", description = "Endpoints for managing books")
public class BooksController {

    @Autowired
    private BookService _service;



    @Operation(
            summary = "List all books",
            description = "List all books",
            tags={"Books"},
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))),
                    @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "401", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @GetMapping
    public ResponseEntity<List<BookVO>> findAll() {
        var books = _service.getAll();
        // adicionando o hateoas
        books.stream().forEach(p -> p.add(linkTo(methodOn(BooksController.class).findById(p.getKey())).withSelfRel()));
        return ResponseEntity.ok().body(books);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<BookVO> findById(@PathVariable Long id) {
        var book = _service.getById(id);
        // adicionando o hateoas
        book.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok().body(book);
    }

    @PostMapping
    public ResponseEntity<BookVO> create (@RequestBody BookVO model) {
        var book = _service.insert(model);
        return ResponseEntity.created(UrlUtil.getURI("/{id}", book.getKey())).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<BookVO> findById(@PathVariable Long id, @RequestBody BookVO model) {
        var book = _service.update(id, model);
        return ResponseEntity.ok().body(book);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        _service.delete(id);
        return ResponseEntity.ok().body(true);
    }
}
