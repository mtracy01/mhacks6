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

public class SelectionListView extends ArrayAdapter<String> {
    private Activity context;
    private String[] web;
    private  Bitmap[] imageId;

    public SelectionListView(Activity context,
                            String[] web, Bitmap[] imageId) {
        super(context, R.layout.radio_row, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        /*View rowView = inflater.inflate(R.layout.list_single_invite, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        com.rey.material.widget.CheckBox checkBox = (com.rey.material.widget.CheckBox) rowView.findViewById(R.id.checkbox);
        final int rowPosition = position;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true)
                    Data.selectedUsers.add(Data.invitableUsers.get(rowPosition));
                else {
                    //If they are on the list of people to invite, remove them
                    if (Data.selectedUsers.contains(Data.invitableUsers.get(rowPosition)))
                        Data.selectedUsers.remove(Data.invitableUsers.get(rowPosition));
                }
            }
        });
        txtTitle.setText(web[position]);
        imageView.setImageBitmap(imageId[position]);
        return rowView;*/
        return null;
    }
}

