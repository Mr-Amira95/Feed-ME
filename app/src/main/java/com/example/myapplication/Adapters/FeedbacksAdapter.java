package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.HashMap;
import java.util.List;

public class FeedbacksAdapter extends RecyclerView.Adapter<FeedbacksAdapter.ItemHolder>{

    private Context context;
    private List<Object> feedbackResults;

    public FeedbacksAdapter(Context context, List<Object> feedbackResults){
        this.context = context;
        this.feedbackResults = feedbackResults;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_feedback, viewGroup, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {

        itemHolder.feedback_txt.setText(((HashMap) feedbackResults.get(i)).get("Feedback").toString());

    }

    @Override
    public int getItemCount() {
        return feedbackResults.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView feedback_txt;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            feedback_txt=itemView.findViewById(R.id.feedback_txt);

        }
    }
}
