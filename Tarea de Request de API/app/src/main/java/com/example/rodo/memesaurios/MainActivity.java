package com.example.rodo.memesaurios;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rodo.memesaurios.Helpers.DBHelper;
import com.example.rodo.memesaurios.Models.Comments;
import com.example.rodo.memesaurios.Models.Posts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TextView txtString, txtString2, txtId;
    Button btnTest, btnSyncPosts, btnSyncComments, btnViewPosts, btnViewComments, btnClear;
    DBHelper db;
    ArrayList<Posts> posts;
    ArrayList<Comments> comments;
    String postsUrl, commentsUrl;

    final String url = "i love memes.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        txtString = (TextView) findViewById(R.id.textString);
        txtString2 = (TextView) findViewById(R.id.textString2);
        txtId = (TextView) findViewById(R.id.textId);
        btnTest = (Button) findViewById((R.id.button1));
        btnSyncPosts = (Button) findViewById((R.id.buttonSyncP));
        btnSyncComments = (Button) findViewById((R.id.buttonSyncC));
        btnViewPosts = (Button) findViewById((R.id.buttonPosts));
        btnViewComments = (Button) findViewById((R.id.buttonComments));
        btnClear = (Button) findViewById((R.id.buttonClear));
        posts = new ArrayList<Posts>();
        comments = new ArrayList<Comments>();
        final RequestQueue queue = Volley.newRequestQueue(this);
        postsUrl = "http://jsonplaceholder.typicode.com/posts";
        commentsUrl = "http://jsonplaceholder.typicode.com/comments";



        final JsonArrayRequest postsArrayRequest = new JsonArrayRequest(Request.Method.GET, postsUrl,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String result = "";
                posts = new ArrayList<Posts>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        //result += response.getString(i) + "\n\n";
                        posts.add(createPost(response.getString(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtString.setText(error.toString());
            }
        });


        final StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        //TEXTVIEW
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });

        final JsonArrayRequest commentsArrayRequest = new JsonArrayRequest(Request.Method.GET, commentsUrl,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String result = "";
                comments = new ArrayList<Comments>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        //result += response.getString(i) + "\n\n";
                        comments.add(createComment(response.getString(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtString.setText(error.toString());
            }
        });

        btnSyncPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sync Post..", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                db.open();
                db.deleteAllPosts();
                db.close();
                queue.add(postsArrayRequest);
                Snackbar.make(view, "Posts successfully synchronized.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        btnSyncComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sync Comments..", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                db.open();
                db.deleteAllComments();
                db.close();
                queue.add(commentsArrayRequest);
                Snackbar.make(view, "Comments successfully synchronized.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        btnViewPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PostsActivity.class);
                db.open();
                intent.putExtra("Parcel", db.getAllPosts());
                db.close();
                startActivity(intent);
            }
        });

        btnViewComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentsActivity.class);
                db.open();
                intent.putExtra("Parcel", db.getAllComments());
                db.close();
                startActivity(intent);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                db.deleteAllPosts();
                db.deleteAllComments();
                db.close();
                Snackbar.make(view, "Data has been cleared.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });
    }



    public Posts createPost(String response) {
        Posts post = new Posts();
        try {
            JSONObject jsonObject = new JSONObject(response);
            int userId = jsonObject.getInt("userId");
            int id = jsonObject.getInt("id");
            String title = jsonObject.getString("title");
            String body = jsonObject.getString("body");
            db.open();
            post = db.addPost(id, userId, title, body);
            db.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

    public Comments createComment(String response) {
        Comments comments = new Comments();
        try {
            JSONObject jsonObject = new JSONObject(response);
            int postId = jsonObject.getInt("postId");
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String body = jsonObject.getString("body");
            db.open();
            comments = db.addComment(id, postId, name, email, body);
            db.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comments;
    }
}


