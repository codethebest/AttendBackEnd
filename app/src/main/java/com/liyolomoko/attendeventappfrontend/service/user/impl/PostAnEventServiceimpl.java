package com.liyolomoko.attendeventappfrontend.service.user.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.liyolomoko.attendeventappfrontend.conf.databases.App;
import com.liyolomoko.attendeventappfrontend.domain.user.PostAnEvent;
import com.liyolomoko.attendeventappfrontend.repositories.user.PostAnEventRepository;
import com.liyolomoko.attendeventappfrontend.repositories.user.impl.PostAnEventRepositoryImp;
import com.liyolomoko.attendeventappfrontend.service.user.PostAnEventService;

import java.sql.SQLException;

/**
 * Created by Leo on 5/8/2016.
 */
public class PostAnEventServiceimpl extends IntentService implements PostAnEventService {
    private final PostAnEventRepository repo;

    public static final String ACTION_ADD = "com.example.leo.tpassignment61.services.user.impl.action.ADD";
    public static final String ACTION_UPDATE = "com.example.leo.tpassignment61.services.user.impl.action.UPDATE";

    // TODO: Rename parameters
    public static final String EXTRA_ADD = "com.example.leo.tpassignment61.services.user.impl.extra.ADD";
    public static final String EXTRA_UPDATE = "com.example.leo.tpassignment61.services.user.impl.extra.UPDATE";

    private static PostAnEventServiceimpl service = null;

    public static PostAnEventServiceimpl getInstance()
    {
        if(service ==null)
            service = new PostAnEventServiceimpl();
        return service;
    }

    public PostAnEventServiceimpl() {
        super("PostAnEventServiceimpl");
        repo= new PostAnEventRepositoryImp(App.getAppContext());
    }

    @Override
    public void postEvent(Context context,PostAnEvent address)
    {
        Intent intent = new Intent (context,PostAnEventServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, address);
        context.startService(intent);
    }

    @Override
    public void editPost(Context context,PostAnEvent address)
    {
        Intent intent =new Intent(context,PostAnEventServiceimpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, address);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PostAnEvent personContact = (PostAnEvent)intent.getSerializableExtra(EXTRA_ADD);

                postAddress(personContact);
            } else if (ACTION_UPDATE.equals(action)) {
                final PostAnEvent postAnEvent = (PostAnEvent)intent.getSerializableExtra(EXTRA_UPDATE);;
                updateaddress(postAnEvent);
            }
        }
    }

    public void postAddress(PostAnEvent postAnEvent)
    {
        try {
            repo.save(postAnEvent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateaddress(PostAnEvent postAnEvent) {
        try {
            repo.save(postAnEvent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

