package com.qr.android.showme.AccountLinking;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.qr.android.showme.HelperClass;
import com.qr.android.showme.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Matthew on 9/12/2015.
 * Purpose: Custom list for our Accounts Linking Page
 */

public class SelectionListAdapter extends ArrayAdapter<String> {

    //Facebook stuff
    CallbackManager callbackManager;

    private Activity context;
    private String[] web;
    private  Bitmap[] imageId;

    public SelectionListAdapter(Activity context,
                                String[] web, Bitmap[] imageId) {
        super(context, R.layout.radio_row, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.radio_row, null, true);
        switch(position){
            case 0: //LinkedIn
                /*LISessionManager.getInstance(getApplicationContext()).init(SelectionActivity, buildScope(), new AuthListener() {
                    @Override
                    public void onAuthSuccess() {
                        // Authentication was successful.  You can now do
                        // other calls with the SDK.
                    }

                    @Override
                    public void onAuthError(LIAuthError error) {
                        // Handle authentication errors
                    }
                }, true);*/
                break;
            case 1: //Github
                //TODO: Github Integration Here
                break;
            case 2: //Facebook
                //TODO: Facebook Integration Here
                rowView = inflater.inflate(R.layout.facebook_row,null,true);
                LoginButton loginButton = (LoginButton) rowView.findViewById(R.id.login_button);
                loginButton.setReadPermissions("public_profile");
                // If using in a fragment
                //loginButton.setFragment(this);
                // Other app specific specialization

                // Callback registration
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        String userid = FacebookSdk.getClientToken();
                        //TODO: Get link to FB profile and put it in database
                        /* make the API call */
                        new GraphRequest(
                                AccessToken.getCurrentAccessToken(),
                                /*"/{user-id}" , */"/" + userid,
                                null,
                                HttpMethod.GET,
                                new GraphRequest.Callback() {
                                    public void onCompleted(GraphResponse response) {
                                      /* handle the result */
                                        try {
                                            String link = (String) response.getJSONObject().get("link");
                                            Log.e("SelectionListAdapter","Facebook link: " + link);
                                            //TODO: Save this facebook link to database
                                            HelperClass.SetFacebookUrl(link);
                                        }
                                        catch(Exception e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        ).executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

                break;
            case 3: //Personal URL
                rowView = inflater.inflate(R.layout.text_row, null, true);
                //TODO: Personal URL integration here
                break;
            case 4: //Upload resume
                //TODO: Create custom row layout for this
                //TODO: Resume upload implementation here
        }
        TextView txt = (TextView)rowView.findViewById(R.id.txt);
        ImageView img = (ImageView)rowView.findViewById(R.id.img);
        txt.setText(web[position]);
        img.setImageBitmap(imageId[position]);
        return rowView;

    }
}

