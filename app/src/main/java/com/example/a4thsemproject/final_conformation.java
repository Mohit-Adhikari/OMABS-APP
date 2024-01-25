package com.example.a4thsemproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class final_conformation extends AppCompatActivity {
    String name;
    String specialization;
    String time;
    private DatabaseReference databaseReference;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_conformation);
        name=getIntent().getStringExtra("name");
        specialization=getIntent().getStringExtra("specialization");
        time=getIntent().getStringExtra("time");
        databaseReference= FirebaseDatabase.getInstance().getReference("doctors").child(specialization).child(name).child("appointment_slots");
        TextView doctor_name=findViewById(R.id.textDoctorName);
        TextView doctor_speciality=findViewById(R.id.textSpeciality);
        TextView doctor_time=findViewById(R.id.textTime);
        Button submit=findViewById(R.id.btnConfirm);
        Button cancel=findViewById(R.id.btnCancel);
        doctor_name.setText(name);
        doctor_speciality.setText(specialization);
        doctor_time.setText(time);
        String dname = DataHolder.getInstance().getName();
        String dgender = DataHolder.getInstance().getGender();
        String dage = DataHolder.getInstance().getAge();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(time).setValue(false);
                Map<String,Object> patient=new HashMap<>();
                patient.put("name",dname);
                patient.put("gender",dgender);
                patient.put("age",dage);
                databaseReference.child(time).setValue(patient);

            }
        });

    }
}