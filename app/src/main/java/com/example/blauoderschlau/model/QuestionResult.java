package com.example.blauoderschlau.model;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionResult implements Parcelable {
    public boolean correctAnswerSelected;
    public long msToAnswerSelected;

    public QuestionResult(){}

    protected QuestionResult(Parcel in) {
        correctAnswerSelected = in.readByte() != 0;
        msToAnswerSelected = in.readLong();
    }

    public static final Creator<QuestionResult> CREATOR = new Creator<QuestionResult>() {
        @Override
        public QuestionResult createFromParcel(Parcel in) {
            return new QuestionResult(in);
        }

        @Override
        public QuestionResult[] newArray(int size) {
            return new QuestionResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (correctAnswerSelected ? 1 : 0));
        parcel.writeLong(msToAnswerSelected);
    }
}
