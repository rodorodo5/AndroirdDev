package com.example.rodo.memesaurios.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.rodo.memesaurios.Models.Comments;
import com.example.rodo.memesaurios.Models.Posts;

import java.util.ArrayList;

/**
 * Created by rodo on 10/6/17.
 */

public class PostsHelper {

    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] POSTS_TABLE_NAME = {
            DBUtils.POSTS_ID,
            DBUtils.POSTS_USERID,
            DBUtils.POSTS_BODY,
            DBUtils.POSTS_TITLE
    };

    public PostsHelper(Context context){
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    private Posts parsePosts(Cursor cursor){
        Posts oPosts = new Posts();
        oPosts.setId(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_ID)));
        oPosts.setTitle(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_TITLE)));
        oPosts.setBody(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_BODY)));
        oPosts.setUserId(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_USERID)));
        return oPosts;
    }

    public int deletePost(int nPostID){
        return database.delete(DBUtils.POSTS_TABLE_NAME, DBUtils.POSTS_ID+ " = " + nPostID, null);
    }

    public Posts addPost(String userd, String id, String title, String body){
        ContentValues values = new ContentValues();
        values.put(DBUtils.POSTS_USERID, userd);
        values.put(DBUtils.POSTS_ID, id );
        values.put(DBUtils.POSTS_BODY, body);
        values.put(DBUtils.POSTS_TITLE, title);

        long lPostID = database.insert(DBUtils.POSTS_TABLE_NAME, null, values);

        Cursor cursor = database.query(DBUtils.POSTS_TABLE_NAME,
                POSTS_TABLE_NAME,
                DBUtils.POSTS_ID+" = " + lPostID, null, null, null, null);
        cursor.moveToFirst();
        Posts oPosts = parsePosts(cursor);
        cursor.close();
        return oPosts;
    }


    public ArrayList<Posts> getAllPosts(){
        ArrayList<Posts>oLPosts = new ArrayList<Posts>();
        Cursor cursor = database.query(DBUtils.POSTS_TABLE_NAME, POSTS_TABLE_NAME, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            oLPosts.add(parseCustomer(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oLPosts;
    }

    private Posts parseCustomer(Cursor cursor){
        Posts oPosts = new Posts();
        oPosts.setUserId(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_USERID)));
        oPosts.setBody(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_BODY)));
        oPosts.setId(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_ID)));
        oPosts.setTitle(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_TITLE)));
        return oPosts;
    }

}
