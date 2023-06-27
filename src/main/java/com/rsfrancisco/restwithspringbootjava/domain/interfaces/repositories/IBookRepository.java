package com.rsfrancisco.restwithspringbootjava.domain.interfaces.repositories;

import com.rsfrancisco.restwithspringbootjava.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IBookRepository extends JpaRepository<Book, Long> {
}
