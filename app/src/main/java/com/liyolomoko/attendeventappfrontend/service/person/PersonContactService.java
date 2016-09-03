package com.liyolomoko.attendeventappfrontend.service.person;

import android.content.Context;

import com.liyolomoko.attendeventappfrontend.domain.person.PersonContact;

/**
 * Created by Leo on 5/8/2016.
 */
public interface PersonContactService {
    void addPersonContact(Context context, PersonContact personContactService);
    void updatePersonContact(Context context, PersonContact personContactService);
}
