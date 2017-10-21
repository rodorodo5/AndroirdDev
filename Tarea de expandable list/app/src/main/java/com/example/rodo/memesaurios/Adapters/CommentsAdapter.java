package com.example.rodo.memesaurios.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.rodo.memesaurios.Models.Comments;
import com.example.rodo.memesaurios.R;
/**
 * Created by rodo on 10/13/17.
 */

public class CommentsAdapter extends ArrayAdapter<Comments> {

    public CommentsAdapter(Context context) {
        super(context, R.layout.comments_lay, R.id.mytext);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView = super.getView(position, convertView, parent);
        TextView txtInfo = (TextView) objectView.findViewById(R.id.mytext);

        final Comments comment = this.getItem(position);
        txtInfo.setText(comment.printComment());

        return objectView;
    }
}
