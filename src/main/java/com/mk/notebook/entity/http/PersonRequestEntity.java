package com.mk.notebook.entity.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mk.notebook.entity.Entity;

/**
 * @author Pavel Fursov
 */
public class PersonRequestEntity implements Entity {

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    @JsonProperty("id")
    private Long id;

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    @JsonProperty("first_name")
    private String firstName;

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    @JsonProperty("last_name")
    private String lastName;

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    @JsonProperty("middle_name")
    private String middleName;

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    @JsonProperty("birthday")
    private Long birthday;

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

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "PersonRequestEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthday=" + birthday +
                '}';
    }

}
