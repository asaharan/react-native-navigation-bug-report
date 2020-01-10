package com.prepleaf.TestList.Models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;

public class AssessmentGroup implements Parcelable {
    private String name;
    private ArrayList<Assessment> assessments;
    AssessmentGroup(String n, ArrayList<Assessment> as){
        name = n;
        assessments = as;
    }

    private AssessmentGroup(Parcel in) {
        name = in.readString();
        assessments = in.createTypedArrayList(Assessment.CREATOR);
    }

    public static final Creator<AssessmentGroup> CREATOR = new Creator<AssessmentGroup>() {
        @Override
        public AssessmentGroup createFromParcel(Parcel in) {
            return new AssessmentGroup(in);
        }

        @Override
        public AssessmentGroup[] newArray(int size) {
            return new AssessmentGroup[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedList(assessments);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<AssessmentGroup> createFromAssessments(ArrayList<Assessment> assessments){
        ArrayList<AssessmentGroup> assessmentGroups = new ArrayList<>();
        HashMap<String, AssessmentGroup> assessmentGroupMap = new HashMap<>();
        for(int index = 0; index< assessments.size(); index++){
            Assessment assessment = assessments.get(index);
            if(!assessmentGroupMap.containsKey(assessment.getLabel())){
                assessmentGroupMap.put(assessment.getLabel(),new AssessmentGroup(assessment.getLabel(), new ArrayList<>()));
            }
            AssessmentGroup assessmentGroup = assessmentGroupMap.get(assessment.getLabel());
            assert assessmentGroup != null;
            assessmentGroup.addAssessment(assessment);
        }
        assessmentGroupMap.forEach((String label, AssessmentGroup ag)->{
            assessmentGroups.add(ag);
        });
        return assessmentGroups;
    }

    private void addAssessment(Assessment assessment){
        assessments.add(assessment);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }
}
