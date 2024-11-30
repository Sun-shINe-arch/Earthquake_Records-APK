package com.example.android.quack_records;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    private String mUrl;
    public EarthquakeLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        ArrayList<Earthquake> earthquakes = Utils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }

}
