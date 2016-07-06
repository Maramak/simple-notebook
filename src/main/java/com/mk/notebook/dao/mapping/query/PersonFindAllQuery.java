package com.mk.notebook.dao.mapping.query;

import com.mk.notebook.dao.mapping.row.PersonMapper;
import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.DaoException;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Pavel Fursov
 */
public class PersonFindAllQuery extends PersonMapper {

    private static final String SQL = String.format(
            "SELECT `%s`, `%s`, `%s`, `%s`, `%s` FROM `person`",
            ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, BIRTHDAY);

    public PersonFindAllQuery(final DataSource dataSource) {
        setDataSource(dataSource);
        setSql(SQL);
        compile();
    }

    public List<PersonEntity> find() throws DaoException {
        try {
            return execute();
        } catch (RuntimeException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
