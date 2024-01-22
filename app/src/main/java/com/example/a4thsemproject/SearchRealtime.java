package com.example.a4thsemproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchRealtime extends AppCompatActivity {


    private DatabaseReference databaseReference;
    String status;
    ArrayList<String> myArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_realtime);
        EditText search=findViewById(R.id.editTextSearch);
        Button submit=findViewById(R.id.buttonSearch);
        TextView availability=findViewById(R.id.textViewNotAvailable);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("doctors").child("eye");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and whenever data at this location is updated.
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            // Access data using snapshot.getValue()
                            String name = snapshot.getKey(); //getValue(String.class);
                            // Do something with the data
                            Log.d("FirebaseData", name);

                            myArrayList.add(name);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle errors
                        Log.e("FirebaseError", "Error fetching data", databaseError.toException());
                    }
                });
                status=search.getText().toString();
                for(String doctors: myArrayList)
                {
                    //Log.i("search", search.getText().toString());
                    if (status.equals(doctors)==true)
                    {   Log.i("Doctors",doctors);
                        availability.setText(doctors + " is available.");
                        break;
                    }
                    else {
                        availability.setText("Oops Sorry. Not Available at the moment");
                    }
                }

            }
        });


    }


}
