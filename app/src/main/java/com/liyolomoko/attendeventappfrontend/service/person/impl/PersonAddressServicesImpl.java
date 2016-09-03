package com.liyolomoko.attendeventappfrontend.service.person.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.liyolomoko.attendeventappfrontend.conf.databases.App;
import com.liyolomoko.attendeventappfrontend.domain.person.PersonAddress;
import com.liyolomoko.attendeventappfrontend.repositories.person.PersonAddressRepository;
import com.liyolomoko.attendeventappfrontend.repositories.person.impl.PersonAddressRepositoryimpl;
import com.liyolomoko.attendeventappfrontend.service.person.PersonAddressService;

import java.sql.SQLException;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */

public class PersonAddressServicesImpl extends IntentService implements PersonAddressService{

    private final PersonAddressRepository repo;

    public static final String ACTION_ADD = "com.example.leo.tpassignment61.services.person.impl.action.ADD";
    public static final String ACTION_UPDATE = "com.example.leo.tpassignment61.services.person.impl.action.UPDATE";

    // TODO: Rename parameters
    public static final String EXTRA_ADD = "com.example.leo.tpassignment61.services.person.impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.example.leo.tpassignment61.services.person.impl.extra.UPDATE";

    private static PersonAddressServicesImpl service = null;

    public static PersonAddressServicesImpl getInstance()
    {
        if(service ==null)
            service = new PersonAddressServicesImpl();
        return service;
    }

    public PersonAddressServicesImpl() {
        super("PersonAddressServicesImpl");
        repo= new PersonAddressRepositoryimpl(App.getAppContext());
    }

    @Override
    public void addPersonAddress(Context context,PersonAddress address)
    {
        Intent intent = new Intent (context,PersonAddressServicesImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, address);
        context.startService(intent);
    }

    @Override
    public void updatePersonAddress(Context context,PersonAddress address)
    {
        Intent intent =new Intent(context,PersonAddressServicesImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, address);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PersonAddress personAddress = (PersonAddress)intent.getSerializableExtra(EXTRA_ADD);

                postAddress(personAddress);
            } else if (ACTION_UPDATE.equals(action)) {
                final PersonAddress personAddress = (PersonAddress)intent.getSerializableExtra(EXTRA_UPDATE);;
                updateaddress(personAddress);
            }
        }
    }

    public void postAddress(PersonAddress personAddress)
    {
        try {
            repo.save(personAddress);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateaddress(PersonAddress personAddress) {
        try {
             repo.save(personAddress);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
