package com.qr.android.showme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class QRScannerActivity extends ActionBarActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public Uri QRUri;
    public static File mediaFile;
    String qrCodeData = "Hello World!";
    String filePath = "QRCode.png";
    String charset = "UTF-8"; // or "ISO-8859-1"
    public static String imagePath;
    public int SCANNER_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        Button scanQRButton = (Button) findViewById(R.id.scanQRButton);
        scanQRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                QRUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, QRUri);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);*/

                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == SCANNER_REQUEST_CODE) {
            System.out.println("1141 scan intent?");// Handle scan intent
            if (resultCode == Activity.RESULT_OK) {
                // Handle successful scan
                String contents = intent.getStringExtra("SCAN_RESULT");
                //String formatName = intent.getStringExtra("SCAN_RESULT_FORMAT");
                //byte[] rawBytes = intent.getByteArrayExtra("SCAN_RESULT_BYTES");
                //int intentOrientation = intent.getIntExtra("SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
                //Integer orientation = (intentOrientation == Integer.MIN_VALUE) ? null : intentOrientation;
                //String errorCorrectionLevel = intent.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL");

                System.out.println("1141 " + contents);

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // Handle cancel
            }
        } else {
            String contents = intent.getStringExtra("SCAN_RESULT");
                System.out.println("1141 other " + contents);
        }

    }

    /*private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }


    private static File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "QRCode.jpg");
            imagePath = mediaStorageDir.getPath() + File.separator + "QRCode.jpg";
        }else {
            return null;
        }

        return mediaFile;
    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {


                /*Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

                createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);


                System.out.println("1141 Data read from QR Code: "
                        + readQRCode(filePath, charset, hintMap));

                /*Runnable runnable = new Runnable() {


                    public void run() {
                        try {//http request goes here

                            File file = mediaFile;
                            HttpClient httpClient = new DefaultHttpClient();
                            HttpPost httpPost = new HttpPost("http://api.qrserver.com/v1/read-qr-code/");


                            InputStreamEntity reqEntity = new InputStreamEntity(
                                    new FileInputStream(file), -1);
                            reqEntity.setContentType("binary/octet-stream");

                            reqEntity.setChunked(true);


                            httpPost.setEntity(reqEntity);

                            HttpResponse response = httpClient.execute(httpPost);

                            System.out.println("1141 " + response.getStatusLine().toString());
                        } catch (FileNotFoundException e) {
                            System.out.println("1141 FileNotFoundException");
                        } catch (ClientProtocolException e) {
                            System.out.println("1141 ClientProtocolException");
                        } catch (IOException e) {
                            System.out.println("1141 IOException");
                        }
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qrscanner, menu);
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
