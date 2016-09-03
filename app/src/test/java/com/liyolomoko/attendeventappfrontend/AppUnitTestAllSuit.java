package com.example.leo.tpassignment61;

import com.example.leo.tpassignment61.factories.CommentFactoryTest;
import com.example.leo.tpassignment61.factories.EventAddressFactoryTest;
import com.example.leo.tpassignment61.factories.EventBasicInformationFactoryTest;
import com.example.leo.tpassignment61.factories.EventContactFactoryTest;
import com.example.leo.tpassignment61.factories.EventFactoryTest;
import com.example.leo.tpassignment61.factories.GenderFactoryTest;
import com.example.leo.tpassignment61.factories.PersonAddressFactoryTest;
import com.example.leo.tpassignment61.factories.PersonBasicInformationFactoryTest;
import com.example.leo.tpassignment61.factories.PersonContactFactoryTest;
import com.example.leo.tpassignment61.factories.UserRegistrationFactoryTest;
import com.example.leo.tpassignment61.factories.PostAnEventFactory;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Leo on 4/18/2016.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EventAddressFactoryTest.class,
        EventBasicInformationFactoryTest.class,
        EventContactFactoryTest.class,
        PersonAddressFactoryTest.class,
        GenderFactoryTest.class,
        PersonBasicInformationFactoryTest.class,
        PersonContactFactoryTest.class,
        PersonBasicInformationFactoryTest.class,
        UserRegistrationFactoryTest.class,
        EventFactoryTest.class,
        PostAnEventFactory.class,
        CommentFactoryTest.class})

public class AppUnitTestAllSuit {}
