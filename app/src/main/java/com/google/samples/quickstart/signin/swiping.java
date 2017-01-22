//package com.google.samples.quickstart.signin;
//
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.AsyncTask;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.myfirstapp.R;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.wikitude.architect.ArchitectView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.myfirstapp.R.id.map;
//
//public class swiping extends AppCompatActivity {
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//    GoogleMap mMap;
//    ViewPagerAdapter adapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_swiping);
//
//
//
//    }
//    private class MyTask extends AsyncTask<String, String, String> {    // Declare an instance of the Location Manager and Listener for retrieving the user's current
//        // latitude and longitude
//        private LocationManager locationManager;
//        private LocationListener locationListener;
//        private ArchitectView architectView;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            viewPager = (ViewPager) findViewById(R.id.viewpager);
//
//            tabLayout = (TabLayout) findViewById(R.id.tabs);
//            tabLayout.setupWithViewPager(viewPager);
//
////            updateDisplay("starting task");
//
//            // Create the instance of the location listener for obtaining the users current location
////            updateDisplay("on pre done");
//
//
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
////            updateDisplay("Do in background started");
//       adapter = new ViewPagerAdapter(getSupportFragmentManager());
//            adapter.addFragment(new OneFragment(), "ONE");
//            adapter.addFragment(new TwoFragment(), "TWO");
//            adapter.addFragment(new ArActivity(), "THREE");
//            // Call the location service of the LocationManager
////            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
//            // Create the instance of the location listener for obtaining the users current location
////            locationListener = new LocationListener() {
////                @Override
////                public void onLocationChanged(Location location) {
////
////                    locationManager.requestLocationUpdates("gps", 40000, 0, locationListener);
////
////                    // When the location does change, grab the lon, lat from the location passed into it by the device + LocationManager
////                    //Set the architectView location with the users current lat,lon and altitude.
////                    // Note: this fires the onLocationChanged function of the multiplepois.js
////                    architectView.setLocation(location.getLatitude(), location.getLongitude(), 100);
////                    System.out.println(String.valueOf(location.getLatitude()));
////
////
////                }
////
////                public void onStatusChanged(String s, int i, Bundle bundle) {
////
////                }
////
////                public void onProviderEnabled(String s) {
////
////                }
////
////                public void onProviderDisabled(String s) {
////                    // If a user device has their GPS or network disabled. Start the
////                    // android setting screen intent for the user to enable it
////                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
////                    startActivity(intent);
////
////                }
////
////            };
//
//            // If the permissions are set already, than configure the button to get location updates
//            // when regularly
//
//
////
////            // Call the location service of the LocationManager
////            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
//
//
////            locationListener = new LocationListener() {
////                @Override
////                public void onLocationChanged(Location location) {
////                    locationManager.requestLocationUpdates("gps", 40000, 0, locationListener);
////                    int dig = 10;
////                    counter ++;
////
////                    // When the location does change, grab the lon, lat from the location passed into it by the device + LocationManager
////                    //Set the architectView location with the users current lat,lon and altitude.
////                    // Note: this fires the onLocationChanged function of the multiplepois.js
////                    architectView.setLocation(location.getLatitude(), location.getLongitude(), 100);
////                    if (counter % 10 == 0 || counter == 1){
////                        Geocoder gc = new Geocoder(getActivity(), Locale.getDefault());
////                        List<Address> addresses = null;
////                        try {
////                            addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
////
////                        if (addresses.size() == 1) {
////                             name = addresses.get(0).getThoroughfare();
////                            cameFromButton = false;
////                            System.out.println("PRINTING ADDRESS");
////                            String streetName = "filterByStreet(" +'"' + name +'"' + ","+  cameFromButton +")";
////                            System.out.println(streetName);
////                            architectView.callJavascript(streetName);
////
////                        } else {
////                        }
////                    }
////
////
////
////
////                }
////
////
////                public void onStatusChanged(String s, int i, Bundle bundle) {
////
////                }
////
////                public void onProviderEnabled(String s) {
////
////                }
////
////                public void onProviderDisabled(String s) {
////                    // If a user device has their GPS or network disabled. Start the
////                    // android setting screen intent for the user to enable it
////                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
////                    startActivity(intent);
////
////                }
////
////            };
//
//            return "task complete";
//        }
//
//        ;
//
//        @Override
//        protected void onPostExecute(String result) {
//            viewPager.setAdapter(adapter);
//
//        }
//
//
//    }
//        // Setup the map fragment
//    private boolean initMap() {
//        if (mMap == null) {
//
//            SupportMapFragment mapFragment =
//                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
//            mapFragment.getMapAsync(new OnMapReadyCallback() {
//                @Override
//                public void onMapReady(GoogleMap googleMap) {
//
//                }
//            });
//
//            System.out.println("init ran");
//
//        }
//        return (mMap != null);
//    }
//    public static class MapFragment extends SupportMapFragment {
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            View mapView = super.onCreateView(inflater, container, savedInstanceState);
//            return mapView;
//        }
//
//    }
//
//
//
//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new OneFragment(), "ONE");
//        adapter.addFragment(new TwoFragment(), "TWO");
//        adapter.addFragment(new ArActivity(), "THREE");
//        viewPager.setAdapter(adapter);
//    }
//    class ViewPagerAdapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//        public ViewPagerAdapter(android.support.v4.app.FragmentManager manager) {
//            super(manager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
//        }
//    }
//}
