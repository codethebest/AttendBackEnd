package com.liyolomoko.attendeventappfrontend.factories.person;

import com.liyolomoko.attendeventappfrontend.domain.person.PersonBasicInformation;

/**
 * Created by Leo on 4/18/2016.
 */
public class PersonBasicInformationFactory {
    public static PersonBasicInformation getPersonBasicInformation (String sex,String hometown, int nowMonth, int nowYear, int age, String interestIn)
    {
        return new PersonBasicInformation.Builder()
                .sex(sex)
                .age(age)
                .hometown(hometown)
                .nowMonth(nowMonth)
                .nowYear(nowYear)
                .interestedIn(interestIn)
                .build();


    }

}
