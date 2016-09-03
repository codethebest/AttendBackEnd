package com.example.leo.tpassignment61.factories;

import com.example.leo.tpassignment61.domain.event.EventContact;
import com.example.leo.tpassignment61.factories.event.EventContactFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Leo on 4/18/2016.
 */
public class EventContactFactoryTest {
    @Test
    public void testPersonAddress()throws Exception
    {
        EventContact eventContact = EventContactFactory.getEventContact(021,"abc@gmail.com","www.google.com");
        Assert.assertEquals("www.google.com", eventContact.getWebsite());
    }

    @Test
    public void testPersonAddress2()throws Exception
    {
        EventContact eventContact = EventContactFactory.getEventContact(021, "abc@gmail.com", "www.google.com");
        Assert.assertNotEquals(452, eventContact.getPhone());
    }
}
