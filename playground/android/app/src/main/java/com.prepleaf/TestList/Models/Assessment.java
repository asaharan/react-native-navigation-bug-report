package com.prepleaf.TestList.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.facebook.react.bridge.ReadableMap;

public class Assessment implements Parcelable {
    public String name;
    public String id;
    public String label;
    public Boolean isGraded;
    public int reward;
    public int duration; // in seconds
    public Boolean isSubmitted;
    public Assessment(ReadableMap rawAssessment){
        name = rawAssessment.getString("name");
        id = rawAssessment.getString("_id");
        label = rawAssessment.getString("label");
        isGraded = rawAssessment.getBoolean("graded");
        reward = rawAssessment.getInt("reward");
        isSubmitted = !rawAssessment.isNull("submission");
        duration = rawAssessment.getInt("duration");
    }

    protected Assessment(Parcel in) {
        name = in.readString();
        id = in.readString();
        label = in.readString();
        byte tmpIsGraded = in.readByte();
        isGraded = tmpIsGraded == 0 ? null : tmpIsGraded == 1;
        reward = in.readInt();
        duration = in.readInt();
        byte tmpIsSubmitted = in.readByte();
        isSubmitted = tmpIsSubmitted == 0 ? null : tmpIsSubmitted == 1;
    }

    public static final Creator<Assessment> CREATOR = new Creator<Assessment>() {
        @Override
        public Assessment createFromParcel(Parcel in) {
            return new Assessment(in);
        }

        @Override
        public Assessment[] newArray(int size) {
            return new Assessment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeString(label);
        parcel.writeByte((byte) (isGraded == null ? 0 : isGraded ? 1 : 2));
        parcel.writeInt(reward);
        parcel.writeInt(duration);
        parcel.writeByte((byte) (isSubmitted == null ? 0 : isSubmitted ? 1 : 2));
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }
    public String getDurationAsString(){
        return DateUtils.formatElapsedTime(duration);
    }
    public interface OnClickAssessment {
        void onClick(Assessment assessment);
    }
}

final class DateUtils {
    static String formatElapsedTime(int elapsedSeconds){
        String s = "";
        int elapsedMinutes = elapsedSeconds/60;
        int elapsedHours = elapsedMinutes/60;
        int minutesToShow = elapsedMinutes%60;
        if(elapsedHours>0){
            s+= elapsedSeconds + " hour";
            if(elapsedHours>1){
                s+="s ";
            }else{
                s+=" ";
            }
        }
        if(minutesToShow>0){
            s+= minutesToShow+" minute";
            if(minutesToShow>1){
                s+="s ";
            }else{
                s+=" ";
            }
        }
        return s;
    }
}