package com.mk.notebook.dao;

import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.DaoException;

import java.util.List;

/**
 * @author Pavel Fursov
 */
public interface PersonDao {

    List<PersonEntity> find(long offset, long limit) throws DaoException;

    PersonEntity save(PersonEntity entity) throws DaoException;

    void update(PersonEntity entity) throws DaoException;

}
