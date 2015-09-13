package com.qr.android.showme;

import android.util.Log;

import com.parse.*;



/**
 * Created by Ji on 9/13/15.
 */
public class HelperClass {
    public static boolean ret[] = new boolean[1];
    public static String resume;
    public static boolean SetFacebookUrl( String Facebookurl){

        ParseUser user = ParseUser.getCurrentUser();
        user.put("FacebookURL",Facebookurl);
        if(((String)user.get("FacebookURL")).equals(Facebookurl)){

            return true;
        }
        Log.e("hI","Wrongt");
        return false;
    }
    public static boolean SetLinkedInUrl( String LinkedInurl){
        ParseUser user = ParseUser.getCurrentUser();
        user.put("LinkedInURL", LinkedInurl);
        if(((String)user.get("LinkedInURL")).equals( LinkedInurl)){
            String s = (String)user.get("FacebookURL");
            Log.e("hI",s );
            return true;
        }
        return false;
    }
    public static boolean SetProfileUrl(String Profileurl){
        ParseUser user = ParseUser.getCurrentUser();
        user.put("ProfileURL", Profileurl);
        if(((String)user.get("ProfileURL")).equals(Profileurl)){
            return true;
        }
        return false;
    }

    public static boolean SetResume(String context, String name){
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

    public static boolean setQRCode(ParseFile file) {
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

    public static ParseFile getQRCode(){
        ParseUser user = ParseUser.getCurrentUser();
        ParseFile file = user.getParseFile("Resume");
        return file;
    }
}
