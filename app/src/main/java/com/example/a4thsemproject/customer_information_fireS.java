package com.example.a4thsemproject;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class customer_information_fireS {
    private FirebaseFirestore db;
    private DocumentReference userRef;
    private String uid;





    public customer_information_fireS(String uid) {
        this.uid=uid;
        this.db = FirebaseFirestore.getInstance();
        this.userRef = db.collection("users").document(uid);
    }
    public void send_to_firestore(String name,int age,String choice){

        Map<String, Object> userData = new HashMap<>();
        //userData.put("userType","patient");
        userData.put("name",  name);
        userData.put("age",  age);
        userData.put("gender",choice);
        userRef.set(userData)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "User document successfully written!"))
                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
    }




}
