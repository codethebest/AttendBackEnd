package com.liyolomoko.attendeventappfrontend.repositories.event.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.liyolomoko.attendeventappfrontend.conf.databases.DBConstants;
import com.liyolomoko.attendeventappfrontend.domain.event.EventContact;
import com.liyolomoko.attendeventappfrontend.repositories.event.EventContactRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 5/7/2016.
 */
public class EventContactRepositoryimpl extends SQLiteOpenHelper implements EventContactRepository{
    public static final String TABLE_NAME = "eventcontacts";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_WEBSITE = "website";
    public static final String COLUMN_EMAIL = "email";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_WEBSITE + " TEXT  NOT NULL , "
            + COLUMN_EMAIL + " TEXT  NOT NULL);";


    public EventContactRepositoryimpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public EventContact read(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_WEBSITE,
                        COLUMN_EMAIL},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final EventContact personalAddress = new EventContact.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .website(cursor.getString(cursor.getColumnIndex(COLUMN_WEBSITE)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .build();
            return personalAddress;
        } else {
            return null;
        }
    }

    @Override
    public EventContact save(EventContact entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_WEBSITE, entity.getWebsite());
        values.put(COLUMN_EMAIL,entity.getEmail());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        EventContact insertedEntity = new EventContact.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public EventContact update(EventContact entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_WEBSITE, entity.getWebsite());
        values.put(COLUMN_EMAIL,entity.getEmail());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public EventContact delete(EventContact entity) throws SQLException {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<EventContact> readAll() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<EventContact> personAddresses = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final EventContact personAddress = new EventContact.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .website(cursor.getString(cursor.getColumnIndex(COLUMN_WEBSITE)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .build();
                personAddresses.add(personAddress);
            } while (cursor.moveToNext());
        }
        return personAddresses;
    }


    @Override
    public int deleteAll() throws SQLException {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

