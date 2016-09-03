package com.example.leo.tpassignment61.factories;

import com.example.leo.tpassignment61.domain.user.PostAnEvent;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Leo on 4/24/2016.
 */
public class PostAnEventFactory {

    @Test
    public void testPerson()throws Exception
    {
        PostAnEvent postAnEvent = com.example.leo.tpassignment61.factories.user.PostAnEventFactory.getPostAnEvent("Pool party happening lads","poolparty");
        Assert.assertNotNull(postAnEvent.getPost());
    }


}
