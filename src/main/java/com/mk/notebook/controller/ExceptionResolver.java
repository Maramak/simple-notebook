package com.mk.notebook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.mk.notebook.util.Constants.APPLICATION_JSON_UTF_8;

/**
 * @author Pavel Fursov
 */
@ControllerAdvice
public class ExceptionResolver {

    private final static Logger LOG = LoggerFactory.getLogger(ExceptionResolver.class);

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus
    private ResponseEntity<String> validationError(MethodArgumentNotValidException ex) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF_8);

        BindingResult result = ex.getBindingResult();
        try {
            final String errorsJson = getErrorsJson(result.getFieldErrors());
            LOG.warn(String.format("Validation failed: %s", errorsJson));

            return new ResponseEntity<>(errorsJson, headers, HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>(String.format("Error building response from: %s", ex.getMessage()), headers,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus
    private ResponseEntity<String> serviceError(Exception ex) {
        LOG.error(ex.getMessage(), ex);

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getErrorsJson(final List<FieldError> fieldErrorList) throws IOException {
        if (fieldErrorList != null && fieldErrorList.size() > 0) {
            final List<String> list = new ArrayList<>();
            for (FieldError error : fieldErrorList) {
                list.add(String.format("Field '%s' validation failed. '%s' rejected. Reason: %s",
                        error.getField(),
                        error.getRejectedValue(),
                        error.getDefaultMessage()));
            }

            return objectMapper.writeValueAsString(list);
        }

        return null;
    }

}
