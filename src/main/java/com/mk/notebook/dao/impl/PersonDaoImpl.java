package com.mk.notebook.dao.impl;

import com.mk.notebook.dao.PersonDao;
import com.mk.notebook.dao.mapping.query.PersonFindAllQuery;
import com.mk.notebook.dao.mapping.query.PersonFindByIdQuery;
import com.mk.notebook.dao.mapping.query.PersonSaveQuery;
import com.mk.notebook.dao.mapping.query.PersonUpdateQuery;
import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Pavel Fursov
 */
public class PersonDaoImpl implements PersonDao {

    @Autowired
    private PersonFindAllQuery findAll;

    @Autowired
    private PersonFindByIdQuery findById;

    @Autowired
    private PersonSaveQuery saveQuery;

    @Autowired
    private PersonUpdateQuery updateQuery;

    @Override
    public List<PersonEntity> find(long offset, long limit) throws DaoException {
        return findAll.find(offset, limit);
    }

    @Override
    public PersonEntity findById(long id) throws DaoException {
        return findById.find(id);
    }

    @Override
    public PersonEntity save(final PersonEntity entity) throws DaoException {
        return saveQuery.save(entity);
    }

    @Override
    public void update(final PersonEntity entity) throws DaoException {
        updateQuery.update(entity);
    }

}
