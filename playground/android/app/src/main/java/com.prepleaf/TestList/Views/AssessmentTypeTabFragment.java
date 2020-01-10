package com.prepleaf.TestList.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.prepleaf.TestList.Adapters.AssessmentGroupsTabPagerAdapter;
import com.prepleaf.TestList.Models.Assessment;
import com.prepleaf.TestList.Models.AssessmentGroup;
import com.prepleaf.TestList.Models.TabData;

import java.util.ArrayList;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;
import com.reactnativenavigation.playground.R;

public class AssessmentTypeTabFragment extends Fragment implements Assessment.OnClickAssessment{
    public static final String ARG_TAB_DATA = "data";

    private TabData tabData;
    private AssessmentGroupsTabPagerAdapter mAdapter;
    private ViewPager viewPager;
    private Assessment.OnClickAssessment clickAssessmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,@Nullable Bundle savedInstanceState) {
        setTabData();
        View view = inflater.inflate(R.layout.assessment_group_list, container, false);
        setupTabs(view);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        ((TextView) view.findViewById(R.id.test_list_tab_content_test_text))
//                .setText(tabData.title);

    }

    private void setTabData(){
        Bundle args = getArguments();
        assert args != null;
        tabData = args.getParcelable(ARG_TAB_DATA);
    }

    private void setupTabs(View view){
        CustomTabLayout customTabLayout = view.findViewById(R.id.assessment_custom_tab_layout);
        ArrayList<AssessmentGroup> tabs= tabData.getAssessmentGroups();
        mAdapter = new AssessmentGroupsTabPagerAdapter(getChildFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabs);
        mAdapter.setOnAssessmentClickListener(this);
        viewPager = view.findViewById(R.id.assessment_tabs_view_pager);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(tabData.getAssessmentGroupsCount());

        customTabLayout.setupWithViewPager(viewPager);
    }

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