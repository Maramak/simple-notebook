package com.mk.notebook.entity.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mk.notebook.entity.Entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Pavel Fursov
 */
public class PersonRequestEntity implements Entity {

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    @JsonProperty("id")
    @Min(value = 1, message = "{id.below.zero}")
    private Long id;

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    @JsonProperty(value = "first_name", required = true)
    @Size(min = 1, max = 25, message = "{wrong.string.size}")
    @Pattern(regexp = "^\\D*$", message = "{string.contains.digits}")
    private String firstName;

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    @JsonProperty(value = "last_name", required = true)
    @Size(min = 1, max = 25, message = "{wrong.string.size}")
    @Pattern(regexp = "^\\D*$", message = "{string.contains.digits}")
    private String lastName;

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    @JsonProperty("middle_name")
    @Size(max = 25, message = "{wrong.string.size}")
    @Pattern(regexp = "^\\D*$", message = "{string.contains.digits}")
    private String middleName;

    @JsonInclude(value = JsonInclude.Include.ALWAYS)
    @JsonProperty(value = "birthday", required = true)
    @Past(message = "{date.in.future}")
    private Date birthday;

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
