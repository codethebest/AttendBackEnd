package com.liyolomoko.attendeventappfrontend.repositories.user.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.liyolomoko.attendeventappfrontend.conf.databases.DBConstants;
import com.liyolomoko.attendeventappfrontend.domain.user.UserRegistration;
import com.liyolomoko.attendeventappfrontend.repositories.user.UserRegistrationRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 4/24/2016.
 */
public class UserRegistrationRepositoryImp extends SQLiteOpenHelper implements UserRegistrationRepository {
    public static final String TABLE_USERREGISTRATION ="userregistration";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "Gender";
    public static final String COLUMN_USEREMAIL = "useremail";
    public static final String COLUMN_PASSWORD = "password";


    private static final String DATABASE_CREATE = " CREATE TABLE "
    + TABLE_USERREGISTRATION + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_GENDER + " TEXT NOT NULL , "
            + COLUMN_USEREMAIL + " TEXT NOT NULL , "
            + COLUMN_PASSWORD + " TEXT NOT NULL );";

    public UserRegistrationRepositoryImp (Context context) {
        super (context , DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close ()
    {
        this.close();
    }

    @Override
    public UserRegistration read(Long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERREGISTRATION, new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_GENDER,
                        COLUMN_USEREMAIL,
                        COLUMN_PASSWORD},
                COLUMN_ID + " =? ",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()){
            final UserRegistration userRegistration = new UserRegistration.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                    .useremail(cursor.getString(cursor.getColumnIndex(COLUMN_USEREMAIL)))
                    .newPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .build();
            return userRegistration;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<UserRegistration> readAll() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<UserRegistration> userRegistrations = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_USERREGISTRATION, null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do
            {
                final UserRegistration userRegistration = new UserRegistration.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                        .useremail(cursor.getString(cursor.getColumnIndex(COLUMN_USEREMAIL)))
                        .newPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .build();
                userRegistrations.add(userRegistration);
            }while (cursor.moveToNext());
        }
        return userRegistrations;
    }

    @Override
    public UserRegistration update(UserRegistration entity) throws SQLException {
        open();
        ContentValues values =new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_GENDER, entity.getGender());
        values.put(COLUMN_USEREMAIL, entity.getUseremail());
        values.put(COLUMN_PASSWORD, entity.getNewPassword());
        db.update(
                TABLE_USERREGISTRATION,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public UserRegistration delete(UserRegistration entity) throws SQLException {
        open();
        db.delete(
                TABLE_USERREGISTRATION,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public int deleteAll() throws SQLException {
        open();
        int rowsDeleted = db.delete(TABLE_USERREGISTRATION,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public UserRegistration save(UserRegistration entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMN_GENDER,entity.getGender());
        values.put(COLUMN_USEREMAIL,entity.getUseremail());
        values.put(COLUMN_PASSWORD,entity.getNewPassword());

        Long id = db.insertOrThrow(TABLE_USERREGISTRATION, null, values);
        UserRegistration insertedEntity = new UserRegistration.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERREGISTRATION);
        onCreate(db);

    }
}
