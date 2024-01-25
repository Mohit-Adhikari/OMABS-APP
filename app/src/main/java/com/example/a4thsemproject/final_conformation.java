package com.example.a4thsemproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child(time).setValue(false);
                Toast.makeText(final_conformation.this,"Your booking has been Conformed",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(final_conformation.this,user_profile.class);
                intent.putExtra("doctor's name",name);
                intent.putExtra("doctor_specialization",specialization);
                intent.putExtra("time",time);
                startActivity(intent);
            }
        });

    }
}
