package com.prepleaf.TestList.Views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Objects;

import com.reactnativenavigation.playground.R;

public class CustomTabLayout extends HorizontalScrollView implements Chip.OnClickListener, ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    ChipGroup chipGroup;
    ArrayList<TabItem> chips;
    int currentPosition;

    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onClick(View view) {
        selectTab(((TabItem)view).getPosition());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(chips.size()<2){
            return;
        }
        TabItem firstChip = chips.get(position);
        int centerOfFirstChip = firstChip.getLeft() + firstChip.getWidth()/2;
        int idealScrollPositionForFirstActive = centerOfFirstChip - getWidth()/2;
        if(positionOffset==0.f){
            smoothScrollTo(idealScrollPositionForFirstActive,0);
        }else {
            TabItem secondChip = chips.get(position+1);
            int centerOfSecondChip = secondChip.getLeft() + secondChip.getWidth() / 2;
            int distanceBetweenCenters = centerOfSecondChip - centerOfFirstChip;
            int desiredScrollPosition = idealScrollPositionForFirstActive + (int) (positionOffset * distanceBetweenCenters);
            smoothScrollTo(desiredScrollPosition,0);
        }
    }

    @Override
    public void onPageSelected(int position) {
        selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setupWithViewPager(ViewPager vp){
        viewPager = vp;
        vp.addOnPageChangeListener(this);
        drawTabs();
    }

    private void drawTabs(){
        int numberOfTabs = Objects.requireNonNull(viewPager.getAdapter()).getCount();
        chips = new ArrayList<>();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        if(numberOfTabs<2){
            layoutParams.height = 0;
            setLayoutParams(layoutParams);
            setVisibility(INVISIBLE);
        }else{
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            setVisibility(VISIBLE);
            setHorizontalScrollBarEnabled(false);
            chipGroup = findViewById(R.id.linear_layout);
            for(int index = 0; index < numberOfTabs; index++){
                boolean isSelected = viewPager.getCurrentItem()==index;
                TabItem tabItem = new TabItem(getContext());
                tabItem.setPosition(index);
                tabItem.setActive(isSelected);
                tabItem.setText(viewPager.getAdapter().getPageTitle(index));
                tabItem.setOnClickListener(this);
                tabItem.setSelected(isSelected);
                chips.add(tabItem);
                chipGroup.addView(tabItem);
            }
        }
    }

    public void selectTab(int position){
        TabItem currentChip = chips.get(currentPosition);
        TabItem nextActiveChip = chips.get(position);
        currentChip.setActive(false);
        nextActiveChip.setActive(true);
        viewPager.setCurrentItem(position);
        currentPosition = position;
    }
}

class TabItem extends Chip{

    private int position;

    public TabItem(Context context) {
        super(context);
        setPadding(8,4,4,8);
    }

    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setActive(boolean isActive) {
        if(isActive){
            this.setChipBackgroundColorResource(R.color.primaryLightBackground);
            this.setTextColor(getResources().getColor(R.color.colorPrimary));
            setChipStrokeWidth(0);
        }else{
            setTextColor(getResources().getColor(R.color.textColor));
            setChipBackgroundColorResource(R.color.transparent);
            setChipStrokeColorResource(R.color.customTabLayoutChipStroke);
            setChipStrokeWidth(2);
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}