package com.example.leo.tpassignment61.factories;

import com.example.leo.tpassignment61.domain.person.PersonContact;
import com.example.leo.tpassignment61.factories.person.PersonContactFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Leo on 4/18/2016.
 */
public class PersonContactFactoryTest {

    @Test
    public void testPersonContact()throws Exception
    {
        PersonContact personContact = PersonContactFactory.getPersonContact("LeeRocky",02,"leo.moko@gmail.com", "www.leo.com");
        Assert.assertEquals("leo.moko@gmail.com", personContact.getEmail());
    }

    @Test
    public void testUpdate ()throws Exception
    {
        PersonContact personContact = PersonContactFactory.getPersonContact("LeeRocky", 02, "leo.moko@gmail.com", "www.leo.com");
        PersonContact personContact1 = new PersonContact.Builder()
                .copy(personContact)
                .email("liyolo.moko8@gmail.com")
                .build();
        Assert.assertEquals("liyolo.moko8@gmail.com",personContact1.getEmail());
    }
}
