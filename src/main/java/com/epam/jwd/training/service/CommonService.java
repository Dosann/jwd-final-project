package com.epam.jwd.training.service;

import com.epam.jwd.training.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface CommonService<T extends BaseEntity> {

    Optional<List<T>> findAll();

    Optional<T> save(T dto);

    boolean create(T entity);

}
