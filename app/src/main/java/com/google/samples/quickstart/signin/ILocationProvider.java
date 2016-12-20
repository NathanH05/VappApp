package com.google.samples.quickstart.signin;

import android.location.LocationListener;

/**
 * Created by nathanhampshire on 23/10/16.
 */
public interface ILocationProvider {

      /**
         * Call when host-activity is resumed (usually within systems life-cycle method)
         */
        public void onResume();

        /**
         * Call when host-activity is paused (usually within systems life-cycle method)
         */
        public void onPause();



    public ILocationProvider getLocationProvider(final LocationListener locationListener);

}
