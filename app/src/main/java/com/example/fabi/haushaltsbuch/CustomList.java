package com.example.fabi.haushaltsbuch;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * CustomListAdapter. Ermöglicht uns Bilder sowie Text in einem ArrayAdapter darzustellen.
 * Created by Fabian on 26.01.2017.
 */


class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    CustomList(Activity context,
               String[] web, Integer[] imageId) {
        super(context, R.layout.layout_add_listitem, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }
    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.layout_add_listitem, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}