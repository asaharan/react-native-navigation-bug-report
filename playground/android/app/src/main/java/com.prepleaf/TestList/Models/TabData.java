package com.prepleaf.TestList.Models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import com.facebook.react.bridge.ReadableArray;

public class TabData implements Parcelable {
    public String title;
    ArrayList<AssessmentGroup> mAssessmentGroups;

    public TabData(String argTitle, ReadableArray rawAssessments){
        title = argTitle;
        setAssessments(rawAssessments);
    }

    protected TabData(Parcel in) {
        title = in.readString();
        mAssessmentGroups = in.createTypedArrayList(AssessmentGroup.CREATOR);
    }

    public static final Creator<TabData> CREATOR = new Creator<TabData>() {
        @Override
        public TabData createFromParcel(Parcel in) {
            return new TabData(in);
        }

        @Override
        public TabData[] newArray(int size) {
            return new TabData[size];
        }
    };

    void setAssessments(ReadableArray rawAssessments){
        ArrayList<Assessment> assessments = new ArrayList<>();
        for(int index = 0; index < rawAssessments.size(); index++){
            assessments.add(new Assessment(rawAssessments.getMap(index)));
        }
        mAssessmentGroups = AssessmentGroup.createFromAssessments(assessments);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeTypedList(mAssessmentGroups);
    }
    public ArrayList<AssessmentGroup> getAssessmentGroups() {
        return mAssessmentGroups;
    }
    public int getAssessmentGroupsCount(){
        return mAssessmentGroups.size();
    }
}
