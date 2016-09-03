package com.liyolomoko.attendeventappfrontend.repositories.event.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.liyolomoko.attendeventappfrontend.conf.databases.DBConstants;
import com.liyolomoko.attendeventappfrontend.domain.event.Event;
import com.liyolomoko.attendeventappfrontend.repositories.event.EventRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 5/7/2016.
 */
public class EventRepositoryimpl extends SQLiteOpenHelper implements EventRepository{

    public static final String TABLE_NAME = "event";
    private SQLiteDatabase db;

    public static final String CULUMN_ID = "id";
    public static final String CULUMN_HOSTER = "hoster";
    public static final String CULUMN_DESTRIPTION = "description";
    public static final String CULUMN_TAGLINE = "tagline";
    public static final String CULUMN_EVENTFLYERIMAGE = "eventflyimage";
    public static final String CULUMN_NAME = "name";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            +TABLE_NAME + "("
            +CULUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            +CULUMN_NAME + " TEXT NOT NULL , "
            +CULUMN_DESTRIPTION + " TEXT , "
            +CULUMN_TAGLINE + " TEXT NOT NULL , "
            //+CULUMN_EVENTFLYERIMAGE + " TEXT NOT NULL"
            +CULUMN_HOSTER + " TEXT NOT NULL );";

    public EventRepositoryimpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }


    @Override
    public Event read(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME, new String[]{
                        CULUMN_ID,
                        CULUMN_NAME,
                        CULUMN_DESTRIPTION,
                        CULUMN_TAGLINE,
                        //CULUMN_EVENTFLYERIMAGE,
                        CULUMN_HOSTER},
                CULUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor.moveToFirst()) {
            final Event person = new Event.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(CULUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(CULUMN_NAME)))
                    .description(cursor.getString(cursor.getColumnIndex(CULUMN_DESTRIPTION)))
                    .tagline(cursor.getString(cursor.getColumnIndex(CULUMN_TAGLINE)))
                    //.eventFlyerImage(cursor.getString(cursor.getColumnIndex(CULUMN_EVENTFLYERIMAGE)))
                    .host(cursor.getString(cursor.getColumnIndex(CULUMN_HOSTER)))
                    .build();
            return person;
        } else {
            return null;
        }
    }

    @Override
    public Set<Event> readAll() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Event> events = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do{Event p = new Event.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(CULUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(CULUMN_NAME)))
                    .description(cursor.getString(cursor.getColumnIndex(CULUMN_DESTRIPTION)))
                    .tagline(cursor.getString(cursor.getColumnIndex(CULUMN_TAGLINE)))
                    //.eventFlyerImage(cursor.getString(cursor.getColumnIndex(CULUMN_EVENTFLYERIMAGE)))
                    .host(cursor.getString(cursor.getColumnIndex(CULUMN_HOSTER)))
                    .build();
                events.add(p);
            }
            while (cursor.moveToFirst()) ;
        }
        return events;
    }

    @Override
    public Event update(Event entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(CULUMN_ID,entity.getId());
        values.put(CULUMN_NAME,entity.getName());
        values.put(CULUMN_DESTRIPTION,entity.getDescription());
        values.put(CULUMN_TAGLINE,entity.getTagline());
        //values.put(CULUMN_EVENTFLYERIMAGE,entity.getEventFlyerImage());
        values.put(CULUMN_HOSTER, entity.getHost());
        db.update
                (
                        TABLE_NAME,
                        values,
                        CULUMN_ID +" =?",
                        new String[]{String.valueOf(entity.getId())}
                );
        return entity;
    }

    @Override
    public Event delete(Event entity) throws SQLException {
        open();
        db.delete(
                TABLE_NAME,
                CULUMN_ID +" =?",
                new String[]{String.valueOf(entity.getId())}

        );
        return entity;
    }

    @Override
    public int deleteAll() throws SQLException {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public Event save(Event entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(CULUMN_ID,entity.getId());
        values.put(CULUMN_NAME,entity.getName());
        values.put(CULUMN_DESTRIPTION,entity.getDescription());
        values.put(CULUMN_TAGLINE,entity.getTagline());
        //values.put(CULUMN_EVENTFLYERIMAGE,entity.getEventFlyerImage());
        values.put(CULUMN_HOSTER, entity.getHost());
        long id = db.insertOrThrow(TABLE_NAME,null,values);
        Event insertEntity = new Event.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertEntity;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

