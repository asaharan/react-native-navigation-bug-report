package com.prepleaf.TestList.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prepleaf.TestList.Models.Assessment;

import java.util.ArrayList;
import com.reactnativenavigation.playground.R;

public class AssessmentListAdapter extends RecyclerView.Adapter<AssessmentListAdapter.ViewHolder> {

    private static final String TAG = "AssessmentGroupList";
    private ArrayList<Assessment> assessments;
    private Assessment.OnClickAssessment clickAssessmentListener;

    public AssessmentListAdapter(ArrayList<Assessment> as){
        assessments = as;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assessment_list_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assessment assessment = assessments.get(position);
        holder.setOnClickListener(v1->{
            clickAssessmentListener.onClick(assessment);
        });
        holder.getNameView().setText(assessment.getName());
        holder.getDurationView().setText(assessment.getDurationAsString());
    }

    @Override
    public int getItemCount() {
        return assessments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView durationView;
        private View.OnClickListener onClickListener;

        ViewHolder(View v) {
            super(v);
            // TODO: Define click listener for the ViewHolder's View.
            v.setOnClickListener(v1 -> {
                Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                onClickListener.onClick(v1);
            });
            nameView = v.findViewById(R.id.name);
            durationView = v.findViewById(R.id.duration);

        }

        TextView getNameView() {
            return nameView;
        }
        TextView getDurationView(){return durationView;}
        void setOnClickListener(View.OnClickListener listener){
            onClickListener= listener;
        }
    }

    public void setOnAssessmentClickListener(Assessment.OnClickAssessment el){
        clickAssessmentListener = el;
    }
}
