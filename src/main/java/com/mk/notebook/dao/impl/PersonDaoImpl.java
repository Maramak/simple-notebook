package com.mk.notebook.dao.impl;

import com.mk.notebook.dao.PersonDao;
import com.mk.notebook.dao.mapping.query.PersonFindAllQuery;
import com.mk.notebook.dao.mapping.query.PersonSaveQuery;
import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.DaoException;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Pavel Fursov
 */
public class PersonDaoImpl implements PersonDao {

    private final PersonFindAllQuery findAllQuery;

    private final PersonSaveQuery saveQuery;

    public PersonDaoImpl(final DataSource dataSource) {
        findAllQuery = new PersonFindAllQuery(dataSource);
        saveQuery = new PersonSaveQuery(dataSource);
    }

    public List<PersonEntity> findAll() throws DaoException {
        return findAllQuery.find();
    }

    public void save(PersonEntity entity) throws DaoException {
        saveQuery.save(entity);
    }

}
