package com.example.a4thsemproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class available_doctors extends AppCompatActivity {
    private DatabaseReference databaseReference;
    String specialization;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_doctors);
        specialization=getIntent().getStringExtra("specialization");
        databaseReference= FirebaseDatabase.getInstance().getReference("doctors").child(specialization);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    String name=dataSnapshot.getKey();

                    String gender=dataSnapshot.child("gender").getValue(String.class);
                    String hospital=dataSnapshot.child("hospital").getValue(String.class);
                    String age=dataSnapshot.child("age").getValue(String.class);
                    addTextView(name,gender,hospital,age);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(available_doctors.this,"Couldn't connect to database",Toast.LENGTH_SHORT).show();


            }
        });



    }
    private void addTextView(String name,String gender,String hospital,String age)
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
        textView.setText(name + "\n" + gender + "\n" + hospital + "\n" + age);
        textView.setBackgroundResource(R.drawable.text_view_background);
        textView.setPadding(16, 16, 16, 16);

        // Generate a unique ID for each TextView
        int id = View.generateViewId();
        textView.setId(id);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(available_doctors.this,available_slots.class);
                intent.putExtra("name",name);
                intent.putExtra("specialization",specialization);
                startActivity(intent);

            }
        });

        layout.addView(textView);

    }
}
