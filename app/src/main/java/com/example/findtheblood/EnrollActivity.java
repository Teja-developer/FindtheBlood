package com.example.findtheblood;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EnrollActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    RadioButton radioButton;
    String name,age,city,gender_str,hiv_str,chronic_str,re_month,blood,address,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("app", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                Button b1 = findViewById(R.id.date);
                b1.setText(date);
                re_month = String.valueOf(month);
            }
        };

        final Spinner bloodg = (Spinner) findViewById(R.id.bldgrp);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.bloodgrp, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodg.setAdapter(adapter);

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name_et = findViewById(R.id.editText);
                name = name_et.getText().toString();

                EditText age_et = findViewById(R.id.age);
                age = age_et.getText().toString();

                EditText city_et = findViewById(R.id.city);
                city = city_et.getText().toString();

                EditText address_et = findViewById(R.id.donor_address);
                address = address_et.getText().toString();

                EditText ph = findViewById(R.id.phone_no);
                phone = ph.getText().toString();

                RadioGroup gender = findViewById(R.id.gender);
                int selectedId = gender.getCheckedRadioButtonId();
                radioButton =  findViewById(selectedId);
                gender_str = radioButton.getText().toString();

                RadioGroup hiv = findViewById(R.id.hiv);
                selectedId = hiv.getCheckedRadioButtonId();
                radioButton =  findViewById(selectedId);
                hiv_str = radioButton.getText().toString();

                RadioGroup chronic = findViewById(R.id.chronic);
                selectedId = chronic.getCheckedRadioButtonId();
                radioButton =  findViewById(selectedId);
                chronic_str = radioButton.getText().toString();



                blood = bloodg.getSelectedItem().toString();

                addPost(name,age,city,gender_str,hiv_str,chronic_str,blood,address);

            }
        });

    }

    private void addPost(String name, String age, String city, String gender_str, String hiv_str, String chronic_str,String blood,String address) {

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseFirestore userposts = FirebaseFirestore.getInstance();

        final CollectionReference posts = db.collection("Donors");

        Date date = new Date();
        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        final String dateofday = (String) DateFormat.format("dd", date); // 20
        final String monthString = (String) DateFormat.format("MMM", date); // Jun
        String monthNumber = (String) DateFormat.format("MM", date); // 06
        String year = (String) DateFormat.format("yyyy", date); // 2013

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        final String currentDateTimeString = sdf.format(date);
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        Log.i("Test",name);

        final PostItems post = new PostItems(mAuth.getUid(), posts.getId(), currentDateTimeString, dateofday, monthString, name, age, city, gender_str, hiv_str, chronic_str,re_month,blood,address,phone);

        userposts.collection("Donors/"+blood+"/Users").add(post).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Working ",Toast.LENGTH_SHORT).show();
                documentReference.update("pid", documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Not Working ",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showDatePicker(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}
