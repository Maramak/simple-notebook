package com.mk.notebook.util;

import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.exception.ValidationException;

import java.util.Properties;

import static com.mk.notebook.util.Constants.LIMIT_BELOW_ZERO;
import static com.mk.notebook.util.Constants.UPDATE_ID_MISSING;

/**
 * @author Pavel Fursov
 */
public class ValidationUtils {

    private Properties properties;

    public ValidationUtils(final Properties properties) {
        this.properties = properties;
    }

    public void checkLimit(final Long limit) throws ValidationException {
        if (limit <= 0) {
            throw new ValidationException(properties.getProperty(LIMIT_BELOW_ZERO));
        }
    }

    public void checkIdForUpdate(final PersonEntity entity) throws ValidationException {
        Long id = entity.getId();
        if (id == null || id <= 0) {
            throw new ValidationException(properties.getProperty(UPDATE_ID_MISSING));
        }
    }

}
