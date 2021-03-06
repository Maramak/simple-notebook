package com.mk.notebook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.entity.http.PersonRequestEntity;
import com.mk.notebook.exception.ApplicationException;
import com.mk.notebook.exception.ValidationException;
import com.mk.notebook.manager.DataAccessManager;
import com.mk.notebook.util.Constants;
import com.mk.notebook.util.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.mk.notebook.util.Constants.APPLICATION_JSON_UTF_8;
import static com.mk.notebook.util.Constants.DATE_FORMAT;
import static com.mk.notebook.util.Constants.DEFAULT_LIMIT;
import static com.mk.notebook.util.Constants.DEFAULT_OFFSET;

/**
 * @author Pavel Fursov
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    private final static Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private DataAccessManager dataAccessManager;

    @Autowired
    private ValidationUtils validationUtils;

    private final ObjectMapper objectMapper;

    @Autowired
    public PersonController(ObjectMapper objectMapper) {
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));

        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {APPLICATION_JSON_UTF_8})
    @ResponseBody
    public ResponseEntity<String> listPersons(
            @RequestParam(value = Constants.OFFSET, required = false, defaultValue = DEFAULT_OFFSET)
            final Long offset,

            @RequestParam(value = Constants.LIMIT, required = false, defaultValue = DEFAULT_LIMIT)
            final Long limit) {

        try {
            final List<PersonEntity> personEntities = dataAccessManager.getAllPersons(offset, limit);
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Received %d person entities", personEntities.size()));
            }

            return ResponseEntity.ok(objectMapper.writeValueAsString(personEntities));
        } catch (ApplicationException e) {
            LOG.error(String.format("Unable to receive persons list: %s", e.getMessage()), e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            LOG.error(String.format("Something really bad have happened: %s", e.getMessage()), e);

            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {APPLICATION_JSON_UTF_8})
    @ResponseBody
    public ResponseEntity<String> getUser(@RequestParam(value = Constants.ID) final Long id) {
        try {
            final PersonEntity personEntity = dataAccessManager.getPerson(id);
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Found person: %s", personEntity));
            }

            return ResponseEntity.ok(objectMapper.writeValueAsString(personEntity));
        } catch (ApplicationException e) {
            LOG.error(String.format("Unable to receive persons list: %s", e.getMessage()), e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            LOG.error(String.format("Something really bad have happened: %s", e.getMessage()), e);

            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = {APPLICATION_JSON_UTF_8})
    @ResponseBody
    public ResponseEntity<String> addPerson(@Valid @RequestBody final PersonRequestEntity entity) {
        try {
            final PersonEntity person = new PersonEntity(entity);

            dataAccessManager.savePerson(person);
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Added %s", person));
            }

            return ResponseEntity.ok(person.getId().toString());
        } catch (ApplicationException e) {
            LOG.error(String.format("Unable to add %s | Error: %s", entity, e.getMessage()), e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            LOG.error(String.format("Something really bad have happened. %s | Error: %s", entity, e.getMessage()), e);

            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> updatePerson(@Valid @RequestBody final PersonRequestEntity entity) {
        try {
            final PersonEntity person = new PersonEntity(entity);

            validationUtils.checkIdForUpdate(person);

            dataAccessManager.updatePerson(person);
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Updated %s", person));
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            LOG.error(e.getMessage(), e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ApplicationException e) {
            LOG.error(String.format("Unable to update %s | Error: %s", entity, e.getMessage()), e);

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            LOG.error(String.format("Something really bad have happened. %s | Error: %s", entity, e.getMessage()), e);

            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
