package com.example.rodo.memesaurios.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rodo on 10/6/17.
 */

public class DBUtils extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="popis.db";
    public static final int DATABASE_VERSION = 1;

    public static final String COMMENTS_TABLE_NAME="COMMENTS";
    public static final String COMMENTS_MAINID = "MainId";
    public static final String COMMENTS_ID = "_id";
    public static final String COMMENTS_POSTID = "_postid";
    public static final String COMMENTS_NAME = "_name";
    public static final String COMMENTS_EMAIL = "_email";
    public static final String COMMENTS_BODY = "_body";

    public static final String POSTS_TABLE_NAME="POSTS";
    public static final String POSTS_MAINID = "MainId";
    public static final String POSTS_ID = "_id";
    public static final String POSTS_USERID = "_userid";
    public static final String POSTS_TITLE = "_title";
    public static final String POSTS_BODY = "_body";

    public static final String DATABASE_CREATE_COMMENTS="CREATE TABLE "+COMMENTS_TABLE_NAME+" ("+
            COMMENTS_ID +
            " integer primary key autoincrement," +
            COMMENTS_POSTID+
            " text not null," +
            COMMENTS_NAME+
            " integer not null," +
            COMMENTS_EMAIL+
            " integer not null"+
            COMMENTS_BODY+
            " integer not null"+
            ")";


    public static final String DATABASE_CREATE_POSTS="CREATE TABLE "+POSTS_TABLE_NAME+" ("+
            POSTS_ID +
            " integer primary key autoincrement," +
            POSTS_USERID+
            " text not null," +
            POSTS_TITLE+
            " integer not null," +
            POSTS_BODY+
            " integer not null"+
            ")";

    public DBUtils(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_COMMENTS);
        db.execSQL(DATABASE_CREATE_POSTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + COMMENTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + POSTS_TABLE_NAME);

        onCreate(db);
    }
}