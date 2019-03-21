package com.example.findtheblood;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AllDonorsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PostItems> postItemsList;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    StorageReference reference;
    private CollectionReference collectionReference ;
    TextView ph;
    PostItemsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donors);

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        collectionReference = db.collection("Donors");

        reference = FirebaseStorage.getInstance().getReference();

        postItemsList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview1);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

        recyclerView.setAdapter(adapter);

        displayAllPosts();

    }

    private void displayAllPosts() {
        Log.i("Tests","Reached");
        db.collection("Donors/O+/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        PostItems p = document.toObject(PostItems.class);

                        postItemsList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", postItemsList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/O-/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        PostItems p = document.toObject(PostItems.class);

                        postItemsList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", postItemsList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });

        db.collection("Donors/A+/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        PostItems p = document.toObject(PostItems.class);

                        postItemsList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", postItemsList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/A-/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        PostItems p = document.toObject(PostItems.class);

                        postItemsList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", postItemsList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/AB+/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        PostItems p = document.toObject(PostItems.class);

                        postItemsList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", postItemsList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/AB-/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        PostItems p = document.toObject(PostItems.class);

                        postItemsList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", postItemsList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/B+/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        PostItems p = document.toObject(PostItems.class);

                        postItemsList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", postItemsList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("Donors/B-/Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        PostItems p = document.toObject(PostItems.class);

                        postItemsList.add(p);
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

                    recyclerView.setAdapter(adapter);
                    Log.d("Tests", postItemsList.toString());
                } else {
                    Log.d("Tests", "Error getting documents: ", task.getException());
                }
            }
        });
    }
}
