package com.example.park;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private Button p1,p2,p3,p4,p5,p6,nxt;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6;
    private int ch1,ch2,ch3,ch4,ch5,ch6=1;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase mData=FirebaseDatabase.getInstance();
    private DatabaseReference mref=mData.getReference();

    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Smart Parking System");

       /* this.mHandler = new Handler();
        mRunnable.run();*/

        nxt=(Button)findViewById(R.id.logout);
        p1=(Button)findViewById(R.id.p1);
        p2=(Button)findViewById(R.id.p2);
        p3=(Button)findViewById(R.id.p3);
        p4=(Button)findViewById(R.id.p4);
        p5=(Button)findViewById(R.id.p5);
        p6=(Button)findViewById(R.id.p6);

        tv1=(TextView)findViewById(R.id.d1);
        tv2=(TextView)findViewById(R.id.d2);
        tv3=(TextView)findViewById(R.id.d3);
        tv4=(TextView)findViewById(R.id.d4);
        tv5=(TextView)findViewById(R.id.d5);
        tv6=(TextView)findViewById(R.id.d6);

        content();

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // FirebaseAuth.getInstance().signOut();

                Intent I=new Intent(MainActivity.this,Book.class);
                startActivity(I);
            }
        });
    }

    public void content(){
        mref.child("Sensor").child("Slot no:- 1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ps=dataSnapshot.getValue(String.class);
                tv1.setText(ps);
                ps=tv1.getText().toString();
                ch1=Integer.parseInt(ps);
                if(ch1==1)
                {
                    p1.setBackgroundColor(Color.GREEN);
                }
                else {
                    p1.setBackgroundColor(Color.RED);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mref.child("Sensor").child("Slot no:- 2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ps=dataSnapshot.getValue(String.class);
                tv2.setText(ps);
                ps=tv2.getText().toString();
                ch2=Integer.parseInt(ps);
                if(ch2==1)
                {
                    p2.setBackgroundColor(Color.GREEN);
                }
                else {
                    p2.setBackgroundColor(Color.RED);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mref.child("Sensor").child("Slot no:- 3").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ps=dataSnapshot.getValue(String.class);
                tv3.setText(ps);
                ps=tv3.getText().toString();
                ch3=Integer.parseInt(ps);
                if(ch3==1)
                {
                    p3.setBackgroundColor(Color.GREEN);
                }
                else {
                    p3.setBackgroundColor(Color.RED);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mref.child("Sensor").child("Slot no:- 4").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ps=dataSnapshot.getValue(String.class);
                tv4.setText(ps);
                ps=tv4.getText().toString();
                ch4=Integer.parseInt(ps);
                if(ch4==1)
                {
                    p4.setBackgroundColor(Color.GREEN);
                }
                else {
                    p4.setBackgroundColor(Color.RED);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mref.child("Sensor").child("Slot no:- 5").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ps=dataSnapshot.getValue(String.class);
                tv5.setText(ps);
                ps=tv5.getText().toString();
                ch5=Integer.parseInt(ps);
                if(ch5==1)
                {
                    p5.setBackgroundColor(Color.GREEN);
                }
                else {
                    p5.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mref.child("Sensor").child("Slot no:- 6").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ps=dataSnapshot.getValue(String.class);
                tv6.setText(ps);
                ps=tv6.getText().toString();
                ch6=Integer.parseInt(ps);
                if(ch6==1)
                {
                    p6.setBackgroundColor(Color.GREEN);
                }
                else {
                    p6.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*dbref = dbData.getReference("Sensor");

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ch1==1)
                {
                    p1.setBackgroundColor(Color.GREEN);
                }
                else {
                    p1.setBackgroundColor(Color.RED);
                    dbref.child("Slot no:- 1").setValue("0");
                }
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ch2==1)
                {
                    p2.setBackgroundColor(Color.GREEN);
                }
                else {
                    p2.setBackgroundColor(Color.RED);
                    dbref.child("Slot no:- 2").setValue("0");
                }
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ch3==1)
                {
                    p3.setBackgroundColor(Color.GREEN);
                }
                else {
                    p3.setBackgroundColor(Color.RED);
                    dbref.child("Slot no:- 3").setValue("0");
                }
            }
        });

        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ch4==1)
                {
                    p4.setBackgroundColor(Color.GREEN);
                }
                else {
                    p4.setBackgroundColor(Color.RED);
                    dbref.child("Slot no:- 4").setValue("0");
                }
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ch5==1)
                {
                    p5.setBackgroundColor(Color.GREEN);
                }
                else {
                    p5.setBackgroundColor(Color.RED);
                    dbref.child("Slot no:- 5").setValue("0");
                }
            }
        });

        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ch6==1)
                {
                    p6.setBackgroundColor(Color.GREEN);
                }
                else {
                    p6.setBackgroundColor(Color.RED);
                    dbref.child("Slot no:- 6").setValue("0");
                }
            }
        });*/
        refresh(500);
    }


    /*private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            MainActivity.this.mHandler.postDelayed(mRunnable,1000);
        }
    } ;*/

    public void refresh(int ms){

        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                content();
            }
        };

        handler.postDelayed(runnable,ms);
    }


    @Override
    public void onBackPressed(){
        Intent intent=new Intent(MainActivity.this,User.class);
        startActivity(intent);
    }
}
