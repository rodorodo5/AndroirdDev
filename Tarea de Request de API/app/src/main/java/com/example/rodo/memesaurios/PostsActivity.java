package com.example.rodo.memesaurios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.rodo.memesaurios.Adapters.PostsAdapter;
import com.example.rodo.memesaurios.Models.Posts;
public class PostsActivity extends AppCompatActivity {

    PostsAdapter postAdapter;
    ListView listView;
    ArrayList<Posts> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        listView = (ListView) findViewById(R.id.listView);
        postAdapter = new PostsAdapter(this);
        listView.setAdapter(postAdapter);

        posts = this.getIntent().getParcelableArrayListExtra("Parcel");
        fillList(posts);
    }

    public void fillList(ArrayList<Posts> posts) {
        for (Posts post : posts) {
            postAdapter.add(post);
        }
        postAdapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        finish();
    }
}
