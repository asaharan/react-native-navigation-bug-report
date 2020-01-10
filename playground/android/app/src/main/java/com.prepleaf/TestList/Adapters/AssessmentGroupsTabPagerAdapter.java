package com.prepleaf.TestList.Adapters;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.prepleaf.TestList.Models.Assessment;
import com.prepleaf.TestList.Models.AssessmentGroup;
import com.prepleaf.TestList.Views.AssessmentGroupFragment;

import java.util.ArrayList;

import static com.prepleaf.TestList.Views.AssessmentGroupFragment.ARG_TAB_DATA;

import com.reactnativenavigation.playground.R;

public class AssessmentGroupsTabPagerAdapter extends FragmentStatePagerAdapter implements Assessment.OnClickAssessment {

    private ArrayList<AssessmentGroup> mTabs;

    public AssessmentGroupsTabPagerAdapter (@NonNull FragmentManager fm, int behavior, ArrayList<AssessmentGroup> tabs) {
        super(fm, behavior);
        mTabs=tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        AssessmentGroupFragment assessmentGroupFragment = new AssessmentGroupFragment();
        AssessmentGroup assessmentGroup = mTabs.get(position);
        Bundle args = new Bundle();
        args.putParcelable(ARG_TAB_DATA,assessmentGroup);
        assessmentGroupFragment.setArguments(args);
        assessmentGroupFragment.setOnAssessmentClickListener(this);
        return assessmentGroupFragment;
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).getName();
    }
    private Assessment.OnClickAssessment clickAssessmentListener;
    public void setOnAssessmentClickListener(Assessment.OnClickAssessment el){
        clickAssessmentListener = el;
    }

    @Override
    public void onClick(Assessment assessment) {
        if(clickAssessmentListener!=null){
            clickAssessmentListener.onClick(assessment);
        }
    }
}