package com.liyolomoko.attendeventappfrontend.service.event;

import android.content.Context;

import com.liyolomoko.attendeventappfrontend.domain.event.EventContact;

/**
 * Created by Leo on 5/8/2016.
 */
public interface EventContactService {
    void addEventContact(Context context, EventContact eventContact);
    void updateEventContact(Context context, EventContact eventContact);
}
