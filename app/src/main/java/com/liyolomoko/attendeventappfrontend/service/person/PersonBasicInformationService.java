package com.liyolomoko.attendeventappfrontend.service.person;

import android.content.Context;

import com.liyolomoko.attendeventappfrontend.domain.person.PersonBasicInformation;

/**
 * Created by Leo on 5/8/2016.
 */
public interface PersonBasicInformationService {
    void addPersonBasicInformation(Context context, PersonBasicInformation basicInformationService);
    void updatePersonBasicInformation(Context context, PersonBasicInformation basicInformationService);
}
