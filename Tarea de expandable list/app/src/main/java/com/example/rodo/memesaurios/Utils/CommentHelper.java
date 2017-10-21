package com.example.rodo.memesaurios.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.example.rodo.memesaurios.Models.Comments;
import com.example.rodo.memesaurios.Adapters.CommentsAdapter;
import com.example.rodo.memesaurios.Adapters.PostsAdapter;




import java.util.ArrayList;

/**
 * Created by rodo on 10/6/17.
 */

public class CommentHelper {

    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] COMMENTS_TABLE_NAME = {
            DBUtils.COMMENTS_ID,
            DBUtils.COMMENTS_NAME,
            DBUtils.COMMENTS_POSTID,
            DBUtils.COMMENTS_EMAIL,
            DBUtils.COMMENTS_BODY
    };

    public CommentHelper(Context context){
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }

    private Comments parseComment(Cursor cursor){
        Comments oComment = new Comments();
        oComment.setName(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_NAME)));
        oComment.setId(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_ID)));
        oComment.setBody(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_BODY)));
        oComment.setPostid(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_POSTID)));
        oComment.setEmail(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_EMAIL)));
        return oComment;
    }

    public int deleteComment(int nCommentID){
        return database.delete(DBUtils.COMMENTS_TABLE_NAME, DBUtils.COMMENTS_ID+ " = " + nCommentID, null);
    }

    public Comments addComment(String id, String postid, String body, String name, String email){
        ContentValues values = new ContentValues();
        values.put(DBUtils.COMMENTS_ID, id);
        values.put(DBUtils.COMMENTS_POSTID, postid);
        values.put(DBUtils.COMMENTS_BODY, body);
        values.put(DBUtils.COMMENTS_EMAIL, email);
        values.put(DBUtils.COMMENTS_NAME, name);

        long lCommentID = database.insert(DBUtils.COMMENTS_TABLE_NAME, null, values);

        Cursor cursor = database.query(DBUtils.COMMENTS_TABLE_NAME,
                COMMENTS_TABLE_NAME,
                DBUtils.COMMENTS_ID+" = " + lCommentID, null, null, null, null);
        cursor.moveToFirst();
        Comments oCustomer = parseComment(cursor);
        cursor.close();
        return oCustomer;
    }

    public ArrayList<Comments> getALlComments(){
        ArrayList<Comments>oLComments = new ArrayList<Comments>();
        Cursor cursor = database.query(DBUtils.COMMENTS_TABLE_NAME, COMMENTS_TABLE_NAME, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            oLComments.add(parseComment(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oLComments;
    }



}
