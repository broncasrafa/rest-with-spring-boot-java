package com.rsfrancisco.restwithspringbootjava.domain.interfaces.repositories;

import java.util.List;

public interface IBaseOperationsRepository<T> {
    List<T> getAll();
    T getById(Long id);
    T insert(T entity);
    T update(Long id, T entity);
    void delete(Long id);
}
