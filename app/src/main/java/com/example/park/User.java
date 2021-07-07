package com.example.park;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class User extends AppCompatActivity {

    private Button book,map,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().setTitle("Smart Parking System");

        book=(Button)findViewById(R.id.book);
        map=(Button)findViewById(R.id.map);
        exit=(Button)findViewById(R.id.ext);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(User.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(User.this,MapActivity.class);
                startActivity(intent);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(User.this,Login.class);
                startActivity(I);
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent I=new Intent(User.this,Login.class);
        startActivity(I);
        FirebaseAuth.getInstance().signOut();
    }
}
