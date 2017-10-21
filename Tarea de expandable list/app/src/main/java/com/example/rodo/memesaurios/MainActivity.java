package com.example.rodo.memesaurios;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    TextView txtString, txtString2, txtId;
    Button btnTest,btnPostsComments, btnSyncPosts, btnSyncComments, btnViewPosts, btnViewComments, btnClear,buttonPostPost,buttonPostComments;
    DBHelper db;
    ArrayList<Posts> posts;
    ArrayList<Comments> comments;
    String postsUrl, commentsUrl;
    String urlPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        txtString = (TextView) findViewById(R.id.textString);
        txtString2 = (TextView) findViewById(R.id.textString2);
        buttonPostComments = btnTest = (Button) findViewById((R.id.buttonPostComments));
        btnTest = (Button) findViewById((R.id.button1));
        buttonPostPost = (Button) findViewById((R.id.buttonPostPost));
        btnSyncPosts = (Button) findViewById((R.id.buttonSyncP));
        btnSyncComments = (Button) findViewById((R.id.buttonSyncC));
        btnViewPosts = (Button) findViewById((R.id.buttonPosts));
        btnViewComments = (Button) findViewById((R.id.buttonComments));
        btnClear = (Button) findViewById((R.id.buttonClear));
        posts = new ArrayList<Posts>();
        btnPostsComments = (Button) findViewById(R.id.buttonPostsComments);
        comments = new ArrayList<Comments>();
        final RequestQueue queue = Volley.newRequestQueue(this);
        postsUrl = "http://jsonplaceholder.typicode.com/posts";
        commentsUrl = "http://jsonplaceholder.typicode.com/comments";
        urlPost ="http://107.170.247.123:2403/posts";



        final JsonArrayRequest postsArrayRequest = new JsonArrayRequest(Request.Method.GET, postsUrl,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String result = "";
                posts = new ArrayList<Posts>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        result += response.getString(i) + "\n\n";
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




        final JsonArrayRequest commentsArrayRequest = new JsonArrayRequest(Request.Method.GET, commentsUrl,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String result = "";
                comments = new ArrayList<Comments>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        result += response.getString(i) + "\n\n";
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


       final StringRequest strRequest = new StringRequest(Request.Method.POST, urlPost,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Posts posts =  new Posts();
                Map<String, String> params = new HashMap<String, String>();
                params.put("postid", String.valueOf(1));

                params.put("title", "memesaurio");
                params.put("body", "RODOLFO RODRIGUEZZZZZZZZZZ");
                return params;
            }
        };


        btnSyncPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "sincronizando posts..", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                db.open();
                db.deleteAllPosts();
                db.close();
                queue.add(postsArrayRequest);
                Snackbar.make(view, "Se sincronizaron los posts.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        buttonPostPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "POSTEANDO posts", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                queue.add(strRequest);
                Snackbar.make(view, "Se POSTEARON los posts", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        btnSyncComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "sincronizando comments..", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                db.open();
                db.deleteAllComments();
                db.close();
                queue.add(commentsArrayRequest);
                Snackbar.make(view, "Se sincronizaron los comments", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
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
        btnPostsComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                posts = db.getAllPosts();
                comments = db.getAllComments();
                db.close();
                Intent intent = new Intent(getApplicationContext(), expander.class);
                intent.putExtra("Posts", posts);
                intent.putExtra("Comments", comments);
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
                Snackbar.make(view, "", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
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


