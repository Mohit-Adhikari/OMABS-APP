package com.example.a4thsemproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class register_info_doctor extends AppCompatActivity {
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_info);

        TextView emailView = findViewById(R.id.emailEditText);
        TextView passwordView = findViewById(R.id.passwordEditText);
        Button signIN=findViewById(R.id.registerButton);
        signIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String email = emailView.getText().toString().trim();
                String password=passwordView.getText().toString().trim();
                TextView noAccount=findViewById(R.id.registerTextView);
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(register_info_doctor.this, task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(register_info_doctor.this,"New Account Made",Toast.LENGTH_SHORT).show();
                                uid = auth.getCurrentUser().getUid();
                                //linkUIDToFirestore(uid);
                               start_get_patient_info();
                            } else {
                                Toast.makeText(register_info_doctor.this,"Failed Authentication",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }
    public void start_get_patient_info()
    {

        Intent customerInfoPage=new Intent(this,doctorInfo.class);
        customerInfoPage.putExtra("UID", uid);
        startActivity(customerInfoPage);
    }
}
