package com.liyolomoko.attendeventappfrontend.factories.event;

import com.liyolomoko.attendeventappfrontend.domain.event.EventContact;

/**
 * Created by Leo on 4/18/2016.
 */
public class EventContactFactory {
    public static EventContact getEventContact (String phone ,String email, String website)
    {
        return new EventContact.Builder()
                .email(email)
                .phone(phone)
                .website(website)
                .build();
    }
}
