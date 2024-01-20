package com.example.a4thsemproject;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class customerInfo extends AppCompatActivity {
    EditText name;
    EditText dob;
    int age;
    String uid;
    Button submit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_info);
        name=findViewById(R.id.editTextName);
         dob=findViewById(R.id.editTextDateOfBirth);
         submit=findViewById(R.id.submitButton);
        uid = getIntent().getStringExtra("UID");


        name.getText(); //get text from the user
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer_information_fireS store_firestore=new customer_information_fireS(uid);
                store_firestore.send_to_firestore(name.getText().toString(),age);

            }
        });




    }
    public void showDatePickerDialog(){
        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(customerInfo.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                String selectedDate = selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear;
                dob.setText(selectedDate);
                calculateAge(selectedYear, selectedMonth, selectedDayOfMonth);
            }
        },year,month,dayOfMonth);
        datePickerDialog.show();

    }
    private void calculateAge(int year, int month, int day) {
        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.set(year, month, day);

        Calendar currentCalendar = Calendar.getInstance();

        age = currentCalendar.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);

        if (currentCalendar.get(Calendar.DAY_OF_YEAR) < dobCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

//        String ageTextString = getString(R.string.age_text, age);
//        ageText.setText(ageTextString);
        Log.i("Age", String.valueOf(age));

    }

}
