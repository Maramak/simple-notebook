package com.mk.notebook.controller;

import com.mk.notebook.entity.PersonEntity;
import com.mk.notebook.entity.http.PersonRequestEntity;
import com.mk.notebook.entity.http.ResponseEntity;
import com.mk.notebook.exception.ApplicationException;
import com.mk.notebook.manager.DataAccessManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Pavel Fursov
 */
@Controller
@RequestMapping("/root")
public class NotebookController {

    private final static Logger LOG = LoggerFactory.getLogger(NotebookController.class);

    @Autowired
    private DataAccessManager dataAccessManager;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/person/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity listPersons() {
        try {
            List<PersonEntity> personEntities = dataAccessManager.getAllPersons();
            LOG.debug(String.format("Received %d person entities", personEntities.size()));

            return ResponseEntity.buildSuccess(objectMapper.writeValueAsString(personEntities));
        } catch (ApplicationException e) {
            LOG.error(String.format("Unable to receive persons list: %s", e.getMessage()), e);

            return ResponseEntity.buildFailed(e.getMessage());
        } catch (Throwable e) {
            LOG.error(String.format("Something really bad have happened: %s", e.getMessage()), e);

            return ResponseEntity.buildFailed(e.toString());
        }
    }

    @RequestMapping(value = "/person/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity addPerson(@RequestBody PersonRequestEntity entity) {
        try {
            final PersonEntity person = new PersonEntity(entity);

            dataAccessManager.savePerson(person);
            LOG.debug(String.format("Added %s", person));

            return ResponseEntity.buildSuccess(objectMapper.writeValueAsString(person.getId()));
        } catch (ApplicationException e) {
            LOG.error(String.format("Unable to add %s | Error: %s", entity, e.getMessage()), e);

            return ResponseEntity.buildFailed(e.getMessage());
        } catch (Throwable e) {
            LOG.error(String.format("Something really bad have happened. %s | Error: %s", entity, e.getMessage()), e);

            return ResponseEntity.buildFailed(e.toString());
        }
    }

}
