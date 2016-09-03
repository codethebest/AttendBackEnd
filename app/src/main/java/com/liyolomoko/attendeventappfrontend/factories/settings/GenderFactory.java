package com.liyolomoko.attendeventappfrontend.factories.settings;


import com.liyolomoko.attendeventappfrontend.domain.settings.Gender;

/**
 * Created by hashcode on 2016/04/12.
 */
public class GenderFactory {
    public static Gender getGender(String name){
        return new Gender.Builder()
                .name(name)
                .build();

    }
}
