package com.mk.notebook.entity.http;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Pavel Fursov
 */
@JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
public class ResponseEntity {

    private static final int OK = 200;

    private static final int VALIDATION_ERROR = 403;

    private static final int SERVICE_ERROR = 500;

    private int code;

    private String data;

    private ResponseEntity(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static ResponseEntity buildSuccess(final String data) {
        return new ResponseEntity(OK, data);
    }

    public static ResponseEntity buildFailed(final String message) {
        return new ResponseEntity(SERVICE_ERROR, message);
    }

    public static ResponseEntity buildValidationFailed(final String message) {
        return new ResponseEntity(VALIDATION_ERROR, message);
    }

}
