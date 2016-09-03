package com.liyolomoko.attendeventappfrontend.repositories.person.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.liyolomoko.attendeventappfrontend.conf.databases.DBConstants;
import com.liyolomoko.attendeventappfrontend.domain.person.PersonContact;
import com.liyolomoko.attendeventappfrontend.repositories.person.PersonContactRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 4/25/2016.
 */
public class PersonContactRepositoryimpl extends SQLiteOpenHelper implements PersonContactRepository{

    public static final String TABLE_NAME = "personcontacts";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SCREENNAME = "screenName";
    public static final String COLUMN_WEBSITE = "website";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_MOBILE = "mobile";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SCREENNAME + " TEXT UNIQUE NOT NULL , "
            + COLUMN_WEBSITE + " TEXT  NOT NULL , "
            + COLUMN_EMAIL + " TEXT  NOT NULL , "
            + COLUMN_MOBILE + " TEXT);";


    public PersonContactRepositoryimpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public PersonContact read(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SCREENNAME,
                        COLUMN_WEBSITE,
                        COLUMN_EMAIL,
                        COLUMN_MOBILE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final PersonContact personalAddress = new PersonContact.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .screenName(cursor.getString(cursor.getColumnIndex(COLUMN_SCREENNAME)))
                    .website(cursor.getString(cursor.getColumnIndex(COLUMN_WEBSITE)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .mobile(cursor.getLong(cursor.getColumnIndex(COLUMN_MOBILE)))
                    .build();
            return personalAddress;
        } else {
            return null;
        }
    }

    @Override
    public PersonContact save(PersonContact entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SCREENNAME, entity.getScreenName());
        values.put(COLUMN_WEBSITE, entity.getWebsite());
        values.put(COLUMN_EMAIL,entity.getEmail());
        values.put(COLUMN_MOBILE, entity.getMobile());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        PersonContact insertedEntity = new PersonContact.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public PersonContact update(PersonContact entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SCREENNAME, entity.getScreenName());
        values.put(COLUMN_WEBSITE, entity.getWebsite());
        values.put(COLUMN_EMAIL,entity.getEmail());
        values.put(COLUMN_MOBILE, entity.getMobile());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public PersonContact delete(PersonContact entity) throws SQLException {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<PersonContact> readAll() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PersonContact> personAddresses = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PersonContact personAddress = new PersonContact.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .screenName(cursor.getString(cursor.getColumnIndex(COLUMN_SCREENNAME)))
                        .website(cursor.getString(cursor.getColumnIndex(COLUMN_WEBSITE)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .mobile(cursor.getLong(cursor.getColumnIndex(COLUMN_MOBILE)))
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
