package com.rsfrancisco.restwithspringbootjava.domain.interfaces.repositories;

import com.rsfrancisco.restwithspringbootjava.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Long> {
}
