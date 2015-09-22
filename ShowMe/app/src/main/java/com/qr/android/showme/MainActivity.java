package com.qr.android.showme;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.parse.Parse;
import com.parse.ParseUser;
import com.qr.android.showme.AccountLinking.SelectionActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!Global.first) {
            Global.first = true;
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "yCdbM12FKZAqcHEvp4PIEFVDEZHMhJNkSUKuxc0d", "M75faIfv4O3DSmhK6gqnI0mBHIHBxNIRDJseHNA5");

        }
        setContentView(R.layout.activity_main);

        Button openQRScannerButton = (Button) findViewById(R.id.openQRScannerButton);
        openQRScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QRScannerActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });

        Button createQRCodeButton = (Button) findViewById(R.id.createQRCodeButton);
        createQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreateQRActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });

        Button signOutButton = (Button) findViewById(R.id.signoutbutton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOutInBackground();
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
            }
        });

        Button linkAccountButton = (Button)findViewById(R.id.linkButton);
        linkAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SelectionActivity.class));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
