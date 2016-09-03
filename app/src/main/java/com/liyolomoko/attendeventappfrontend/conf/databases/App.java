package com.liyolomoko.attendeventappfrontend.conf.databases;

import android.app.Application;
import android.content.Context;

/**
 * Created by Leo on 4/25/2016.
 */
public class App extends Application{
    private static Context context;
    private static App singleton;

    public void onCreate(){
        super.onCreate();
        App.context= getApplicationContext();
        singleton=this;
    }
    public static Context getAppContext() {
        return App.context;
    }
    public Context getContext() {
        return App.context;
    }
    public static  synchronized App getInstance(){
        return  singleton;
    }
}

