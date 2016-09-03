package com.liyolomoko.attendeventappfrontend.service.event.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.liyolomoko.attendeventappfrontend.conf.databases.App;
import com.liyolomoko.attendeventappfrontend.domain.event.Event;
import com.liyolomoko.attendeventappfrontend.domain.event.EventContact;
import com.liyolomoko.attendeventappfrontend.repositories.event.EventRepository;
import com.liyolomoko.attendeventappfrontend.repositories.event.impl.EventRepositoryimpl;
import com.liyolomoko.attendeventappfrontend.service.event.EventContactService;

import java.sql.SQLException;

/**
 * Created by Leo on 5/8/2016.
 */
public class EventServiceimpl extends IntentService implements EventContactService {

    private final EventRepository repo;

    public static final String ACTION_ADD = "com.example.leo.tpassignment61.services.person.impl.action.ADD";
    public static final String ACTION_UPDATE = "com.example.leo.tpassignment61.services.person.impl.action.UPDATE";

    // TODO: Rename parameters
    public static final String EXTRA_ADD = "com.example.leo.tpassignment61.services.person.impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.example.leo.tpassignment61.services.person.impl.extra.UPDATE";

    private static EventServiceimpl service = null;

    public static EventServiceimpl getInstance()
    {
        if(service ==null)
            service = new EventServiceimpl();
        return service;
    }

    public EventServiceimpl() {
        super("PersonServiceimpl");
        repo= new EventRepositoryimpl(App.getAppContext());
    }


    @Override
    public void addEventContact(Context context, EventContact eventContact) {
        Intent intent = new Intent (context,EventServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, eventContact);
        context.startService(intent);
    }

    @Override
    public void updateEventContact(Context context, EventContact eventContact) {
        Intent intent =new Intent(context,EventServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, eventContact);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Event event = (Event)intent.getSerializableExtra(EXTRA_ADD);

                postAddress(event);
            } else if (ACTION_UPDATE.equals(action)) {
                final Event event = (Event)intent.getSerializableExtra(EXTRA_UPDATE);;
                updateaddress(event);
            }
        }
    }

    public void postAddress(Event event)
    {
        try {
            repo.save(event);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateaddress(Event event) {
        try {
            repo.save(event);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
