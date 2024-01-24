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
    private String type;





    public customer_information_fireS(String uid,String type) {
        this.uid=uid;
        this.type=type;
        this.db = FirebaseFirestore.getInstance();
        this.userRef = db.collection(type).document(uid);
        Log.i("Mohit","specialization");
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
    public void send_to_firestore(String name, int age,String choice,String specialization, String hospital){
        Map<String,Object> doctorData=new HashMap<>();
        doctorData.put("name",name);
        doctorData.put("age",age);
        doctorData.put("gender",choice);
        doctorData.put("specialization",specialization);
        doctorData.put("hospital",hospital);
        userRef.set(doctorData)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "User document successfully written!"))
                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
    }




}
