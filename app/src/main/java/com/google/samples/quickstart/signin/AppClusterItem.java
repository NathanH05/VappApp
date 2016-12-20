package com.google.samples.quickstart.signin;


import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Dimitar Danailov on 6/3/15.
 * email: dimityr.danailov@gmail.com
 *
 * Documentation: https://developers.google.com/maps/documentation/android/utility/marker-clustering
 */
public class AppClusterItem implements ClusterItem {

    private final LatLng mPosition;
    String Opty;
    public final String mTitle;
    public final String mSnippet;

    public AppClusterItem(double latitude, double longitude,String mTitle, String mSnippet) {
        this.mTitle = mTitle;
        this.mSnippet = mSnippet;
        mPosition = new LatLng(latitude, longitude);
        this.Opty = mSnippet;
        System.out.println(mSnippet);


    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }



}