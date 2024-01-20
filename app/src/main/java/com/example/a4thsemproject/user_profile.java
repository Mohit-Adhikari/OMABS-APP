package com.example.a4thsemproject;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class user_profile extends AppCompatActivity {
    String uid;
    TextView name;
    TextView age;
    TextView gender;



        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_profile);
            name=findViewById(R.id.nameTextView);
            age=findViewById(R.id.ageTextView);
            gender=findViewById(R.id.genderTextView);
            uid = getIntent().getStringExtra("uid");

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


        }
}

