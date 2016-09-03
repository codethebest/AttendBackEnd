package com.liyolomoko.attendeventappfrontend.service.person.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.liyolomoko.attendeventappfrontend.conf.databases.App;
import com.liyolomoko.attendeventappfrontend.domain.person.PersonContact;
import com.liyolomoko.attendeventappfrontend.repositories.person.PersonContactRepository;
import com.liyolomoko.attendeventappfrontend.repositories.person.impl.PersonContactRepositoryimpl;
import com.liyolomoko.attendeventappfrontend.service.person.PersonContactService;

import java.sql.SQLException;

/**
 * Created by Leo on 5/8/2016.
 */
public class PersonContactServiceimpl extends IntentService implements PersonContactService {
    private final PersonContactRepository repo;

    public static final String ACTION_ADD = "com.example.leo.tpassignment61.services.person.impl.action.ADD";
    public static final String ACTION_UPDATE = "com.example.leo.tpassignment61.services.person.impl.action.UPDATE";

    // TODO: Rename parameters
    public static final String EXTRA_ADD = "com.example.leo.tpassignment61.services.person.impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.example.leo.tpassignment61.services.person.impl.extra.UPDATE";

    private static PersonContactServiceimpl service = null;

    public static PersonContactServiceimpl getInstance()
    {
        if(service ==null)
            service = new PersonContactServiceimpl();
        return service;
    }

    public PersonContactServiceimpl() {
        super("PersonContactServiceimpl");
        repo= new PersonContactRepositoryimpl(App.getAppContext());
    }

    @Override
    public void addPersonContact(Context context,PersonContact address)
    {
        Intent intent = new Intent (context,PersonAddressServicesImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, address);
        context.startService(intent);
    }

    @Override
    public void updatePersonContact(Context context,PersonContact address)
    {
        Intent intent =new Intent(context,PersonContactServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, address);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PersonContact personContact = (PersonContact)intent.getSerializableExtra(EXTRA_ADD);

                postAddress(personContact);
            } else if (ACTION_UPDATE.equals(action)) {
                final PersonContact personContact = (PersonContact)intent.getSerializableExtra(EXTRA_UPDATE);;
                updateaddress(personContact);
            }
        }
    }

    public void postAddress(PersonContact personAddress)
    {
        try {
            repo.save(personAddress);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateaddress(PersonContact personAddress) {
        try {
            repo.save(personAddress);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
