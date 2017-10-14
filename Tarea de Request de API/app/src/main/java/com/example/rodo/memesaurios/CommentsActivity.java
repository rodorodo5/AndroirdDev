package com.example.rodo.memesaurios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rodo.memesaurios.Adapters.CommentsAdapter;
import com.example.rodo.memesaurios.Models.Comments;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    CommentsAdapter commentAdapter;
    ListView listView;
    ArrayList<Comments> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        listView = (ListView) findViewById(R.id.listView);
        commentAdapter = new CommentsAdapter(this);
        listView.setAdapter(commentAdapter);

        comments = this.getIntent().getParcelableArrayListExtra("Parcel");
        fillList(comments);
    }

    public void fillList(ArrayList<Comments> comments) {
        for (Comments comment : comments) {
            commentAdapter.add(comment);
        }
        commentAdapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        finish();
    }
}
