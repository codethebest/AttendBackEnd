package com.example.leo.tpassignment61.factories;

import com.example.leo.tpassignment61.domain.event.EventBasicInformation;
import com.example.leo.tpassignment61.factories.event.EventBasicInformationFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Leo on 4/18/2016.
 */
public class EventBasicInformationFactoryTest {

    @Test
    public void test()throws Exception
    {
        EventBasicInformation event = EventBasicInformationFactory.getEventBasicInformation("Pool party");
        Assert.assertEquals("Pool party", event.getEventtype());
    }

    @Test
    public void test2()throws Exception
    {
        EventBasicInformation event = EventBasicInformationFactory.getEventBasicInformation("Pool party");
        Assert.assertNotEquals("bitch party", event.getEventtype());
    }
}
