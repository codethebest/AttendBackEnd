package com.liyolomoko.attendeventappfrontend.service.person;

import android.content.Context;

import com.liyolomoko.attendeventappfrontend.domain.person.PersonAddress;

/**
 * Created by Leo on 5/8/2016.
 */
public interface PersonAddressService {
    void addPersonAddress(Context context, PersonAddress address);
    void updatePersonAddress(Context context, PersonAddress address);
}
