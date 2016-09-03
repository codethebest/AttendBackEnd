package com.liyolomoko.attendeventappfrontend.repositories.user.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.liyolomoko.attendeventappfrontend.conf.databases.DBConstants;
import com.liyolomoko.attendeventappfrontend.conf.util.AppUtil;
import com.liyolomoko.attendeventappfrontend.domain.user.CommentOnPost;
import com.liyolomoko.attendeventappfrontend.repositories.user.CommentOnPostRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 4/25/2016.
 */
public class CommentOnPostRepositoryImp extends SQLiteOpenHelper implements CommentOnPostRepository{
    public static final String TABLE_NAME ="CommentOnPost";
    private SQLiteDatabase db;

    public static final String CULUMN_ID = "id";
    public static final String CULUMN_POST ="post";
    public static final String CULUMN_DATE ="date";

    private static final String DATEBASE_CREATE = " CREATE TABLE "
            + TABLE_NAME +"("
            + CULUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CULUMN_POST + " TEXT NOT NULL , "
            + CULUMN_DATE + " TEXT NOT NULL ); ";


    public CommentOnPostRepositoryImp(Context context) {
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
    public CommentOnPost read(Long id) {
        db = this.getReadableDatabase();
        Cursor cursor =db.query(TABLE_NAME,
                new String[]{
                        CULUMN_ID,
                        CULUMN_POST,
                        CULUMN_DATE
             },
                CULUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final CommentOnPost commentOnPost = new CommentOnPost.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(CULUMN_ID)))
                    .post(cursor.getString(cursor.getColumnIndex(CULUMN_POST)))
                    .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(CULUMN_DATE))))
                    .build();
            return commentOnPost;
        }
        else {
            return null;
        }
    }

    @Override
    public CommentOnPost save (CommentOnPost entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(CULUMN_ID,entity.getId());
        values.put(CULUMN_POST,entity.getPost());
        values.put(CULUMN_DATE,entity.getDate().toString());

        Long id = db.insertOrThrow(TABLE_NAME, null , values);
        CommentOnPost insertEntity = new CommentOnPost.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertEntity;

    }

    @Override
    public Set<CommentOnPost> readAll() throws SQLException {
        db = this.getReadableDatabase();
        Set commntOnPosts =new HashSet<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst())
        {
            do
            {
                CommentOnPost commentOnPost = new CommentOnPost.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(CULUMN_ID)))
                        .post(cursor.getString(cursor.getColumnIndex(CULUMN_POST)))
                        .build();
            commntOnPosts.add(commentOnPost);
            }while (cursor.moveToNext());
            return commntOnPosts;
        }

        else {
            return commntOnPosts;
        }
    }

    @Override
    public CommentOnPost update(CommentOnPost entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(CULUMN_ID, entity.getId());
        values.put(CULUMN_POST,entity.getPost());
        values.put(CULUMN_DATE,entity.getDate().toString());
        db.update(TABLE_NAME, values, CULUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public CommentOnPost delete(CommentOnPost entity) throws SQLException {
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

