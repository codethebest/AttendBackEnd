package com.example.leo.tpassignment61.factories;

import com.example.leo.tpassignment61.domain.person.Person;
import com.example.leo.tpassignment61.factories.person.PersonFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Leo on 4/18/2016.
 */
public class PersonTest {

    @Test
    public void testPerson()throws Exception
    {
        Person person = PersonFactory.getPerson("leo.moko@gmail.com","L123");
        Assert.assertEquals("leo.moko@gmail.com",person.getEmail());
    }

    @Test
    public void testUpdate ()throws Exception
    {
        Person person = PersonFactory.getPerson("Leo.moko8@gmail.com","454");
        Person newperson = new Person.Builder()
                .copy(person)
                .email("liyolo.moko8@gmail.com")
                .build();
        Assert.assertEquals("liyolo.moko8@gmail.com",newperson.getEmail());
        Assert.assertEquals("454",newperson.getAuvalue());
    }
}
