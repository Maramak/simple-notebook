package com.mk.notebook.entity;

import com.mk.notebook.entity.http.PersonRequestEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author Pavel Fursov
 */
public class PersonEntity implements Entity {

    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date birthday;

    public PersonEntity() {
    }

    public PersonEntity(final PersonRequestEntity entity) {
        if (!Objects.isNull(entity.getId())) {
            id = entity.getId();
        }

        if (StringUtils.isNotEmpty(entity.getMiddleName())) {
            middleName = entity.getMiddleName();
        }

        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        birthday = new Date(entity.getBirthday());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
