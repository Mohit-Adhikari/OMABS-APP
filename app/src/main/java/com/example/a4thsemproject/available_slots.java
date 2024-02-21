package com.example.a4thsemproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
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

import java.util.HashMap;

public class available_slots extends AppCompatActivity {
    private DatabaseReference databaseReference;
    boolean flag = true;
    String name;
    String specialization;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_doctors);
        name = getIntent().getStringExtra("name");
        specialization = getIntent().getStringExtra("specialization");
        databaseReference = FirebaseDatabase.getInstance().getReference("doctors").child(specialization).child(name).child("appointment_slots");
        //String slot[] = {"10", "10.30", "11", "11.30", "12", "12.30", "1", "1.30", "2", "2.30"};
       
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String time = dataSnapshot.getKey();
                        Log.i("availability time", time);
                        Object availability = dataSnapshot.getValue();
                        // Log.i("availability type", String.valueOf(availability));
                        //PlaceHolder 1
                        //Log.i("beauty",time);
                        //Log.i("availability type",dataSnapshot.child("10 00").getValue(boolean.class).toString());
                        //boolean availability=dataSnapshot.child(time).getValue(boolean.class);


                        addTextView(time, availability);
                    }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void addTextView(String time, Object availability) {
        RelativeLayout layout = findViewById(R.id.layout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        if (layout.getChildCount() > 0) {
            params.addRule(RelativeLayout.BELOW, layout.getChildAt(layout.getChildCount() - 1).getId());
        }

        if (availability instanceof String) {
            String availabilityString = (String) availability;

            // You can customize the appearance of the TextView based on availability
            TextView textView = new TextView(this);
            textView.setLayoutParams(params);

            textView.setText(time + "\n");
            textView.setBackgroundResource(R.drawable.professional_text_view_background);
            textView.setPadding(16, 16, 16, 16);
            textView.setTextColor(Color.BLACK); // Set text color
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            //textView.setBackgroundResource(R.drawable.text_view_background);// Set text size

            // Customize other TextView properties as needed

            int id = View.generateViewId();
            textView.setId(id);
            layout.addView(textView);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Your existing code for launching an activity
                    Intent intent = new Intent(available_slots.this, final_conformation.class);
                    intent.putExtra("time", time);
                    intent.putExtra("name", name);
                    intent.putExtra("specialization", specialization);
                    startActivity(intent);
                }
            });
        } else if (availability instanceof HashMap) {
            Log.i("Hashish", "Instance of hashmap");
            // Handle HashMap case if needed
        }
    }


}