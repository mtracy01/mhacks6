package com.qr.android.showme;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.*;

import java.io.File;

/**
 * Created by Ji on 9/13/15.
 */
public class HelperClass {
    public static boolean ret[] = new boolean[1];
    public static String resume;
    public boolean SetFacebookUrl( String Facebookurl){

        ParseUser user = ParseUser.getCurrentUser();
        user.put("FacebookURL",Facebookurl);
        if(user.get("FacebookURL").equals(Facebookurl)){
            return true;
        }
        return false;
    }
    public boolean SetLinkedInUrl( String LinkedInurl){
        ParseUser user = ParseUser.getCurrentUser();
        user.put("LinkedInURL", LinkedInurl);
        if(user.get("LinkedInURL").equals( LinkedInurl)){
            return true;
        }
        return false;
    }
    public boolean SetProfileUrl(String Profileurl){
        ParseUser user = ParseUser.getCurrentUser();
        user.put("ProfileURL", Profileurl);
        if(user.get("ProfileURL").equals(Profileurl)){
            return true;
        }
        return false;
    }

    public boolean SetResume(String context, String name){
        ParseUser user = ParseUser.getCurrentUser();
        byte[] data = context.getBytes();
        ParseFile file = new ParseFile(name,data);
        file.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    ret[0] = true;
                } else {
                    ret[0] = false;
                }
            }
        });
        if(ret[0] == true){
            user.put("Resume",file);
            user.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e!=null) ret[0] = false;
                }
            });
        }
        return ret[0];
    }

    public boolean setQRCode(ParseFile file) {
        ParseUser user = ParseUser.getCurrentUser();
        user.put("QRCode", file);
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) ret[0] = true;
                else ret[0] = false;
            }
        });
        return ret[0];
    }

    public ParseFile getQRCode(){
        ParseUser user = ParseUser.getCurrentUser();
        ParseFile file = user.getParseFile("Resume");
        return file;

    }
}
