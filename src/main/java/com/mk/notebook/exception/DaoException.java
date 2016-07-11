package com.mk.notebook.exception;

/**
 * @author Pavel Fursov
 */
public class DaoException extends ApplicationException {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

}
