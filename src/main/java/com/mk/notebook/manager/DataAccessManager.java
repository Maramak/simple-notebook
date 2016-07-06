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

    public List<PersonEntity> getAllPersons() throws DaoException {
        return personDao.findAll();
    }

    public void savePerson(final PersonEntity entity) throws DaoException {
        personDao.save(entity);
    }

}
