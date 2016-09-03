package com.liyolomoko.attendeventappfrontend.repositories.user.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.liyolomoko.attendeventappfrontend.conf.databases.DBConstants;
import com.liyolomoko.attendeventappfrontend.conf.util.AppUtil;
import com.liyolomoko.attendeventappfrontend.domain.user.PostAnEvent;
import com.liyolomoko.attendeventappfrontend.repositories.user.PostAnEventRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 4/25/2016.
 */
public class PostAnEventRepositoryImp extends SQLiteOpenHelper implements PostAnEventRepository {

    public static final String TABLE_NAME ="PostAnEvent";
    private SQLiteDatabase db;

    public static final String CULUMN_ID = "id";
    public static final String CULUMN_POST ="post";
    public static final String CULUMN_DATE ="date";
    public static final String CULUMN_TAGLINE ="tagline";

    private static final String DATEBASE_CREATE = " CREATE TABLE "
    + TABLE_NAME +"("
            + CULUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CULUMN_POST + " TEXT NOT NULL , "
            + CULUMN_DATE + " TEXT NOT NULL, "
            + CULUMN_TAGLINE + " TEXT NOT NULL ) ";

    public PostAnEventRepositoryImp(Context context) {
        super(context, DBConstants.DATABASE_NAME,null ,DBConstants.DATABASE_VERSION);
    }

    public void open()throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close()
    {
        this.close();
    }

    @Override
    public PostAnEvent read(Long id) {
         db = this.getReadableDatabase();
        Cursor cursor =db.query(TABLE_NAME,
                new String[]{
                        CULUMN_ID,
                        CULUMN_POST,
                        CULUMN_DATE,
                        CULUMN_TAGLINE},
                CULUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final PostAnEvent postAnEvent = new PostAnEvent.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(CULUMN_ID)))
                    .post(cursor.getString(cursor.getColumnIndex(CULUMN_POST)))
                    .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(CULUMN_DATE))))
                    .tagline(cursor.getString(cursor.getColumnIndex(CULUMN_TAGLINE)))
                    .build();
                return postAnEvent;
        }
        else {
            return null;
        }
    }

    @Override
    public PostAnEvent save (PostAnEvent entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(CULUMN_ID,entity.getId());
        values.put(CULUMN_POST,entity.getPost());
        values.put(CULUMN_DATE,entity.getDate().toString());
        values.put(CULUMN_TAGLINE,entity.getTagline());

        Long id = db.insertOrThrow(TABLE_NAME, null , values);
        PostAnEvent insertEntity = new PostAnEvent.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertEntity;

    }

    @Override
    public Set<PostAnEvent> readAll() throws SQLException {
        db = this.getReadableDatabase();
        Set postAnEvents =new HashSet<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do
            {
                PostAnEvent postAnEvent = new PostAnEvent.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(CULUMN_ID)))
                        .post(cursor.getString(cursor.getColumnIndex(CULUMN_POST)))
                        .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(CULUMN_DATE))))
                        .tagline(cursor.getString(cursor.getColumnIndex(CULUMN_TAGLINE)))
                        .build();
                postAnEvents.add(postAnEvent);
            }while (cursor.moveToNext());
            return postAnEvents;
        }

        else {
            return null;
        }
    }

    @Override
    public PostAnEvent update(PostAnEvent entity) throws SQLException {
            open();
        ContentValues values = new ContentValues();
        values.put(CULUMN_ID, entity.getId());
        values.put(CULUMN_POST,entity.getPost());
        values.put(CULUMN_DATE,entity.getDate().toString());
        values.put(CULUMN_TAGLINE,entity.getTagline());
        db.update(TABLE_NAME, values, CULUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public PostAnEvent delete(PostAnEvent entity) throws SQLException {
        open();
        db.delete(
                TABLE_NAME,
                CULUMN_ID + " =?" ,
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
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATEBASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(this.getClass().getName(),
                    "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ",which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
