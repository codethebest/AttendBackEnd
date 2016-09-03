package com.liyolomoko.attendeventappfrontend.service.person.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.liyolomoko.attendeventappfrontend.conf.databases.App;
import com.liyolomoko.attendeventappfrontend.domain.person.Person;
import com.liyolomoko.attendeventappfrontend.repositories.person.PersonRepository;
import com.liyolomoko.attendeventappfrontend.repositories.person.impl.PersonRepositoryimpl;
import com.liyolomoko.attendeventappfrontend.service.person.PersonService;

import java.sql.SQLException;

/**
 * Created by Leo on 5/8/2016.
 */
public class PersonServiceimpl extends IntentService implements PersonService{
    private final PersonRepository repo;

    public static final String ACTION_ADD = "com.example.leo.tpassignment61.services.person.impl.action.ADD";
    public static final String ACTION_UPDATE = "com.example.leo.tpassignment61.services.person.impl.action.UPDATE";

    // TODO: Rename parameters
    public static final String EXTRA_ADD = "com.example.leo.tpassignment61.services.person.impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.example.leo.tpassignment61.services.person.impl.extra.UPDATE";

    private static PersonServiceimpl service = null;

    public static PersonServiceimpl getInstance()
    {
        if(service ==null)
            service = new PersonServiceimpl();
        return service;
    }

    public PersonServiceimpl() {
        super("PersonServiceimpl");
        repo= new PersonRepositoryimpl(App.getAppContext());
    }

    @Override
    public void addPerson(Context context,Person address)
    {
        Intent intent = new Intent (context,PersonServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, address);
        context.startService(intent);
    }

    @Override
    public void updatePerson(Context context,Person address)
    {
        Intent intent =new Intent(context,PersonServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, address);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Person personContact = (Person)intent.getSerializableExtra(EXTRA_ADD);

                postAddress(personContact);
            } else if (ACTION_UPDATE.equals(action)) {
                final Person person = (Person)intent.getSerializableExtra(EXTRA_UPDATE);;
                updateaddress(person);
            }
        }
    }

    public void postAddress(Person person)
    {
        try {
            repo.save(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateaddress(Person person) {
        try {
            repo.save(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
