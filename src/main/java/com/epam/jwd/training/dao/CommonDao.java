package com.epam.jwd.training.dao;

import com.epam.jwd.training.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface CommonDao<T extends BaseEntity> {

    Optional<List<T>> findAll();

    boolean create(T entity);



}
