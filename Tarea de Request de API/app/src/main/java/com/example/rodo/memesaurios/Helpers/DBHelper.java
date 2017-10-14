package com.example.rodo.memesaurios.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.rodo.memesaurios.Models.Posts;
import com.example.rodo.memesaurios.Models.Comments;
import com.example.rodo.memesaurios.Utils.DBUtils;
import java.util.ArrayList;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rodo on 10/13/17.
 */

public class DBHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] POST_TABLE_COLUMNS =
            {
                    DBUtils.POSTS_MAINID,
                    DBUtils.POSTS_ID,
                    DBUtils.POSTS_USERID,
                    DBUtils.POSTS_TITLE,
                    DBUtils.POSTS_BODY
            };
    private String[] COMMENT_TABLE_COLUMNS =
            {
                    DBUtils.COMMENTS_MAINID,
                    DBUtils.COMMENTS_ID,
                    DBUtils.COMMENTS_POSTID,
                    DBUtils.COMMENTS_NAME,
                    DBUtils.COMMENTS_EMAIL,
                    DBUtils.COMMENTS_BODY
            };

    public DBHelper(Context context) {
        dbHelper = new DBUtils(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        database.close();
    }

    public Comments addComment(int id, int post, String name, String email, String body) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.COMMENTS_ID, id);
        values.put(DBUtils.COMMENTS_POSTID, post);
        values.put(DBUtils.COMMENTS_NAME, name);
        values.put(DBUtils.COMMENTS_EMAIL, email);
        values.put(DBUtils.COMMENTS_BODY, body);
        long commentId = database.insert(DBUtils.COMMENTS_TABLE_NAME, null, values);
        Cursor cursor = database.query(DBUtils.COMMENTS_TABLE_NAME, COMMENT_TABLE_COLUMNS,
                DBUtils.COMMENTS_MAINID + " = " + commentId, null, null, null, null);
        cursor.moveToFirst();
        Comments comments = parseComment(cursor);
        cursor.close();
        return comments;
    }

    public Posts addPost(int id, int user, String title, String body) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.POSTS_ID, id);
        values.put(DBUtils.POSTS_USERID, user);
        values.put(DBUtils.POSTS_TITLE, title);
        values.put(DBUtils.POSTS_BODY, body);
        long postId = database.insert(DBUtils.POSTS_TABLE_NAME, null, values);
        Cursor cursor = database.query(DBUtils.POSTS_TABLE_NAME, POST_TABLE_COLUMNS,
                DBUtils.POSTS_MAINID + " = " + postId, null, null, null, null);
        cursor.moveToFirst();
        Posts posts = parsePost(cursor);
        cursor.close();
        return posts;
    }

    public void deletePost(int id) {
        database.delete(DBUtils.POSTS_TABLE_NAME, DBUtils.POSTS_ID+" = " + id, null);
    }

    public void deleteAllPosts() {
        database.delete(DBUtils.POSTS_TABLE_NAME, DBUtils.POSTS_ID + " > 0", null);
    }

    public void deleteComment(int id) {
        database.delete(DBUtils.COMMENTS_TABLE_NAME, DBUtils.COMMENTS_ID+" = " + id, null);
    }

    public void deleteAllComments() {
        database.delete(DBUtils.COMMENTS_TABLE_NAME, DBUtils.COMMENTS_ID + " > 0", null);
    }

    public ArrayList<Posts> getAllPosts() {
        ArrayList<Posts> posts = new ArrayList<Posts>();
        Cursor cursor = database.query(DBUtils.POSTS_TABLE_NAME, POST_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            posts.add(parsePost(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return posts;
    }

    public ArrayList<Comments> getAllComments() {
        ArrayList<Comments> comments = new ArrayList<Comments>();
        Cursor cursor = database.query(DBUtils.COMMENTS_TABLE_NAME, COMMENT_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            comments.add(parseComment(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return comments;
    }

    private Comments parseComment(Cursor cursor) {
        Comments comments = new Comments();
        comments.setId(cursor.getInt(cursor.getColumnIndex(DBUtils.COMMENTS_ID)) + "");
        comments.setPostid(cursor.getInt(cursor.getColumnIndex(DBUtils.COMMENTS_POSTID)) + "");
        comments.setName(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_NAME)));
        comments.setEmail(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_EMAIL)));
        comments.setBody(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_BODY)));
        return comments;
    }

    private Posts parsePost(Cursor cursor) {
        Posts post = new Posts();
        post.setId(cursor.getInt(cursor.getColumnIndex(DBUtils.POSTS_ID)) + "");
        post.setUserId(cursor.getInt(cursor.getColumnIndex(DBUtils.POSTS_USERID)) + "");
        post.setTitle(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_TITLE)));
        post.setBody(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_BODY)));
        return post;
    }
}
