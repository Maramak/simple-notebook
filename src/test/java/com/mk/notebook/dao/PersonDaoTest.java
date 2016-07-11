package com.mk.notebook.dao;

import com.mk.notebook.entity.PersonEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Pavel Fursov
 */
@ContextConfiguration(locations = "classpath:dao-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional("txManager")
public class PersonDaoTest {

    @Autowired
    private PersonDao personDao;

    private final PersonEntity testPerson = new PersonEntity();

    @Before
    public void setUpTestPerson() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        String firstName = "TestFirstName";
        String lastName = "TestLastName";
        String middleName = "TestMiddleName";
        Date birthday = sdf.parse("2001/11/04");

        testPerson.setFirstName(firstName);
        testPerson.setLastName(lastName);
        testPerson.setMiddleName(middleName);
        testPerson.setBirthday(birthday);
    }

    @Test
    @Rollback
    public void find() throws Exception {
        long offset = 0;
        long limit = 3;

        List<PersonEntity> personEntities = personDao.find(offset, limit);
        Assert.assertEquals("Not all persons were found in DB", limit, personEntities.size());
    }

    @Test
    @Rollback
    public void save() throws Exception {
        PersonEntity savedEntity = personDao.save(testPerson);

        long offset = 0;
        long limit = 4;

        List<PersonEntity> personEntities = personDao.find(offset, limit);
        Assert.assertEquals("Not all persons were found in DB", limit, personEntities.size());
        Assert.assertTrue("Newly added person does not exists in DB", personEntities.contains(savedEntity));
    }

    @Test
    @Rollback
    public void update() throws Exception {
        PersonEntity savedEntity = personDao.save(testPerson);

        String updatedLastName = "UpdatedLastName";

        savedEntity.setLastName(updatedLastName);

        personDao.update(savedEntity);

        long offset = 0;
        long limit = 4;

        List<PersonEntity> personEntities = personDao.find(offset, limit);
        Assert.assertEquals("Not all persons were found in DB", limit, personEntities.size());
        Assert.assertTrue("Newly added person does not exists in DB", personEntities.contains(savedEntity));

        for (PersonEntity entity : personEntities) {
            if (entity.equals(savedEntity)) {
                Assert.assertEquals("Person update failed", updatedLastName, entity.getLastName());
                return;
            }
        }

        Assert.fail("Person update failed");
    }

}