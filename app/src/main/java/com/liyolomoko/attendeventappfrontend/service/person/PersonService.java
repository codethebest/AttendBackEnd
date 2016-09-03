package com.liyolomoko.attendeventappfrontend.service.person;

import android.content.Context;

import com.liyolomoko.attendeventappfrontend.domain.person.Person;

/**
 * Created by Leo on 5/8/2016.
 */
public interface PersonService {
    void addPerson(Context context, Person person);
    void updatePerson(Context context, Person person);
}
