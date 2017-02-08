
package com.google.samples.quickstart.signin;

import android.Manifest;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.SettingsActivity;
import com.example.myfirstapp.SignInActivity;
import com.example.myfirstapp.couponActivity;
import com.example.myfirstapp.help;
import com.example.myfirstapp.paymentscreen;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import ch.hsr.geohash.GeoHash;

import static com.example.myfirstapp.R.id.map;
import static com.example.myfirstapp.R.layout.loggedout;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {    TextView textX, textY, textZ;
    SensorManager sensorManager;
    Sensor sensor;
    Boolean firstSubmit;
    String cat = "";
    MarkerManager markerManager;
    TextView nearbyOffer1RetName;
    TextView nearbyOffer2RetName;
    TextView nearbyOffer3RetName;
    TextView nearbyOffer4RetName;
    TextView nearbyOffer5RetName;
    TextView nearbyOffer6RetName;
    TextView nearbyOffer7RetName;
    TextView nearbyOffer8RetName;

    Collection<Marker> markers;
    GoogleMap mMap;
    Boolean isKeyboardOpen;
    String OfferCat = null;
    String[] individualOfferDetsArray = new String[15];
    ArrayList<String> allOffersArray = new ArrayList<>();
    CountDownTimer timer;
    CountDownTimer timer2;
    CountDownTimer timer3;
    String singleOfferString = null;
    long epochDif;
    boolean backpressed = false;
    int timeToRunHash = 0;


    SemiCircleProgressBarView semiCircleProgressBarView;
    SemiCircleProgressBarView semiCircleProgressBarView2;
    SemiCircleProgressBarView semiCircleProgressBarView3;
    private boolean mVisible;
    boolean backStack = false;
    private boolean moviesIsClicked = false;
    private boolean shoppingIsClicked = false;
    private boolean foodDiningIsClicked = false;
    private boolean drinksIsClicked = false;
    private boolean attractionsIsClicked = false;
    private boolean accomodationIsClicked = false;
    private static final String FORMAT = "%02d:%02d";
    String dealOffer1Text = "";
    String dealOffer2Text = "";
    String dealOffer3Text = "";

    // Declare the flat progress bars used in the multi offer and nearby list view progress sliding draws
    ProgressBar multiOfferProgressBar1;
    ProgressBar multiOfferProgressBar2;
    ProgressBar multiOfferProgressBar3;
    ProgressBar nearbyOfferProgressBar1;
    ProgressBar nearbyOfferProgressBar2;
    ProgressBar nearbyOfferProgressBar3;
    ProgressBar nearbyOfferProgressBar4;
    ProgressBar nearbyOfferProgressBar5;
    ProgressBar nearbyOfferProgressBar6;
    ProgressBar nearbyOfferProgressBar7;
    ProgressBar nearbyOfferProgressBar8;
    ClusterManager<AppClusterItem> mClusterManager;

    private GoogleApiClient mGoogleApiClient;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private GoogleApiClient client;
    // Declare an instance of the Location Manager and Listener for retrieving the user's current
    // latitude and longitude
    private LocationManager locationManager;
    private LocationListener locationListener;
    View nearbyOffersDrawer;
    ArrayList<String[]> parent = new ArrayList<>();
    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    // Once a user has responded to the popup boxes asking for app permission to use the
    // gps and camera, grant the intended permissions
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.


                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        
    }

    //Setup the Cluster Markers for google maps
    public class ClusterMarkerLocation implements ClusterItem, com.google.maps.android.clustering.ClusterItem {

        private LatLng position;

        public ClusterMarkerLocation(LatLng latLng) {
            position = latLng;
        }

        @Override
        public LatLng getPosition() {
            return position;
        }

        @Override
        public String getTitle() {
            return null;
        }

        @Override
        public String getSnippet() {
            return null;
        }

        public void setPosition(LatLng position) {
            this.position = position;
        }
    }

    //Define the geohash params to calculate. This is required for the nearby offers list view
    private static final int MAX_CHARACTER_PRECISION = 12;
    private static final int[] BITS = {16, 8, 4, 2, 1};
    private static final char[] BASE_32 = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    // Logout function: when the user hits logout on the sliding drawer, return them to the main signin activity
    public void logout(View view) {
        Intent intent = new Intent(MapsActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    // Here we get the geohashes of the user and his surrounding geohashes, this accepts the Latlng
    // object that defines the users current location
    public ArrayList getListViewHashes(LatLng latLng) {
        String userGeoHash = encode(latLng.latitude, latLng.longitude).substring(0, 7);
        GeoHash[] adjacent = GeoHash.fromGeohashString(userGeoHash).getAdjacent();
        System.out.println(adjacent[0].toBase32());
        String north = adjacent[0].toBase32();
        String northeast = adjacent[1].toBase32();
        String east = adjacent[2].toBase32();
        String southeast = adjacent[3].toBase32();
        String south = adjacent[4].toBase32();
        String southwest = adjacent[5].toBase32();
        String west = adjacent[6].toBase32();
        String northwest = adjacent[7].toBase32();
        ArrayList nearHashes = new ArrayList();
        nearHashes.add(0, userGeoHash);
        nearHashes.add(1, north);
        nearHashes.add(2, northeast);
        nearHashes.add(3, east);
        nearHashes.add(4, southeast);
        nearHashes.add(5, south);
        nearHashes.add(6, southwest);
        nearHashes.add(7, west);
        nearHashes.add(8, northwest);
        return nearHashes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        firstSurbmit = true;
        //Initiate the sensor that wil be used to listen for vertical 'flickups' of the device to switch to AR activity
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // Create the media player to play a noice on tap of particular buttons
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);

        //Initiate the google api
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        System.out.println("onConn fired");

        // Call the location service of the LocationManager
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Check the google play services is available, if so start the actions below
        if (servicesOK()) {
            //Define the main layout for this activity
            setContentView(R.layout.activity_maps);


            System.out.println("ready to map");
            // Retnames are the 8 potential text views where nearby offers can populate.
            // Note: when 8 offers are not nearby only those that are, will populate the text views here
            nearbyOffer1RetName = (TextView) findViewById(R.id.nearbyOffer1RetName);
            nearbyOffer2RetName = (TextView) findViewById(R.id.nearbyOffer2RetName);
            nearbyOffer3RetName = (TextView) findViewById(R.id.nearbyOffer3RetName);
            nearbyOffer4RetName = (TextView) findViewById(R.id.nearbyOffer4RetName);
            nearbyOffer5RetName = (TextView) findViewById(R.id.nearbyOffer5RetName);
            nearbyOffer6RetName = (TextView) findViewById(R.id.nearbyOffer6RetName);
            nearbyOffer7RetName = (TextView) findViewById(R.id.nearbyOffer7RetName);
            nearbyOffer8RetName = (TextView) findViewById(R.id.nearbyOffer8RetName);

            // Initiate the first progress bar that displays in the multi offer popup view
            multiOfferProgressBar1 = (ProgressBar) findViewById(R.id.multiOfferProgressBar1);
            //make the progress bar visible
            multiOfferProgressBar1.setVisibility(View.VISIBLE);
            multiOfferProgressBar1.setMax(100);
            LinearLayout lino = (LinearLayout) findViewById(R.id.multiOffer1Layout);
            multiOfferProgressBar1.setProgress(10);
            multiOfferProgressBar1.setMinimumWidth(lino.getWidth());
            //Set the initial/default colour of the progress bar
            multiOfferProgressBar1.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

            // Initiate the second progress bar that displays in the multi offer popup view
            multiOfferProgressBar2 = (ProgressBar) findViewById(R.id.multiOfferProgressBar2);
            //make the progress bar visible
            multiOfferProgressBar2.setVisibility(View.VISIBLE);
            multiOfferProgressBar2.setMax(100);
            multiOfferProgressBar2.setProgress(50);
            LinearLayout lino2 = (LinearLayout) findViewById(R.id.multiOffer1Layout);
            multiOfferProgressBar2.setMinimumWidth(lino2.getWidth());
            //Set the initial/default colour of the progress bar
            multiOfferProgressBar2.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

            // Initiate the third progress bar that displays in the multi offer popup view
            multiOfferProgressBar3 = (ProgressBar) findViewById(R.id.multiOfferProgressBar3);
            //make the progress bar visible
            multiOfferProgressBar3.setVisibility(View.VISIBLE);
            multiOfferProgressBar3.setMax(100);
            multiOfferProgressBar3.setProgress(50);
            LinearLayout lino3 = (LinearLayout) findViewById(R.id.multiOffer1Layout);
            multiOfferProgressBar3.setMinimumWidth(lino3.getWidth());
            //Set the initial/default colour of the progress bar
            multiOfferProgressBar3.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

            // Initiate the fourth progress bar that displays in the multi offer popup view
            nearbyOfferProgressBar1 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar1);
            //make the progress bar visible
            nearbyOfferProgressBar1.setVisibility(View.VISIBLE);
            nearbyOfferProgressBar1.setMax(100);
            nearbyOfferProgressBar1.setProgress(50);
            final FrameLayout contentNearby = (FrameLayout) findViewById(R.id.nearbyOffer1);
            nearbyOfferProgressBar1.setMinimumWidth(lino3.getWidth());
            //Set the initial/default colour of the progress bar
            nearbyOfferProgressBar1.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

            // Initiate the fifth progress bar that displays in the multi offer popup view
            nearbyOfferProgressBar2 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar2);
            //make the progress bar visible
            nearbyOfferProgressBar2.setVisibility(View.VISIBLE);
            nearbyOfferProgressBar2.setMinimumWidth(lino3.getWidth());
            nearbyOfferProgressBar2.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            nearbyOfferProgressBar2.setMax(100);
            nearbyOfferProgressBar2.setProgress(50);

            // Initiate the sixth progress bar that displays in the multi offer popup view
            nearbyOfferProgressBar3 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar3);
            //make the progress bar visible
            nearbyOfferProgressBar3.setVisibility(View.VISIBLE);
            nearbyOfferProgressBar3.setMinimumWidth(lino3.getWidth());
            nearbyOfferProgressBar3.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            nearbyOfferProgressBar3.setMax(100);
            nearbyOfferProgressBar3.setProgress(50);


            nearbyOfferProgressBar4 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar4);
            //make the progress bar visible
            nearbyOfferProgressBar4.setVisibility(View.VISIBLE);
            nearbyOfferProgressBar4.setMinimumWidth(lino3.getWidth());
            nearbyOfferProgressBar4.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            nearbyOfferProgressBar4.setMax(100);
            nearbyOfferProgressBar4.setProgress(50);
            //make the progress bar visible

            final FrameLayout nearbyOffer5 = (FrameLayout) findViewById(R.id.nearbyOffer5);

            nearbyOfferProgressBar5 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar5);
            nearbyOfferProgressBar6 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar6);
            nearbyOfferProgressBar7 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar7);
            nearbyOfferProgressBar8 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar8);

            nearbyOfferProgressBar5.setMinimumWidth(nearbyOffer5.getWidth());

            nearbyOfferProgressBar5.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            nearbyOfferProgressBar5.setMax(100);
            nearbyOfferProgressBar5.setProgress(50);
            nearbyOfferProgressBar5 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar5);
            //make the progress bar visible

            nearbyOfferProgressBar6.setVisibility(View.VISIBLE);
            nearbyOfferProgressBar6.setMinimumWidth(lino3.getWidth());
            nearbyOfferProgressBar6.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            nearbyOfferProgressBar6.setMax(100);
            nearbyOfferProgressBar6.setProgress(50);
            nearbyOfferProgressBar6 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar6);
            //make the progress bar visible

            nearbyOfferProgressBar7.setMinimumWidth(lino3.getWidth());
            nearbyOfferProgressBar7.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            nearbyOfferProgressBar7.setMax(100);
            nearbyOfferProgressBar7.setProgress(50);
            nearbyOfferProgressBar7 = (ProgressBar) findViewById(R.id.nearbyOfferProgressBar7);
            //make the progress bar visible

            final FrameLayout nearbyOffer8 = (FrameLayout) findViewById(R.id.nearbyOffer8);
            nearbyOfferProgressBar8.setMinimumWidth(nearbyOffer8.getWidth());
            nearbyOfferProgressBar8.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            nearbyOfferProgressBar8.setMax(100);
            nearbyOfferProgressBar8.setProgress(50);

            //Define the search view for maps
            final SearchView searchView = (SearchView) findViewById(R.id.searchView);
            searchView.setQueryHint(Html.fromHtml("<font color = #000000 family = FF CLAN>" + "WHERE TO..." + "</font>"));
            int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            TextView textView = (TextView) searchView.findViewById(id);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(15);

            // Set the onclick action to perform the search function
            firstSubmit = true;
            ImageView search = (ImageView) findViewById(R.id.search);
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    searchFunc(v);
                }
            });

            // Initiate the close button of the offerSlideDrawer
            ImageView multiAndSingleOfferClose = (ImageView) findViewById(R.id.multiAndSingleOfferClose);
            multiAndSingleOfferClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                            R.anim.bottom_down);
                    ViewGroup offerSlideDrawer = (ViewGroup) findViewById(R.id.offerSlideDrawer);
                    offerSlideDrawer.startAnimation(bottomDown);
                    offerSlideDrawer.setVisibility(View.INVISIBLE);

                }
            });

            // Initiate the close button of the nearby offers drawer
            ImageView nearbyOffersClose = (ImageView) findViewById(R.id.nearbyOffersClose);
            nearbyOffersClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                            R.anim.bottom_down);
                    ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.nearbyOffersDrawer);
                    hiddenPanel.startAnimation(bottomDown);
                    hiddenPanel.setVisibility(View.INVISIBLE);

                }
            });

            // Initiate the events for displaying only drinks on the map and alternating the grey or gold icon
            final ImageView drinks = (ImageView) findViewById(R.id.drinks);
            drinks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();

                    if (drinksIsClicked == false) {
                        drinks.setBackgroundResource(R.drawable.drinks);
                        drinksIsClicked = true;
                        hideDrinks();

                    } else {
                        drinks.setBackgroundResource(R.drawable.drinksgrey);
                        drinksIsClicked = false;
                        hideDrinks();
                    }
                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
                }
            });

            // Initiate the events for displaying only shopping on the map and alternating the grey or gold icon
            final ImageView shopping = (ImageView) findViewById(R.id.shopping);
            shopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mp.start();

                    if (shoppingIsClicked == false) {
                        shopping.setBackgroundResource(R.drawable.shopping);

                        shoppingIsClicked = true;
                        hideShopping();
                    } else {
                        shopping.setBackgroundResource(R.drawable.shoppinggrey);
                        shoppingIsClicked = false;
                        hideShopping();
                        System.out.println("goldbar shpng is clicked fired and was true");
                    }
                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
                }
            });
            // Initiate the events for displaying only accomodation icons on the map and alternating the grey or gold icon
            final ImageView accommodation = (ImageView) findViewById(R.id.accommodation);
            accommodation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    if (accomodationIsClicked == false) {
                        accommodation.setBackgroundResource(R.drawable.accommodation);
                        accomodationIsClicked = true;
                        hideAccomodation();
                    } else {
                        accommodation.setBackgroundResource(R.drawable.accommodationgrey);
                        accomodationIsClicked = false;
                        hideAccomodation();
                    }
                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
                }
            });

            // Initiate the events for displaying only food and dining on the map and alternating the grey or gold icon
            final ImageView fooddining = (ImageView) findViewById(R.id.fooddining);
            fooddining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();

                    if (foodDiningIsClicked == false) {
                        fooddining.setBackgroundResource(R.drawable.fooddining);  // 50% transparent
                        foodDiningIsClicked = true;
                        hideFoodDining();

                    } else {
                        fooddining.setBackgroundResource(R.drawable.fooddininggrey);  // 50% transparent
                        foodDiningIsClicked = false;
                        hideFoodDining();
                    }

                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
                }
            });
            // Initiate the events for displaying only movies on the map and alternating the grey or gold icon
            final ImageView movies = (ImageView) findViewById(R.id.movies);
            movies.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();

                    if (moviesIsClicked == false) {
                        movies.setBackgroundResource(R.drawable.movies);  // 50% transparent
                        moviesIsClicked = true;
                        hideMovies();
                    } else {
                        movies.setBackgroundResource(R.drawable.moviesgrey);  // 50% transparent
                        moviesIsClicked = false;
                        hideMovies();
                    }
                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
                }
            });
            // Initiate the events for displaying only attractions on the map and alternating the grey or gold icon
            final ImageView attractions = (ImageView) findViewById(R.id.attractions);
            attractions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    if (attractionsIsClicked == false) {
                        attractions.setBackgroundResource(R.drawable.activities);
                        System.out.println("attractions is clicked was false");
                        attractionsIsClicked = true;
                        hideAttractions();
                    } else {
                        attractions.setBackgroundResource(R.drawable.activitiesgrey);
                        System.out.println("attractions is clicked was true");
                        attractionsIsClicked = false;
                        hideAttractions();
                    }
                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
                }
            });


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

// Configure the share button in the multi offer view to take the current offers text and allow the user to share them through various channels including email
            ImageView multiOffersShare = (ImageView) findViewById(R.id.ofsdShare2);
            multiOffersShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView multiOfferRet1Name = (TextView) findViewById(R.id.multiOfferRet1Name);
                    TextView multiOfferRet2Name = (TextView) findViewById(R.id.multiOfferRet2Name);
                    TextView multiOfferRet3Name = (TextView) findViewById(R.id.multiOfferRet3Name);

                    String offer2 = null;
                    String offer3 = null;

                    if (multiOfferRet1Name.getText().toString().equals("offer 2 empty")) {
                        offer2 = "";
                    } else {
                        offer2 = multiOfferRet1Name.getText().toString();
                    }
                    if (multiOfferRet2Name.getText().toString().equals("offer 3 empty")) {
                        offer3 = "";
                    } else {
                        offer3 = multiOfferRet2Name.getText().toString();
                    }

                    Intent emailIntent = new Intent();
                    emailIntent.setAction(Intent.ACTION_SEND);

                    // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
                    emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("ghh"));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ngg");
                    emailIntent.setType("message/rfc822");

                    PackageManager pm = getPackageManager();
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");


                    Intent openInChooser = Intent.createChooser(emailIntent, "Share via:");

                    List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
                    List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
                    for (int i = 0; i < resInfo.size(); i++) {
                        // Extract the label, append it, and repackage it in a LabeledIntent
                        ResolveInfo ri = resInfo.get(i);
                        String packageName = ri.activityInfo.packageName;
                        if (packageName.contains("android.email")) {
                            emailIntent.setPackage(packageName);
                        } else if (packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("mms") || packageName.contains("android.gm")) {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                            intent.setAction(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            if (packageName.contains("twitter")) {
                                intent.putExtra(Intent.EXTRA_TEXT, (nearbyOffer1RetName.getText().toString() + "\n" + multiOfferRet1Name.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("facebook")) {
                                // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                                // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                                // will show the <meta content ="..."> text from that page with our link in Facebook.
                                intent.putExtra(Intent.EXTRA_TEXT, (nearbyOffer1RetName.getText().toString() + "\n" + multiOfferRet1Name.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                                intent.putExtra(Intent.EXTRA_SUBJECT, nearbyOffer1RetName.getText().toString() + "\n" + multiOfferRet1Name.getText().toString() + "\n" + offer2 + "\n" + offer3);
                                intent.setType("message/rfc822");
                            }

                            intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                        }
                    }

                    // convert intentList to array
                    LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);

                    openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                    startActivity(openInChooser);
                }
            });

            // Set up sharing through multiple channels on the third share button of the offer share drawer (ofsd)
            ImageView shareButton3 = (ImageView) findViewById(R.id.ofsdShare3);
            shareButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView singleCouponOfferText1 = (TextView) findViewById(R.id.singleCouponOfferText1);
                    TextView singleCouponOfferText2 = (TextView) findViewById(R.id.singleCouponOfferText2);
                    TextView singleCouponOfferText3 = (TextView) findViewById(R.id.singleCouponOfferText3);
                    TextView retName = (TextView) findViewById(R.id.ofsdRetailerName);
                    String offer2 = null;
                    String offer3 = null;
                    if (singleCouponOfferText2.getText().toString().equals("offer 2 empty")) {
                        offer2 = "";

                    } else {
                        offer2 = singleCouponOfferText2.getText().toString();

                    }
                    if (singleCouponOfferText3.getText().toString().equals("offer 3 empty")) {
                        offer3 = "";

                    } else {
                        offer3 = singleCouponOfferText3.getText().toString();

                    }

                    Resources resources = getResources();

                    Intent emailIntent = new Intent();
                    emailIntent.setAction(Intent.ACTION_SEND);
                    // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
                    emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("ghh"));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ngg");
                    emailIntent.setType("message/rfc822");

                    PackageManager pm = getPackageManager();
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");


                    Intent openInChooser = Intent.createChooser(emailIntent, "Share via:");

                    List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
                    List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
                    for (int i = 0; i < resInfo.size(); i++) {
                        // Extract the label, append it, and repackage it in a LabeledIntent
                        ResolveInfo ri = resInfo.get(i);
                        String packageName = ri.activityInfo.packageName;
                        if (packageName.contains("android.email")) {
                            emailIntent.setPackage(packageName);
                        } else if (packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("mms") || packageName.contains("android.gm")) {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                            intent.setAction(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            if (packageName.contains("twitter")) {
                                intent.putExtra(Intent.EXTRA_TEXT, (retName.getText().toString() + "\n" + singleCouponOfferText1.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("facebook")) {
                                // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                                // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                                // will show the <meta content ="..."> text from that page with our link in Facebook.
                                intent.putExtra(Intent.EXTRA_TEXT, (retName.getText().toString() + "\n" + singleCouponOfferText1.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                                intent.putExtra(Intent.EXTRA_SUBJECT, retName.getText().toString() + "\n" + singleCouponOfferText1.getText().toString() + "\n" + offer2 + "\n" + offer3);
                                intent.setType("message/rfc822");
                            }

                            intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                        }
                    }

                    // convert intentList to array
                    LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);

                    openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                    startActivity(openInChooser);
                }

            });

            final FrameLayout multiOffer1 = (FrameLayout) findViewById(R.id.multiOffer1);
            final FrameLayout multiOffer2 = (FrameLayout) findViewById(R.id.multiOffer2);
            final FrameLayout multiOffer3 = (FrameLayout) findViewById(R.id.multiOffer3);
            final FrameLayout nodCoupOffer1 = (FrameLayout) findViewById(R.id.nodCoupOffer1);
            final FrameLayout nodCoupOffer2 = (FrameLayout) findViewById(R.id.nodCoupOffer2);
            final FrameLayout nodCoupOffer3 = (FrameLayout) findViewById(R.id.nodCoupOffer3);
            final ImageView starsOverlay = (ImageView) findViewById(R.id.multiOffersStars);
            final SemiCircleProgressBarView semiCirc = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc1);

            final ImageView nodSemiCircBg = (ImageView) findViewById(R.id.nodSemiCircBg);
            final ImageView ofsdSemiCircBg = (ImageView) findViewById(R.id.ofsdSemiCircBg);
            View nearbyOffersDrawer = findViewById(R.id.nearbyOffersToolbar);

            // When a user swipes right on the three navigation circles at the bottom of the maps view, display the nearby offers
            nearbyOffersDrawer.setOnTouchListener(new OnSwipeTouchListener(this) {
                public static final String DEBUG_TAG = "error";

                public void onSwipeTop() {
                    Toast.makeText(MapsActivity.this, "top", Toast.LENGTH_SHORT).show();
                }

                public void onSwipeRight() {

                    ViewGroup offerSlideDrawer = (ViewGroup) findViewById(R.id.nearbyOffersDrawer);
                    offerSlideDrawer.setVisibility(View.VISIBLE);

                    TextView tv = (TextView) findViewById(R.id.nodRetailerName);
                    tv.setText("Your nearby offers");

                    overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
                    // Set this boolean so that when the back button is pressed the drawer will
                    // close (dealt with in 'onBackPressed' method
                    backpressed = true;

                    // Hide the frames that may have been visible for an offer in the single coupon view
                    nodCoupOffer1.setVisibility(View.GONE);
                    nodCoupOffer2.setVisibility(View.GONE);
                    nodCoupOffer3.setVisibility(View.GONE);
                    // Hide the  timer circle
                    nodSemiCircBg.setVisibility(View.GONE);
                    ofsdSemiCircBg.setVisibility(View.GONE);
                    semiCirc.setVisibility(View.GONE);
                    starsOverlay.setVisibility(View.GONE);
                    // Display the three navigation circles at the bottom of the page
                    Toolbar nearbyOffersNavCirclesToolbar = (Toolbar) findViewById(R.id.nearbyOffersNavCirclesToolbar);
                    nearbyOffersNavCirclesToolbar.setVisibility(View.VISIBLE);

                    // The scroll view is dislpayed at 180 degs, i.e upside down in slidedrawers so ensure the focus
                    // is down so it displays the offers at the top first
                    ScrollView scroll = (ScrollView) findViewById(R.id.ofsdScroll);
                    scroll.fullScroll(View.FOCUS_DOWN);
                }

                public void onSwipeLeft() {
                    Intent intent = new Intent(MapsActivity.this, ArActivity.class);
                    startActivity(intent);
                }

                public void onSwipeBottom() {
                    Toast.makeText(MapsActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                }

            });
            // Set the nav circles on the nearby offers view to swipe back to the maps on left swipe
            View nearbyOffersNavCirclesToolbar = findViewById(R.id.nearbyOffersNavCirclesToolbar);
            nearbyOffersNavCirclesToolbar.setOnTouchListener(new OnSwipeTouchListener(this) {
                public static final String DEBUG_TAG = "error";

                public void onSwipeTop() {
                }

                public void onSwipeRight() {

                }

                public void onSwipeLeft() {
                    // Animate the disappearance of the nearby offers drawer
                    // Commit the hide of the drawer
                    ViewGroup offerSlideDrawer = (ViewGroup) findViewById(R.id.nearbyOffersDrawer);
                    offerSlideDrawer.setVisibility(View.GONE);
                    overridePendingTransition(R.anim.slide_right,
                            R.anim.slide_right);

                    // Hide all multi offers views and components in the nearby offer drawer view
                    multiOffer1.setVisibility(View.GONE);
                    backpressed = true;
                    multiOffer2.setVisibility(View.GONE);
                    nodCoupOffer2.setVisibility(View.GONE);
                    nodCoupOffer1.setVisibility(View.GONE);
                    nodCoupOffer3.setVisibility(View.GONE);

                    ofsdSemiCircBg.setVisibility(View.GONE);
                    nodSemiCircBg.setVisibility(View.GONE);
                    semiCirc.setVisibility(View.GONE);
                    starsOverlay.setVisibility(View.GONE);
                    // Hide the nav circles as these nav circles are another component in maps view
                    Toolbar nearbyOffersNavCirclesToolbar = (Toolbar) findViewById(R.id.nearbyOffersNavCirclesToolbar);
                    nearbyOffersNavCirclesToolbar.setVisibility(View.GONE);
                }

                public void onSwipeBottom() {
                    Toast.makeText(MapsActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                }

            });
            // Set the share action via multiple social channels for the first offer share drawer share button
            ImageView shareButton = (ImageView) findViewById(R.id.ofsdShare1);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView singleCouponOfferText1 = (TextView) findViewById(R.id.singleCouponOfferText1);
                    TextView singleCouponOfferText2 = (TextView) findViewById(R.id.singleCouponOfferText2);
                    TextView singleCouponOfferText3 = (TextView) findViewById(R.id.singleCouponOfferText3);
                    TextView ofsdRetailerName = (TextView) findViewById(R.id.ofsdRetailerName);
                    String offer2 = null;
                    String offer3 = null;
                    if (singleCouponOfferText3.getText().toString().equals("offer 2 empty")) {
                        offer2 = "";

                    } else {
                        offer2 = singleCouponOfferText3.getText().toString();

                    }
                    if (singleCouponOfferText2.getText().toString().equals("offer 3 empty")) {
                        offer3 = "";

                    } else {
                        offer3 = singleCouponOfferText2.getText().toString();

                    }

                    Resources resources = getResources();

                    Intent emailIntent = new Intent();
                    emailIntent.setAction(Intent.ACTION_SEND);
                    // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
                    emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("ghh"));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ngg");
                    emailIntent.setType("message/rfc822");

                    PackageManager pm = getPackageManager();
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");


                    Intent openInChooser = Intent.createChooser(emailIntent, "Share via:");

                    List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
                    List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
                    for (int i = 0; i < resInfo.size(); i++) {
                        // Extract the label, append it, and repackage it in a LabeledIntent
                        ResolveInfo ri = resInfo.get(i);
                        String packageName = ri.activityInfo.packageName;
                        if (packageName.contains("android.email")) {
                            emailIntent.setPackage(packageName);
                        } else if (packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("mms") || packageName.contains("android.gm")) {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                            intent.setAction(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            if (packageName.contains("twitter")) {
                                intent.putExtra(Intent.EXTRA_TEXT, (ofsdRetailerName.getText().toString() + "\n" + singleCouponOfferText1.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("facebook")) {
                                // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                                // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                                // will show the <meta content ="..."> text from that page with our link in Facebook.
                                intent.putExtra(Intent.EXTRA_TEXT, (ofsdRetailerName.getText().toString() + "\n" + singleCouponOfferText1.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                                intent.putExtra(Intent.EXTRA_SUBJECT, ofsdRetailerName.getText().toString() + "\n" + singleCouponOfferText1.getText().toString() + "\n" + offer2 + "\n" + offer3);
                                intent.setType("message/rfc822");
                            }

                            intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                        }
                    }

                    // convert intentList to array
                    LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);

                    openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                    startActivity(openInChooser);
                }


            });

            // Set up the toggling of the menu(payment, help, coupons etc.) open and close in maps view
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            // Set swipe actions from the right to left to switch to AR, this is mostly redundant as exchanged for nav circles
            // and due to maps taking up whole view, the swipe does not work
            View va = (View) findViewById(R.id.mapsy);
            va.setOnTouchListener(new OnSwipeTouchListener(MapsActivity.this) {
                public void onSwipeTop() {
                }

                public void onSwipeRight() {
                }


                public void onSwipeLeft() {

                    Intent intent = new Intent(MapsActivity.this, ArActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.lefter, R.anim.lefter);
                }

                public void onSwipeBottom() {
                }
            });
            // Now we call initMap() to setup our fragment to hold the map
            initMap();

            // if for whatever reason the google maps services are not ok we take the user back to the initial sign in activity
        } else {
            setContentView(R.layout.activity_main);
        }


    }
    // This function opens the website for a user
    public void openRetailerSite(View view) {
        TextView website = (TextView) findViewById(R.id.website);
        String url = "http://" + website.getText().toString();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    // SearchFunc changes the camera zoom to three defined locations if the search query matches thosse locations
    public void searchFunc(View v) {
        final android.widget.SearchView searchView = (android.widget.SearchView) findViewById(R.id.searchView);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
        mp.start();
        searchView.setVisibility(View.VISIBLE);

        searchView.setIconified(false);
        v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));


        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.e("onQueryTextChange", "called");
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("On Query submitted");
                System.out.println(query);
                // Do your task here
                if (query.equals("epsom") || query.equals("epsom ")) {
                    System.out.println("On eps submitted");
                    // Set the lat and lon for epsom
                    LatLng latLng = new LatLng(-36.903968, 174.764763);
                    Toast.makeText(MapsActivity.this, "BOOM EPS", Toast.LENGTH_SHORT);

                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
                    mMap.animateCamera(cameraUpdate);
                }            // Do your task here
                if (query.equals("ponsonby") || query.equals("ponsonby ")) {
                    System.out.println("On pons submitted");
                    // Set the lat and lon for Ponsonby
                    LatLng latLng = new LatLng(-36.859104, 174.750184);
                    Toast.makeText(MapsActivity.this, "BOOM PONS", Toast.LENGTH_SHORT);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
                    mMap.animateCamera(cameraUpdate);
                }            // Do your task here
                if (query.equals("britomart") || query.equals("britomart ")) {
                    // Set the lat and lon for Britomart
                    LatLng latLng = new LatLng(-36.844010, 174.766991);
                    Toast.makeText(MapsActivity.this, "BOOM BRITs", Toast.LENGTH_SHORT);

                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
                    mMap.animateCamera(cameraUpdate);
                }
                //Clear the search query and hide it
                searchView.setQuery("", false);
                searchView.setVisibility(View.GONE);

                return false;
            }

        });
        return;

    }

    // openFilter is called when the user taps the filter button when it is not selected
    public void openFilter(View view) {
        // Clear the cluster of all markers
        mClusterManager.clearItems();
        mClusterManager.cluster();
        final ImageView imgFavorite = (ImageView) findViewById(R.id.drinks);
        final ImageView imgFavorite2 = (ImageView) findViewById(R.id.accommodation);
        final ImageView imgFavorite3 = (ImageView) findViewById(R.id.attractions);
        final ImageView imgFavorite4 = (ImageView) findViewById(R.id.movies);
        final ImageView imgFavorite5 = (ImageView) findViewById(R.id.fooddining);
        final ImageView imgFavorite6 = (ImageView) findViewById(R.id.shopping);

        // Ensure the filter icons are all set to grey colours
        imgFavorite.setBackgroundResource(R.drawable.drinksgrey);
        drinksIsClicked = false;

        imgFavorite2.setBackgroundResource(R.drawable.accommodationgrey);
        accomodationIsClicked = false;

        imgFavorite3.setBackgroundResource(R.drawable.activitiesgrey);
        attractionsIsClicked = false;

        imgFavorite4.setBackgroundResource(R.drawable.moviesgrey);
        moviesIsClicked = false;

        imgFavorite5.setBackgroundResource(R.drawable.fooddininggrey);
        foodDiningIsClicked = false;

        imgFavorite6.setBackgroundResource(R.drawable.shoppinggrey);
        shoppingIsClicked = false;

        // Make the touch noise on selection of the filter
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
        mp.start();

        // Slide the filter draw up from the bottom
        Animation bottomUp = AnimationUtils.loadAnimation(MapsActivity.this,
                R.anim.bottom_up);
        ViewGroup filterSlideDrawer = (ViewGroup) findViewById(R.id.filterSlideDrawer);
        filterSlideDrawer.startAnimation(bottomUp);
        filterSlideDrawer.setVisibility(View.VISIBLE);
        overridePendingTransition(R.anim.slid_up, R.anim.slide_out);
    }

    // Below we create a sensor to use the devices gyroscope so a x-axis device
    // flick on the maps view takes the user into AR mode
    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) {
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
/*
            textX.setText("X : " + (int)x + " rad/s");
            textY.setText("Y : " + (int)y + " rad/s");
            textZ.setText("Z : " + (int)z + " rad/s");
*/
            if ((int) x > 1) {
                Intent intent = new Intent(MapsActivity.this, ArActivity.class);
                startActivity(intent);

            }
            if ((int) y != 0) {
                System.out.println("Y flipped sideways");

            }
            if ((int) z != 0) {
                System.out.println("Z palm rotated ");

            }
        }
    };

    public void onResume() {
        super.onResume();
        System.out.println("onResume");
        // Register the sensor gyro again
        sensorManager.registerListener(gyroListener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);


    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(gyroListener);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    // The below hide methods are invoked by the layout xmls
    // onClick event when the users taps the filter icons at the bottom off the map screen
    public void hideDrinks() {
        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();
        System.out.println(markerLength);
        System.out.println(parent.get(1)[1]);
        System.out.println(parent.get(2)[1]);

        // If any of the filters are clicked than we display only those that are clicked by adding them to the cluster manager
        if (drinksIsClicked == true) {
            mClusterManager.clearItems();
            markers = mClusterManager.getMarkerCollection().getMarkers();
//            ArrayList markers2 = new ArrayList(markers);
//            System.out.println(markers2.get(1).toString());
            // Loop through the allOffers array and assign a string to the current offers values
            for (int i = 0; i < allOffersArray.size(); i++) {
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                }
                // Pass this new string of the current offer to the AppClusterItem instance that is created. Next, only add this new marker
                // if the current offers category is clicked
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                if (parent.get(i)[1] == "drinks") {
                    mClusterManager.addItem(offsetItem);
                }
                if (parent.get(i)[1] == "shopping" && shoppingIsClicked == true) {
                    mClusterManager.addItem(offsetItem);
                }
                if (parent.get(i)[1] == "food/dining" && foodDiningIsClicked == true) {
                    mClusterManager.addItem(offsetItem);
                } else if (parent.get(i)[1] == "accomodation" && accomodationIsClicked == true) {
                    mClusterManager.addItem(offsetItem);
                } else if (parent.get(i)[1] == "attractions" && attractionsIsClicked == true) {
                    mClusterManager.addItem(offsetItem);
                } else if (parent.get(i)[1] == "movies" && moviesIsClicked == true) {
                    mClusterManager.addItem(offsetItem);
                } else {

                }
            }
            mClusterManager.cluster();

        }
        // If the filter is already selected, clear all the items from the cluster and only add those markers for the categorys that
        // have been clicked. No markers will be added if none are clicked
        else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();
                for (int i = 0; i < allOffersArray.size(); i++) {
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                    if (parent.get(i)[1] == "shopping" && shoppingIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "food/dining" && foodDiningIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "accomodation" && accomodationIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "attractions" && attractionsIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "movies" && moviesIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else {
                    }
                }
            } else {
                mClusterManager.clearItems();
            }
            mClusterManager.cluster();
        }
    }


    public void hideAttractions() {
        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();
        // If any of the filters are clicked than we display only those that are clicked by adding them to the cluster manager
        if (attractionsIsClicked == true) {
            mClusterManager.clearItems();
            for (int i = 0; i < allOffersArray.size(); i++) {
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                if (parent.get(i)[1] == "attractions") {
                    mClusterManager.addItem(offsetItem);
                }
                if (parent.get(i)[1] == "shopping" && shoppingIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "food/dining" && foodDiningIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "accomodation" && accomodationIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "drinks" && drinksIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "movies" && moviesIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else {

                }
            }
            mClusterManager.cluster();


        }
        // If the filter is already selected, clear all the items from the cluster and only add those markers for the categorys that
        // have been clicked. No markers will be added if none are clicked
         else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();

                for (int i = 0; i < allOffersArray.size(); i++) {
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                    if (parent.get(i)[1] == "drinks" && drinksIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "food/dining" && foodDiningIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "accomodation" && accomodationIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "shopping" && shoppingIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "movies" && moviesIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else {
                    }
                }
            }

            else {
                mClusterManager.clearItems();
            }
            mClusterManager.cluster();
        }
    }

    public void hideAccomodation() {
        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();
        // If any of the filters are clicked than we display only those that are clicked by adding them to the
        // cluster manager
        if (accomodationIsClicked == true) {
            mClusterManager.clearItems();
            for (int i = 0; i < allOffersArray.size(); i++) {
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                if (parent.get(i)[1] == "accomodation") {
                    mClusterManager.addItem(offsetItem);
                }
                if (parent.get(i)[1] == "shopping" && shoppingIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "food/dining" && foodDiningIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "drinks" && drinksIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "attractions" && attractionsIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "movies" && moviesIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else {

                }
            }
            mClusterManager.cluster();


        }
        // If the filter is already selected, clear all the items from the cluster and only add those markers for the categorys that
        // have been clicked. No markers will be added if none are clicked
        else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();

                for (int i = 0; i < allOffersArray.size(); i++) {
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                    if (parent.get(i)[1] == "drinks" && drinksIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "food/dining" && foodDiningIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "shopping" && shoppingIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "attractions" && attractionsIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "movies" && moviesIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else {
                    }
                }
            }

            else {
                mClusterManager.clearItems();
            }
            mClusterManager.cluster();
        }
    }

    public void hideMovies() {

        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();
        // If any of the filters are clicked than we display only those that are clicked by adding them to the cluster manager
        if (moviesIsClicked == true) {
            mClusterManager.clearItems();


            for (int i = 0; i < allOffersArray.size(); i++) {
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                if (parent.get(i)[1] == "movies") {
                    mClusterManager.addItem(offsetItem);
                }
                if (parent.get(i)[1] == "shopping" && shoppingIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "food/dining" && foodDiningIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "accomodation" && accomodationIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "attractions" && attractionsIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "drinks" && drinksIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else {

                }
            }
            mClusterManager.cluster();


        }
        // If the filter is already selected, clear all the items from the cluster and only add those markers for the categorys that
        // have been clicked. No markers will be added if none are clicked
         else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();

                for (int i = 0; i < allOffersArray.size(); i++) {
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                    if (parent.get(i)[1] == "drinks" && drinksIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "food/dining" && foodDiningIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "shopping" && shoppingIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "attractions" && attractionsIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "accomodation" && accomodationIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else {
                    }
                }
            }

            else {
                mClusterManager.clearItems();
            }
            mClusterManager.cluster();
        }
    }

    public void hideFoodDining() {

        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();
        // If any of the filters are clicked than we display only those that are clicked by adding them to the cluster manager
        if (foodDiningIsClicked == true) {
            mClusterManager.clearItems();

            for (int i = 0; i < allOffersArray.size(); i++) {
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                if (parent.get(i)[1] == "food/dining") {
                    mClusterManager.addItem(offsetItem);
                }
                if (parent.get(i)[1] == "shopping" && shoppingIsClicked == true) {
                    mClusterManager.addItem(offsetItem);
                } else if (parent.get(i)[1] == "drinks" && drinksIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "accomodation" && accomodationIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "attractions" && attractionsIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "movies" && moviesIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else {
                }
            }
            mClusterManager.cluster();


        }
        // If the filter is already selected, clear all the items from the cluster and only add those markers for the categorys that
        // have been clicked. No markers will be added if none are clicked
        else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();

                for (int i = 0; i < allOffersArray.size(); i++) {
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                    if (parent.get(i)[1] == "drinks" && drinksIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "shopping" && shoppingIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "accomodation" && accomodationIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "attractions" && attractionsIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "movies" && moviesIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else {
                    }
                }

            } else {
                mClusterManager.clearItems();
            }
            mClusterManager.cluster();

        }

    }

    public void hideShopping() {

        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();
        // If any of the filters are clicked than we display only those that are clicked by adding
        // them to the cluster manager
        if (shoppingIsClicked == true) {
            mClusterManager.clearItems();
            markers = mClusterManager.getMarkerCollection().getMarkers();

            for (int i = 0; i < allOffersArray.size(); i++) {
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                if (parent.get(i)[1] == "shopping") {
                    mClusterManager.addItem(offsetItem);
                }
                if (parent.get(i)[1] == "drinks" && drinksIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "food/dining" && foodDiningIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "accomodation" && accomodationIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "attractions" && attractionsIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else if (parent.get(i)[1] == "movies" && moviesIsClicked == true) {
                    mClusterManager.addItem(offsetItem);

                } else {
                }
            }
            mClusterManager.cluster();


        }
        // If the filter is already selected, clear all the items from the cluster and only add those markers for the categorys that
        // have been clicked. No markers will be added if none are clicked
        else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();

                for (int i = 0; i < allOffersArray.size(); i++) {
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                    if (parent.get(i)[1] == "drinks" && drinksIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "food/dining" && foodDiningIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "accomodation" && accomodationIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "attractions" && attractionsIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else if (parent.get(i)[1] == "movies" && moviesIsClicked == true) {
                        mClusterManager.addItem(offsetItem);

                    } else {
                    }
                }

            } else {
                mClusterManager.clearItems();

            }
        }
        mClusterManager.cluster();
    }

    // Called to check google play services is available and ready when first loading the map
    public boolean servicesOK() {

        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, ERROR_DIALOG_REQUEST);
            dialog.show();

        } else {
            Toast.makeText(this, "Can't connect to mapping service", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    // When the map view is switched to the landscape view by the device, open the AR view activity
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        // Checks whether a hardware keyboard is available
        if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_NO) {
            Toast.makeText(this, "keyboard visible", Toast.LENGTH_SHORT).show();
            SearchView searchView = (SearchView) findViewById(R.id.searchView);
            searchView.setVisibility(View.GONE);
        } else if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_YES) {
            Toast.makeText(this, "keyboard hidden", Toast.LENGTH_SHORT).show();
            SearchView searchView = (SearchView) findViewById(R.id.searchView);
            searchView.setVisibility(View.GONE);
        }
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            System.out.println("ORIENTATION CHANGED");
            System.out.println("Landscape");

            try {
                Intent intent = new Intent(this, ArActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                System.out.println("didnt work");

            }


        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        }
    }


    // When the back button is pressed in maps view there are various scenarios that are delat with below
    @Override
    public void onBackPressed() {
        final FrameLayout nodCoupOffer1 = (FrameLayout) findViewById(R.id.nodCoupOffer1);
        final TextView nodSingleCouponOffer3Text = (TextView) findViewById(R.id.nodSingleCouponOffer3Text);
        final FrameLayout nearbyOffer = (FrameLayout) findViewById(R.id.nearbyOffer1);
        final FrameLayout nearbyOffer2 = (FrameLayout) findViewById(R.id.nearbyOffer2);
        final FrameLayout nearbyOffer3 = (FrameLayout) findViewById(R.id.nearbyOffer3);
        final FrameLayout nearbyOffer4 = (FrameLayout) findViewById(R.id.nearbyOffer4);
        final FrameLayout nearbyOffer5 = (FrameLayout) findViewById(R.id.nearbyOffer5);
        final FrameLayout nearbyOffer6 = (FrameLayout) findViewById(R.id.nearbyOffer6);
        final FrameLayout nearbyOffer7 = (FrameLayout) findViewById(R.id.nearbyOffer7);
        final FrameLayout nearbyOffer8 = (FrameLayout) findViewById(R.id.nearbyOffer8);

        SemiCircleProgressBarView semiCirc = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc1);
        SemiCircleProgressBarView semiCirc2 = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc2);
        SemiCircleProgressBarView semiCirc3 = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc3);


        ViewGroup filterSlideDrawer = (ViewGroup) findViewById(R.id.filterSlideDrawer);
        ViewGroup nearbyOffersDrawer = (ViewGroup) findViewById(R.id.nearbyOffersDrawer);
        ViewGroup offerSlideDrawer = (ViewGroup) findViewById(R.id.offerSlideDrawer);
        // If none of the drawers are open/visible, go back to the last activity in the back stack (either signin or AR activity)
        if (offerSlideDrawer.getVisibility() != View.VISIBLE && filterSlideDrawer.getVisibility() != View.VISIBLE && nearbyOffersDrawer.getVisibility() != View.VISIBLE) {
            super.onBackPressed();
        }

        // If the nearby offer coupon is visible hide its associated components
        if (nodCoupOffer1.getVisibility() == View.VISIBLE) {
            nodCoupOffer1.setVisibility(View.GONE);
            nodSingleCouponOffer3Text.setVisibility(View.GONE);
            if (!nearbyOffer1RetName.getText().equals("")) {
                nearbyOffer.setVisibility(View.VISIBLE);

            }
            if (!nearbyOffer2RetName.getText().equals("")) {
                nearbyOffer2.setVisibility(View.VISIBLE);

            }
            if (!nearbyOffer3RetName.getText().equals("")) {
                nearbyOffer3.setVisibility(View.VISIBLE);

            }
            if (!nearbyOffer4RetName.getText().equals("")) {
                nearbyOffer4.setVisibility(View.VISIBLE);

            }
            if (!nearbyOffer5RetName.getText().equals("")) {
                nearbyOffer5.setVisibility(View.VISIBLE);

            }
            if (!nearbyOffer6RetName.getText().equals("")) {
                nearbyOffer6.setVisibility(View.VISIBLE);

            }
            if (!nearbyOffer7RetName.getText().equals("")) {
                nearbyOffer7.setVisibility(View.VISIBLE);

            }
            if (!nearbyOffer8RetName.getText().equals("")) {
                nearbyOffer8.setVisibility(View.VISIBLE);

            }


        }

        // If the nearby offer drawer is visible close the drawer
        if (nearbyOffersDrawer.getVisibility() == View.VISIBLE && nodCoupOffer1.getVisibility() != View.VISIBLE) {
            Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                    R.anim.bottom_down);
            nearbyOffersDrawer.startAnimation(bottomDown);
            nearbyOffersDrawer.setVisibility(View.GONE);
        }

        final SearchView searchView = (SearchView) findViewById(R.id.searchView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                R.anim.bottom_down);
        if (backpressed == false) {
            offerSlideDrawer.startAnimation(bottomDown);
            offerSlideDrawer.setVisibility(View.GONE);
        }

        final FrameLayout ofsdCoupOffer2 = (FrameLayout) findViewById(R.id.ofsdCoupOffer2);
        final FrameLayout ofsdCoupOffer3 = (FrameLayout) findViewById(R.id.ofsdCoupOffer3);
        final FrameLayout multiOffer1 = (FrameLayout) findViewById(R.id.multiOffer1);
        final FrameLayout multiOffer2 = (FrameLayout) findViewById(R.id.multiOffer2);

        final FrameLayout multiOffer3 = (FrameLayout) findViewById(R.id.multiOffer3);
        FrameLayout lin2 = (FrameLayout) findViewById(R.id.ofsdCoupOffer2);
        FrameLayout lin3 = (FrameLayout) findViewById(R.id.ofsdCoupOffer3);

        ImageView ofsdSemiCircBg = (ImageView) findViewById(R.id.ofsdSemiCircBg);
        ImageView starsOverlay = (ImageView) findViewById(R.id.ofsdStarsOverlay);
        ImageView multiOfferStar = (ImageView) findViewById(R.id.multiOffersStars);

        // If the drawer layout is open, close it
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        // If the filter drawer is visible hide the drawer and display all markers in the cluster again
        if (filterSlideDrawer.getVisibility() == View.VISIBLE) {
            System.out.println("filter hidden panel was open");
            filterSlideDrawer.startAnimation(bottomDown);
            filterSlideDrawer.setVisibility(View.GONE);
            mClusterManager.clearItems();
            markers = mClusterManager.getMarkerCollection().getMarkers();
//            ArrayList markers2 = new ArrayList(markers);
//            System.out.println(markers2.get(1).toString());

            for (int i = 0; i < allOffersArray.size(); i++) {
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                }

                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                mClusterManager.addItem(offsetItem);

            }
            mClusterManager.cluster();
        }
        // If the offer slide drawer is visible hide its associated components
        if (offerSlideDrawer.getVisibility() == View.VISIBLE) {
            if (multiOffer2.getVisibility() == View.VISIBLE) {
                backpressed = false;
                offerSlideDrawer.startAnimation(bottomDown);
                offerSlideDrawer.setVisibility(View.GONE);
                System.out.println("cont2 was visible");
            } else if (backStack == false) {
                offerSlideDrawer.startAnimation(bottomDown);
                offerSlideDrawer.setVisibility(View.GONE);
                System.out.println("cont2 was visible");
            }
        }

        // If the nearby offer coupon of either of the three offers is visible and the second offer is not empty, hide its associated components
        if (nodCoupOffer1.getVisibility() == View.VISIBLE && !dealOffer2Text.equals("offer 2 empty") || ofsdCoupOffer2.getVisibility() == View.VISIBLE && !dealOffer2Text.equals("offer 2 empty") || ofsdCoupOffer3.getVisibility() == View.VISIBLE && !dealOffer2Text.equals("offer 2 empty")) {
            nodCoupOffer1.setVisibility(View.GONE);
            ofsdCoupOffer2.setVisibility(View.GONE);
            ofsdCoupOffer3.setVisibility(View.GONE);
            semiCirc.setVisibility(View.GONE);
            semiCirc2.setVisibility(View.GONE);
            semiCirc3.setVisibility(View.GONE);

            lin2.setVisibility(FrameLayout.GONE);
            multiOffer1.setVisibility(View.VISIBLE);
            multiOffer2.setVisibility(View.VISIBLE);
            multiOffer3.setVisibility(View.VISIBLE);
            ImageView catIcon = (ImageView) findViewById(R.id.ofsdIconImage);
            catIcon.setVisibility(View.VISIBLE);
            if (dealOffer3Text.equals("offer 3 empty")) {
                multiOffer3.setVisibility(View.GONE);

            } else {
                multiOffer3.setVisibility(View.VISIBLE);
            }
            nodCoupOffer1.setVisibility(View.GONE);
            nearbyOffer2.setVisibility(View.GONE);
            System.out.println("offer2 is empty");
            ofsdSemiCircBg.setVisibility(View.GONE);
            semiCirc.setVisibility(View.GONE);
            starsOverlay.setVisibility(View.GONE);


        }
        FrameLayout ofsdCoupOffer1 = (FrameLayout) findViewById(R.id.ofsdCoupOffer1);
        if(ofsdCoupOffer1.getVisibility() == (View.VISIBLE) && !dealOffer2Text.equals("offer 2 empty")){
            ofsdCoupOffer1.setVisibility(View.GONE);

            multiOffer1.setVisibility(View.VISIBLE);
            multiOffer2.setVisibility(View.VISIBLE);
        }
        // Hide the search view on back button pressed
        searchView.setVisibility(View.GONE);

    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
            mp.start();

        }
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
        mp.start();

        return super.onMenuOpened(featureId, menu);
    }

    // Simple method to overide what is executed when a nac bar action is selected, we check if its the help, coupon or
    // other menu items that are selected
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.settings) {
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new FirstFragment()).commit();
            Intent intent = new Intent(MapsActivity.this, SettingsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.lefter, R.anim.lefter);
        } else if (id == R.id.coupons) {
            couponsDashboard();
            Intent intent = new Intent(MapsActivity.this, couponsDash.class);
            startActivity(intent);
        } else if (id == R.id.help) {
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new ThirdFragment()).commit();
            Intent intent = new Intent(MapsActivity.this, help.class);
            startActivity(intent);
        } else if (id == R.id.loginlogout) {
            RelativeLayout mLinearLayout = (RelativeLayout) findViewById(R.id.mapsy);
            Context mContext;
            mContext = getApplicationContext();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            final View customView = inflater.inflate(loggedout, null);
            PopupWindow mPopupWindow;

            mPopupWindow = new PopupWindow(
                    customView,
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            Button remove = (Button) customView.findViewById(R.id.ib_close);

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logout(v);
                }
            });
            mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER, 0, 0);
            mPopupWindow.update(1300, 1000);
            mPopupWindow.setFocusable(true);
            mPopupWindow.update();


        } else if (id == R.id.payment) {
            Intent intent = new Intent(MapsActivity.this, paymentscreen.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        super.onPrepareOptionsMenu(menu);
        return true;
    }

    //getOffers is called in the onRequestData method and passes it the url of our AWS GET request API
    private class getOffers extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

        }

        // Send our GET request to AWS using the Java Http Manager class
        @Override
        protected String doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);
            System.out.println(content);
            System.out.println("content printed");
            return content;
        }

        @Override
        protected void onPostExecute(String result) {
            // Once we send the GET request we have the result passed into this method with all the offers in the app
            try {
                // Put our JSON string of offers returned from the GET request into a JSON array
                // where each element in the array is an offer
                JSONArray array = new JSONArray(result);
                System.out.println(array.getJSONObject(1).toString());
                System.out.println(array.length());


                // Loop through every offer (element in the array) and assign the attributes of each
                // offer into an array. Then we add this array as an element in an array list
                for (int i = 0; i < array.length(); i++) {
                    JSONObject objectCategory = array.getJSONObject(i);

                    System.out.println(objectCategory);
                    individualOfferDetsArray[0] = objectCategory.getString("category");
                    individualOfferDetsArray[1] = objectCategory.getString("retailerName");
                    individualOfferDetsArray[2] = objectCategory.getString("offerEndTime");
                    individualOfferDetsArray[3] = objectCategory.getString("retailerID");
                    individualOfferDetsArray[4] = objectCategory.getString("altitude");
                    individualOfferDetsArray[5] = objectCategory.getString("longitude");
                    individualOfferDetsArray[6] = objectCategory.getString("latitude");
                    individualOfferDetsArray[7] = objectCategory.getString("offerDescription");
                    individualOfferDetsArray[8] = objectCategory.getString("duration");
                    individualOfferDetsArray[10] = objectCategory.getString("rating");
                    individualOfferDetsArray[11] = objectCategory.getString("website");

                    if (!objectCategory.has("offerDescription2")) {
                        System.out.println("offd2 does not exist");
                        individualOfferDetsArray[9] = "offer 2 empty";

                    } else {
                        individualOfferDetsArray[9] = objectCategory.getString("offerDescription2");

                        System.out.println("offd2 exists");
                    }
                    try {
                        individualOfferDetsArray[12] = objectCategory.getString("offerDescription3");
                    } catch (Exception err) {
                        individualOfferDetsArray[12] = "offer 3 empty";
                    }
                    try {
                        individualOfferDetsArray[13] = objectCategory.getString("offerEndTime2");
                    } catch (Exception err) {
                        individualOfferDetsArray[13] = "offer 2 empty";
                    }
                    try {
                        individualOfferDetsArray[14] = objectCategory.getString("offerEndTime3");
                    } catch (Exception err) {
                        individualOfferDetsArray[14] = "offer 3 empty";
                    }


                    // Seperate each array element with a pipe so we can later identify the different attributesfor a unique offer
                    allOffersArray.add("|" + individualOfferDetsArray[0] + "|" + individualOfferDetsArray[1] + "|" + individualOfferDetsArray[2] + "|" + individualOfferDetsArray[3] + "|" + individualOfferDetsArray[4] + "|" + individualOfferDetsArray[5] + "|" + individualOfferDetsArray[6] + "|" + individualOfferDetsArray[7] + "|" + individualOfferDetsArray[8] + "|" + individualOfferDetsArray[9] + "|" + individualOfferDetsArray[10] + "|" + individualOfferDetsArray[11] + "|" + individualOfferDetsArray[12] + "|" + individualOfferDetsArray[13] + "|" + individualOfferDetsArray[14]);

                    System.out.println(allOffersArray.get(i));
                    String[] ert = (allOffersArray.get(i)).split(Pattern.quote("|"));
                    System.out.println("Wider array printing..");

                    System.out.println(allOffersArray.get(i).split(Pattern.quote("|"))[11]);
                    System.out.println(individualOfferDetsArray[9]);
                    System.out.println(objectCategory.getString("offerDescription3"));
                    OfferCat = objectCategory.getString("category");

                }
                // Check the size of our new array
                System.out.println(allOffersArray.size());

            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Request the users current location and move the camera plus set up a circle radius around the user
            try {
                initTestMap();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            System.out.println(values[0]);
        }
    }

    // On Map Ready () is called when we connect to the google api client with 'client.connect()'
    // in our onStart() method. Google calls the onMapReady passing our app the google map
    // for us to use in our app
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        MapStyleOptions style;
        mMap = googleMap;
        style = new MapStyleOptions(("[" +
                "  {" +
                "    \"featureType\":\"all\"," +
                "    \"elementType\":\"all\"," +
                "    \"stylers\":[" +
                "      {" +
                "        \"saturation\":\"-100\"" +
                "      }" +
                "    ]" +
                "  }" +
                "]"));

        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setMapStyle(style);
        // Initiate the GET request to AWS to retrieve our offers for loading into the app
        requestData("https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/geooffers");

        Toast toast = Toast.makeText(MapsActivity.this, "Be careful crossing the road with Vapp App", Toast.LENGTH_SHORT);
        toast.show();
        System.out.println("Map started");

        mMap.setPadding(0, 250, 0, 0);


    }

    public Bitmap getResizedBitmap(Bitmap image, int bitmapWidth, int bitmapHeight) {
        return Bitmap.createScaledBitmap(image, bitmapWidth, bitmapHeight, true);
    }

    private void addClusterMarkers(final ClusterManager<AppClusterItem> mClusterManager) {
        if (parent != null) {
            parent.clear();
        }

        // Set some lat/lng coordinates to start with.
        final double latitude = 51.5145160;
        double longitude = -0.1270060;
        int height = 140;
        int width = 140;


        // Load all our marker image assets
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.shopping);
        Bitmap b = bitmapdraw.getBitmap();
        final Bitmap smallMarker10 = Bitmap.createScaledBitmap(b, width, height, false);


        BitmapDrawable bitmapdraw3 = (BitmapDrawable) getResources().getDrawable(R.drawable.fooddining);
        Bitmap d = bitmapdraw3.getBitmap();
        final Bitmap smallMarker3 = Bitmap.createScaledBitmap(d, width, height, false);


        BitmapDrawable bitmapdraw5 = (BitmapDrawable) getResources().getDrawable(R.drawable.movies);
        Bitmap e = bitmapdraw5.getBitmap();
        final Bitmap smallMarker5 = Bitmap.createScaledBitmap(e, width, height, false);

        // Load all our marker image assets
        BitmapDrawable bitmapdraw7 = (BitmapDrawable) getResources().getDrawable(R.drawable.drinks);
        Bitmap g = bitmapdraw7.getBitmap();
        final Bitmap smallMarker7 = Bitmap.createScaledBitmap(g, width, height, false);

        BitmapDrawable bitmapdraw8 = (BitmapDrawable) getResources().getDrawable(R.drawable.activities);
        Bitmap h = bitmapdraw8.getBitmap();
        final Bitmap smallMarker8 = Bitmap.createScaledBitmap(h, width, height, false);

        BitmapDrawable bitmapdraw9 = (BitmapDrawable) getResources().getDrawable(R.drawable.accomodation);
        Bitmap j = bitmapdraw9.getBitmap();
        Bitmap smallMarker9 = Bitmap.createScaledBitmap(j, width, height, false);

        final Bitmap[] markerIcon = {smallMarker10};
        String category = null;

        for (int i = 0; i < allOffersArray.size(); i++) {
            try {
                singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

            } catch (Exception er) {
                singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
            }
            try {
                singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

            } catch (Exception er) {
                singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

            }
            if ("shopping".equals((allOffersArray.get(i)).split(Pattern.quote("|"))[1])) {
                markerIcon[0] = smallMarker10;
                category = "shopping";
                System.out.println("True");
            } else if ("attractions".equals((allOffersArray.get(i)).split(Pattern.quote("|"))[1])) {
                markerIcon[0] = smallMarker8;
                category = "attractions";

                System.out.println("True");
            } else if ("fastfoods".equals((allOffersArray.get(i)).split(Pattern.quote("|"))[1])) {
                markerIcon[0] = smallMarker3;
                category = "fastfoods";

                System.out.println("True");
            } else if ("food/dining".equals((allOffersArray.get(i)).split(Pattern.quote("|"))[1])) {
                markerIcon[0] = smallMarker3;
                category = "food/dining";

                System.out.println("True");
            } else if ("drinks".equals(((allOffersArray.get(i)).split(Pattern.quote("|"))[1]))) {
                markerIcon[0] = smallMarker7;
                category = "drinks";
                System.out.println("True");
            } else if ("movies".equals(((allOffersArray.get(i)).split(Pattern.quote("|"))[1]))) {
                markerIcon[0] = smallMarker5;
                category = "movies";
                System.out.println("True");
            }


            // Upload our markers with all associated info
            MarkerOptions Opty = new MarkerOptions()
                    .title((((allOffersArray.get(i)).split(Pattern.quote("|"))[2])))
                    .position(new LatLng(Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7])), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6]))))
                    .snippet(singleOfferString)
                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon[0]));

            AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
            mClusterManager.addItem(offsetItem);

//
//            System.out.println("wider arrs printing");
//            System.out.println(allOffersArray.get(i).split(Pattern.quote("|"))[6]);
//            System.out.println(allOffersArray.get(i).split(Pattern.quote("|"))[7]);
//            System.out.println(allOffersArray.get(i).split(Pattern.quote("|"))[8]);
//            System.out.println(allOffersArray.get(i).split(Pattern.quote("|"))[9]);

            // Declare a temporary array to hold the title an category of each marker
            String[] child = new String[2];

            child[0] = offsetItem.mTitle;
            child[1] = category;
            // Store the marker title and category from the child array in a parent ArrayList
            // We create this array list so that the filter can filter the markers by category
            // we cannot add an identifier to google markers so this option is an alternative

            parent.add(i, child);
            mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<AppClusterItem>() {
                @Override
                public boolean onClusterItemClick(AppClusterItem appClusterItem) {
                    System.out.println("Msnip below");
                    System.out.println(appClusterItem.mSnippet);
                    int height = 120;
                    int width = 120;

                    String category = null;
                    for (int i = 0; i < allOffersArray.size(); i++) {


                        System.out.println("MARKERS about to print");
                        System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[14]);
                        System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[15]);
                        System.out.println("MARKERS about to print");
                        String singleOfferString = null;
                        try {
                            singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                        } catch (Exception er) {
                            singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                        }
                        try {
                            singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11];

                        } catch (Exception er) {
                            singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                        }




//            markerManager.getCollection("1").addMarker(new MarkerOptions()
//                    .title((((allOffersArray.get(i)).split(Pattern.quote("|"))[2])))
//                    .position(new LatLng(Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7])), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6]))))
//                    .snippet(singleOfferString)
//                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon)));

                        final String finalSingleOfferString = singleOfferString;
                        final int finalI = i;


                        ScrollView scroll = (ScrollView) findViewById(R.id.ofsdScroll);

                        scroll.fullScroll(View.FOCUS_DOWN);

                        System.out.println(appClusterItem.mSnippet);
                        System.out.println(allOffersArray.get(finalI));
                        TextView tv = (TextView) findViewById(R.id.ofsdRetailerName);
                        tv.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[9]);
                        TextView multiOfferRet1Name = (TextView) findViewById(R.id.multiOfferRet1Name);
                        multiOfferRet1Name.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[0]);

                        System.out.println("onClusterItemClicked");
                        System.out.println(appClusterItem.mSnippet.split(Pattern.quote("|"))[0]);

                        TextView tvn = (TextView) findViewById(R.id.singleCouponOfferText1);
                        tvn.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[0]);
                        dealOffer1Text = appClusterItem.mSnippet.split(Pattern.quote("|"))[0];
                        dealOffer2Text = appClusterItem.mSnippet.split(Pattern.quote("|"))[1];
                        dealOffer3Text = appClusterItem.mSnippet.split(Pattern.quote("|"))[4];

                        TextView tvj = (TextView) findViewById(R.id.multiOfferRet2Name);
                        tvj.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[1]);


                        TextView tvx = (TextView) findViewById(R.id.singleCouponOfferText2);
                        tvx.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[1]);

                        TextView tvd = (TextView) findViewById(R.id.singleCouponOfferText3);
                        tvd.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[4]);
                        TextView tvk = (TextView) findViewById(R.id.multiOfferRet3Name);
                        tvk.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[4]);

                        ImageView starIcon = (ImageView) findViewById(R.id.ofsdStarsOverlay);
                        final ImageView multiOffersStars = (ImageView) findViewById(R.id.multiOffersStars);
                        final ImageView multiOffersStars2 = (ImageView) findViewById(R.id.multiOffersStars2);
                        final ImageView multiOffersStars3 = (ImageView) findViewById(R.id.multiOffersStars3);
                        System.out.println("RATINGS");
                        multiOffersStars2.setTag("five");
                        multiOffersStars3.setTag("five");


                        if (singleOfferString.split(Pattern.quote("|"))[2].equals("1")) {
                            starIcon.setImageResource(R.drawable.starone);
                            multiOffersStars.setImageResource(R.drawable.onestr);
                            multiOffersStars.setTag("one");

                            multiOffersStars2.setImageResource(R.drawable.fivestr);
                            multiOffersStars3.setImageResource(R.drawable.fivestr);
                            System.out.println("goo");

                        }
                        if (singleOfferString.split(Pattern.quote("|"))[2].equals("2")) {
                            starIcon.setImageResource(R.drawable.startwo);
                            multiOffersStars.setImageResource(R.drawable.twostr);
                            multiOffersStars2.setImageResource(R.drawable.fivestr);
                            multiOffersStars3.setImageResource(R.drawable.fivestr);

                            multiOffersStars.setTag("two");

                            System.out.println("goo");

                        }
                        if (singleOfferString.split(Pattern.quote("|"))[2].equals("3")) {
                            starIcon.setImageResource(R.drawable.starthree);
                            multiOffersStars.setTag("three");

                            System.out.println("goo");
                            android.view.ViewGroup.LayoutParams layoutParams = multiOffersStars.getLayoutParams();
                            layoutParams.width = 170;
                            layoutParams.height = 145;
                            multiOffersStars.setPadding(250, 0, 0, 0);
                            multiOffersStars.setLayoutParams(layoutParams);

                            multiOffersStars.setImageResource(R.drawable.threestr);
                            multiOffersStars2.setImageResource(R.drawable.fivestr);
                            multiOffersStars3.setImageResource(R.drawable.fivestr);

                        }
                        if (singleOfferString.split(Pattern.quote("|"))[2].equals("4")) {
                            starIcon.setImageResource(R.drawable.starfour);
                            multiOffersStars.setImageResource(R.drawable.fourstr);
                            multiOffersStars.setTag("four");


                            System.out.println("goo");

                        }
                        if (singleOfferString.split(Pattern.quote("|"))[2].equals("5")) {
                            starIcon.setImageResource(R.drawable.starfive);
                            multiOffersStars.setImageResource(R.drawable.fivestr);
                            multiOffersStars2.setImageResource(R.drawable.fivestr);
                            multiOffersStars3.setImageResource(R.drawable.fivestr);
                            multiOffersStars.setTag("five");

                            System.out.println("goo");

                        }

                        final FrameLayout lin = (FrameLayout) findViewById(R.id.multiOffer1);
                        FrameLayout lin2 = (FrameLayout) findViewById(R.id.ofsdCoupOffer2);
                        FrameLayout lin3 = (FrameLayout) findViewById(R.id.ofsdCoupOffer3);


                        lin.setVisibility(FrameLayout.VISIBLE);
                        lin2.setVisibility(FrameLayout.VISIBLE);
                        lin3.setVisibility(FrameLayout.VISIBLE);
                        System.out.println(allOffersArray.get(0).split(Pattern.quote("|"))[11]);
//                    System.out.println(marker.getSnippet().split(Pattern.quote("|"))[2]);


                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[2].equals("1")) {
                            starIcon.setImageResource(R.drawable.starone);
                            System.out.println("goo");
                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[2].equals("2")) {
                            starIcon.setImageResource(R.drawable.startwo);
                            System.out.println("goo");
                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[2].equals("3")) {
                            starIcon.setImageResource(R.drawable.starthree);
                            System.out.println("goo");
                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[2].equals("4")) {
                            starIcon.setImageResource(R.drawable.starfour);
                            System.out.println("goo");
                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[2].equals("5")) {
                            starIcon.setImageResource(R.drawable.starfive);
                            System.out.println("goo");
                        }

                        final ImageView catIcon = (ImageView) findViewById(R.id.ofsdIconImage);
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[8].equals("drinks")) {
                            catIcon.setImageResource(R.drawable.drinks);
                            System.out.println("GAAAAAA");
                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[8].equals("attractions")) {
                            catIcon.setImageResource(R.drawable.activities);
                            System.out.println("GAAAAAA");

                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[8].equals("accommodation")) {
                            catIcon.setImageResource(R.drawable.accomodation);
                            System.out.println("GAAAAAA");

                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[8].equals("food/dining")) {
                            catIcon.setImageResource(R.drawable.fooddining);
                            System.out.println("GAAAAAA");
                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[8].equals("movies")) {
                            catIcon.setImageResource(R.drawable.movies);
                            System.out.println("GAAAAAA");
                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[8].equals("shopping")) {
                            catIcon.setImageResource(R.drawable.shopping);
                            System.out.println("GAAAAAA");
                        }

                        try {
                            startTimers(appClusterItem.mSnippet.split(Pattern.quote("|"))[5], appClusterItem.mSnippet.split(Pattern.quote("|"))[6], appClusterItem.mSnippet.split(Pattern.quote("|"))[7]);
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }


//                    try {
//                        if (timersInitialized != false){
//
//                        }else {
//
//                            for (int e = 0; e < allOffersArray.size(); e++){
//
//
//                            }
//                            startTimers(marker.getSnippet().split(Pattern.quote("|"))[5],marker.getSnippet().split(Pattern.quote("|"))[6],marker.getSnippet().split(Pattern.quote("|"))[7]);
//                            timersInitialized = true;
//                        }
//                        System.out.println(marker.getSnippet().split(Pattern.quote("|"))[5]);
//                        System.out.println(marker.getSnippet().split(Pattern.quote("|"))[4]);
//                        System.out.println(marker.getSnippet().split(Pattern.quote("|"))[2]);
//                    } catch (ParseException e1) {
//                        e1.printStackTrace();
//                    }

                        final FrameLayout multiOffer1 = (FrameLayout) findViewById(R.id.multiOffer1);

                        final FrameLayout multiOffer2 = (FrameLayout) findViewById(R.id.multiOffer2);
                        final FrameLayout multiOffer3 = (FrameLayout) findViewById(R.id.multiOffer3);
                        final FrameLayout ofsdCoupOffer1 = (FrameLayout) findViewById(R.id.ofsdCoupOffer1);
                        final FrameLayout ofsdCoupOffer2 = (FrameLayout) findViewById(R.id.ofsdCoupOffer2);
                        final FrameLayout ofsdCoupOffer3 = (FrameLayout) findViewById(R.id.ofsdCoupOffer3);

                        final ImageView ofsdSemiCircBg = (ImageView) findViewById(R.id.ofsdSemiCircBg);
                        final SemiCircleProgressBarView semiCirc = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc1);
                        final SemiCircleProgressBarView semiCirc2 = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc2);
                        final SemiCircleProgressBarView semiCirc3 = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc3);
                        final ImageView starsOverlay = (ImageView) findViewById(R.id.ofsdStarsOverlay);


                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[1].equals("offer 2 empty")) {
                            lin2.setVisibility(FrameLayout.GONE);
                            multiOffer1.setVisibility(View.GONE);
                            multiOffer2.setVisibility(View.GONE);
                            multiOffer3.setVisibility(View.GONE);
                            tvn.setVisibility(View.VISIBLE);
                            tvn.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[0]);

                            ofsdCoupOffer1.setVisibility(View.VISIBLE);
                            ofsdCoupOffer2.setVisibility(View.GONE);
                            System.out.println("offer2 is empty");
                            ofsdSemiCircBg.setVisibility(View.VISIBLE);
                            semiCirc.setVisibility(View.VISIBLE);
                            starsOverlay.setVisibility(View.VISIBLE);
                        }
                        if (!appClusterItem.mSnippet.split(Pattern.quote("|"))[1].equals("offer 2 empty")) {
                            multiOffer2.setVisibility(View.VISIBLE);
                            backpressed = true;
                            multiOffer2.setVisibility(View.VISIBLE);
                            ofsdCoupOffer2.setVisibility(View.GONE);
                            ofsdCoupOffer1.setVisibility(View.GONE);
                            ofsdCoupOffer3.setVisibility(View.GONE);

                            ofsdSemiCircBg.setVisibility(View.GONE);
                            semiCirc.setVisibility(View.GONE);
                            semiCirc2.setVisibility(View.GONE);
                            semiCirc3.setVisibility(View.GONE);

                            starsOverlay.setVisibility(View.GONE);
                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[4].equals("offer 3 empty")) {
                            System.out.println("offer3 is empty");
                            multiOffer3.setVisibility(View.GONE);
                            ofsdCoupOffer3.setVisibility(View.GONE);

                        }
                        if (!appClusterItem.mSnippet.split(Pattern.quote("|"))[4].equals("offer 3 empty")) {
                            multiOffer3.setVisibility(View.VISIBLE);
                            ofsdCoupOffer3.setVisibility(View.GONE);
                            ofsdCoupOffer2.setVisibility(View.GONE);
                            ofsdCoupOffer1.setVisibility(View.GONE);
                            semiCirc.setVisibility(View.GONE);
                            semiCirc2.setVisibility(View.GONE);
                            semiCirc3.setVisibility(View.GONE);
                        }
                        final TextView singleCouponOfferText1 = (TextView)findViewById(R.id.singleCouponOfferText1);
                        final TextView singleCouponOfferText2 = (TextView)findViewById(R.id.singleCouponOfferText2);
                        final TextView singleCouponOfferText3 = (TextView)findViewById(R.id.singleCouponOfferText3);


                        final String SingleOfferString = singleOfferString;
                        multiOffer1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                ofsdSemiCircBg.setVisibility(View.VISIBLE);
                                semiCirc.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);

                                semiCirc2.setVisibility(View.GONE);
                                semiCirc3.setVisibility(View.GONE);

                                multiOffer1.setVisibility(View.GONE);
                                multiOffer2.setVisibility(View.GONE);
                                multiOffer3.setVisibility(View.GONE);
                                ofsdCoupOffer1.setVisibility(View.VISIBLE);
                                ofsdCoupOffer2.setVisibility(View.GONE);
                                ofsdCoupOffer3.setVisibility(View.GONE);

                                singleCouponOfferText1.setVisibility(View.VISIBLE);
                                singleCouponOfferText2.setVisibility(View.GONE);
                                singleCouponOfferText3.setVisibility(View.GONE);
                                System.out.println("multiOffer2");


                                if (multiOffersStars.getTag().equals("one")) {
                                    starsOverlay.setImageResource(R.drawable.starone);
                                    System.out.println("one");
                                }
                                if (multiOffersStars.getTag().equals("two")) {
                                    starsOverlay.setImageResource(R.drawable.startwo);
                                    System.out.println("one");
                                }
                                if (multiOffersStars.getTag().equals("three")) {
                                    starsOverlay.setImageResource(R.drawable.starthree);
                                    System.out.println("one");
                                }
                                if (multiOffersStars.getTag().equals("four")) {
                                    starsOverlay.setImageResource(R.drawable.starfour);
                                    System.out.println("one");
                                }
                                if (multiOffersStars.getTag().equals("five")) {
                                    starsOverlay.setImageResource(R.drawable.starfive);
                                    System.out.println("one");
                                }


                            }
                        });
                        final String SingleOfferString2 = singleOfferString;
                        multiOffer2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                System.out.println("multiOffer2");
                                ofsdSemiCircBg.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);

                                singleCouponOfferText1.setVisibility(View.GONE);
                                singleCouponOfferText2.setVisibility(View.VISIBLE);
                                singleCouponOfferText3.setVisibility(View.GONE);

                                if (multiOffersStars2.getTag().equals("one")) {
                                    starsOverlay.setImageResource(R.drawable.starone);
                                    System.out.println("one");
                                }
                                if (multiOffersStars2.getTag().equals("two")) {
                                    starsOverlay.setImageResource(R.drawable.startwo);
                                    System.out.println("one");
                                }
                                if (multiOffersStars2.getTag().equals("three")) {
                                    starsOverlay.setImageResource(R.drawable.starthree);
                                    System.out.println("one");
                                }
                                if (multiOffersStars2.getTag().equals("four")) {
                                    starsOverlay.setImageResource(R.drawable.starfour);
                                    System.out.println("one");
                                }
                                if (multiOffersStars2.getTag().equals("five")) {
                                    starsOverlay.setImageResource(R.drawable.starfive);
                                    System.out.println("one");
                                }


                                semiCirc.setVisibility(View.GONE);
                                semiCirc2.setVisibility(View.VISIBLE);
                                semiCirc3.setVisibility(View.GONE);

                                multiOffer1.setVisibility(View.GONE);
                                multiOffer2.setVisibility(View.GONE);
                                multiOffer3.setVisibility(View.GONE);
                                ofsdCoupOffer1.setVisibility(View.GONE);
                                ofsdCoupOffer2.setVisibility(View.VISIBLE);

                                ofsdCoupOffer3.setVisibility(View.GONE);

                            }
                        });


                        multiOffer3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                System.out.println("multiOffer3");
                                ofsdSemiCircBg.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);
                                singleCouponOfferText1.setVisibility(View.GONE);
                                singleCouponOfferText2.setVisibility(View.GONE);
                                singleCouponOfferText3.setVisibility(View.VISIBLE);

                                semiCirc.setVisibility(View.GONE);
                                semiCirc2.setVisibility(View.GONE);
                                semiCirc3.setVisibility(View.GONE);

                                multiOffer1.setVisibility(View.GONE);
                                multiOffer2.setVisibility(View.GONE);
                                multiOffer3.setVisibility(View.GONE);
                                ofsdCoupOffer1.setVisibility(View.GONE);
                                ofsdCoupOffer2.setVisibility(View.GONE);
                                ofsdCoupOffer3.setVisibility(View.VISIBLE);


                                if (multiOffersStars3.getTag().equals("one")) {
                                    starsOverlay.setImageResource(R.drawable.starone);
                                    System.out.println("one");
                                }
                                if (multiOffersStars3.getTag().equals("two")) {
                                    starsOverlay.setImageResource(R.drawable.startwo);
                                    System.out.println("one");
                                }
                                if (multiOffersStars3.getTag().equals("three")) {
                                    starsOverlay.setImageResource(R.drawable.starthree);
                                    System.out.println("one");
                                }
                                if (multiOffersStars3.getTag().equals("four")) {
                                    starsOverlay.setImageResource(R.drawable.starfour);
                                    System.out.println("one");
                                }
                                if (multiOffersStars3.getTag().equals("five")) {
                                    starsOverlay.setImageResource(R.drawable.starfive);
                                    System.out.println("one");
                                }


                            }
                        });


//                    for (int i=0;i<allOffersArray.size();i++) {
//                        System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[1]);
//                        System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[2]);
//                        System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[3]);
//                        System.out.println(marker.getTitle());
//                        System.out.println(parent.get(i)[0]);
//
//                        if (marker.getTitle().equals(parent.get(i)[0])) {
//
//                            System.out.println("ROOSTY WAS True");
//                        }
//
//
//                    }


//                    Animation animation2 =
//                            AnimationUtils.loadAnimation(MapsActivity.this,
//                                    R.anim.slid_down);
//                    animation2.setAnimationListener
//                            (new GameAnimationListener(MapsActivity.this));
                        ViewGroup offerSlideDrawer = (ViewGroup) findViewById(R.id.filterSlideDrawer);
                        if (offerSlideDrawer.getVisibility() == View.VISIBLE) {

                            Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                                    R.anim.bottom_down);
                            offerSlideDrawer.startAnimation(bottomDown);
                            offerSlideDrawer.setVisibility(View.GONE);
                        }

                        Animation bottomUp = AnimationUtils.loadAnimation(MapsActivity.this,
                                R.anim.bottom_up);
                        ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.offerSlideDrawer);
                        hiddenPanel.startAnimation(bottomUp);
                        hiddenPanel.setVisibility(View.VISIBLE);
//
//                    scroll.fullScroll(View.FOCUS_DOWN);

//                    ScrollView scrollView = (ScrollView) findViewById(R.id.scrolly);
//                    scrollView.setFocusableInTouchMode(true);
//                    scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
//                    SlidingDrawer sdf = (SlidingDrawer) findViewById(R.id.offerSlideDrawer);
//                    sdf.animateOpen();//                    sdf.startAnimation(animation2);

//final ImageView iv = (ImageView)findViewById(R.id.couponBorder);

//                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(lin.getWidth(), lin.getHeight());
//                    lin.setLayoutParams(lp);

//                    ViewTreeObserver vto = lin.getViewTreeObserver();
//                    vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                        @Override
//                        public void onGlobalLayout() {
//                            lin.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                            int width  = lin.getMeasuredWidth();
//                            int height = lin.getMeasuredHeight();
//                            System.out.println(width);
//                            System.out.println(height);
//                            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
//                            iv.setLayoutParams(layoutParams);
//
//                        }
//                    });


                        return true;
                    }
                    return true;
                }

            });


            cat = allOffersArray.get(i).split(Pattern.quote("|"))[1];
            System.out.println(allOffersArray.get(0).split(Pattern.quote("|"))[1]);
            System.out.println();
            System.out.println(i);

//            if ("shopping".equals((allOffersArray.get(i)).split(Pattern.quote("|"))[1])) {
//                markerIcon = smallMarker;
//                category = "shopping";
//                System.out.println("True");
//            } else if ("attractions".equals((allOffersArray.get(i)).split(Pattern.quote("|"))[1])) {
//                markerIcon = smallMarker8;
//                category = "attractions";
//
//                System.out.println("True");
//            } else if ("fastfoods".equals((allOffersArray.get(i)).split(Pattern.quote("|"))[1])) {
//                markerIcon = smallMarker3;
//                category = "fastfoods";
//
//                System.out.println("True");
//            } else if ("food/dining".equals((allOffersArray.get(i)).split(Pattern.quote("|"))[1])) {
//                markerIcon = smallMarker3;
//                category = "food/dining";
//
//                System.out.println("True");
//            } else if ("drinks".equals(((allOffersArray.get(i)).split(Pattern.quote("|"))[1]))) {
//                markerIcon = smallMarker7;
//                category = "drinks";
//                System.out.println("True");
//            } else if ("movies".equals(((allOffersArray.get(i)).split(Pattern.quote("|"))[1]))) {
//                markerIcon = smallMarker5;
//                category = "movies";
//                System.out.println("True");
//            }

            System.out.println("MARKERS about to print");
            System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[2]);
            System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[10]);
            System.out.println("MARKERS about to print");

//
//            // Upload our markers with all associated info
//            Marker marker = mMap.addMarker(new MarkerOptions()
//                    .title((((allOffersArray.get(i)).split(Pattern.quote("|"))[2])))
//                    .position(new LatLng(Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7])), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6]))))
//                    .snippet(singleOfferString)
//                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon))
//            );


//            // Declare a variable for the cluster manager.
//             final ClusterManager<ClusterMarkerLocation> mClusterManager;
//
//            // Initialize the manager with the context and the map.
//            // (Activity extends context, so we can pass 'this' in the constructor.)
//            mClusterManager = new ClusterManager<>(MapsActivity.this,mMap);
////            mMap.setOnCameraIdleListener(clusterManager);


//            mClusterManager.addItem(new ClusterMarkerLocation(new LatLng(Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7])), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])))));
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                final SearchView searchView = (SearchView) findViewById(R.id.searchView);
                searchView.setVisibility(View.GONE);

                Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                        R.anim.bottom_down);
                ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.filterSlideDrawer);
                if (hiddenPanel.getVisibility() == View.VISIBLE) {
                    hiddenPanel.startAnimation(bottomDown);
                    hiddenPanel.setVisibility(View.GONE);

                    mClusterManager.clearItems();
                    markers = mClusterManager.getMarkerCollection().getMarkers();
//            ArrayList markers2 = new ArrayList(markers);
//            System.out.println(markers2.get(1).toString());

                    for (int i = 0; i < allOffersArray.size(); i++) {
                        try {
                            singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

                        } catch (Exception er) {
                            singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                            singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
                        }
                        try {
                            singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[1] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + allOffersArray.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                        } catch (Exception er) {
                            singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

                        }


                        AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, singleOfferString);
                        mClusterManager.addItem(offsetItem);
                        mClusterManager.cluster();

                    }
                }
            }

        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {

                ScrollView scroll = (ScrollView) findViewById(R.id.ofsdScroll);

                scroll.fullScroll(View.FOCUS_DOWN);
                Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                        R.anim.bottom_down);
                ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.offerSlideDrawer);
                hiddenPanel.startAnimation(bottomDown);
                hiddenPanel.setVisibility(View.INVISIBLE);

                System.out.println(marker.getSnippet());
                TextView tv = (TextView) findViewById(R.id.ofsdRetailerName);
                tv.setText(singleOfferString.split(Pattern.quote("|"))[9]);
                TextView tvr = (TextView) findViewById(R.id.retailerNamesy);
                tvr.setText(singleOfferString.split(Pattern.quote("|"))[0]);
                TextView tvn = (TextView) findViewById(R.id.singleCouponOfferText1);
                tvn.setText(singleOfferString.split(Pattern.quote("|"))[0]);

                final FrameLayout lin = (FrameLayout) findViewById(R.id.multiOffer1);
                FrameLayout lin2 = (FrameLayout) findViewById(R.id.ofsdCoupOffer2);
                FrameLayout lin3 = (FrameLayout) findViewById(R.id.ofsdCoupOffer3);


                lin.setVisibility(FrameLayout.VISIBLE);
                lin2.setVisibility(FrameLayout.VISIBLE);
                lin3.setVisibility(FrameLayout.VISIBLE);
                System.out.println(allOffersArray.get(0).split(Pattern.quote("|"))[11]);
//                    System.out.println(marker.getSnippet().split(Pattern.quote("|"))[2]);


                ImageView catIcon = (ImageView) findViewById(R.id.ofsdIconImage);
                if (singleOfferString.split(Pattern.quote("|"))[8].equals("drinks")) {
                    catIcon.setImageResource(R.drawable.dealdrinks);
                    System.out.println("GAAAAAA");

                }
                if (singleOfferString.split(Pattern.quote("|"))[8].equals("attractions")) {
                    catIcon.setImageResource(R.drawable.dealactivities);
                    System.out.println("GAAAAAA");

                }
                if (singleOfferString.split(Pattern.quote("|"))[8].equals("accommodation")) {
                    catIcon.setImageResource(R.drawable.dealaccom);
                    System.out.println("GAAAAAA");

                }
                if (singleOfferString.split(Pattern.quote("|"))[8].equals("food/dining")) {
                    catIcon.setImageResource(R.drawable.dealhospitality);
                    System.out.println("GAAAAAA");
                }
                if (singleOfferString.split(Pattern.quote("|"))[8].equals("movies")) {
                    catIcon.setImageResource(R.drawable.dealentertainment);
                    System.out.println("GAAAAAA");
                }
                if (singleOfferString.split(Pattern.quote("|"))[8].equals("shopping")) {
                    catIcon.setImageResource(R.drawable.dealshopping);
                    System.out.println("GAAAAAA");
                }

                try {
                    startTimers(singleOfferString.split(Pattern.quote("|"))[5], singleOfferString.split(Pattern.quote("|"))[6], singleOfferString.split(Pattern.quote("|"))[7]);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

//                    try {
//                        if (timersInitialized != false){
//
//                        }else {
//
//                            for (int e = 0; e < allOffersArray.size(); e++){
//
//
//                            }
//                            startTimers(marker.getSnippet().split(Pattern.quote("|"))[5],marker.getSnippet().split(Pattern.quote("|"))[6],marker.getSnippet().split(Pattern.quote("|"))[7]);
//                            timersInitialized = true;
//                        }
//                        System.out.println(marker.getSnippet().split(Pattern.quote("|"))[5]);
//                        System.out.println(marker.getSnippet().split(Pattern.quote("|"))[4]);
//                        System.out.println(marker.getSnippet().split(Pattern.quote("|"))[2]);
//                    } catch (ParseException e1) {
//                        e1.printStackTrace();
//                    }


//                        if (marker.getSnippet().split(Pattern.quote("|"))[4].equals("offer 3 empty")) {
//                            lin.setVisibility(LinearLayout.INVISIBLE);
//                    System.out.println("offer3 is empty");
//                        }
//                        if (marker.getSnippet().split(Pattern.quote("|"))[1].equals("offer 2 empty")){
//                            lin2.setVisibility(LinearLayout.INVISIBLE);
//                                            System.out.println("offer2 is empty");
//                }


//                    for (int i=0;i<allOffersArray.size();i++) {
//                        System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[1]);
//                        System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[2]);
//                        System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[3]);
//                        System.out.println(marker.getTitle());
//                        System.out.println(parent.get(i)[0]);
//
//                        if (marker.getTitle().equals(parent.get(i)[0])) {
//
//                            System.out.println("ROOSTY WAS True");
//                        }
//
//
//                    }


//                    Animation animation2 =
//                            AnimationUtils.loadAnimation(MapsActivity.this,
//                                    R.anim.slid_down);
//                    animation2.setAnimationListener
//                            (new GameAnimationListener(MapsActivity.this));
                Animation bottomUp = AnimationUtils.loadAnimation(MapsActivity.this,
                        R.anim.bottom_up);
                hiddenPanel.startAnimation(bottomUp);
                hiddenPanel.setVisibility(View.VISIBLE);
//
//                    scroll.fullScroll(View.FOCUS_DOWN);

//                    ScrollView scrollView = (ScrollView) findViewById(R.id.scrolly);
//                    scrollView.setFocusableInTouchMode(true);
//                    scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
//                    SlidingDrawer sdf = (SlidingDrawer) findViewById(R.id.offerSlideDrawer);
//                    sdf.animateOpen();//                    sdf.startAnimation(animation2);

//final ImageView iv = (ImageView)findViewById(R.id.couponBorder);

//                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(lin.getWidth(), lin.getHeight());
//                    lin.setLayoutParams(lp);

//                    ViewTreeObserver vto = lin.getViewTreeObserver();
//                    vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                        @Override
//                        public void onGlobalLayout() {
//                            lin.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                            int width  = lin.getMeasuredWidth();
//                            int height = lin.getMeasuredHeight();
//                            System.out.println(width);
//                            System.out.println(height);
//                            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
//                            iv.setLayoutParams(layoutParams);
//
//                        }
//                    });


                return true;

            }


        });

//            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                @Override
//                public void onMapClick(LatLng point) {
////                    SlidingDrawer sdg = (SlidingDrawer) findViewById(R.id.offerSlideDrawer);
//                    Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
//                            R.anim.bottom_down);
//                    SearchView searchView = (SearchView) findViewById(R.id.searchView);
//                    searchView.setVisibility(View.GONE);
//
//                    ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.filterSlideDrawer);
//                    if (hiddenPanel.getVisibility() == View.VISIBLE) {
//
//                        hiddenPanel.startAnimation(bottomDown);
//                        hiddenPanel.startAnimation(bottomDown);
//                        hiddenPanel.setVisibility(View.GONE);
//                    }
//                }
//            });
//                    sde.close();
//                }
//
//            });

//            // Add the marker to the list of markers that are added to our google map mMap
//            markers.add(i, marker);
    }


    private void setUpClustering() {

        markerManager = new MarkerManager(mMap);

        // Initialize the manager with the context and the map.
        markerManager.newCollection("1");

        mClusterManager = new ClusterManager<AppClusterItem>(MapsActivity.this, mMap, markerManager);

        // Point the map's listeners at the listeners implemented by the cluster manager.
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager.getMarkerManager());


        mClusterManager.setRenderer(new CustomClusterRenderer(this, mMap, mClusterManager));
        // Add cluster items (markers) to the cluster manager.
        addClusterMarkers(mClusterManager);


    }


    public void setupOfferMapIcons() throws IOException, ParseException {
        setUpClustering();
        int height = 140;
        int width = 140;

        // Load all our marker image assets
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.shopping);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);


        BitmapDrawable bitmapdraw3 = (BitmapDrawable) getResources().getDrawable(R.drawable.fooddining);
        Bitmap d = bitmapdraw3.getBitmap();
        Bitmap smallMarker3 = Bitmap.createScaledBitmap(d, width, height, false);


        BitmapDrawable bitmapdraw5 = (BitmapDrawable) getResources().getDrawable(R.drawable.movies);
        Bitmap e = bitmapdraw5.getBitmap();
        Bitmap smallMarker5 = Bitmap.createScaledBitmap(e, width, height, false);

        // Load all our marker image assets
        BitmapDrawable bitmapdraw7 = (BitmapDrawable) getResources().getDrawable(R.drawable.drinks);
        Bitmap g = bitmapdraw7.getBitmap();
        Bitmap smallMarker7 = Bitmap.createScaledBitmap(g, width, height, false);

        BitmapDrawable bitmapdraw8 = (BitmapDrawable) getResources().getDrawable(R.drawable.activities);
        Bitmap h = bitmapdraw8.getBitmap();
        Bitmap smallMarker8 = Bitmap.createScaledBitmap(h, width, height, false);

        BitmapDrawable bitmapdraw9 = (BitmapDrawable) getResources().getDrawable(R.drawable.accomodation);
        Bitmap j = bitmapdraw9.getBitmap();
        Bitmap smallMarker9 = Bitmap.createScaledBitmap(j, width, height, false);
        Bitmap markerIcon = smallMarker;
        String category = null;
        for (int i = 0; i < allOffersArray.size(); i++) {


            System.out.println("MARKERS about to print");
            System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[14]);
            System.out.println((allOffersArray.get(i)).split(Pattern.quote("|"))[15]);
            System.out.println("MARKERS about to print");
            String singleOfferString = null;
            try {
                singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12];

            } catch (Exception er) {
                singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];
            }
            try {
                singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[10] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[12] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[13] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[3] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[14] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[15] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[2] + "|" + (allOffersArray.get(i)).split(Pattern.quote("|"))[11];

            } catch (Exception er) {
                singleOfferString = (allOffersArray.get(i)).split(Pattern.quote("|"))[8];

            }

//            markerManager.getCollection("1").addMarker(new MarkerOptions()
//                    .title((((allOffersArray.get(i)).split(Pattern.quote("|"))[2])))
//                    .position(new LatLng(Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[7])), Double.parseDouble(((allOffersArray.get(i)).split(Pattern.quote("|"))[6]))))
//                    .snippet(singleOfferString)
//                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon)));

            final String finalSingleOfferString = singleOfferString;
            final int finalI = i;
            markerManager.getCollection("1").setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker marker) {

                    ScrollView scroll = (ScrollView) findViewById(R.id.ofsdScroll);

                    scroll.fullScroll(View.FOCUS_DOWN);

                    System.out.println(marker.getSnippet());
                    System.out.println(allOffersArray.get(finalI));
                    TextView tv = (TextView) findViewById(R.id.ofsdRetailerName);
                    tv.setText(marker.getSnippet().split(Pattern.quote("|"))[8]);
                    TextView tvr = (TextView) findViewById(R.id.retailerNamesy);
                    tvr.setText(finalSingleOfferString.split(Pattern.quote("|"))[0]);

                    final FrameLayout lin = (FrameLayout) findViewById(R.id.multiOffer1);
                    FrameLayout lin2 = (FrameLayout) findViewById(R.id.ofsdCoupOffer2);
                    FrameLayout lin3 = (FrameLayout) findViewById(R.id.ofsdCoupOffer3);


                    lin.setVisibility(FrameLayout.VISIBLE);
                    lin2.setVisibility(FrameLayout.VISIBLE);
                    lin3.setVisibility(FrameLayout.VISIBLE);
                    System.out.println(allOffersArray.get(0).split(Pattern.quote("|"))[11]);
//                    System.out.println(marker.getSnippet().split(Pattern.quote("|"))[2]);


                    ImageView starIcon = (ImageView) findViewById(R.id.ofsdStarsOverlay);
                    if (marker.getSnippet().split(Pattern.quote("|"))[9].equals("1")) {
                        starIcon.setImageResource(R.drawable.starone);
                        System.out.println("goo");

                    }
                    if (marker.getSnippet().split(Pattern.quote("|"))[9].equals("2")) {
                        starIcon.setImageResource(R.drawable.startwo);
                        System.out.println("goo");

                    }
                    if (marker.getSnippet().split(Pattern.quote("|"))[9].equals("3")) {
                        starIcon.setImageResource(R.drawable.starthree);
                        System.out.println("goo");

                    }
                    if (marker.getSnippet().split(Pattern.quote("|"))[9].equals("4")) {
                        starIcon.setImageResource(R.drawable.starfour);
                        System.out.println("goo");

                    }
                    if (marker.getSnippet().split(Pattern.quote("|"))[9].equals("5")) {
                        starIcon.setImageResource(R.drawable.starfive);
                        System.out.println("goo");

                    }

                    ImageView catIcon = (ImageView) findViewById(R.id.ofsdIconImage);
                    if (allOffersArray.get(finalI).split(Pattern.quote("|"))[8].equals("drinks")) {
                        catIcon.setImageResource(R.drawable.dealdrinks);
                        System.out.println("GAAAAAA");

                    }
                    if (allOffersArray.get(finalI).split(Pattern.quote("|"))[8].equals("attractions")) {
                        catIcon.setImageResource(R.drawable.dealactivities);
                        System.out.println("GAAAAAA");

                    }
                    if (allOffersArray.get(finalI).split(Pattern.quote("|"))[8].equals("accommodation")) {
                        catIcon.setImageResource(R.drawable.dealaccom);
                        System.out.println("GAAAAAA");

                    }
                    if (allOffersArray.get(finalI).split(Pattern.quote("|"))[8].equals("food/dining")) {
                        catIcon.setImageResource(R.drawable.dealhospitality);
                        System.out.println("GAAAAAA");
                    }
                    if (allOffersArray.get(finalI).split(Pattern.quote("|"))[8].equals("movies")) {
                        catIcon.setImageResource(R.drawable.dealentertainment);
                        System.out.println("GAAAAAA");
                    }
                    if (allOffersArray.get(finalI).split(Pattern.quote("|"))[8].equals("shopping")) {
                        catIcon.setImageResource(R.drawable.dealshopping);
                        System.out.println("GAAAAAA");
                    }

                    try {
                        startTimers(allOffersArray.get(finalI).split(Pattern.quote("|"))[5], finalSingleOfferString.split(Pattern.quote("|"))[6], finalSingleOfferString.split(Pattern.quote("|"))[7]);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

                    return true;

                }
            });


            mMap.setOnMarkerClickListener(markerManager);

        }
        System.out.println("off cat just printed");

        System.out.println(allOffersArray.size());
        System.out.println(allOffersArray.size());

        System.out.println("ENDTIME PRINTED");

    }
    public void callBackDataFromAsyncTask(String address) {
        System.out.println(address);
    }
    private void startTimers(String offEndTime, String offEndTime2, String offEndTime3) throws ParseException {
        final TextView textCountdown1 = (TextView) findViewById(R.id.countdown);
        final TextView textCountdown2 = (TextView) findViewById(R.id.countdown2);
        final TextView textCountdown3 = (TextView) findViewById(R.id.countdown3);
        textCountdown1.setText("");
        textCountdown2.setText("");
        textCountdown3.setText("");
        if (timer != null) {
            timer.cancel();
            textCountdown1.setTextColor(Color.parseColor("#ffffff"));
            textCountdown1.clearAnimation();
        }
        if (timer2 != null) {
            timer2.cancel();
            textCountdown2.setTextColor(Color.parseColor("#ffffff"));
            textCountdown2.clearAnimation();
        }
        if (timer3 != null) {
            timer.cancel();
            textCountdown3.setTextColor(Color.parseColor("#ffffff"));
            textCountdown3.clearAnimation();
        }
//        System.out.println((((allOffersArray.get(0)).split(Pattern.quote("|"))[3])));
//        String offerTime = (((allOffersArray.get(0)).split(Pattern.quote("|"))[3]));

//        offerTime = offerTime.substring(0,8);
        System.out.println(offEndTime);
        System.out.println(offEndTime2);
        System.out.println(offEndTime3);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat sde = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));
        sde.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+13:00"));
//        System.out.println(sdf.format(cal));
//        System.out.println(dateFormat);
//        System.out.println(dateFormat.format(cal));
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime());

//        final ImageView timerBar = (ImageView) findViewById(R.id.iconImageBg2);

        System.out.println(offEndTime);
        Date date = sde.parse(offEndTime);
        long epoch = date.getTime();
        System.out.println(epoch); // 1055545912454

        Date date2 = sde.parse(offEndTime);
        long epoch2 = date2.getTime();
        System.out.println(epoch2); // 1055545912454

        Date date3 = sde.parse(timeStamp);
        long epoch3 = date3.getTime();
        System.out.println(epoch3); // 1055545912454

        epochDif = epoch - epoch3;
        System.out.println(epochDif);

        Date dater = new Date(epoch3);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(dater);

        Date dater2 = new Date(epoch);
        DateFormat formatter2 = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted2 = formatter2.format(dater2);


        System.out.println(dateFormatted);
        System.out.println(dateFormatted2);

        int grey = R.drawable.greycircl;
        int orange = R.drawable.orangecircl;
        int green = R.drawable.greencircl;

//        timerBar.setImageResource(R.drawable.threequartertime);
        semiCircleProgressBarView = new SemiCircleProgressBarView(MapsActivity.this, orange);

        semiCircleProgressBarView = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc1);
        semiCircleProgressBarView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        semiCircleProgressBarView.setClipping(100);
        long timerBarValue = epochDif / 60000;

        if (epochDif == 0 || epochDif < 0) {
            String colourBar = "grey";
            timerBarValue = 0;
            semiCircleProgressBarView.setBitMap(colourBar);
        } else if (epochDif < 900000) {
            String colourBar = "orange";
            semiCircleProgressBarView.setBitMap(colourBar);
        } else if (epochDif > 900000) {
            String colourBar = "green";
            semiCircleProgressBarView.setBitMap(colourBar);
        }
        semiCircleProgressBarView.setClipping(timerBarValue);
        multiOfferProgressBar1.setProgress((int) timerBarValue);


        final long finalTimerBarValue = timerBarValue;
        final long finalTimerBarValue1 = timerBarValue;
        timer = new CountDownTimer(epochDif, 1000) {

            public void onTick(long millisUntilFinished) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                long x = epochDif / 60000;

//here you can have your logic to set text to edittext
                textCountdown1.setText("" + String.format(String.valueOf(FORMAT),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                long modulo = millisUntilFinished % 60000;
                int length = String.valueOf(modulo).length();

                if (length == 3) {

                    System.out.println(dateFormat.format(date));
                    multiOfferProgressBar1.setProgress((int) finalTimerBarValue);

                    System.out.println(millisUntilFinished);
                    if (x == 0) {
                        String colourBar = "grey";
                        semiCircleProgressBarView.setBitMap(colourBar);
                    } else {
                        x--;
                        if (millisUntilFinished < 360000) {
                            Animation anim = new AlphaAnimation(0.0f, 1.0f);
                            anim.setDuration(200); //You can manage the time of the blink with this parameter
                            anim.setStartOffset(20);
                            anim.setRepeatMode(Animation.REVERSE);
                            anim.setRepeatCount(Animation.INFINITE);
                            textCountdown1.startAnimation(anim);
                            textCountdown1.setTextColor(Color.parseColor("#ff0000"));
                            String colourBar = "orange";
                            semiCircleProgressBarView.setBitMap(colourBar);

                        } else if (millisUntilFinished < 900000) {
                            String colourBar = "orange";
                            semiCircleProgressBarView.setBitMap(colourBar);
                            multiOfferProgressBar1.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));


//                    timerBar.setImageResource(R.drawable.quartertime);

                        }
                        if (millisUntilFinished > 900000) {
                            multiOfferProgressBar1.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

                        }


                    }

                    // 1,200,000 millis is 20 minutes so display three quarter bar


                    // 300,000 millis is 5 minutes so display one quarter bar

                    semiCircleProgressBarView.setClipping(x);


                }
            }

            public void onFinish() {
                textCountdown1.clearAnimation();
//                timerBar.setImageResource(R.drawable.notime);

                textCountdown1.setTextColor(Color.parseColor("#ff0000"));
                textCountdown1.setText("Offer has ended");
                String colourBar = "grey";
                semiCircleProgressBarView.setBitMap(colourBar);

            }

        }.start();

        if (offEndTime2 != "offer 2 empty") {
            System.out.println("OFFER 2 AINT EMPTY");


            System.out.println(offEndTime2);
            Date dateOffr2 = sde.parse(offEndTime2);
            long epochOffr2 = dateOffr2.getTime();
            System.out.println(epochOffr2); // 1055545912454

            Date date2Offr2 = sde.parse(offEndTime2);
            long epoch2Offr2 = date2Offr2.getTime();
            System.out.println(epoch2Offr2); // 1055545912454

            Date date3Offr2 = sde.parse(timeStamp);
            long epoch3Offr2 = date3Offr2.getTime();
            System.out.println(epoch3Offr2); // 1055545912454

            final long epochDifOffr2 = epochOffr2 - epoch3Offr2;
            System.out.println(epochDifOffr2);

            Date daterOffr2 = new Date(epochDif);
            DateFormat formatterOffr2 = new SimpleDateFormat("HH:mm:ss:SSS");
            String dateFormattedOffr2 = formatterOffr2.format(daterOffr2);
            System.out.println(dateFormattedOffr2);
            final long timerBarValue2 = epochDifOffr2 / 60000;


//        timerBar.setImageResource(R.drawable.threequartertime);
            semiCircleProgressBarView2 = new SemiCircleProgressBarView(MapsActivity.this, orange);

            semiCircleProgressBarView2 = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc2);
            semiCircleProgressBarView2.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

            semiCircleProgressBarView2.setClipping(100);
            long timerBarValue4 = epochDifOffr2 / 60000;

            if (epochDifOffr2 == 0 || epochDif < 0) {
                String colourBar = "grey";
                timerBarValue = 0;
                semiCircleProgressBarView2.setBitMap(colourBar);
            } else if (epochDifOffr2 < 900000) {
                String colourBar = "orange";
                semiCircleProgressBarView2.setBitMap(colourBar);
            } else if (epochDifOffr2 > 900000) {
                String colourBar = "green";
                semiCircleProgressBarView2.setBitMap(colourBar);
            }
            semiCircleProgressBarView2.setClipping(timerBarValue2);


            multiOfferProgressBar2.setProgress((int) timerBarValue2);
            timer2 = new CountDownTimer(epochDifOffr2, 1000) {

                public void onTick(long millisUntilFinished) {

                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    long x = epochDifOffr2 / 60000;

//here you can have your logic to set text to edittext
                    textCountdown2.setText("" + String.format(String.valueOf(FORMAT),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    long modulo = millisUntilFinished % 60000;
                    int length = String.valueOf(modulo).length();

                    if (length == 3) {

                        System.out.println(dateFormat.format(date));
                        multiOfferProgressBar2.setProgress((int) timerBarValue2);

                        System.out.println(millisUntilFinished);
                        if (x == 0) {
                            String colourBar = "grey";
                            semiCircleProgressBarView2.setBitMap(colourBar);
                        } else {
                            x--;
                            if (millisUntilFinished < 360000) {
                                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                                anim.setDuration(200); //You can manage the time of the blink with this parameter
                                anim.setStartOffset(20);
                                anim.setRepeatMode(Animation.REVERSE);
                                anim.setRepeatCount(Animation.INFINITE);
                                textCountdown2.startAnimation(anim);
                                textCountdown2.setTextColor(Color.parseColor("#ff0000"));
                                String colourBar = "orange";
                                semiCircleProgressBarView2.setBitMap(colourBar);
                            } else if (millisUntilFinished < 900000) {
                                String colourBar = "orange";
                                semiCircleProgressBarView2.setBitMap(colourBar);
                                multiOfferProgressBar2.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));
                                ;

//                    timerBar.setImageResource(R.drawable.quartertime);

                            }


                        }


                    }

                    // 1,200,000 millis is 20 minutes so display three quarter bar


                    // 300,000 millis is 5 minutes so display one quarter bar

                    semiCircleProgressBarView2.setClipping(x);


                }


                public void onFinish() {
                    textCountdown2.clearAnimation();
                    String colourBar = "grey";
                    semiCircleProgressBarView2.setClipping(0);
                    semiCircleProgressBarView2.setBitMap(colourBar);
                    textCountdown2.setTextColor(Color.parseColor("#ff0000"));
                    textCountdown2.setText("Offer has ended");
                }

            }.start();
        }

        if (offEndTime3 != "offer 3 empty") {

            System.out.println(offEndTime3);
            Date dateOffr3 = sde.parse(offEndTime3);
            long epochOffr3 = dateOffr3.getTime();
            System.out.println(epochOffr3); // 1055545912454

            Date date2Offr3 = sde.parse(offEndTime3);
            long epoch2Offr3 = date2Offr3.getTime();
            System.out.println(epoch2Offr3); // 1055545912454

            Date date3Offr3 = sde.parse(timeStamp);
            long epoch3Offr3 = date3Offr3.getTime();
            System.out.println(epoch3Offr3); // 1055545912454

            final long epochDifOffr3 = epochOffr3 - epoch3Offr3;
            System.out.println(epochDifOffr3);

            Date daterOffr3 = new Date(epochDif);
            DateFormat formatterOffr3 = new SimpleDateFormat("HH:mm:ss:SSS");
            String dateFormattedOffr3 = formatterOffr3.format(daterOffr3);
            System.out.println(dateFormattedOffr3);
            final long timerBarValue3 = epochDifOffr3 / 60000;


//        timerBar.setImageResource(R.drawable.threequartertime);
            semiCircleProgressBarView3 = new SemiCircleProgressBarView(MapsActivity.this, orange);

            semiCircleProgressBarView3 = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc3);
            semiCircleProgressBarView3.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

            semiCircleProgressBarView3.setClipping(100);
            long timerBarValue4 = epochDifOffr3 / 60000;

            if (epochDifOffr3 == 0 || epochDif < 0) {
                String colourBar = "grey";
                timerBarValue = 0;
                semiCircleProgressBarView3.setBitMap(colourBar);
            } else if (epochDifOffr3 < 900000) {
                String colourBar = "orange";
                semiCircleProgressBarView3.setBitMap(colourBar);
            } else if (epochDifOffr3 > 900000) {
                String colourBar = "green";
                semiCircleProgressBarView3.setBitMap(colourBar);
            }
            semiCircleProgressBarView3.setClipping(timerBarValue3);


            multiOfferProgressBar3.setProgress((int) timerBarValue3);

            timer3 = new CountDownTimer(epochDifOffr3, 1000) {

                public void onTick(long millisUntilFinished) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    long x = epochDifOffr3 / 60000;

//here you can have your logic to set text to edittext
                    textCountdown3.setText("" + String.format(String.valueOf(FORMAT),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    long modulo = millisUntilFinished % 60000;
                    int length = String.valueOf(modulo).length();

                    if (length == 3) {

                        System.out.println(dateFormat.format(date));
                        multiOfferProgressBar3.setProgress((int) timerBarValue3);

                        System.out.println(millisUntilFinished);
                        if (x == 0) {
                            String colourBar = "grey";
                            semiCircleProgressBarView3.setBitMap(colourBar);
                        } else {
                            x--;
                            if (millisUntilFinished < 360000) {
                                Animation anim = new AlphaAnimation(0.0f, 1.0f);
                                anim.setDuration(200); //You can manage the time of the blink with this parameter
                                anim.setStartOffset(20);
                                anim.setRepeatMode(Animation.REVERSE);
                                anim.setRepeatCount(Animation.INFINITE);
                                textCountdown3.startAnimation(anim);
                                textCountdown3.setTextColor(Color.parseColor("#ff0000"));
                                String colourBar = "orange";
                                semiCircleProgressBarView3.setBitMap(colourBar);
                            } else if (millisUntilFinished < 900000) {
                                String colourBar = "orange";
                                multiOfferProgressBar3.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));
                                ;
                                semiCircleProgressBarView3.setBitMap(colourBar);

//                    timerBar.setImageResource(R.drawable.quartertime);

                            }


                        }

                        // 1,200,000 millis is 20 minutes so display three quarter bar


                        // 300,000 millis is 5 minutes so display one quarter bar

                        semiCircleProgressBarView3.setClipping(x);


                    }

                }

                public void onFinish() {
                    textCountdown3.clearAnimation();
                    String colourBar = "grey";
                    semiCircleProgressBarView3.setBitMap(colourBar);
                    textCountdown3.setTextColor(Color.parseColor("#ff0000"));
                    textCountdown3.setText("Offer has ended");
                }

            }.start();
        }
    }

    // Setup the map fragment
    private boolean initMap() {
        if (mMap == null) {

            SupportMapFragment mapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
            mapFragment.getMapAsync(this);

            System.out.println("init ran");

        }
        return (mMap != null);
    }


    public class MapFragment extends SupportMapFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View mapView = super.onCreateView(inflater, container, savedInstanceState);
            return mapView;
        }
    }

    //    public static GeoHash withCharacterPrecision(double latitude, double longitude, int numberOfCharacters) {
//        if (numberOfCharacters > MAX_CHARACTER_PRECISION) {
//            throw new IllegalArgumentException("A geohash can only be " + MAX_CHARACTER_PRECISION + " character long.");
//        }
//        int desiredPrecision = (numberOfCharacters * 5 <= 60) ? numberOfCharacters * 5 : 60;
////        return new GeoHash(latitude, longitude, desiredPrecision);
//        return GeoHash;
//    }
    public static String encode(double latitude, double longitude) {
        double[] latInterval = {-90.0, 90.0};
        double[] lngInterval = {-180.0, 180.0};

        final StringBuilder geohash = new StringBuilder();
        boolean isEven = true;

        int bit = 0;
        int ch = 0;

        while (geohash.length() < MAX_CHARACTER_PRECISION) {
            double mid = 0.0;
            if (isEven) {
                mid = (lngInterval[0] + lngInterval[1]) / 2D;
                if (longitude > mid) {
                    ch |= BITS[bit];
                    lngInterval[0] = mid;
                } else {
                    lngInterval[1] = mid;
                }
            } else {
                mid = (latInterval[0] + latInterval[1]) / 2D;
                if (latitude > mid) {
                    ch |= BITS[bit];
                    latInterval[0] = mid;
                } else {
                    latInterval[1] = mid;
                }
            }

            isEven = !isEven;

            if (bit < 4) {
                bit++;
            } else {
                geohash.append(BASE_32[ch]);
                bit = 0;
                ch = 0;
            }
        }

        return geohash.toString();
    }

    public void openQR(View v) {
        Bundle ePzl = new Bundle();
        Intent intent = new Intent(MapsActivity.this, couponActivity.class);
        overridePendingTransition(R.anim.righter, R.anim.righter);
        TextView retailerName = (TextView) findViewById(R.id.ofsdRetailerName);
        TextView singleCouponOfferText1 = (TextView) findViewById(R.id.singleCouponOfferText1);
        TextView singleCouponOfferText2 = (TextView) findViewById(R.id.singleCouponOfferText2);
        TextView singleCouponOfferText3 = (TextView) findViewById(R.id.singleCouponOfferText3);
        ePzl.putString("retailer", retailerName.getText().toString());
        ePzl.putString("offerDesc1", retailerName.getText().toString());
        System.out.println(singleCouponOfferText1.getText().toString());
        intent.putExtra("retailer", retailerName.getText().toString());
        intent.putExtra("offerDesc1", singleCouponOfferText1.getText().toString());
//    intent.putExtra("offerDesc2",tvr.getText().toString());
//    intent.putExtra("offerDesc3",tvr.getText().toString());

        startActivity(intent);
    }


    //long[] arOriginal ={10,20,0};
    private void initTestMap() throws IOException, ParseException {
// Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
        mGoogleApiClient.connect();


        locationListener = new LocationListener() {
            Boolean cameraSet = false;
            Boolean circleExists = false;
            Circle myCircle;

            @Override
            public void onLocationChanged(Location location) {
                System.out.println("onLocFired: 1st onCreate");
                // Each time the location changes, get the users coordinates and move the camera
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                timeToRunHash++;
                int count = 0;
                ArrayList nearbyRetNames = new ArrayList();
                final ArrayList offerDesc1 = new ArrayList();
                ArrayList offerDesc2 = new ArrayList();
                ArrayList offerDesc3 = new ArrayList();
                ArrayList nearHashes = getListViewHashes(latLng);
                if (timeToRunHash % 5 == 0 || timeToRunHash == 0 || timeToRunHash == 1) {
                    for (int i = 0; i < allOffersArray.size(); i++) {
                        String retailerHash = allOffersArray.get(i).split(Pattern.quote("|"))[4].substring(3, 10);

                        if (retailerHash.equals(nearHashes.get(0)) || retailerHash.equals(nearHashes.get(1))
                                || retailerHash.equals(nearHashes.get(2)) ||
                                retailerHash.equals(nearHashes.get(3)) ||
                                retailerHash.equals(nearHashes.get(4)) ||
                                retailerHash.equals(nearHashes.get(5)) ||
                                retailerHash.equals(nearHashes.get(6)) ||
                                retailerHash.equals(nearHashes.get(7)) ||
                                retailerHash.equals(nearHashes.get(8))) {
                            System.out.println(allOffersArray.get(i));
                            System.out.println(allOffersArray.size());

                            nearbyRetNames.add(count, allOffersArray.get(i).split(Pattern.quote("|"))[2]);
                            offerDesc1.add(count, allOffersArray.get(i).split(Pattern.quote("|"))[8]);
                            offerDesc2.add(count, allOffersArray.get(i).split(Pattern.quote("|"))[10]);
                            offerDesc3.add(count, allOffersArray.get(i).split(Pattern.quote("|"))[13]);

                            count++;

                        }
                    }

                    final FrameLayout nearbyOffer1 = (FrameLayout) findViewById(R.id.nearbyOffer1);
                    final FrameLayout nearbyOffer2 = (FrameLayout) findViewById(R.id.nearbyOffer2);
                    final FrameLayout nearbyOffer3 = (FrameLayout) findViewById(R.id.nearbyOffer3);
                    final FrameLayout nearbyOffer4 = (FrameLayout) findViewById(R.id.nearbyOffer4);
                    final FrameLayout nearbyOffer5 = (FrameLayout) findViewById(R.id.nearbyOffer5);
                    final FrameLayout nearbyOffer6 = (FrameLayout) findViewById(R.id.nearbyOffer6);
                    final FrameLayout nearbyOffer7 = (FrameLayout) findViewById(R.id.nearbyOffer7);
                    final FrameLayout nearbyOffer8 = (FrameLayout) findViewById(R.id.nearbyOffer8);

                    final FrameLayout multiOffer2 = (FrameLayout) findViewById(R.id.multiOffer2);

                    final FrameLayout multiOffer3 = (FrameLayout) findViewById(R.id.multiOffer3);
                    final FrameLayout nodCoupOffer1 = (FrameLayout) findViewById(R.id.nodCoupOffer1);
                    final FrameLayout nodCoupOffer2 = (FrameLayout) findViewById(R.id.nodCoupOffer2);
                    final FrameLayout nodCoupOffer3 = (FrameLayout) findViewById(R.id.nodCoupOffer3);

                    final ImageView ofsdSemiCircBg = (ImageView) findViewById(R.id.ofsdSemiCircBg);
                    final SemiCircleProgressBarView semiCirc = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc1);
                    final SemiCircleProgressBarView semiCirc2 = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc2);
                    final SemiCircleProgressBarView semiCirc3 = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc3);
                    final ImageView starsOverlay = (ImageView) findViewById(R.id.ofsdStarsOverlay);

                    ImageView starIcon = (ImageView) findViewById(R.id.ofsdStarsOverlay);
                    final ImageView multiOffersStars = (ImageView) findViewById(R.id.multiOffersStars);
                    final ImageView multiOffersStars2 = (ImageView) findViewById(R.id.multiOffersStars2);
                    final ImageView multiOffersStars3 = (ImageView) findViewById(R.id.multiOffersStars3);

                    final TextView nodSingleCouponOffer1Text = (TextView) findViewById(R.id.nodSingleCouponOffer1Text);
                    final TextView nodSingleCouponOffer2Text = (TextView) findViewById(R.id.nodSingleCouponOffer2Text);
                    final TextView nodSingleCouponOffer3Text = (TextView) findViewById(R.id.nodSingleCouponOffer3Text);

                    final TextView singleCouponOfferText1 = (TextView) findViewById(R.id.singleCouponOfferText1);
                    final TextView singleCouponOfferText2 = (TextView) findViewById(R.id.singleCouponOfferText2);
                    final TextView singleCouponOfferText3 = (TextView) findViewById(R.id.singleCouponOfferText3);

                    nodCoupOffer1.setVisibility(View.GONE);
                    nodCoupOffer2.setVisibility(View.GONE);
                    nodCoupOffer3.setVisibility(View.GONE);
//                    singleCouponOfferText1.setVisibility(View.GONE);
//                    singleCouponOfferText2.setVisibility(View.GONE);
//                    singleCouponOfferText3.setVisibility(View.GONE);
                    nodSingleCouponOffer3Text.setVisibility(View.GONE);
                    nodSingleCouponOffer2Text.setVisibility(View.GONE);
                    nodSingleCouponOffer1Text.setVisibility(View.GONE);
                    try {
                        nearbyOffer1RetName.setText(nearbyRetNames.get(0).toString());

                        nearbyOffer1.setVisibility(View.VISIBLE);
                        nodSingleCouponOffer1Text.setText(offerDesc1.get(0).toString());

                        nearbyOffer1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                ofsdSemiCircBg.setVisibility(View.VISIBLE);
                                semiCirc.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);

//
                                semiCirc2.setVisibility(View.GONE);
                                semiCirc3.setVisibility(View.GONE);

                                nearbyOffer1.setVisibility(View.GONE);
                                nearbyOffer2.setVisibility(View.GONE);
                                nearbyOffer3.setVisibility(View.GONE);
                                nearbyOffer4.setVisibility(View.GONE);
                                nearbyOffer5.setVisibility(View.GONE);
                                nearbyOffer6.setVisibility(View.GONE);
                                nearbyOffer7.setVisibility(View.GONE);
                                nearbyOffer8.setVisibility(View.GONE);
                                nodCoupOffer1.setVisibility(View.VISIBLE);
                                nodSingleCouponOffer1Text.setVisibility(View.VISIBLE);
                                nodCoupOffer2.setVisibility(View.GONE);
                                nodCoupOffer3.setVisibility(View.GONE);
                                System.out.println("multiOffer2");
                                System.out.println("Nearby 1");


                            }
                        });
                    } catch (Exception e) {
                        System.out.println(e);
                        nearbyOffer1.setVisibility(View.GONE);
                    }
                    try {
                        nearbyOffer2RetName.setText(nearbyRetNames.get(1).toString());
                        nearbyOffer2.setVisibility(View.VISIBLE);
                        singleCouponOfferText2.setText(offerDesc1.get(0).toString());

                        nearbyOffer2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                ofsdSemiCircBg.setVisibility(View.VISIBLE);
                                semiCirc.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);

System.out.println("Nearby 2");
                                semiCirc2.setVisibility(View.GONE);
                                semiCirc3.setVisibility(View.GONE);

                                nearbyOffer1.setVisibility(View.GONE);
                                nearbyOffer2.setVisibility(View.GONE);
                                nearbyOffer3.setVisibility(View.GONE);
                                nearbyOffer4.setVisibility(View.GONE);
                                nearbyOffer5.setVisibility(View.GONE);
                                nearbyOffer6.setVisibility(View.GONE);
                                nearbyOffer7.setVisibility(View.GONE);
                                nearbyOffer8.setVisibility(View.GONE);
                                nodCoupOffer1.setVisibility(View.GONE);
                                nodSingleCouponOffer2Text.setVisibility(View.VISIBLE);
                                nodCoupOffer2.setVisibility(View.VISIBLE);
                                nodCoupOffer3.setVisibility(View.GONE);
                                System.out.println("multiOffer2");


                            }
                        });
                    } catch (Exception e) {
                        System.out.println(e);
                        nearbyOffer2.setVisibility(View.GONE);
                    }
                    try {
                        nearbyOffer3RetName.setText(nearbyRetNames.get(2).toString());
                        nearbyOffer3.setVisibility(View.VISIBLE);
                        singleCouponOfferText3.setText(offerDesc1.get(0).toString());

                        nearbyOffer3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                ofsdSemiCircBg.setVisibility(View.VISIBLE);
                                semiCirc.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);

//
                                semiCirc2.setVisibility(View.GONE);
                                semiCirc3.setVisibility(View.GONE);

                                nearbyOffer1.setVisibility(View.GONE);
                                nearbyOffer2.setVisibility(View.GONE);
                                nearbyOffer3.setVisibility(View.GONE);
                                nearbyOffer4.setVisibility(View.GONE);
                                nearbyOffer5.setVisibility(View.GONE);
                                nearbyOffer6.setVisibility(View.GONE);
                                nearbyOffer7.setVisibility(View.GONE);
                                nearbyOffer8.setVisibility(View.GONE);
                                nodCoupOffer1.setVisibility(View.GONE);
                                nodSingleCouponOffer3Text.setVisibility(View.VISIBLE);
                                nodCoupOffer2.setVisibility(View.GONE);
                                nodCoupOffer3.setVisibility(View.VISIBLE);
                                System.out.println("Nearby 3");


                            }
                        });

                    } catch (Exception e) {
                        System.out.println(e);
                        nearbyOffer3.setVisibility(View.GONE);
                    }
                    try {
                        nearbyOffer4RetName.setText(nearbyRetNames.get(3).toString());
                        nearbyOffer4.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        System.out.println(e);
                        nearbyOffer4.setVisibility(View.GONE);
                    }
                    try {
                        nearbyOffer5RetName.setText(nearbyRetNames.get(4).toString());
                        nearbyOffer5.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        System.out.println(e);
                        nearbyOffer5.setVisibility(View.GONE);
                    }
                    try {
                        nearbyOffer6RetName.setText(nearbyRetNames.get(5).toString());
                        nearbyOffer6.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        System.out.println(e);
                        nearbyOffer6.setVisibility(View.GONE);
                    }
                    try {
                        nearbyOffer7RetName.setText(nearbyRetNames.get(6).toString());
                        nearbyOffer7.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        System.out.println(e);
                        nearbyOffer7.setVisibility(View.GONE);
                    }
                    try {
                        nearbyOffer8RetName.setText(nearbyRetNames.get(7).toString());
                        nearbyOffer8.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        System.out.println(e);
                        nearbyOffer8.setVisibility(View.GONE);
                    }

                    System.out.println(allOffersArray.get(2).split(Pattern.quote("|"))[4].substring(3, 10));
                    System.out.println(allOffersArray.get(3));
                    System.out.println(allOffersArray.get(4));
                    nearbyRetNames.clear();
                    offerDesc1.clear();
                    offerDesc2.clear();
                    offerDesc3.clear();

                }

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
                // if m
                if (myCircle != null) {
                    myCircle.remove();
                }

                CircleOptions circleOptions = new CircleOptions()
                        .center(latLng)   //set center
                        .radius(70)   //set radius in meters
                        .fillColor(0x551FBED6)  //default
                        .strokeColor(0x10000000)
                        .strokeWidth(5);

                myCircle = mMap.addCircle(circleOptions);

                if (cameraSet == false) {
                    mMap.moveCamera(cameraUpdate);
                }
                cameraSet = true;


            }

            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            public void onProviderEnabled(String s) {
            }

            public void onProviderDisabled(String s) {
                // If a user device has their GPS or network disabled. Start the
                // android setting screen intent for the user to enable it
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

        };

        // Call the location service of the LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
        System.out.println("initLoc about to fire");


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

        // Show the option to go to your current location on the map
        mMap.setMyLocationEnabled(true);

        // Populate map with markers and their icons, text, titles etc.
        setupOfferMapIcons();


    }

    private void sound() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);

    }

    private void hideSoftKeyboard(View v) {
        InputMethodManager imm =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromInputMethod(v.getWindowToken(), 0);

    }

    @Override
    public void onConnected(Bundle connectionHint) {

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main2 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("onStart");


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    private void requestData(String uri) {
        System.out.println("req data firing");
        getOffers task = new getOffers();
        task.execute(uri);

    }


    protected void updateDisplay(String message) {
        System.out.println(message);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    public void couponsDashboard() {
        System.out.println("hi");
    }
//    public class LocationActivity extends Activity {
//
//        private TextView locationText;
//        private TextView addressText;
//        private GoogleMap map;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_maps);
//
//            locationText = (TextView) findViewById(R.id.location);
//            addressText = (TextView) findViewById(R.id.address);
//
//            //replace GOOGLE MAP fragment in this Activity
//            replaceMapFragment();
//        }
//
//        private void replaceMapFragment() {
//            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
//                    .getMap();
//
//            // Enable Zoom
//            map.getUiSettings().setZoomGesturesEnabled(true);
//
//            //set Map TYPE
//            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//
//            //enable Current location Button
//            map.setMyLocationEnabled(true);
//
//            //set "listener" for changing my location
//            map.setOnMyLocationChangeListener(myLocationChangeListener());
//        }
//
//        private GoogleMap.OnMyLocationChangeListener myLocationChangeListener() {
//            return new GoogleMap.OnMyLocationChangeListener() {
//                @Override
//                public void onMyLocationChange(Location location) {
//                    LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
//                    double longitude = location.getLongitude();
//                    double latitude = location.getLatitude();
//
//                    Marker marker;
//                    marker = map.addMarker(new MarkerOptions().position(loc));
//                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
//
//                }
//            };
//        }
//
//
//
//    }
}


