package com.example.park;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class QrCode extends AppCompatActivity {

    private static final String TAG ="GenerateQrCode" ;
    private ImageView qrimg;
    private Button ext,save;
    Bitmap bitmap;
    private String inputValue;
    OutputStream outputStream;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = database.getReference();
    DatabaseReference dbRef1 = database.getReference();

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }


    QRGEncoder qrgEncoder;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        getSupportActionBar().setTitle("Smart Parking System");

        inputValue=randomAlphaNumeric(7);

        dbRef = database.getReference("QRCodeData");

        Calendar c=Calendar.getInstance();

        int s=c.get(Calendar.SECOND);
        int m=c.get(Calendar.MINUTE);
        int h=c.get(Calendar.HOUR);

        int d=c.get(Calendar.DAY_OF_MONTH);
        int mo=c.get(Calendar.MONTH);
        int y=c.get(Calendar.YEAR);

        Helper help =new Helper(inputValue,s,m,h,d,mo,y);

        dbRef.child(inputValue).setValue(help);
        dbRef1 = database.getReference("/QRCodeData/"+inputValue+"/Attempt");
        dbRef1.setValue(2);

        qrimg=(ImageView)findViewById(R.id.qr);
        ext=(Button)findViewById(R.id.ext);
        //save=(Button)findViewById(R.id.sve);
;
        if(inputValue.length()>0){
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;

            qrgEncoder = new QRGEncoder(
                    inputValue, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                qrimg.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }
        } else {
            Toast.makeText(QrCode.this,"Error!",Toast.LENGTH_LONG).show();
        }

        ext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(QrCode.this,User.class);
                startActivity(I);
            }
        });

        /*save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable drawable = (BitmapDrawable) qrimg.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                File path = getExternalFilesDir(Environment.DIRECTORY_DCIM);
                File dir = new File(path.getAbsolutePath()+"/SPS/");
                dir.mkdirs();
                File file = new File(dir, System.currentTimeMillis()+".jpg");
                try {
                    outputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG ,100,outputStream);
                Toast.makeText(getApplicationContext(), "Image Saved to Internal!",Toast.LENGTH_SHORT).show();
                try {
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }

        });*/
    }

    @Override
    public void onBackPressed(){
        Intent I=new Intent(QrCode.this,Login.class);
        startActivity(I);
    }
}
