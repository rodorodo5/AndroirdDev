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
    public static final String COMMENTS_ID = "id";
    public static final String COMMENTS_POSTID = "postid";
    public static final String COMMENTS_NAME = "name";
    public static final String COMMENTS_EMAIL = "email";
    public static final String COMMENTS_BODY = "body";

    public static final String POSTS_TABLE_NAME="POSTS";
    public static final String POSTS_MAINID = "MainId";
    public static final String POSTS_ID = "id";
    public static final String POSTS_USERID = "userid";
    public static final String POSTS_TITLE = "title";
    public static final String POSTS_BODY = "body";

    public static final String DATABASE_CREATE_COMMENTS =
            "CREATE TABLE "+ POSTS_TABLE_NAME + "(" +
                    POSTS_MAINID + " INTEGER PRIMARY KEY, " +
                    POSTS_ID + " INTEGER, " +
                    POSTS_USERID + " INTEGER, " +
                    POSTS_TITLE + " TEXT," +
                    POSTS_BODY + " TEXT)";

    public static final String DATABASE_CREATE_POSTS =
            "CREATE TABLE "+ COMMENTS_TABLE_NAME + "(" +
                    COMMENTS_MAINID + " INTEGER PRIMARY KEY, " +
                    COMMENTS_ID+" INTEGER, " +
                    COMMENTS_POSTID + " INTEGER, " +
                    COMMENTS_NAME + " TEXT," +
                    COMMENTS_EMAIL + " TEXT," +
                    COMMENTS_BODY + " TEXT)";

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