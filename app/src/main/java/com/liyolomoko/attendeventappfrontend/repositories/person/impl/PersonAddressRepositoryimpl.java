package com.liyolomoko.attendeventappfrontend.repositories.person.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.liyolomoko.attendeventappfrontend.conf.databases.DBConstants;
import com.liyolomoko.attendeventappfrontend.domain.person.PersonAddress;
import com.liyolomoko.attendeventappfrontend.repositories.person.PersonAddressRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 4/25/2016.
 */
public class PersonAddressRepositoryimpl extends SQLiteOpenHelper implements PersonAddressRepository {
    public static final String TABLE_NAME = "personaddress";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SUBURB = "suburb";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STREET = "street";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SUBURB + " TEXT NOT NULL , "
            + COLUMN_COUNTRY + " TEXT NOT NULL , "
            + COLUMN_CITY + " TEXT  NOT NULL , "
            + COLUMN_STREET + " TEXT  NOT NULL); ";


    public PersonAddressRepositoryimpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public PersonAddress read(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SUBURB,
                        COLUMN_COUNTRY,
                        COLUMN_CITY,
                        COLUMN_STREET},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final PersonAddress personalAddress = new PersonAddress.Builder()
                    .Id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .sub(cursor.getString(cursor.getColumnIndex(COLUMN_SUBURB)))
                    .country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                    .build();
            return personalAddress;
        }
        else {
            return null;
        }
    }


    @Override
    public PersonAddress save(PersonAddress entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SUBURB,entity.getSub());
        values.put(COLUMN_COUNTRY, entity.getCountry());
        values.put(COLUMN_CITY, entity.getCity());
        values.put(COLUMN_STREET, entity.getStreet());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        PersonAddress insertedEntity = new PersonAddress.Builder()
                .copy(entity)
                .Id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public PersonAddress update(PersonAddress entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SUBURB,entity.getSub());
        values.put(COLUMN_COUNTRY, entity.getCountry());
        values.put(COLUMN_CITY, entity.getCity());
        values.put(COLUMN_STREET, entity.getStreet());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public PersonAddress delete(PersonAddress entity) throws SQLException {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<PersonAddress> readAll() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PersonAddress> personAddresses = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PersonAddress personAddress = new PersonAddress.Builder()
                        .Id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .sub(cursor.getString(cursor.getColumnIndex(COLUMN_SUBURB)))
                        .country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                        .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                        .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
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
