package com.example.park;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book extends AppCompatActivity {

    private EditText start,end,sn;
    private Spinner sp1;
    private Button cnf;
    private Spinner s1;
    private TimePickerDialog timePickerDialog;
    private FirebaseDatabase dbData=FirebaseDatabase.getInstance();
    private DatabaseReference dbref=dbData.getReference();
    private int slots [] = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getSupportActionBar().setTitle("Smart Parking System");

        dbref = dbData.getReference("Sensor");

        start = (EditText) findViewById(R.id.start);
        end = (EditText) findViewById(R.id.end);
        cnf = (Button) findViewById(R.id.cnf);
        sp1 = (Spinner)findViewById(R.id.vType);
        sn = (EditText)findViewById(R.id.sn);

        start.setOnClickListener(new View.OnClickListener() {

            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);

            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(Book.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        start.setText(hourOfDay+":"+minute);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {

            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);

            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(Book.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        end.setText(hourOfDay+":"+minute);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });

        cnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tstart=start.getText().toString();
                String tend=end.getText().toString();
                String snum = sn.getText().toString();

                int ch =Integer.parseInt(snum);

                if(tstart.isEmpty() && tend.isEmpty() && snum.isEmpty()){
                    Toast.makeText(Book.this,"Fields Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(ch < 1 || ch > 6){
                    Toast.makeText(Book.this,"Please enter valid slot",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(Book.this,snum,Toast.LENGTH_LONG).show();
                    dbref.child("Slot no:- "+snum).setValue("0");
                    startActivity(new Intent(Book.this,QrCode.class));
                }

            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent I=new Intent(Book.this,MainActivity.class);
        startActivity(I);
    }
}
