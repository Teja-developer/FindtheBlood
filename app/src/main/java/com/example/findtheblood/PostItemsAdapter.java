package com.example.findtheblood;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostItemsAdapter  extends  RecyclerView.Adapter<PostItemsAdapter.PostItemsViewHolder>{

    private Context context;
    private List<PostItems> items;
    private FirebaseFirestore db;

    PostItemsAdapter(Context context, List<PostItems> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public PostItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.i("app","View created");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,viewGroup,false);

        return new PostItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostItemsViewHolder postItemsViewHolder, int i) {
        final PostItems post = items.get(i);

        if(haveNetworkConnection()) {

            final FirebaseAuth mAuth = FirebaseAuth.getInstance();
            db = FirebaseFirestore.getInstance();

            Log.i("Tests",post.getCity()+" "+post.getName()+" "+post.getBlood());
            postItemsViewHolder.p_month.setText(post.getPost_month());
            postItemsViewHolder.p_date.setText(post.getPost_date());
            postItemsViewHolder.p_post.setText(post.getAddress());
            postItemsViewHolder.title.setText(post.getName());
            postItemsViewHolder.phone.setText(post.getPhone());
            postItemsViewHolder.blood.setText(post.getBlood());
        }

        else {
            Toast.makeText(context,"Network required",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class PostItemsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView p_date,p_month,p_post,title,phone,blood;

        PostItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            blood = mView.findViewById(R.id.bloodgroup);
            phone = mView.findViewById(R.id.ph);
            title = mView.findViewById(R.id.Title_problem);
            p_date = mView.findViewById(R.id.day);
            p_month = mView.findViewById(R.id.month);
            p_post = mView.findViewById(R.id.address);
        }

    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
