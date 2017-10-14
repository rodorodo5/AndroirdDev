package com.example.rodo.memesaurios.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.rodo.memesaurios.Models.Posts;
import com.example.rodo.memesaurios.R;
/**
 * Created by rodo on 10/13/17.
 */
public class PostsAdapter extends ArrayAdapter<Posts> {

    public PostsAdapter(Context context) {
        super(context, R.layout.posts_lay, R.id.myPlay);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView = super.getView(position, convertView, parent);
        TextView txtInfo = (TextView) objectView.findViewById(R.id.myPlay);

        final Posts Posts = this.getItem(position);
        //txtInfo.setText(Posts.printPost());

        return objectView;
    }
}

