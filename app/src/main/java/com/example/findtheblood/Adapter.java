package com.example.findtheblood;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Adapter extends FirestoreRecyclerAdapter<PostItems,Adapter.NoteHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter(@NonNull FirestoreRecyclerOptions<PostItems> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull PostItems model) {
        holder.textMonth.setText(model.getPost_month());
        holder.address.setText(model.getAddress());
        holder.blood.setText(model.getBlood());
        holder.ph.setText(model.getPhone());
        holder.textViewTitle.setText(model.getName());
        holder.textDay.setText(model.getPost_date());
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,
                viewGroup, false);
        return new NoteHolder(v);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle,textDay,textMonth,address,ph,blood;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.Title_problem);
            textDay = itemView.findViewById(R.id.day);
            textMonth = itemView.findViewById(R.id.month);
            address = itemView.findViewById(R.id.address);
            ph = itemView.findViewById(R.id.ph);
            blood = itemView.findViewById(R.id.bloodgroup);
        }
    }
}
