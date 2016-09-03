package com.liyolomoko.attendeventappfrontend.repositories.person.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.liyolomoko.attendeventappfrontend.conf.databases.DBConstants;
import com.liyolomoko.attendeventappfrontend.domain.person.Person;
import com.liyolomoko.attendeventappfrontend.repositories.person.PersonRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 4/25/2016.
 */
public class PersonRepositoryimpl extends SQLiteOpenHelper implements PersonRepository {

    public static final String TABLE_NAME = "person";
    private SQLiteDatabase db;

    public static final String CULUMN_ID = "id";
    public static final String CULUMN_NAME = "name";
    public static final String CULUMN_SURNAME = "surname";
    public static final String CULUMN_EMAIL = "email";
    public static final String CULUMN_AUVALUE = "authvalue";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            +TABLE_NAME + "("
            +CULUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            +CULUMN_NAME + " TEXT NOT NULL , "
            +CULUMN_SURNAME + " TEXT NOT NULL , "
            +CULUMN_EMAIL + " TEXT NOT NULL , "
            +CULUMN_AUVALUE + " TEXT NOT NULL );";

    public PersonRepositoryimpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }


    @Override
    public Person read(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME, new String[]{
                        CULUMN_ID,
                        CULUMN_NAME,
                        CULUMN_SURNAME,
                        CULUMN_EMAIL,
                        CULUMN_AUVALUE},
                CULUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor.moveToFirst()) {
            final Person person = new Person.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(CULUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(CULUMN_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(CULUMN_SURNAME)))
                    .email(cursor.getString(cursor.getColumnIndex(CULUMN_EMAIL)))
                    .auvalue(cursor.getString(cursor.getColumnIndex(CULUMN_AUVALUE)))
                    .build();
            return person;
        } else {
            return null;
        }
    }

    @Override
    public Set<Person> readAll() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Person> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do{Person p = new Person.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(CULUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(CULUMN_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(CULUMN_SURNAME)))
                    .email(cursor.getString(cursor.getColumnIndex(CULUMN_EMAIL)))
                    .auvalue(cursor.getString(cursor.getColumnIndex(CULUMN_AUVALUE)))
                    .build();
            Person.add(p);
        }
            while (cursor.moveToFirst()) ;
      }
        return Person;
    }

    @Override
    public Person update(Person entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(CULUMN_ID,entity.getId());
        values.put(CULUMN_NAME,entity.getName());
        values.put(CULUMN_SURNAME,entity.getSurname());
        values.put(CULUMN_EMAIL,entity.getEmail());
        values.put(CULUMN_AUVALUE, entity.getAuvalue());
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
    public Person delete(Person entity) throws SQLException {
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
    public Person save(Person entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(CULUMN_ID,entity.getId());
        values.put(CULUMN_NAME,entity.getName());
        values.put(CULUMN_SURNAME,entity.getSurname());
        values.put(CULUMN_EMAIL,entity.getEmail());
        values.put(CULUMN_AUVALUE,entity.getAuvalue());
        long id = db.insertOrThrow(TABLE_NAME,null,values);
        Person insertEntity = new Person.Builder()
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
