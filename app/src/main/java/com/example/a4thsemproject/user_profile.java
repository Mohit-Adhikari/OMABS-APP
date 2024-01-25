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

public class user_profile extends AppCompatActivity {
    String uid;
    TextView name;
    TextView age;
    TextView gender;
    List<String> myStringList = new ArrayList<>();

    TextView search;



        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_profile);
            name=findViewById(R.id.nameTextView);
            age=findViewById(R.id.ageTextView);
            gender=findViewById(R.id.genderTextView);
            uid = getIntent().getStringExtra("uid");
            search=findViewById(R.id.searchTextView);

            FirebaseFirestore database = FirebaseFirestore.getInstance();
            DocumentReference userRef= database.collection("users").document(uid);

            userRef.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    // Data is available in the document
                                    String namef = document.getString("name");
                                    int agef = document.getLong("age").intValue(); // Assuming "age" is stored as a number
                                    String genderf = document.getString("gender");
                                    name.setText(namef);
                                    age.setText(String.valueOf(agef));
                                    gender.setText(genderf);
                                    DataHolder.getInstance().setData(namef, genderf, String.valueOf(agef));


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
           search.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent search_doctor=new Intent(user_profile.this, SearchRealtime.class);
                   startActivity(search_doctor);
               }
           });

        }
}

