package com.prepleaf.TestList.Views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prepleaf.TestList.Adapters.AssessmentListAdapter;
import com.prepleaf.TestList.Models.Assessment;
import com.prepleaf.TestList.Models.AssessmentGroup;
import com.reactnativenavigation.playground.R;

import com.reactnativenavigation.playground.R;

public class AssessmentGroupFragment extends Fragment implements Assessment.OnClickAssessment {
    public static final String ARG_TAB_DATA = "data";
    private static final String TAG = "AssessmentGroupFragment";

    private AssessmentGroup assessmentGroup;
    private Assessment.OnClickAssessment clickAssessmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,@Nullable Bundle savedInstanceState) {
        setTabData();
        View rootView = inflater.inflate(R.layout.assessment_group, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.assessment_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        AssessmentListAdapter adapter = new AssessmentListAdapter(assessmentGroup.getAssessments());
        adapter.setOnAssessmentClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }

    private void setTabData(){
        Bundle args = getArguments();
        assert args != null;
        assessmentGroup = args.getParcelable(ARG_TAB_DATA);
    }

    @Override
    public void onClick(Assessment assessment) {

        if(clickAssessmentListener!=null){
            clickAssessmentListener.onClick(assessment);
        }
    }

    public void setOnAssessmentClickListener(Assessment.OnClickAssessment el){
        clickAssessmentListener = el;
    }
}