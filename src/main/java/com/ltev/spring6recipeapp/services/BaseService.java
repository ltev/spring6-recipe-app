package com.ltev.spring6recipeapp.services;

import java.util.List;
import java.util.Optional;


public interface BaseService<T, ID> {

    Optional<T> getById(ID id);

    List<T> getAll();

    T save(T t);

    void deleteById(ID id);
}
