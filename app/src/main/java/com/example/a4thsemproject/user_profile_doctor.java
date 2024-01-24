package com.example.a4thsemproject;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class user_profile_doctor extends AppCompatActivity {
    String uid;
    TextView name;
    TextView age;
    TextView gender;
    List<String> myStringList = new ArrayList<>();

    TextView search;
    String namef;
    TextView hospital;
    TextView speciality;
    int agef;
    String genderf;
    String hospital_name;
    String specialization;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_doctor);
        name=findViewById(R.id.nameTextView);
        age=findViewById(R.id.ageTextView);
        gender=findViewById(R.id.genderTextView);
        uid = getIntent().getStringExtra("uid");
        search=findViewById(R.id.searchTextView);
        hospital=findViewById(R.id.hospitalTextView);
        speciality=findViewById(R.id.specializationTextView);

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference userRef= database.collection("doctors").document(uid);

        userRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Data is available in the document
                                namef = document.getString("name");
                                agef = document.getLong("age").intValue(); // Assuming "age" is stored as a number
                                genderf = document.getString("gender");
                                hospital_name=document.getString("hospital");
                                specialization=document.getString("specialization");
                                name.setText(namef);
                                age.setText(String.valueOf(agef));
                                gender.setText(genderf);


                                Log.d(TAG, "Name: " + namef);
                                Log.d(TAG, "Age: " + agef);
                                Log.d(TAG, "Gender: " + genderf);
                                Log.i(TAG,"UID:"+uid);

                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.w(TAG, "Error getting document", task.getException());
                        }
                    }
                });
        search.setText("Want to go live?");
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent set_live=new Intent(user_profile_doctor.this, assignRealtime.class);
                //set_live.putExtra("uid",uid);
                //startActivity(set_live);
                start_assaignRealtime();

            }
        });




    }
    public void start_assaignRealtime(){
        Intent set_live=new Intent(user_profile_doctor.this, assignRealtime.class);
        set_live.putExtra("uid",uid);
        set_live.putExtra("namef",namef);
        set_live.putExtra("age",agef);
        set_live.putExtra("gender",genderf);
        set_live.putExtra("hospital",hospital_name);
        set_live.putExtra("speciality",specialization);
        startActivity(set_live);
    }

}

