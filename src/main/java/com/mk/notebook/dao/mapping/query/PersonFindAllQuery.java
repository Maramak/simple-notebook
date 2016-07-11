package com.mk.notebook.dao.mapping.query;

import com.mk.notebook.dao.mapping.row.PersonMapper;
import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.DaoException;
import org.springframework.jdbc.core.SqlParameter;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * @author Pavel Fursov
 */
public class PersonFindAllQuery extends PersonMapper {

    private static final String SQL = String.format(
            "SELECT `%s`, `%s`, `%s`, `%s`, `%s` FROM `person` OFFSET ? LIMIT ?",
            ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, BIRTHDAY);

    public PersonFindAllQuery(final DataSource dataSource) {
        setDataSource(dataSource);
        setSql(SQL);

        declareParameter(new SqlParameter(Types.INTEGER));
        declareParameter(new SqlParameter(Types.INTEGER));

        compile();
    }

    public List<PersonEntity> find(long offset, long limit) throws DaoException {
        try {
            return execute(offset, limit);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
