package com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.fragment;

import android.os.Parcel;
import android.os.Parcelable;

import com.applandeo.materialcalendarview.EventDay;

import java.util.Calendar;

/**
 * Created by Jyotirmay on 19-Jan-18.
 */

public class MyEventDay extends EventDay implements Parcelable {

    private String mNote;

    public MyEventDay(Calendar day, int imageResource) {
        super(day, imageResource);
    }

    public MyEventDay(Calendar day) {
        super(day);
    }

    public String getNote() {
        return mNote;
    }
    private MyEventDay(Parcel in) {
        super((Calendar) in.readSerializable(), in.readInt());
        mNote = in.readString();
    }
    public static final Creator<MyEventDay> CREATOR = new Creator<MyEventDay>() {
        @Override
        public MyEventDay createFromParcel(Parcel in) {
            return new MyEventDay(in);
        }
        @Override
        public MyEventDay[] newArray(int size) {
            return new MyEventDay[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(getCalendar());
        parcel.writeInt(getImageResource());
        parcel.writeString(mNote);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
