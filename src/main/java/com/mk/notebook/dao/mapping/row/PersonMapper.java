package com.mk.notebook.dao.mapping.row;

import com.mk.notebook.entity.PersonEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Pavel Fursov
 */
public class PersonMapper extends MappingSqlQuery<PersonEntity> implements RowMapper<PersonEntity> {

    public static final String ID = "id";

    public static final String FIRST_NAME = "first_name";

    public static final String LAST_NAME = "last_name";

    public static final String MIDDLE_NAME = "middle_name";

    public static final String BIRTHDAY = "birthday";

    public PersonEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        final PersonEntity entity = new PersonEntity();
        entity.setId(resultSet.getLong(ID));
        entity.setFirstName(resultSet.getString(FIRST_NAME));
        entity.setLastName(resultSet.getString(LAST_NAME));
        entity.setMiddleName(resultSet.getString(MIDDLE_NAME));
        entity.setBirthday(resultSet.getTimestamp(BIRTHDAY));

        return entity;
    }

}
