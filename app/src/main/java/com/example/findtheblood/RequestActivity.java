package com.example.findtheblood;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RequestActivity extends AppCompatActivity {
    Spinner bloodg,state;
    EditText name,contactnm,add,city;
    String blood,states,rname,rcontact,radd,city_s;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        bloodg = (Spinner) findViewById(R.id.bldgrp);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bloodgrp, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodg.setAdapter(adapter);

        state = (Spinner) findViewById(R.id.stat);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.state, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(adapter2);

        name =findViewById(R.id.fname);
        contactnm = findViewById(R.id.ctnum);
        add = findViewById(R.id.hosnadd);
        submit = findViewById(R.id.submit);
        city = findViewById(R.id.city_re);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rname = name.getText().toString();
                rcontact = contactnm.getText().toString();
                radd = add.getText().toString();
                blood = bloodg.getSelectedItem().toString();
                states = state.getSelectedItem().toString();
                city_s = city.getText().toString();
//                Log.i("Tests",city_s);
                addpost(rname,rcontact,radd,blood,states,city_s);
            }
        });
    }

    public void addpost(String rname, String rcontact, String radd, final String blood, String states, final String city_s){
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final FirebaseFirestore requesterposts = FirebaseFirestore.getInstance();
        final CollectionReference requesters = db.collection("Requesters");
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        final RequestPost post = new RequestPost(mAuth.getUid(), requesters.getId(), rname, rcontact, radd, blood, states,city_s);
        requesterposts.collection("Requesters/"+mAuth.getUid()+"/posts").add(post).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Working ",Toast.LENGTH_SHORT).show();
                documentReference.update("pid", post.getPid());

                Intent intent = new Intent(getApplicationContext(),SearchResult.class);
                intent.putExtra("blood_grp",blood);
                intent.putExtra("city",city_s);

                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Not Working ",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
