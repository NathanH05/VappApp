package com.google.samples.quickstart.signin;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.wikitude.architect.ArchitectView;

/**
 * Created by nathanhampshire on 30/11/16.
 */


public class MyService extends IntentService {
    private static final String TAG = "BOOMBOOMTESTGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 40000;
    private static final float LOCATION_DISTANCE = 0;
    private ArchitectView architectView;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    public final class Constants {

        // Defines a custom Intent action
        public static final String BROADCAST_ACTION =
                "com.example.android.threadsample.BROADCAST";

        // Defines the key for the status "extra" in an Intent
        public static final String EXTENDED_DATA_STATUS =
                "com.example.android.threadsample.STATUS";

    }

    @Override
    public void onHandleIntent(Intent workIntent) {

        int g = 5+2;
        Intent localIntent = new Intent("hi")
            .putExtra("message", "hi");
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

//        String dataString = workIntent.getDataString();
//        class LocationListener implements android.location.LocationListener {
//            Location mLastLocation;
//
//            public LocationListener(String provider) {
//                Log.e(TAG, "LocationListener " + provider);
//                mLastLocation = new Location(provider);
//            }
//
//            @Override
//            public void onLocationChanged(Location location) {
//                Log.e(TAG, "onLocationChanged: " + location);
//                architectView.setLocation(location.getLatitude(), location.getLongitude(), 100);
//                System.out.println(String.valueOf(location.getLatitude()));
//                mLastLocation.set(location);
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//                Log.e(TAG, "onProviderDisabled: " + provider);
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//                Log.e(TAG, "onProviderEnabled: " + provider);
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//                Log.e(TAG, "onStatusChanged: " + provider);
//            }
//        }
//
//        LocationListener[] mLocationListeners = new LocationListener[]{
//                new LocationListener(LocationManager.GPS_PROVIDER),
//                new LocationListener(LocationManager.NETWORK_PROVIDER)
//
//        };
//
//        initializeLocationManager();
//        try {
//            mLocationManager.requestLocationUpdates(
//                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
//                    mLocationListeners[1]);
//        } catch (java.lang.SecurityException ex) {
//            Log.i(TAG, "fail to request location update, ignore", ex);
//        } catch (IllegalArgumentException ex) {
//            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
//        }
//        try {
//            mLocationManager.requestLocationUpdates(
//                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
//                    mLocationListeners[0]);
//        } catch (java.lang.SecurityException ex) {
//            Log.i(TAG, "fail to request location update, ignore", ex);
//        } catch (IllegalArgumentException ex) {
//            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
//        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        LocationListener[] mLocationListeners = new LocationListener[0];

        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
        System.out.println("SERVICE STARTTTEED");


    }
}