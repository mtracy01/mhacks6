package com.qr.android.showme.AccountLinking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.qr.android.showme.R;

import java.util.ArrayList;
import java.util.List;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        ArrayList<String> titles = createTitles();
        ArrayList<Bitmap> icons  = createImagesList();
        ListView listView = (ListView)findViewById(R.id.listView);
        String[] stuff = new String[titles.size()];
        Bitmap[] bitstuff = new Bitmap[icons.size()];
        SelectionListAdapter adapter = new SelectionListAdapter(this,titles.toArray(stuff),icons.toArray(bitstuff));
        listView.setAdapter(adapter);

    }

    private ArrayList<String> createTitles(){
        ArrayList<String> list = new ArrayList<>();
        list.add("LinkedIn");
        list.add("Github");
        list.add("Facebook");
        list.add("Personal URL");
        return list;
    }

    private ArrayList<Bitmap> createImagesList(){
        ArrayList<Bitmap> list = new ArrayList<>();
        list.add(BitmapFactory.decodeResource(getResources(), R.mipmap.linkedin));
        list.add(BitmapFactory.decodeResource(getResources(), R.mipmap.github));
        list.add(BitmapFactory.decodeResource(getResources(), R.mipmap.facebook));
        list.add(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        return list;
    }

}
