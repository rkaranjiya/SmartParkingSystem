package com.example.park;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Sign extends AppCompatActivity {


    private EditText name,email,password;
    private Button register;
    private FirebaseAuth auth;
    private String str_name;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        getSupportActionBar().setTitle("Smart Parking System");

        auth=FirebaseAuth.getInstance();

        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);
        register=(Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_name=name.getText().toString();
                String ipemail=email.getText().toString();
                String ippass=password.getText().toString();


                if(str_name.isEmpty()){
                    name.setError("Enter name");
                    name.requestFocus();
                }

                else if(ipemail.isEmpty()){
                    email.setError("Enter email Address");
                    email.requestFocus();
                }

                else if(ippass.isEmpty()){
                    password.setError("Enter Password");
                    password.requestFocus();
                }

                else if(ippass.length()<6){
                    Toast.makeText(getApplicationContext(),"Password too short, enter minimum 6 character",Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(ipemail.isEmpty() && ippass.isEmpty() && str_name.isEmpty()){
                    Toast.makeText(Sign.this,"Fields Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(ipemail.isEmpty() && ippass.isEmpty())){

                    auth.createUserWithEmailAndPassword(ipemail,ippass).addOnCompleteListener(Sign.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Sign.this,"Signup unsuccessful:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(Sign.this,MainActivity.class));
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(Sign.this,"Error!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent I=new Intent(Sign.this,Login.class);
        startActivity(I);
    }
}
