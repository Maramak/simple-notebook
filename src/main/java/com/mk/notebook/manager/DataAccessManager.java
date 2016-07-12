package com.mk.notebook.manager;

import com.mk.notebook.dao.PersonDao;
import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.DaoException;

import java.util.List;

/**
 * @author Pavel Fursov
 */
public class DataAccessManager {

    private final PersonDao personDao;

    public DataAccessManager(final PersonDao personDao) {
        this.personDao = personDao;
    }

    public List<PersonEntity> getAllPersons(Long offset, Long limit) throws DaoException {
        return personDao.find(offset, limit);
    }

    public PersonEntity getPerson(Long id) throws DaoException {
        return personDao.findById(id);
    }

    public PersonEntity savePerson(final PersonEntity entity) throws DaoException {
        return personDao.save(entity);
    }

    public void updatePerson(final PersonEntity entity) throws DaoException {
        personDao.update(entity);
    }

}
