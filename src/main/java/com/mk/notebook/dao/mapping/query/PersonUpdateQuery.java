package com.mk.notebook.dao.mapping.query;

import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.DaoException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import static com.mk.notebook.dao.mapping.row.PersonMapper.BIRTHDAY;
import static com.mk.notebook.dao.mapping.row.PersonMapper.FIRST_NAME;
import static com.mk.notebook.dao.mapping.row.PersonMapper.ID;
import static com.mk.notebook.dao.mapping.row.PersonMapper.LAST_NAME;
import static com.mk.notebook.dao.mapping.row.PersonMapper.MIDDLE_NAME;

/**
 * @author Pavel Fursov
 */
public class PersonUpdateQuery extends SqlUpdate {

    private static final String SQL = String.format(
            "UPDATE `person` SET `%s` = :%s, `%s` = :%s, `%s` = :%s, `%s` = :%s WHERE `%s` = :%s",
            FIRST_NAME, FIRST_NAME, LAST_NAME, LAST_NAME, MIDDLE_NAME, MIDDLE_NAME, BIRTHDAY, BIRTHDAY, ID, ID);

    public PersonUpdateQuery(final DataSource dataSource) {
        setDataSource(dataSource);
        setSql(SQL);

        declareParameter(new SqlParameter(ID, Types.BIGINT));
        declareParameter(new SqlParameter(FIRST_NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(LAST_NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(MIDDLE_NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(BIRTHDAY, Types.TIMESTAMP));

        compile();
    }

    public void update(final PersonEntity entity) throws DaoException {
        try {
            final Map<String, Object> paramMap = new HashMap<>();
            paramMap.put(ID, entity.getId());
            paramMap.put(FIRST_NAME, entity.getFirstName());
            paramMap.put(LAST_NAME, entity.getLastName());
            paramMap.put(MIDDLE_NAME, entity.getMiddleName());
            paramMap.put(BIRTHDAY, entity.getBirthday());

            updateByNamedParam(paramMap);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
