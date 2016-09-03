package com.liyolomoko.attendeventappfrontend.service.event;

import android.content.Context;

import com.liyolomoko.attendeventappfrontend.domain.event.Event;

/**
 * Created by Leo on 5/8/2016.
 */
public interface EventService{
    void addEvent(Context context, Event event);
    void updateEvent(Context context, Event event);
}
