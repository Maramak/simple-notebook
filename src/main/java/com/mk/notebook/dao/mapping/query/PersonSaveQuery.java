package com.mk.notebook.dao.mapping.query;

import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.DaoException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import static com.mk.notebook.dao.mapping.row.PersonMapper.BIRTHDAY;
import static com.mk.notebook.dao.mapping.row.PersonMapper.FIRST_NAME;
import static com.mk.notebook.dao.mapping.row.PersonMapper.LAST_NAME;
import static com.mk.notebook.dao.mapping.row.PersonMapper.MIDDLE_NAME;

/**
 * @author Pavel Fursov
 */
public class PersonSaveQuery extends SqlUpdate {

    private static final String SQL = String.format(
            "INSERT INTO `person` (`%s`, `%s`, `%s`, `%s`) VALUES (:%s, :%s, :%s, :%s)",
            FIRST_NAME, LAST_NAME, MIDDLE_NAME, BIRTHDAY, FIRST_NAME, LAST_NAME, MIDDLE_NAME, BIRTHDAY);

    public PersonSaveQuery(final DataSource dataSource) {
        setDataSource(dataSource);
        setSql(SQL);

        declareParameter(new SqlParameter(FIRST_NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(LAST_NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(MIDDLE_NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(BIRTHDAY, Types.TIMESTAMP));

        compile();
    }

    public void save(final PersonEntity entity) throws DaoException {
        try {
            final Map<String, Object> paramMap = new HashMap<>();
            paramMap.put(FIRST_NAME, entity.getFirstName());
            paramMap.put(LAST_NAME, entity.getLastName());
            paramMap.put(MIDDLE_NAME, entity.getMiddleName());
            paramMap.put(BIRTHDAY, entity.getBirthday());

            final KeyHolder keyHolder = new GeneratedKeyHolder();
            updateByNamedParam(paramMap, keyHolder);

            entity.setId(keyHolder.getKey().longValue());
        } catch (RuntimeException e) {
            throw new DaoException(e);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
