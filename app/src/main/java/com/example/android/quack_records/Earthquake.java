package com.example.android.quack_records;

public class Earthquake {
    private Double mMagnitude;
    private String mLocation;
    private long mTime;
    private String mURL;

    public Earthquake(Double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTime = timeInMilliseconds;
        mURL = url;
    }

    public Double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTime;
    }

    public String getURL() {
        return mURL;
    }
}