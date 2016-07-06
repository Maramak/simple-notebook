package com.mk.notebook.dao;

import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.DaoException;

import java.util.List;

/**
 * @author Pavel Fursov
 */
public interface PersonDao {

    List<PersonEntity> findAll() throws DaoException;

    void save(PersonEntity entity) throws DaoException;

}
