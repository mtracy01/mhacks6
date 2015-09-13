package com.qr.android.showme.AccountLinking;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.qr.android.showme.R;

/**
 * Created by Matthew on 9/12/2015.
 * Purpose: Custom list for our Accounts Linking Page
 */

public class SelectionListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private String[] web;
    private  Bitmap[] imageId;

    public SelectionListAdapter(Activity context,
                                String[] web, Bitmap[] imageId) {
        super(context, R.layout.radio_row, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.radio_row, null, true);
        switch(position){
            case 0: //LinkedIn
                //TODO: LinkedIn Integration Here
                break;

            case 1: //Github
                //TODO: Github Integration Here
                break;
            case 2: //Facebook
                //TODO: Facebook Integration Here
                break;
            case 3: //Personal URL
                rowView = inflater.inflate(R.layout.text_row, null, true);
                //TODO: Personal URL integration here
                break;
        }
        TextView txt = (TextView)rowView.findViewById(R.id.txt);
        ImageView img = (ImageView)rowView.findViewById(R.id.img);
        txt.setText(web[position]);
        img.setImageBitmap(imageId[position]);
        return rowView;

    }
}

