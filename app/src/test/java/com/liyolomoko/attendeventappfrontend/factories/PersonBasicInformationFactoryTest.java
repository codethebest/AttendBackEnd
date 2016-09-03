package com.example.leo.tpassignment61.factories;

import com.example.leo.tpassignment61.domain.person.PersonBasicInformation;
import com.example.leo.tpassignment61.factories.person.PersonBasicInformationFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Leo on 4/18/2016.
 */
public class PersonBasicInformationFactoryTest {
    @Test
    public void testPersonBasicInformation()throws Exception
    {
        PersonBasicInformation personBasicInformation = PersonBasicInformationFactory.getPersonBasicInformation("Male","Khayelitsha",06,2016,19,"Cricket");
        Assert.assertEquals("Male", personBasicInformation.getSex());
    }

    @Test
    public void testUpdate ()throws Exception
    {
        PersonBasicInformation personBasicInformation = PersonBasicInformationFactory.getPersonBasicInformation("Male", "Khayelitsha", 06, 2016, 19, "Cricket");
        PersonBasicInformation personBasicInformation1 = new PersonBasicInformation.Builder()
                .copy(personBasicInformation)
                .interestedIn("Cricket")
                .build();
        Assert.assertNotEquals("Soccer",personBasicInformation1.getInterestedIn());

    }


}
