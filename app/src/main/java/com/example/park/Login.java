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
import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button login,register;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Smart Parking System");

        firebaseAuth=FirebaseAuth.getInstance();



        email=(EditText)findViewById(R.id.logemail);
        password=(EditText)findViewById(R.id.logpass);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user =firebaseAuth.getCurrentUser();

                if(user!=null){
                    Toast.makeText(Login.this,"User Logged In",Toast.LENGTH_SHORT).show();
                    Intent I=new Intent(Login.this,User.class);
                    startActivity(I);
                }else {
                    Toast.makeText(Login.this,"LogIn to continue",Toast.LENGTH_SHORT).show();
                }
            }
        };


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Sign.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String useremail=email.getText().toString();
                String userpass=password.getText().toString();

                if(useremail.isEmpty()){
                    email.setError("Enter email address!");
                    email.requestFocus();
                }

                else if(userpass.isEmpty()){
                    password.setError("Enter password!");
                    password.requestFocus();
                }
                else if(useremail.isEmpty() && userpass.isEmpty()){
                    Toast.makeText(Login.this,"Fields Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(useremail.isEmpty() && userpass.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(useremail,userpass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this,"Not Sucessfull",Toast.LENGTH_SHORT).show();
                            }else {
                                startActivity(new Intent(Login.this,User.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Login.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onBackPressed(){
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
    }

}
