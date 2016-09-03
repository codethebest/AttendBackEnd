package com.liyolomoko.attendeventappfrontend.service.event.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.liyolomoko.attendeventappfrontend.conf.databases.App;
import com.liyolomoko.attendeventappfrontend.domain.event.EventAddress;
import com.liyolomoko.attendeventappfrontend.repositories.event.EventAddressRepository;
import com.liyolomoko.attendeventappfrontend.repositories.event.impl.EventAddressRepositoryimpl;
import com.liyolomoko.attendeventappfrontend.service.event.EventAddressService;

import java.sql.SQLException;

/**
 * Created by Leo on 5/8/2016.
 */
public class EventAddressServiceimpl extends IntentService implements EventAddressService{

    private final EventAddressRepository repo;

    public static final String ACTION_ADD = "com.example.leo.tpassignment61.services.event.impl.action.ADD";
    public static final String ACTION_UPDATE = "com.example.leo.tpassignment61.services.event.impl.action.UPDATE";

    // TODO: Rename parameters
    public static final String EXTRA_ADD = "com.example.leo.tpassignment61.services.event.impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.example.leo.tpassignment61.services.event.impl.extra.UPDATE";

    private static EventAddressServiceimpl service = null;

    public static EventAddressServiceimpl getInstance()
    {
        if(service ==null)
            service = new EventAddressServiceimpl();
        return service;
    }

    public EventAddressServiceimpl() {
        super("EventAddressServiceimpl");
        repo= new EventAddressRepositoryimpl(App.getAppContext());
    }

    @Override
    public void addPEventAddress(Context context,EventAddress address)
    {
        Intent intent = new Intent (context,EventBasicInformationServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, address);
        context.startService(intent);
    }

    @Override
    public void updateEventAddress(Context context,EventAddress address)
    {
        Intent intent =new Intent(context,EventBasicInformationServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, address);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final EventAddress personAddress = (EventAddress)intent.getSerializableExtra(EXTRA_ADD);

                postAddress(personAddress);
            } else if (ACTION_UPDATE.equals(action)) {
                final EventAddress personAddress = (EventAddress)intent.getSerializableExtra(EXTRA_UPDATE);;
                updateaddress(personAddress);
            }
        }
    }

    public void postAddress(EventAddress eventAddress)
    {
        try {
            repo.save(eventAddress);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateaddress(EventAddress eventAddress) {
        try {
            repo.save(eventAddress);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

