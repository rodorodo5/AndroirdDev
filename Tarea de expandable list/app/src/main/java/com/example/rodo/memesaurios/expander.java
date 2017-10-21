package com.example.rodo.memesaurios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


import com.example.rodo.memesaurios.Adapters.expandAdapter;
import com.example.rodo.memesaurios.Models.Comments;
import com.example.rodo.memesaurios.Models.Posts;

public class expander extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    TreeMap<String, List<String>> expandableListDetail;
    ArrayList<Posts> posts;
    ArrayList<Comments> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expander);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        posts = this.getIntent().getParcelableArrayListExtra("Posts");
        comments = this.getIntent().getParcelableArrayListExtra("Comments");
        expandableListDetail = getData(posts, comments);

        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new expandAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                return false;
            }
        });
    }

    public TreeMap<String, List<String>> getData(ArrayList<Posts> posts, ArrayList<Comments> comments) {
        TreeMap<String, List<String>> data = new TreeMap<>();
        for (Posts p : posts) {
            String postString = p.getTitle() + " (ID: " + p.getId() + ")";
            List<String> commentStrings = new ArrayList<String>();
            for(Comments c : comments) {
                if (c.getPostid().equals(p.getId())) {
                    String commentString = c.getName() + " said:\n" + c.getBody();
                    commentStrings.add(commentString);
                }
            }
            data.put(postString, commentStrings);
        }
        System.out.println("Data size: " + data.keySet().size());
        return data;
    }
    public void onBackPressed() {
        finish();
    }
}
