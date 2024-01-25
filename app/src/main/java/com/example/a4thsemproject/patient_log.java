package com.example.a4thsemproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class patient_log extends AppCompatActivity {
    DatabaseReference databaseReference;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_doctors);
        String specialization=getIntent().getStringExtra("specialization");
        String name=getIntent().getStringExtra("name");
        databaseReference= FirebaseDatabase.getInstance().getReference("doctors").child(specialization).child(name).child("appointment_slots");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    String time=dataSnapshot.getKey();

                    String namep=dataSnapshot.child("name").getValue(String.class);
                    String genderp=dataSnapshot.child("gender").getValue(String.class);
                    String agep=dataSnapshot.child("age").getValue(String.class);
                    addTextView(namep,genderp,agep);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void addTextView(String name,String gender,String age)
    {    Log.i("Sexy Baby",name+"displayed");
        RelativeLayout layout = findViewById(R.id.layout);

        // Create a new RelativeLayout for each TextView
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Set below the previously created TextView
        if (layout.getChildCount() > 0) {
            params.addRule(RelativeLayout.BELOW, layout.getChildAt(layout.getChildCount() - 1).getId());
        }

        TextView textView = new TextView(this);
        textView.setLayoutParams(params);
        textView.setText(name + "\n" + gender + "\n" +  "\n" + age);
        textView.setBackgroundResource(R.drawable.text_view_background);
        textView.setPadding(16, 16, 16, 16);

        // Generate a unique ID for each TextView
        int id = View.generateViewId();
        textView.setId(id);


        layout.addView(textView);

    }
}
