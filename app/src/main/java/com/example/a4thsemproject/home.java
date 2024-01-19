package com.example.a4thsemproject;

import static com.google.firebase.auth.FirebaseAuth.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);



        TextView emailView = findViewById(R.id.emailEditText);
        TextView passwordView = findViewById(R.id.passwordEditText);
        Button signIN=findViewById(R.id.registerButton);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        TextView noAccount=findViewById(R.id.registerTextView);
        signIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailView.getText().toString().trim();
                String password=passwordView.getText().toString().trim();
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(home.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(home.this,"Sign In Sucessful",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(home.this,"Failed. Try Again Later",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegistration();
            }
        });


    }
    public void startRegistration()
    {
        Intent RegistertPage=new Intent(this,register_info.class);
        startActivity(RegistertPage);

    }
}

