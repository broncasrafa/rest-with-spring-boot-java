package com.rsfrancisco.restwithspringbootjava.application.services;

import com.rsfrancisco.restwithspringbootjava.application.exceptions.RequiredObjectIsNullException;
import com.rsfrancisco.restwithspringbootjava.application.exceptions.ResourceNotFoundException;
import com.rsfrancisco.restwithspringbootjava.application.mappers.Mapper;
import com.rsfrancisco.restwithspringbootjava.application.valueobjects.BookVO;
import com.rsfrancisco.restwithspringbootjava.domain.entities.Book;
import com.rsfrancisco.restwithspringbootjava.domain.interfaces.repositories.IBaseOperationsRepository;
import com.rsfrancisco.restwithspringbootjava.domain.interfaces.repositories.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService implements IBaseOperationsRepository<BookVO> {

    private Logger logger = Logger.getLogger(BookService.class.getName());

    @Autowired
    private IBookRepository _repository;

    @Override
    public List<BookVO> getAll() {
        var books = _repository.findAll();
        return Mapper.mapper(books, BookVO.class);
    }

    @Override
    public BookVO getById(Long id) {
        var book = _repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object not exists with id: "+id));
        return Mapper.mapper(book, BookVO.class);
    }

    @Override
    public BookVO insert(BookVO bookVO) {
        if (bookVO == null) throw new RequiredObjectIsNullException();

        Book book = Mapper.mapper(bookVO, Book.class);
        var persisted = _repository.save(book);
        return Mapper.mapper(persisted, BookVO.class);
    }

    @Override
    public BookVO update(Long id, BookVO bookVO) {
        if (bookVO == null) throw new RequiredObjectIsNullException();

        var db = _repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object not exists with id: "+id));
        db.setTitle(bookVO.getTitle());
        db.setAuthor(bookVO.getAuthor());
        db.setPrice(bookVO.getPrice());
        db.setLauchDate(bookVO.getLauchDate());

        var persisted = _repository.save(db);
        return Mapper.mapper(persisted, BookVO.class);
    }

    @Override
    public void delete(Long id) {
        var db = _repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object not exists with id: "+id));
        _repository.delete(db);
    }
}
