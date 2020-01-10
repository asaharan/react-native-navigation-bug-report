package com.prepleaf.TestList.Adapters;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.prepleaf.TestList.Models.Assessment;
import com.prepleaf.TestList.Models.TabData;
import com.prepleaf.TestList.Views.AssessmentTypeTabFragment;

import java.util.ArrayList;

import static com.prepleaf.TestList.Views.AssessmentTypeTabFragment.ARG_TAB_DATA;

import com.reactnativenavigation.playground.R;

public class AssessmentTypeTabPagerAdapter extends FragmentStatePagerAdapter implements Assessment.OnClickAssessment {

    private ArrayList<TabData> mTabs;
    private Assessment.OnClickAssessment clickAssessmentListener;
    public void setOnAssessmentClickListener(Assessment.OnClickAssessment el){
        clickAssessmentListener = el;
    }

    public AssessmentTypeTabPagerAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<TabData> tabs) {
        super(fm, behavior);
        mTabs=tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        AssessmentTypeTabFragment fragment = new AssessmentTypeTabFragment();
        fragment.setOnAssessmentClickListener(this);
        TabData tabData = mTabs.get(position);
        Bundle args = new Bundle();
        args.putParcelable(ARG_TAB_DATA,tabData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).title;
    }

    public void setTabs(ArrayList<TabData> tabs){
        mTabs = tabs;
    }

    @Override
    public void onClick(Assessment assessment) {
        if(clickAssessmentListener!=null){
            clickAssessmentListener.onClick(assessment);
        }
    }
}