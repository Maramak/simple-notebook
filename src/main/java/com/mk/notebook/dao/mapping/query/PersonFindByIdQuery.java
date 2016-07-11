package com.mk.notebook.dao.mapping.query;

import com.mk.notebook.dao.mapping.row.PersonMapper;
import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.DaoException;
import org.springframework.jdbc.core.SqlParameter;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author Pavel Fursov
 */
public class PersonFindByIdQuery extends PersonMapper {

    private static final String SQL = String.format(
            "SELECT `%s`, `%s`, `%s`, `%s`, `%s` FROM `person` WHERE `%s` = ? LIMIT 1",
            ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, BIRTHDAY, ID);

    public PersonFindByIdQuery(final DataSource dataSource) {
        setDataSource(dataSource);
        setSql(SQL);

        declareParameter(new SqlParameter(Types.INTEGER));

        compile();
    }

    public PersonEntity find(long id) throws DaoException {
        try {
            return findObject(id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
