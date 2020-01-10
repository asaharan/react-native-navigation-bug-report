package com.prepleaf.TestList.Views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.material.tabs.TabLayout;
import com.prepleaf.TestList.Adapters.AssessmentTypeTabPagerAdapter;
import com.prepleaf.TestList.Models.Assessment;
import com.prepleaf.TestList.Models.TabData;

import java.util.ArrayList;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import com.reactnativenavigation.playground.R;

public class RootView extends ViewPager implements Assessment.OnClickAssessment {

    public static final String TAG = "RootView";
    Context mContext;
    AssessmentTypeTabPagerAdapter mAssessmentTypeTabPagerAdapter;
    TabLayout tabLayout;

    public RootView(@NonNull Context context) {
        super(context);
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.test_list_root_layout,this);
        initialize();
    }

    private void initialize(){
        tabLayout = findViewById(R.id.tabs);
        FragmentActivity activity = (FragmentActivity) ((ReactContext)getContext()).getCurrentActivity();
        ArrayList<TabData> tabs= new ArrayList<>();
        mAssessmentTypeTabPagerAdapter = new AssessmentTypeTabPagerAdapter(
                activity.getSupportFragmentManager(),
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                tabs
        );
        setAdapter(
                mAssessmentTypeTabPagerAdapter
        );
        tabLayout.setupWithViewPager(this);
        mAssessmentTypeTabPagerAdapter.setOnAssessmentClickListener(this);
        setOffscreenPageLimit(tabs.size());
    }

    public void setTabs(@Nullable ReadableArray rawTabs){
        ArrayList<TabData> tabs = new ArrayList<>();
        int numberOfTabs = rawTabs.size();
        for(int index = 0; index < numberOfTabs; index++){
            ReadableMap tabItem = rawTabs.getMap(index);
            assert tabItem != null;
            String title = tabItem.getString("title");
            TabData tabData = new TabData(title, tabItem.getArray("assessments"));
            tabs.add(tabData);
        }
        if(numberOfTabs>3){
//            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        setOffscreenPageLimit(tabs.size());
        mAssessmentTypeTabPagerAdapter.setTabs(tabs);
        mAssessmentTypeTabPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(Assessment assessment) {
        Log.d(TAG,"Assessment: " + assessment.getName());
        WritableMap event = Arguments.createMap();
        event.putString("_id",assessment.getId());
        dispatchEvent("clickAssessment",event);
    }

    private void dispatchEvent(String eventName, WritableMap event){
        ReactContext reactContext = (ReactContext)getContext();
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(),eventName, event);
    }
}