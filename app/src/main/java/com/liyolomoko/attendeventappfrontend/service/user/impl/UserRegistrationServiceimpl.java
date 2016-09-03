package com.liyolomoko.attendeventappfrontend.service.user.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.liyolomoko.attendeventappfrontend.conf.databases.App;
import com.liyolomoko.attendeventappfrontend.domain.user.UserRegistration;
import com.liyolomoko.attendeventappfrontend.repositories.user.UserRegistrationRepository;
import com.liyolomoko.attendeventappfrontend.repositories.user.impl.UserRegistrationRepositoryImp;
import com.liyolomoko.attendeventappfrontend.service.user.UserRegistrationService;

import java.sql.SQLException;

/**
 * Created by Leo on 5/8/2016.
 */
public class UserRegistrationServiceimpl extends IntentService implements UserRegistrationService{
    private final UserRegistrationRepository repo;

    public static final String ACTION_ADD = "com.liyolomoko.attendeventappfrontend.services.user.impl.action.ADD";
    public static final String ACTION_UPDATE = "com.liyolomoko.attendeventappfrontend.services.user.impl.action.UPDATE";

    // TODO: Rename parameters
    public static final String EXTRA_ADD = "com.liyolomoko.attendeventappfrontend.services.person.impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.liyolomoko.attendeventappfrontend.services.person.impl.extra.UPDATE";

    private static UserRegistrationServiceimpl service = null;

    public static UserRegistrationServiceimpl getInstance()
    {
        if(service ==null)
            service = new UserRegistrationServiceimpl();
        return service;
    }

    public UserRegistrationServiceimpl() {
        super("PersonServiceimpl");
        repo= new UserRegistrationRepositoryImp(App.getAppContext());
    }

    @Override
    public void addUser(Context context,UserRegistration address)
    {
        Intent intent = new Intent (context,UserRegistrationServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, address);
        context.startService(intent);
    }

    @Override
    public void updateUser(Context context,UserRegistration address)
    {
        Intent intent =new Intent(context,UserRegistrationServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, address);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final UserRegistration personContact = (UserRegistration)intent.getSerializableExtra(EXTRA_ADD);

                postAddress(personContact);
            } else if (ACTION_UPDATE.equals(action)) {
                final UserRegistration person = (UserRegistration)intent.getSerializableExtra(EXTRA_UPDATE);;
                updateaddress(person);
            }
        }
    }

    public void postAddress(UserRegistration person)
    {
        try {
            repo.save(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateaddress(UserRegistration person) {
        try {
            repo.save(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
