package com.example.a4thsemproject;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class assignRealtime extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_realtime);
        String uid, namef,agef,genderf,hospital,speciality;
        setContentView(R.layout.assign_realtime);
        Button live = findViewById(R.id.myButton);
        uid = getIntent().getStringExtra("uid");
        namef = getIntent().getStringExtra("namef");
        agef=String.valueOf(getIntent().getStringExtra("agef"));
        genderf=getIntent().getStringExtra("genderf");
        hospital=getIntent().getStringExtra("hospital");
        speciality=getIntent().getStringExtra("speciality");
        FirebaseDatabase database_add = FirebaseDatabase.getInstance();
        DatabaseReference doctorsRef = database_add.getReference("doctors");

        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String doctorId="eye";
                //String doctorId = speciality;



                Map<String, Object> doctorData = new HashMap<>();
                doctorData.put("age:",agef);
                doctorData.put("gender",genderf);
                doctorData.put("hospital",hospital);

                Map<String, Object> appointmentSlots = new HashMap<>();
                appointmentSlots.put("10 00", true);
                appointmentSlots.put("10 30", false);
                appointmentSlots.put("11 00", true);
                appointmentSlots.put("11 30", true);
                appointmentSlots.put("12 00", true);
                appointmentSlots.put("12 30", true);
                appointmentSlots.put("13 00", true);
                appointmentSlots.put("13 30", true);
                appointmentSlots.put("14 00", true);
                appointmentSlots.put("14 30", true);
                doctorData.put("appointment_slots", appointmentSlots);
                doctorsRef.child(speciality).child(namef).setValue(doctorData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "Data successfully written to Realtime Database!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing data to Realtime Database", e);
                            }
                        });

            }
        });


    }

}
