package com.mk.notebook.entity;

import com.mk.notebook.entity.http.PersonRequestEntity;

import java.util.Date;

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
        id = entity.getId();
        middleName = entity.getMiddleName();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        birthday = entity.getBirthday();
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
        return String.format("PersonEntity{id = %d, firstName = %s, lastName = %s, middleName = %s, birthday = %s}",
                id, firstName, lastName, middleName, birthday);
    }
}
