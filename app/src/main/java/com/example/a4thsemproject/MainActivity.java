package com.example.a4thsemproject;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button doctor=findViewById(R.id.doctors);
        Button patient=findViewById(R.id.patient);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPatient();
            }
        });


    }
    public void startPatient()
    {
        Intent PatientPage=new Intent(this,home.class);
        startActivity(PatientPage);

    }
}
