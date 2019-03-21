package com.example.findtheblood;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class SearchResult extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<PostItems> postItemsList;
    FirebaseFirestore db;
    private CollectionReference collectionReference ;
    FirebaseAuth mAuth;
    StorageReference reference;
    PostItemsAdapter adapter;
    String blood,city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

         blood = getIntent().getStringExtra("blood_grp");
         city = getIntent().getStringExtra("city");

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        collectionReference = db.collection("Donors/"+blood+"/Users");

        reference = FirebaseStorage.getInstance().getReference();

        postItemsList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview2);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

        recyclerView.setAdapter(adapter);
//        setUpRecyclerView();

        displayPosts();
    }

    void displayPosts(){
        db.collection("Donors/"+blood+"/Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {

                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {

                        PostItems p = d.toObject(PostItems.class);

                        assert p != null;
                        if(p.getCity().equals(city)){
                            postItemsList.add(p);
                        }
                    }
                    adapter.notifyDataSetChanged();

                    adapter = new PostItemsAdapter(getApplicationContext(), postItemsList);

                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
    private void setUpRecyclerView() {
        Query query = collectionReference.orderBy("age", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<PostItems> options = new FirestoreRecyclerOptions.Builder<PostItems>()
                .setQuery(query, PostItems.class)
                .build();

//        adapter = new Adapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}
