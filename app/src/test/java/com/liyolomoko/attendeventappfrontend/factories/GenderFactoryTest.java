package com.example.leo.tpassignment61.factories;


import com.example.leo.tpassignment61.domain.settings.Gender;
import com.example.leo.tpassignment61.factories.settings.GenderFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by hashcode on 2016/04/14.
 */
public class GenderFactoryTest {
    @Test
    public void test1() throws Exception {
        Gender gender = GenderFactory.getGender("MALE");
        Assert.assertEquals("MALE", gender.getName());

    }

    @Test
    public void test2() throws Exception {
        Gender gender = GenderFactory.getGender("MALE");
        Gender newGender = new Gender
                .Builder()
                .copy(gender)
                .name("FEMALE")
                .build();
        Assert.assertEquals("FEMALE",newGender.getName());

    }
}
