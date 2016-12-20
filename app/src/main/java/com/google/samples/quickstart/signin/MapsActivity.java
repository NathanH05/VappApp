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
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Geocoder;
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
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import ch.hsr.geohash.GeoHash;

import static com.example.myfirstapp.R.id.map;
import static com.example.myfirstapp.R.id.retName;
import static com.example.myfirstapp.R.id.retailerNamesy8;
import static com.example.myfirstapp.R.id.retailerNameszs3;
import static com.example.myfirstapp.R.layout.loggedout;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    TextView textX, textY, textZ;
    SensorManager sensorManager;
    Sensor sensor;
    Boolean firstSubmit;
    String cat = "";
    MarkerManager markerManager;
    List<MyTask> tasks;
    TextView retName;
    TextView retName2;
    TextView retName3;
    TextView retName4;
    TextView retName5;
    TextView retName6;
    TextView retName7;
    TextView retName8;

    Collection<Marker> markers;
    GoogleMap mMap;
    Boolean isKeyboardOpen;
    String OfferCat = null;
    String[] innerArrs = new String[15];
    ArrayList<String> widerArrays = new ArrayList<>();
    Boolean visibleCoffeeIcon = true;
    Boolean visibleDrinksIcon = true;
    Boolean visibleFastfoodsIcon = true;
    Boolean visibleMoviesIcon = true;
    Boolean visibleFoodDiningIcon = true;
    Boolean visibleAccomodationIcon = true;
    Boolean visibleAttractionsIcon = true;
    Boolean visibleElectronicsIcon = true;
    Boolean visibleRestaurantIcon = true;
    Boolean visibleGroceriesIcon = true;
    Boolean visibleShoppingIcon = true;
    CountDownTimer timer;
    CountDownTimer timer2;
    CountDownTimer timer3;
    String nd = null;
    long epochDif;
    boolean backpressed = false;
    int timeToRunHash = 0;


    SemiCircleProgressBarView semiCircleProgressBarView;
    SemiCircleProgressBarView semiCircleProgressBarView2;
    SemiCircleProgressBarView semiCircleProgressBarView3;
    private boolean mVisible;
    boolean backStack = false;
    private boolean beverageIsClicked = false;
    private boolean gymIsClicked = false;
    private boolean moviesIsClicked = false;
    private boolean groceriesIsClicked = false;
    private boolean techIsClicked = false;
    private boolean coffeeIsClicked = false;
    private boolean shoppingIsClicked = false;
    private boolean fastfoodsIsClicked = false;
    private boolean foodDiningIsClicked = false;
    private boolean drinksIsClicked = false;
    private boolean attractionsIsClicked = false;
    private boolean accomodationIsClicked = false;
    private static final String FORMAT = "%02d:%02d";
    String dealOffer1Text = "";
    String dealOffer2Text = "";
    String dealOffer3Text = "";
    // Declare a variable for the cluster manager.
    ProgressBar firstBar;
    ProgressBar secondBar;
    ProgressBar thirdBar;
    ProgressBar fourthBar;
    ProgressBar fifthBar;
    ProgressBar sixthBar;
    ProgressBar seventhBar;
    ProgressBar eigththBar;
    ProgressBar ninthBar;
    ProgressBar tenthBar;
    ProgressBar eleventhBar;
    ClusterManager<AppClusterItem> mClusterManager;

    private GoogleApiClient mGoogleApiClient;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private GoogleApiClient client;
    // Declare an instance of the Location Manager and Listener for retrieving the user's current
    // latitude and longitude
    private LocationManager locationManager;
    private LocationListener locationListener;
    View myView;
    ArrayList<String[]> parent = new ArrayList<>();
    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS =1;
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

    public class GameAnimationListener
            implements Animation.AnimationListener {

        public GameAnimationListener(MapsActivity mapsActivity) {
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    //    public Drawable getDimmedDrawable(Drawable drawable) {
//        Resources resources = getContext().getResources();
//        StateListDrawable stateListDrawable = new StateListDrawable();
//        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{
//                drawable,
//                new ColorDrawable(resources.getColor(R.color.translucent_black))
//        });
//        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, layerDrawable);
//        stateListDrawable.addState(new int[]{android.R.attr.state_focused}, layerDrawable);
//        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, layerDrawable);
//        stateListDrawable.addState(new int[]{}, drawable);
//        return stateListDrawable;
//    }
    private static final int MAX_CHARACTER_PRECISION = 12;
    private static final int[] BITS = {16, 8, 4, 2, 1};
    private static final char[] BASE_32 = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private Menu menu;


    public void logout(View view) {
        Intent intent = new Intent(MapsActivity.this, SignInActivity.class);
        startActivity(intent);

    }

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
        System.out.println(userGeoHash);
        System.out.println(north);
        System.out.println(northeast);
        System.out.println(east);
        System.out.println(southeast);
        return nearHashes;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");


        firstSubmit = true;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        tasks = new ArrayList<>();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);


//        ImageView imgFavorite = (ImageView) findViewById(R.id.imgView);
//        imgFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MapsActivity.this,
//                        "The favorite list would appear on clicking this icon",
//                        Toast.LENGTH_LONG).show();
//
//
//                v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
////Your other coding if present
//
//            }
//        });
        // We call the globally defined (above) google api client for our map
        // we dont connect until onStart runs though using client.connect();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        System.out.println("onConn fired");
        // Call the location service of the LocationManager
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (servicesOK()) {

            setContentView(R.layout.activity_maps);



            System.out.println("ready to map");
            retName = (TextView) findViewById(R.id.retName);
            retName2 = (TextView) findViewById(R.id.retName2);
            retName3 = (TextView) findViewById(R.id.retName3);
            retName4 = (TextView) findViewById(R.id.retName4);
            retName5 = (TextView) findViewById(R.id.retName5);
            retName6 = (TextView) findViewById(R.id.retName6);
            retName7 = (TextView) findViewById(R.id.retName7);
            retName8 = (TextView) findViewById(R.id.retName8);


//            String s =  adjacent[1].toString();
//            String str ="";
//            for (int i = 0; i < s.length()/8; i++) {
//
//                int a = Integer.parseInt(s.substring(8*i,(i+1)*8),2);
//                str += (char)(a);
//            }

//            System.out.println(str);

            firstBar = (ProgressBar) findViewById(R.id.firstBar);
            //make the progress bar visible

            firstBar.setVisibility(View.VISIBLE);

            firstBar.setMax(100);
            LinearLayout lino = (LinearLayout) findViewById(R.id.content2dy);

            firstBar.setProgress(10);

            firstBar.setMinimumWidth(lino.getWidth());

            firstBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

            secondBar = (ProgressBar) findViewById(R.id.secondBar);
            //make the progress bar visible

            secondBar.setVisibility(View.VISIBLE);

            secondBar.setMax(100);
            secondBar.setProgress(50);
            LinearLayout lino2 = (LinearLayout) findViewById(R.id.content2dy);

            secondBar.setMinimumWidth(lino2.getWidth());

            secondBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

            thirdBar = (ProgressBar) findViewById(R.id.thirdBar);
            //make the progress bar visible

            thirdBar.setVisibility(View.VISIBLE);

            thirdBar.setMax(100);
            thirdBar.setProgress(50);
            LinearLayout lino3 = (LinearLayout) findViewById(R.id.content2dy);

            thirdBar.setMinimumWidth(lino3.getWidth());

            thirdBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

            fourthBar = (ProgressBar) findViewById(R.id.fourthBar);
            //make the progress bar visible

            fourthBar.setVisibility(View.VISIBLE);

            fourthBar.setMax(100);
            fourthBar.setProgress(50);
            final FrameLayout contentNearby = (FrameLayout) findViewById(R.id.contentNearby);

            fourthBar.setMinimumWidth(lino3.getWidth());

            fourthBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));


            fifthBar = (ProgressBar) findViewById(R.id.fifthBar);
            //make the progress bar visible

            fifthBar.setVisibility(View.VISIBLE);


            final FrameLayout contentNearby2 = (FrameLayout) findViewById(R.id.contentNearby2);

            fifthBar.setMinimumWidth(lino3.getWidth());

            fifthBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            fifthBar.setMax(100);
            fifthBar.setProgress(50);

            sixthBar = (ProgressBar) findViewById(R.id.sixthBar);
            //make the progress bar visible

            sixthBar.setVisibility(View.VISIBLE);


            final FrameLayout contentNearby3 = (FrameLayout) findViewById(R.id.contentNearby3);

            sixthBar.setMinimumWidth(lino3.getWidth());

            sixthBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            sixthBar.setMax(100);
            sixthBar.setProgress(50);


            seventhBar = (ProgressBar) findViewById(R.id.seventhBar);
            //make the progress bar visible

            seventhBar.setVisibility(View.VISIBLE);


            final FrameLayout contentNearby4 = (FrameLayout) findViewById(R.id.contentNearby4);

            seventhBar.setMinimumWidth(lino3.getWidth());

            seventhBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            seventhBar.setMax(100);
            seventhBar.setProgress(50);
            seventhBar = (ProgressBar) findViewById(R.id.seventhBar);
            //make the progress bar visible

            seventhBar.setVisibility(View.VISIBLE);


            final FrameLayout contentNearby5 = (FrameLayout) findViewById(R.id.contentNearby5);
            eigththBar = (ProgressBar) findViewById(R.id.eightthBar);
            ninthBar = (ProgressBar) findViewById(R.id.ninthBar);
            tenthBar = (ProgressBar) findViewById(R.id.tenthBar);
            eleventhBar = (ProgressBar) findViewById(R.id.eleventhBar);

            eigththBar.setMinimumWidth(contentNearby5.getWidth());

            eigththBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            eigththBar.setMax(100);
            eigththBar.setProgress(50);
            eigththBar = (ProgressBar) findViewById(R.id.eightthBar);
            //make the progress bar visible

            ninthBar.setVisibility(View.VISIBLE);


            final FrameLayout contentNearby6 = (FrameLayout) findViewById(R.id.contentNearby6);

            ninthBar.setMinimumWidth(lino3.getWidth());

            ninthBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            ninthBar.setMax(100);
            ninthBar.setProgress(50);
            ninthBar = (ProgressBar) findViewById(R.id.ninthBar);
            //make the progress bar visible

            ninthBar.setVisibility(View.VISIBLE);


            final FrameLayout contentNearby7 = (FrameLayout) findViewById(R.id.contentNearby7);

            tenthBar.setMinimumWidth(lino3.getWidth());

            tenthBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            tenthBar.setMax(100);
            tenthBar.setProgress(50);
            tenthBar = (ProgressBar) findViewById(R.id.tenthBar);
            //make the progress bar visible

            tenthBar.setVisibility(View.VISIBLE);


            final FrameLayout contentNearby8 = (FrameLayout) findViewById(R.id.contentNearby8);

            eleventhBar.setMinimumWidth(contentNearby8.getWidth());

            eleventhBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
            eleventhBar.setMax(100);
            eleventhBar.setProgress(50);

            Drawable grey = getResources().getDrawable(R.drawable.greycircl);
            Drawable orange = getResources().getDrawable(R.drawable.orangecircl);
            Drawable green = getResources().getDrawable(R.drawable.greencircl);
            eleventhBar.setVisibility(View.VISIBLE);

            final SearchView sv = (SearchView) findViewById(R.id.sv);
            sv.setQueryHint(Html.fromHtml("<font color = #000000 family = FF CLAN>" + "WHERE TO..." + "</font>"));
            int id = sv.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
            TextView textView = (TextView) sv.findViewById(id);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(15);


            firstSubmit = true;
            ImageView toolbar_title = (ImageView) findViewById(R.id.toolbar_title);
            toolbar_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    searchFunc(v);
//                    armode(v);
                }
            });

            ImageView closeButtn = (ImageView) findViewById(R.id.closeButtn);
            closeButtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                            R.anim.bottom_down);
                    ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer1);
                    hiddenPanel.startAnimation(bottomDown);
                    hiddenPanel.setVisibility(View.INVISIBLE);

                }
            });
            ImageView closeButtn2 = (ImageView) findViewById(R.id.closeButtn2);
            closeButtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                            R.anim.bottom_down);
                    ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer3);
                    hiddenPanel.startAnimation(bottomDown);
                    hiddenPanel.setVisibility(View.INVISIBLE);

                }
            });


            final ImageView imgFavorite = (ImageView) findViewById(R.id.drinks);
            imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();

                    if (drinksIsClicked == false) {
                        imgFavorite.setBackgroundResource(R.drawable.drinks);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarDrinks);
//                        goldBar.setVisibility(View.INVISIBLE);
                        drinksIsClicked = true;
                        hideDrinks();

                    } else {
                        imgFavorite.setBackgroundResource(R.drawable.drinksgrey);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarDrinks);
//                        goldBar.setVisibility(View.VISIBLE);
                        drinksIsClicked = false;
                        hideDrinks();

                    }


                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
//Your other coding if present

                }
            });

            final ImageView imgFavorite2 = (ImageView) findViewById(R.id.shopping);
            imgFavorite2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mp.start();

                    if (shoppingIsClicked == false) {
                        imgFavorite2.setBackgroundResource(R.drawable.shopping);

                        shoppingIsClicked = true;
                        hideShopping();
                    } else {
                        imgFavorite2.setBackgroundResource(R.drawable.shoppinggrey);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarShopping);
//                        goldBar.setVisibility(View.VISIBLE);
                        shoppingIsClicked = false;
                        hideShopping();
                        System.out.println("goldbar shpng is clicked fired and was true");

                    }
                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
//Your other coding if present

                }
            });
            final ImageView imgFavorite3 = (ImageView) findViewById(R.id.accommodation);
            imgFavorite3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    if (accomodationIsClicked == false) {
                        imgFavorite3.setBackgroundResource(R.drawable.accommodation);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarAccomodation);
//                        goldBar.setVisibility(View.INVISIBLE);
                        accomodationIsClicked = true;
                        hideAccomodation();
                    } else {
                        imgFavorite3.setBackgroundResource(R.drawable.accommodationgrey);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarAccomodation);
//                        goldBar.setVisibility(View.VISIBLE);
                        accomodationIsClicked = false;
                        hideAccomodation();
                    }

                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
//Your other coding if present

                }
            });


            final ImageView imgFavorite6 = (ImageView) findViewById(R.id.fooddining);
            imgFavorite6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();

                    if (foodDiningIsClicked == false) {
                        imgFavorite6.setBackgroundResource(R.drawable.fooddining);  // 50% transparent

//                        background.setColorFilter(0xBB000000, PorterDuff.Mode.SCREEN);
//                        img.setBackgroundDrawable(background);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarFoodDining);
//                        goldBar.setVisibility(View.INVISIBLE);
                        foodDiningIsClicked = true;
                        hideFoodDining();

                    } else {
                        imgFavorite6.setBackgroundResource(R.drawable.fooddininggrey);  // 50% transparent
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarFoodDining);
//                        goldBar.setVisibility(View.VISIBLE);
                        foodDiningIsClicked = false;
                        hideFoodDining();
                    }

                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
//Your other coding if present

                }
            });
            final ImageView imgFavorite7 = (ImageView) findViewById(R.id.movies);
            imgFavorite7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();

                    if (moviesIsClicked == false) {
                        imgFavorite7.setBackgroundResource(R.drawable.movies);  // 50% transparent

//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarMovies);
//                        goldBar.setVisibility(View.INVISIBLE);
                        moviesIsClicked = true;
                        hideMovies();
                    } else {
                        imgFavorite7.setBackgroundResource(R.drawable.moviesgrey);  // 50% transparent
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarMovies);
//                        goldBar.setVisibility(View.VISIBLE);
                        moviesIsClicked = false;
                        hideMovies();
                    }


                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
//Your other coding if present

                }
            });
            final ImageView imgFavorite8 = (ImageView) findViewById(R.id.attractions);
            imgFavorite8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    System.out.println("Image Fav fired");
                    if (attractionsIsClicked == false) {
                        imgFavorite8.setBackgroundResource(R.drawable.activities);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarAttractions);
//                        goldBar.setVisibility(View.INVISIBLE);
                        System.out.println("attractions is clicked was false");

                        attractionsIsClicked = true;
                        hideAttractions();

                    } else {
                        imgFavorite8.setBackgroundResource(R.drawable.activitiesgrey);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarAttractions);
//                        goldBar.setVisibility(View.VISIBLE);
                        System.out.println("attractions is clicked was true");

                        attractionsIsClicked = false;
                        hideAttractions();

                    }


                    v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
//Your other coding if present

                }
            });


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            ImageView shareButton2 = (ImageView) findViewById(R.id.shareButton2);
            shareButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tvy = (TextView) findViewById(R.id.retailerNamesy);
                    TextView tvz = (TextView) findViewById(R.id.retailerNamesz);
                    TextView tvd = (TextView) findViewById(R.id.retailerNamesd);
                    TextView retName = (TextView) findViewById(R.id.retailerName);
                    String offer2 = null;
                    String offer3 = null;
                    if (tvz.getText().toString().equals("offer 2 empty")) {
                        offer2 = "";

                    } else {

                        offer2 = tvz.getText().toString();

                    }
                    if (tvd.getText().toString().equals("offer 3 empty")) {
                        offer3 = "";

                    } else {
                        offer3 = tvd.getText().toString();

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
                                intent.putExtra(Intent.EXTRA_TEXT, (retName.getText().toString() + "\n" + tvy.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("facebook")) {
                                // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                                // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                                // will show the <meta content ="..."> text from that page with our link in Facebook.
                                intent.putExtra(Intent.EXTRA_TEXT, (retName.getText().toString() + "\n" + tvy.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                                intent.putExtra(Intent.EXTRA_SUBJECT, retName.getText().toString() + "\n" + tvy.getText().toString() + "\n" + offer2 + "\n" + offer3);
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

//                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                    sharingIntent.setType("text/plain");
//                String shareBody = retName.getText().toString() + "\nOffer 1: " + tvy.getText().toString() + "\nOffer 2: " + offer2 + "\nOffer 3: " + offer3;
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hey, check out these great offers");
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//                    startActivity(Intent.createChooser(sharingIntent, "Share via"));


            });


            ImageView shareButton3 = (ImageView) findViewById(R.id.shareButton3);
            shareButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tvy = (TextView) findViewById(R.id.retailerNamesy);
                    TextView tvz = (TextView) findViewById(R.id.retailerNamesz);
                    TextView tvd = (TextView) findViewById(R.id.retailerNamesd);
                    TextView retName = (TextView) findViewById(R.id.retailerName);
                    String offer2 = null;
                    String offer3 = null;
                    if (tvz.getText().toString().equals("offer 2 empty")) {
                        offer2 = "";

                    } else {
                        offer2 = tvz.getText().toString();

                    }
                    if (tvd.getText().toString().equals("offer 3 empty")) {
                        offer3 = "";

                    } else {
                        offer3 = tvd.getText().toString();

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
                                intent.putExtra(Intent.EXTRA_TEXT, (retName.getText().toString() + "\n" + tvy.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("facebook")) {
                                // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                                // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                                // will show the <meta content ="..."> text from that page with our link in Facebook.
                                intent.putExtra(Intent.EXTRA_TEXT, (retName.getText().toString() + "\n" + tvy.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                                intent.putExtra(Intent.EXTRA_SUBJECT, retName.getText().toString() + "\n" + tvy.getText().toString() + "\n" + offer2 + "\n" + offer3);
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

//                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                    sharingIntent.setType("text/plain");
//                String shareBody = retName.getText().toString() + "\nOffer 1: " + tvy.getText().toString() + "\nOffer 2: " + offer2 + "\nOffer 3: " + offer3;
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hey, check out these great offers");
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//                    startActivity(Intent.createChooser(sharingIntent, "Share via"));


            });

            final FrameLayout content2 = (FrameLayout) findViewById(R.id.content2);

            final FrameLayout content3 = (FrameLayout) findViewById(R.id.content3);
            final FrameLayout content4 = (FrameLayout) findViewById(R.id.content4);
            final FrameLayout couponOffer1 = (FrameLayout) findViewById(R.id.content28);
            final FrameLayout couponOffer2 = (FrameLayout) findViewById(R.id.content2z);
            final FrameLayout couponOffer3 = (FrameLayout) findViewById(R.id.content2d);
            final ImageView starsOverlay = (ImageView) findViewById(R.id.starsoverlay2);
            final SemiCircleProgressBarView semiCirc = (SemiCircleProgressBarView) findViewById(R.id.iconImageBg32);

            final ImageView greyBack = (ImageView) findViewById(R.id.iconImageBg42);
            View myView = findViewById(R.id.toolbar2);
            myView.setOnTouchListener(new OnSwipeTouchListener(this) {
                public static final String DEBUG_TAG = "error";

                public void onSwipeTop() {
                    Toast.makeText(MapsActivity.this, "top", Toast.LENGTH_SHORT).show();
                }

                public void onSwipeRight() {

                    Intent intent = new Intent(MapsActivity.this, SettingsActivity.class);
                    overridePendingTransition(R.anim.righter, R.anim.righter);

//                    Animation fromLeft = AnimationUtils.loadAnimation(MapsActivity.this,
//                            R.anim.slide_right);
                    ViewGroup hiddenPanel2 = (ViewGroup) findViewById(R.id.slidingDrawer3);
                    hiddenPanel2.setVisibility(View.VISIBLE);

                    TextView tv = (TextView) findViewById(R.id.retailerName2);
                    tv.setText("Your nearby offers");

                    overridePendingTransition(R.anim.slide_left, R.anim.slide_left);

                    backpressed = true;
                    couponOffer2.setVisibility(View.GONE);
                    couponOffer1.setVisibility(View.GONE);
                    couponOffer3.setVisibility(View.GONE);

                    greyBack.setVisibility(View.GONE);
                    semiCirc.setVisibility(View.GONE);
                    starsOverlay.setVisibility(View.GONE);

                    Toolbar toolbar3 = (Toolbar) findViewById(R.id.toolbar3);
                    toolbar3.setVisibility(View.VISIBLE);

                    ScrollView scroll = (ScrollView) findViewById(R.id.scrolly);

                    scroll.fullScroll(View.FOCUS_DOWN);
                }

                public void onSwipeLeft() {
                    Intent intent = new Intent(MapsActivity.this, Main2Activity.class);
                    startActivity(intent);
                }

                public void onSwipeBottom() {
                    Toast.makeText(MapsActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                }

            });
            View myView2 = findViewById(R.id.toolbar3);
            myView2.setOnTouchListener(new OnSwipeTouchListener(this) {
                public static final String DEBUG_TAG = "error";

                public void onSwipeTop() {
                    Toast.makeText(MapsActivity.this, "top", Toast.LENGTH_SHORT).show();
                }

                public void onSwipeRight() {

                }

                public void onSwipeLeft() {

                    Intent intent = new Intent(MapsActivity.this, SettingsActivity.class);
                    overridePendingTransition(R.anim.lefter, R.anim.lefter);

//                    Animation fromLeft = AnimationUtils.loadAnimation(MapsActivity.this,
//                            R.anim.slide_right);
                    ViewGroup hiddenPanel2 = (ViewGroup) findViewById(R.id.slidingDrawer3);
                    hiddenPanel2.setVisibility(View.GONE);
                    overridePendingTransition(R.anim.slide_right,
                            R.anim.slide_right);

                    content2.setVisibility(View.GONE);
                    backpressed = true;
                    content3.setVisibility(View.GONE);
                    couponOffer2.setVisibility(View.GONE);
                    couponOffer1.setVisibility(View.GONE);
                    couponOffer3.setVisibility(View.GONE);

                    greyBack.setVisibility(View.GONE);
                    semiCirc.setVisibility(View.GONE);
                    starsOverlay.setVisibility(View.GONE);

                    Toolbar toolbar3 = (Toolbar) findViewById(R.id.toolbar3);
                    toolbar3.setVisibility(View.GONE);
                }

                public void onSwipeBottom() {
                    Toast.makeText(MapsActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                }

            });

            ImageView shareButton = (ImageView) findViewById(R.id.share);
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tvy = (TextView) findViewById(R.id.retailerNamesy);
                    TextView tvz = (TextView) findViewById(R.id.retailerNamesz);
                    TextView tvd = (TextView) findViewById(R.id.retailerNamesd);
                    TextView retName = (TextView) findViewById(R.id.retailerName);
                    String offer2 = null;
                    String offer3 = null;
                    if (tvz.getText().toString().equals("offer 2 empty")) {
                        offer2 = "";

                    } else {
                        offer2 = tvz.getText().toString();

                    }
                    if (tvd.getText().toString().equals("offer 3 empty")) {
                        offer3 = "";

                    } else {
                        offer3 = tvd.getText().toString();

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
                                intent.putExtra(Intent.EXTRA_TEXT, (retName.getText().toString() + "\n" + tvy.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("facebook")) {
                                // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                                // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                                // will show the <meta content ="..."> text from that page with our link in Facebook.
                                intent.putExtra(Intent.EXTRA_TEXT, (retName.getText().toString() + "\n" + tvy.getText().toString() + "\n" + offer2 + "\n" + offer3));
                            } else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                                intent.putExtra(Intent.EXTRA_SUBJECT, retName.getText().toString() + "\n" + tvy.getText().toString() + "\n" + offer2 + "\n" + offer3);
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

//                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                    sharingIntent.setType("text/plain");
//                String shareBody = retName.getText().toString() + "\nOffer 1: " + tvy.getText().toString() + "\nOffer 2: " + offer2 + "\nOffer 3: " + offer3;
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hey, check out these great offers");
//                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//                    startActivity(Intent.createChooser(sharingIntent, "Share via"));


            });


//            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//            });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                }
            });
            View va = (View) findViewById(R.id.mapsy);

            va.setOnTouchListener(new OnSwipeTouchListener(MapsActivity.this) {
                public void onSwipeTop() {
                }

                public void onSwipeRight() {
                    Intent intent = new Intent(MapsActivity.this, SettingsActivity.class);
                    overridePendingTransition(R.anim.righter, R.anim.righter);
                    startActivity(intent);


                }


                public void onSwipeLeft() {

                    Intent intent = new Intent(MapsActivity.this, Main2Activity.class);
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

    public void openRetailerSite(View view) {
        TextView website = (TextView) findViewById(R.id.website);
        String url = "http://" + website.getText().toString();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void filterIcons(View v) {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
        mp.start();
        v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));

        Animation bottomUp = AnimationUtils.loadAnimation(MapsActivity.this,
                R.anim.bottom_up);
        ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer2);
        hiddenPanel.startAnimation(bottomUp);
        hiddenPanel.setVisibility(View.GONE);
        hideAttractions();
        hideMovies();
        hideShopping();
        hideAccomodation();
        hideDrinks();
        hideFoodDining();


        v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));
    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    public void searchFunc(View v) {
        final android.widget.SearchView sv = (android.widget.SearchView) findViewById(R.id.sv);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
        mp.start();
        sv.setVisibility(View.VISIBLE);

        sv.setIconified(false);
        v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));


        sv.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {

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

                    LatLng latLng = new LatLng(-36.903968, 174.764763);
                    Toast.makeText(MapsActivity.this, "BOOM EPS", Toast.LENGTH_SHORT);

                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
                    mMap.animateCamera(cameraUpdate);
                }            // Do your task here
                if (query.equals("ponsonby") || query.equals("ponsonby ")) {
                    System.out.println("On pons submitted");

                    LatLng latLng = new LatLng(-36.859104, 174.750184);
                    Toast.makeText(MapsActivity.this, "BOOM PONS", Toast.LENGTH_SHORT);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
                    mMap.animateCamera(cameraUpdate);
                }            // Do your task here
                if (query.equals("britomart") || query.equals("britomart ")) {

                    LatLng latLng = new LatLng(-36.844010, 174.766991);
                    Toast.makeText(MapsActivity.this, "BOOM BRITs", Toast.LENGTH_SHORT);

                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
                    mMap.animateCamera(cameraUpdate);
                }
                sv.setQuery("", false);
                sv.setVisibility(View.GONE);

                return false;
            }

        });
        return;


    }


    public void openFilter(View view) {
        mClusterManager.clearItems();
        mClusterManager.cluster();
        final ImageView imgFavorite = (ImageView) findViewById(R.id.drinks);
        final ImageView imgFavorite2 = (ImageView) findViewById(R.id.accommodation);
        final ImageView imgFavorite3 = (ImageView) findViewById(R.id.attractions);
        final ImageView imgFavorite4 = (ImageView) findViewById(R.id.movies);
        final ImageView imgFavorite5 = (ImageView) findViewById(R.id.fooddining);
        final ImageView imgFavorite6 = (ImageView) findViewById(R.id.shopping);

        imgFavorite.setBackgroundResource(R.drawable.drinksgrey);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarDrinks);
//                        goldBar.setVisibility(View.VISIBLE);
        drinksIsClicked = false;

        imgFavorite2.setBackgroundResource(R.drawable.accommodationgrey);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarDrinks);
//                        goldBar.setVisibility(View.VISIBLE);
        accomodationIsClicked = false;

        imgFavorite3.setBackgroundResource(R.drawable.activitiesgrey);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarDrinks);
//                        goldBar.setVisibility(View.VISIBLE);
        attractionsIsClicked = false;

        imgFavorite4.setBackgroundResource(R.drawable.moviesgrey);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarDrinks);
//                        goldBar.setVisibility(View.VISIBLE);
        moviesIsClicked = false;

        imgFavorite5.setBackgroundResource(R.drawable.fooddininggrey);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarDrinks);
//                        goldBar.setVisibility(View.VISIBLE);
        foodDiningIsClicked = false;
        imgFavorite6.setBackgroundResource(R.drawable.shoppinggrey);
//                        ImageView goldBar = (ImageView) findViewById(R.id.goldbarDrinks);
//                        goldBar.setVisibility(View.VISIBLE);
        shoppingIsClicked = false;


//            Intent intent = new Intent(this, FullscreenActivity.class);
//        intent.putExtra("groceries",visibleGroceriesIcon);
//        intent.putExtra("beverages",visibleBeverageIcon);
//        intent.putExtra("coffee",visibleCoffeeIcon);
//        intent.putExtra("gym",visibleGymIcon);
//        intent.putExtra("fastfoods",visibleFastfoodsIcon);
//        intent.putExtra("restaurants",visibleRestaurantIcon);
//        intent.putExtra("tech",visibleElectronicsIcon);
//        intent.putExtra("movies",visibleMoviesIcon);
//        intent.putExtra("shopping",visibleShoppingIcon);
//
//        intent.putExtra("firstSubmit",firstSubmit);
//System.out.println("sending from maps, 1st submit is: " + firstSubmit);

//            startActivityForResult(intent,1000);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);

        mp.start();


        Animation bottomUp = AnimationUtils.loadAnimation(MapsActivity.this,
                R.anim.bottom_up);
        ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer2);
        hiddenPanel.startAnimation(bottomUp);
        hiddenPanel.setVisibility(View.VISIBLE);
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
                Intent intent = new Intent(MapsActivity.this, Main2Activity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getExtras() != null) {
//    visibleGroceriesIcon = data.getExtras().getBoolean("groceries");
//    visibleMoviesIcon = data.getExtras().getBoolean("movies");
//    visibleRestaurantIcon = data.getExtras().getBoolean("restaurant");
//    visibleShoppingIcon = data.getExtras().getBoolean("shopping");
//    visibleGymIcon = data.getExtras().getBoolean("gym");
//    visibleFastfoodsIcon = data.getExtras().getBoolean("fastfoods");
//    visibleBeverageIcon = data.getExtras().getBoolean("beverages");
//    visibleElectronicsIcon = data.getExtras().getBoolean("tech");
//    visibleCoffeeIcon = data.getExtras().getBoolean("coffee");
//    firstSubmit = data.getExtras().getBoolean("firstSubmit");
//    hideCoffee();
//    hideBeverages();
//    hideElectronics();
//    hideFastfoods();
//    hideShopping();
//    hideRestaurants();
//    hideMovies();
//    hideGroceries();
//    hideGym();

        } else {
            return;
        }

    }

    public void onResume() {
        super.onResume();
        System.out.println("onResume");
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

    // The below hideburgers(), hideShopping and hideCoffee methods are invoked my the layout xmls
    // onClick event when the users taps the filter icons at the bottom off the map screen

    public void hideDrinks() {


        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();
        System.out.println(markerLength);
        System.out.println(parent.get(1)[1]);
        System.out.println(parent.get(2)[1]);

        if (drinksIsClicked == true) {
            mClusterManager.clearItems();
            markers = mClusterManager.getMarkerCollection().getMarkers();
//            ArrayList markers2 = new ArrayList(markers);
//            System.out.println(markers2.get(1).toString());

            for (int i = 0; i < widerArrays.size(); i++) {
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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

        } else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();
                for (int i = 0; i < widerArrays.size(); i++) {
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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
//            for (int i = 0;i < widerArrays.size();i++){
//                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
//                mClusterManager.addItem(offsetItem);
//            }


            System.out.println("elsed");
//
//            for (int i = 0; i < widerArrays.size(); i++) {
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                }
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//
//                }
//                if (parent.get(i)[1] == "drinks") {
//                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
//                    mClusterManager.addItem(offsetItem);
//                }
//            }


        }
    }


    public void hideAttractions() {


        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();
        if (attractionsIsClicked == true) {
            mClusterManager.clearItems();
            for (int i = 0; i < widerArrays.size(); i++) {
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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


        } else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();

                for (int i = 0; i < widerArrays.size(); i++) {
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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

//            for (int i = 0; i < widerArrays.size(); i++) {
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                }
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//
//                }
//                if (parent.get(i)[1] == "attractions") {
//                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
//                    mClusterManager.addItem(offsetItem);
//                }
//            }
            else {
                mClusterManager.clearItems();
            }
            mClusterManager.cluster();
        }
    }

    public void hideAccomodation() {


        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();

        if (accomodationIsClicked == true) {
            mClusterManager.clearItems();
            for (int i = 0; i < widerArrays.size(); i++) {
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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


        } else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();

                for (int i = 0; i < widerArrays.size(); i++) {
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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
//
//            for (int i = 0; i < widerArrays.size(); i++) {
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                }
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//
//                }
//                if (parent.get(i)[1] == "accomodation") {
//                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
//
//                    mClusterManager.addItem(offsetItem);
//                }
//            }

            else {
                mClusterManager.clearItems();
            }
            mClusterManager.cluster();
        }
    }

    public void hideMovies() {


        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();
        System.out.println(markerLength);
        System.out.println(parent.get(1)[1]);
        System.out.println(parent.get(2)[1]);

        if (moviesIsClicked == true) {
            mClusterManager.clearItems();


            for (int i = 0; i < widerArrays.size(); i++) {
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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


        } else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();

                for (int i = 0; i < widerArrays.size(); i++) {
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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
//
//            for (int i = 0; i < widerArrays.size(); i++) {
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                }
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//
//                }
//                if (parent.get(i)[1] == "movies") {
//                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
//                    mClusterManager.addItem(offsetItem);
//                }
//            }

            else {
                mClusterManager.clearItems();
            }
            mClusterManager.cluster();
        }
    }

    public void hideFoodDining() {

        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();

        if (foodDiningIsClicked == true) {
            mClusterManager.clearItems();

            for (int i = 0; i < widerArrays.size(); i++) {
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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


        } else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();

                for (int i = 0; i < widerArrays.size(); i++) {
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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
//
//            for (int i = 0; i < widerArrays.size(); i++) {
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                }
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//
//                }
//                if (parent.get(i)[1] == "food/dining") {
//                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
//                    mClusterManager.addItem(offsetItem);
//                }
//            }


            } else {
                mClusterManager.clearItems();
            }
            mClusterManager.cluster();

        }

    }

    public void hideShopping() {

        int markerLength = mClusterManager.getMarkerCollection().getMarkers().size();

        if (shoppingIsClicked == true) {
            mClusterManager.clearItems();
            markers = mClusterManager.getMarkerCollection().getMarkers();

            for (int i = 0; i < widerArrays.size(); i++) {
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                }
                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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


        } else {
            if (moviesIsClicked == true || attractionsIsClicked == true || accomodationIsClicked == true || foodDiningIsClicked == true || drinksIsClicked == true || shoppingIsClicked == true) {
                mClusterManager.clearItems();

                for (int i = 0; i < widerArrays.size(); i++) {
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    }
                    try {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                    } catch (Exception er) {
                        nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                    }
                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
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

//            for (int i = 0; i < widerArrays.size(); i++) {
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//                }
//                try {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";
//
//                } catch (Exception er) {
//                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
//
//                }
//                if (parent.get(i)[1] == "shopping") {
//                    AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
//                    mClusterManager.addItem(offsetItem);
//                }
//            }

        }

        mClusterManager.cluster();

    }


    public android.location.Address getAddressForLocation(Context context, Location location) throws IOException {

        if (location == null) {
            return null;
        }
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        int maxResults = 1;

        //get current address by invoke an AsyncTask object
        new GetAddressTask(MapsActivity.this).execute(String.valueOf(latitude), String.valueOf(longitude));


        Geocoder gc = new Geocoder(context, Locale.getDefault());
        List<android.location.Address> addresses = gc.getFromLocation(latitude, longitude, maxResults);

        if (addresses.size() == 1) {
            System.out.println("PRINTING ADDRESS");
            System.out.println(addresses.get(0));
            return addresses.get(0);
        } else {
            return null;
        }

    }

    public void callBackDataFromAsyncTask(String address) {
        System.out.println(address);
    }

    public void armode(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(MapsActivity.this, R.anim.image_click));

        Intent intent = new Intent(MapsActivity.this, Main2Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.lefter, R.anim.lefter);
    }

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
            SearchView sv = (SearchView) findViewById(R.id.sv);
            sv.setVisibility(View.GONE);
        } else if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_YES) {
            Toast.makeText(this, "keyboard hidden", Toast.LENGTH_SHORT).show();
            SearchView sv = (SearchView) findViewById(R.id.sv);
            sv.setVisibility(View.GONE);
        }
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            System.out.println("ORIENTATION CHANGED");
            System.out.println("Landscape");

            try {
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
            } catch (Exception e) {
                System.out.println("didnt work");

            }


        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public class editText extends SearchView {

        public editText(Context context) {
            super(context);
        }

        @Override
        public boolean onKeyPreIme(int keyCode, KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                // Do your thing.
                SearchView sv = (SearchView) findViewById(R.id.sv);
                sv.setVisibility(View.GONE);
                return true;  // So it is not propagated.
            }
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public void onBackPressed() {
        final FrameLayout couponOffer1 = (FrameLayout) findViewById(R.id.content28s3);
        final TextView retailerNamesy8s3 =(TextView)findViewById(R.id.retailerNamesy8s3);
        final FrameLayout contentNearby = (FrameLayout) findViewById(R.id.contentNearby);
        final FrameLayout contentNearby2 = (FrameLayout) findViewById(R.id.contentNearby2);
        final FrameLayout contentNearby3 = (FrameLayout) findViewById(R.id.contentNearby3);
        final FrameLayout contentNearby4 = (FrameLayout) findViewById(R.id.contentNearby4);
        final FrameLayout contentNearby5 = (FrameLayout) findViewById(R.id.contentNearby5);
        final FrameLayout contentNearby6 = (FrameLayout) findViewById(R.id.contentNearby6);
        final FrameLayout contentNearby7 = (FrameLayout) findViewById(R.id.contentNearby7);
        final FrameLayout contentNearby8 = (FrameLayout) findViewById(R.id.contentNearby8);

        SemiCircleProgressBarView semiCirc = (SemiCircleProgressBarView) findViewById(R.id.iconImageBg3);
        SemiCircleProgressBarView semiCirc2 = (SemiCircleProgressBarView) findViewById(R.id.semicirc2);
        SemiCircleProgressBarView semiCirc3 = (SemiCircleProgressBarView) findViewById(R.id.semicirc3);


        ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer2);
        ViewGroup hiddenPanel3 = (ViewGroup) findViewById(R.id.slidingDrawer3);
        ViewGroup hiddenPanel2 = (ViewGroup) findViewById(R.id.slidingDrawer1);
        if (hiddenPanel2.getVisibility() != View.VISIBLE && hiddenPanel.getVisibility() != View.VISIBLE && hiddenPanel3.getVisibility() != View.VISIBLE) {
            super.onBackPressed();
        }

        if(couponOffer1.getVisibility() == View.VISIBLE){
            couponOffer1.setVisibility(View.GONE);
            retailerNamesy8s3.setVisibility(View.GONE);
            if(!retName.getText().equals("")){
                contentNearby.setVisibility(View.VISIBLE);

            }
            if(!retName2.getText().equals("")){
                contentNearby2.setVisibility(View.VISIBLE);

            }
            if(!retName3.getText().equals("")){
                contentNearby3.setVisibility(View.VISIBLE);

            }
            if(!retName4.getText().equals("")){
                contentNearby4.setVisibility(View.VISIBLE);

            }
            if(!retName5.getText().equals("")){
                contentNearby5.setVisibility(View.VISIBLE);

            }
            if(!retName6.getText().equals("")){
                contentNearby6.setVisibility(View.VISIBLE);

            }
            if(!retName7.getText().equals("")){
                contentNearby7.setVisibility(View.VISIBLE);

            }
            if(!retName8.getText().equals("")){
                contentNearby8.setVisibility(View.VISIBLE);

            }


        }
        if (hiddenPanel3.getVisibility() == View.VISIBLE && couponOffer1.getVisibility() != View.VISIBLE) {
            Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                    R.anim.bottom_down);
            hiddenPanel3.startAnimation(bottomDown);
            hiddenPanel3.setVisibility(View.GONE);
        }


        final SearchView sv = (SearchView) findViewById(R.id.sv);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                R.anim.bottom_down);
        if (backpressed == false) {
            hiddenPanel2.startAnimation(bottomDown);
            hiddenPanel2.setVisibility(View.GONE);

        }

        final FrameLayout couponOffer2 = (FrameLayout) findViewById(R.id.content2z);
        final FrameLayout couponOffer3 = (FrameLayout) findViewById(R.id.content2d);
        final FrameLayout content2 = (FrameLayout) findViewById(R.id.content2);

        final FrameLayout content3 = (FrameLayout) findViewById(R.id.content3);
        final FrameLayout content4 = (FrameLayout) findViewById(R.id.content4);
        FrameLayout lin2 = (FrameLayout) findViewById(R.id.content2z);
        FrameLayout lin3 = (FrameLayout) findViewById(R.id.content2d);

        ImageView greyBack = (ImageView) findViewById(R.id.iconImageBg4);
        ImageView starsOverlay = (ImageView) findViewById(R.id.starsoverlay);
        ImageView multiOfferStar = (ImageView) findViewById(R.id.multiOffersStars);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (hiddenPanel.getVisibility() == View.VISIBLE) {
            System.out.println("filter hidden panel was open");
            hiddenPanel.startAnimation(bottomDown);
            hiddenPanel.setVisibility(View.GONE);
            mClusterManager.clearItems();
            markers = mClusterManager.getMarkerCollection().getMarkers();
//            ArrayList markers2 = new ArrayList(markers);
//            System.out.println(markers2.get(1).toString());

            for (int i = 0; i < widerArrays.size(); i++) {
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                }
                try {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                } catch (Exception er) {
                    nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                }

                AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
                mClusterManager.addItem(offsetItem);

            }
            mClusterManager.cluster();
        }
        if (hiddenPanel2.getVisibility() == View.VISIBLE) {
            if (content2.getVisibility() == View.VISIBLE) {
                backpressed = false;
                hiddenPanel2.startAnimation(bottomDown);
                hiddenPanel2.setVisibility(View.GONE);
                System.out.println("cont2 was visible");
            } else if (backStack == false) {
                hiddenPanel2.startAnimation(bottomDown);
                hiddenPanel2.setVisibility(View.GONE);
                System.out.println("cont2 was visible");
            }
        }


        if (couponOffer1.getVisibility() == View.VISIBLE && !dealOffer2Text.equals("offer 2 empty") || couponOffer2.getVisibility() == View.VISIBLE && !dealOffer2Text.equals("offer 2 empty") || couponOffer3.getVisibility() == View.VISIBLE && !dealOffer2Text.equals("offer 2 empty")) {
            couponOffer1.setVisibility(View.GONE);
            couponOffer2.setVisibility(View.GONE);
            couponOffer3.setVisibility(View.GONE);
            semiCirc.setVisibility(View.GONE);
            semiCirc2.setVisibility(View.GONE);
            semiCirc3.setVisibility(View.GONE);

            lin2.setVisibility(FrameLayout.GONE);
            content2.setVisibility(View.VISIBLE);
            content3.setVisibility(View.VISIBLE);
            ImageView catIcon = (ImageView) findViewById(R.id.iconImage);
            catIcon.setVisibility(View.VISIBLE);
            if (dealOffer3Text.equals("offer 3 empty")) {
                content4.setVisibility(View.GONE);

            } else {
                content4.setVisibility(View.VISIBLE);
            }
            couponOffer1.setVisibility(View.GONE);
            couponOffer2.setVisibility(View.GONE);
            System.out.println("offer2 is empty");
            greyBack.setVisibility(View.GONE);
            semiCirc.setVisibility(View.GONE);
            starsOverlay.setVisibility(View.GONE);


        }


        sv.setVisibility(View.GONE);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
        mp.start();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
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

    //My task is called in the onRequestData method and passes it the url of our AWS GET request API
    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            // Send our GET request to AWS using the Java Http Manager class

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
                    innerArrs[0] = objectCategory.getString("category");
                    innerArrs[1] = objectCategory.getString("retailerName");
                    innerArrs[2] = objectCategory.getString("offerEndTime");
                    innerArrs[3] = objectCategory.getString("retailerID");
                    innerArrs[4] = objectCategory.getString("altitude");
                    innerArrs[5] = objectCategory.getString("longitude");
                    innerArrs[6] = objectCategory.getString("latitude");
                    innerArrs[7] = objectCategory.getString("offerDescription");
                    innerArrs[8] = objectCategory.getString("duration");
                    innerArrs[10] = objectCategory.getString("rating");
                    innerArrs[11] = objectCategory.getString("website");

                    if (!objectCategory.has("offerDescription2")) {
                        System.out.println("offd2 does not exist");
                        innerArrs[9] = "offer 2 empty";

                    } else {
                        innerArrs[9] = objectCategory.getString("offerDescription2");

                        System.out.println("offd2 exists");
                    }
                    try {
                        innerArrs[12] = objectCategory.getString("offerDescription3");
                    } catch (Exception err) {
                        innerArrs[12] = "offer 3 empty";
                    }
                    try {
                        innerArrs[13] = objectCategory.getString("offerEndTime2");
                    } catch (Exception err) {
                        innerArrs[13] = "offer 2 empty";
                    }
                    try {
                        innerArrs[14] = objectCategory.getString("offerEndTime3");
                    } catch (Exception err) {
                        innerArrs[14] = "offer 3 empty";
                    }


                    // Seperate each array element with a pipe so we can later identify the different attributesfor a unique offer
                    widerArrays.add("|" + innerArrs[0] + "|" + innerArrs[1] + "|" + innerArrs[2] + "|" + innerArrs[3] + "|" + innerArrs[4] + "|" + innerArrs[5] + "|" + innerArrs[6] + "|" + innerArrs[7] + "|" + innerArrs[8] + "|" + innerArrs[9] + "|" + innerArrs[10] + "|" + innerArrs[11] + "|" + innerArrs[12] + "|" + innerArrs[13] + "|" + innerArrs[14]);

                    System.out.println(widerArrays.get(i));
                    String[] ert = (widerArrays.get(i)).split(Pattern.quote("|"));
                    System.out.println("Wider array printing..");

                    System.out.println(widerArrays.get(i).split(Pattern.quote("|"))[11]);
                    System.out.println(innerArrs[9]);
                    System.out.println(objectCategory.getString("offerDescription3"));
                    OfferCat = objectCategory.getString("category");

                }
                // Check the size of our new array
                System.out.println(widerArrays.size());

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

    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open("drawable/" + filename + ".png")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
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


        System.out.println("off cat just printed");
        final Bitmap[] markerIcon = {smallMarker10};
        String category = null;

        // Add ten cluster items in close proximity, for purposes of this example.
//        for (int i = 0; i < 10; i++) {
//
//            AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])));
//            mClusterManager.addItem(offsetItem);
//            mClusterManager.setRenderer(new CustomClusterRenderer(MapsActivity.this, mMap, mClusterManager));
//        }


        for (int i = 0; i < widerArrays.size(); i++) {
            try {
                nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

            } catch (Exception er) {
                nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
            }
            try {
                nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

            } catch (Exception er) {
                nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

            }
            if ("shopping".equals((widerArrays.get(i)).split(Pattern.quote("|"))[1])) {
                markerIcon[0] = smallMarker10;
                category = "shopping";
                System.out.println("True");
            } else if ("attractions".equals((widerArrays.get(i)).split(Pattern.quote("|"))[1])) {
                markerIcon[0] = smallMarker8;
                category = "attractions";

                System.out.println("True");
            } else if ("fastfoods".equals((widerArrays.get(i)).split(Pattern.quote("|"))[1])) {
                markerIcon[0] = smallMarker3;
                category = "fastfoods";

                System.out.println("True");
            } else if ("food/dining".equals((widerArrays.get(i)).split(Pattern.quote("|"))[1])) {
                markerIcon[0] = smallMarker3;
                category = "food/dining";

                System.out.println("True");
            } else if ("drinks".equals(((widerArrays.get(i)).split(Pattern.quote("|"))[1]))) {
                markerIcon[0] = smallMarker7;
                category = "drinks";
                System.out.println("True");
            } else if ("movies".equals(((widerArrays.get(i)).split(Pattern.quote("|"))[1]))) {
                markerIcon[0] = smallMarker5;
                category = "movies";
                System.out.println("True");
            }


            // Upload our markers with all associated info
            MarkerOptions Opty = new MarkerOptions()
                    .title((((widerArrays.get(i)).split(Pattern.quote("|"))[2])))
                    .position(new LatLng(Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7])), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6]))))
                    .snippet(nd)
                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon[0]));

            AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
            mClusterManager.addItem(offsetItem);

//
//            System.out.println("wider arrs printing");
//            System.out.println(widerArrays.get(i).split(Pattern.quote("|"))[6]);
//            System.out.println(widerArrays.get(i).split(Pattern.quote("|"))[7]);
//            System.out.println(widerArrays.get(i).split(Pattern.quote("|"))[8]);
//            System.out.println(widerArrays.get(i).split(Pattern.quote("|"))[9]);

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
                    for (int i = 0; i < widerArrays.size(); i++) {


                        System.out.println("MARKERS about to print");
                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[14]);
                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[15]);
                        System.out.println("MARKERS about to print");
                        String nd = null;
                        try {
                            nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                        } catch (Exception er) {
                            nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                        }
                        try {
                            nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11];

                        } catch (Exception er) {
                            nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                        }

//            markerManager.getCollection("1").addMarker(new MarkerOptions()
//                    .title((((widerArrays.get(i)).split(Pattern.quote("|"))[2])))
//                    .position(new LatLng(Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7])), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6]))))
//                    .snippet(nd)
//                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon)));

                        final String finalNd = nd;
                        final int finalI = i;


                        ScrollView scroll = (ScrollView) findViewById(R.id.scrolly);

                        scroll.fullScroll(View.FOCUS_DOWN);

                        System.out.println(appClusterItem.mSnippet);
                        System.out.println(widerArrays.get(finalI));
                        TextView tv = (TextView) findViewById(R.id.retailerName);
                        tv.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[9]);
                        TextView tvr = (TextView) findViewById(R.id.retailerNamesy);
                        tvr.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[0]);

                        TextView tvn = (TextView) findViewById(R.id.retailerNamesy8);
                        tvn.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[0]);
                        dealOffer1Text = appClusterItem.mSnippet.split(Pattern.quote("|"))[0];
                        dealOffer2Text = appClusterItem.mSnippet.split(Pattern.quote("|"))[1];
                        dealOffer3Text = appClusterItem.mSnippet.split(Pattern.quote("|"))[4];

                        TextView tvj = (TextView) findViewById(R.id.retailerNamesy3);
                        tvj.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[1]);


                        TextView tvx = (TextView) findViewById(R.id.retailerNamesz);
                        tvx.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[1]);

                        TextView tvd = (TextView) findViewById(R.id.retailerNamesd);
                        tvd.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[4]);
                        TextView tvk = (TextView) findViewById(R.id.retailerNamesy4);
                        tvk.setText(appClusterItem.mSnippet.split(Pattern.quote("|"))[4]);

                        ImageView starIcon = (ImageView) findViewById(R.id.starsoverlay);
                        final ImageView multiOffersStars = (ImageView) findViewById(R.id.multiOffersStars);
                        final ImageView multiOffersStars2 = (ImageView) findViewById(R.id.multiOffersStars2);
                        final ImageView multiOffersStars3 = (ImageView) findViewById(R.id.multiOffersStars3);
                        System.out.println("RATINGS");
                        multiOffersStars2.setTag("five");
                        multiOffersStars3.setTag("five");


                        if (nd.split(Pattern.quote("|"))[2].equals("1")) {
                            starIcon.setImageResource(R.drawable.starone);
                            multiOffersStars.setImageResource(R.drawable.onestr);
                            multiOffersStars.setTag("one");

                            multiOffersStars2.setImageResource(R.drawable.fivestr);
                            multiOffersStars3.setImageResource(R.drawable.fivestr);
                            System.out.println("goo");

                        }
                        if (nd.split(Pattern.quote("|"))[2].equals("2")) {
                            starIcon.setImageResource(R.drawable.startwo);
                            multiOffersStars.setImageResource(R.drawable.twostr);
                            multiOffersStars2.setImageResource(R.drawable.fivestr);
                            multiOffersStars3.setImageResource(R.drawable.fivestr);

                            multiOffersStars.setTag("two");

                            System.out.println("goo");

                        }
                        if (nd.split(Pattern.quote("|"))[2].equals("3")) {
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
                        if (nd.split(Pattern.quote("|"))[2].equals("4")) {
                            starIcon.setImageResource(R.drawable.starfour);
                            multiOffersStars.setImageResource(R.drawable.fourstr);
                            multiOffersStars.setTag("four");


                            System.out.println("goo");

                        }
                        if (nd.split(Pattern.quote("|"))[2].equals("5")) {
                            starIcon.setImageResource(R.drawable.starfive);
                            multiOffersStars.setImageResource(R.drawable.fivestr);
                            multiOffersStars2.setImageResource(R.drawable.fivestr);
                            multiOffersStars3.setImageResource(R.drawable.fivestr);
                            multiOffersStars.setTag("five");

                            System.out.println("goo");

                        }

                        final FrameLayout lin = (FrameLayout) findViewById(R.id.content2);
                        FrameLayout lin2 = (FrameLayout) findViewById(R.id.content2z);
                        FrameLayout lin3 = (FrameLayout) findViewById(R.id.content2d);


                        lin.setVisibility(FrameLayout.VISIBLE);
                        lin2.setVisibility(FrameLayout.VISIBLE);
                        lin3.setVisibility(FrameLayout.VISIBLE);
                        System.out.println(widerArrays.get(0).split(Pattern.quote("|"))[11]);
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

                        final ImageView catIcon = (ImageView) findViewById(R.id.iconImage);
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
//                            for (int e = 0; e < widerArrays.size(); e++){
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

                        final FrameLayout content2 = (FrameLayout) findViewById(R.id.content2);

                        final FrameLayout content3 = (FrameLayout) findViewById(R.id.content3);
                        final FrameLayout content4 = (FrameLayout) findViewById(R.id.content4);
                        final FrameLayout couponOffer1 = (FrameLayout) findViewById(R.id.content28);
                        final FrameLayout couponOffer2 = (FrameLayout) findViewById(R.id.content2z);
                        final FrameLayout couponOffer3 = (FrameLayout) findViewById(R.id.content2d);

                        final ImageView greyBack = (ImageView) findViewById(R.id.iconImageBg4);
                        final SemiCircleProgressBarView semiCirc = (SemiCircleProgressBarView) findViewById(R.id.iconImageBg3);
                        final SemiCircleProgressBarView semiCirc2 = (SemiCircleProgressBarView) findViewById(R.id.semicirc2);
                        final SemiCircleProgressBarView semiCirc3 = (SemiCircleProgressBarView) findViewById(R.id.semicirc3);
                        final ImageView starsOverlay = (ImageView) findViewById(R.id.starsoverlay);


                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[1].equals("offer 2 empty")) {
                            lin2.setVisibility(FrameLayout.GONE);
                            content2.setVisibility(View.GONE);
                            content3.setVisibility(View.GONE);
                            content4.setVisibility(View.GONE);
                            couponOffer1.setVisibility(View.VISIBLE);
                            couponOffer2.setVisibility(View.GONE);
                            System.out.println("offer2 is empty");
                            greyBack.setVisibility(View.VISIBLE);
                            semiCirc.setVisibility(View.VISIBLE);
                            starsOverlay.setVisibility(View.VISIBLE);
                        }
                        if (!appClusterItem.mSnippet.split(Pattern.quote("|"))[1].equals("offer 2 empty")) {
                            content2.setVisibility(View.VISIBLE);
                            backpressed = true;
                            content3.setVisibility(View.VISIBLE);
                            couponOffer2.setVisibility(View.GONE);
                            couponOffer1.setVisibility(View.GONE);
                            couponOffer3.setVisibility(View.GONE);

                            greyBack.setVisibility(View.GONE);
                            semiCirc.setVisibility(View.GONE);
                            starsOverlay.setVisibility(View.GONE);
                        }
                        if (appClusterItem.mSnippet.split(Pattern.quote("|"))[4].equals("offer 3 empty")) {
                            System.out.println("offer3 is empty");
                            content4.setVisibility(View.GONE);
                            couponOffer3.setVisibility(View.GONE);

                        }
                        if (!appClusterItem.mSnippet.split(Pattern.quote("|"))[4].equals("offer 3 empty")) {
                            content4.setVisibility(View.VISIBLE);
                            couponOffer3.setVisibility(View.GONE);
                            couponOffer2.setVisibility(View.GONE);
                            couponOffer1.setVisibility(View.GONE);
                        }

                        final String finalNd1 = nd;
                        content2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                greyBack.setVisibility(View.VISIBLE);
                                semiCirc.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);

//
                                semiCirc2.setVisibility(View.GONE);
                                semiCirc3.setVisibility(View.GONE);

                                content2.setVisibility(View.GONE);
                                content3.setVisibility(View.GONE);
                                content4.setVisibility(View.GONE);
                                couponOffer1.setVisibility(View.VISIBLE);
                                couponOffer2.setVisibility(View.GONE);
                                couponOffer3.setVisibility(View.GONE);
                                System.out.println("content2");


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
                        final String finalNd2 = nd;
                        content3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                System.out.println("content3");
                                greyBack.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);
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

                                content2.setVisibility(View.GONE);
                                content3.setVisibility(View.GONE);
                                content4.setVisibility(View.GONE);
                                couponOffer1.setVisibility(View.GONE);
                                couponOffer2.setVisibility(View.VISIBLE);
                                couponOffer3.setVisibility(View.GONE);

                            }
                        });
                        content4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                System.out.println("content4");
                                greyBack.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);

                                semiCirc.setVisibility(View.GONE);
                                semiCirc2.setVisibility(View.GONE);
                                semiCirc3.setVisibility(View.GONE);

                                content2.setVisibility(View.GONE);
                                content3.setVisibility(View.GONE);
                                content4.setVisibility(View.GONE);
                                couponOffer1.setVisibility(View.GONE);
                                couponOffer2.setVisibility(View.GONE);
                                couponOffer3.setVisibility(View.VISIBLE);


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


//                    for (int i=0;i<widerArrays.size();i++) {
//                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[1]);
//                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[2]);
//                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[3]);
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
                        ViewGroup hiddenPanel2 = (ViewGroup) findViewById(R.id.slidingDrawer2);
                        if (hiddenPanel2.getVisibility() == View.VISIBLE) {

                            Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                                    R.anim.bottom_down);
                            hiddenPanel2.startAnimation(bottomDown);
                            hiddenPanel2.setVisibility(View.GONE);
                        }

                        Animation bottomUp = AnimationUtils.loadAnimation(MapsActivity.this,
                                R.anim.bottom_up);
                        ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer1);
                        hiddenPanel.startAnimation(bottomUp);
                        hiddenPanel.setVisibility(View.VISIBLE);
//
//                    scroll.fullScroll(View.FOCUS_DOWN);

//                    ScrollView scrollView = (ScrollView) findViewById(R.id.scrolly);
//                    scrollView.setFocusableInTouchMode(true);
//                    scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
//                    SlidingDrawer sdf = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
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


            cat = widerArrays.get(i).split(Pattern.quote("|"))[1];
            System.out.println(widerArrays.get(0).split(Pattern.quote("|"))[1]);
            System.out.println();
            System.out.println(i);

//            if ("shopping".equals((widerArrays.get(i)).split(Pattern.quote("|"))[1])) {
//                markerIcon = smallMarker;
//                category = "shopping";
//                System.out.println("True");
//            } else if ("attractions".equals((widerArrays.get(i)).split(Pattern.quote("|"))[1])) {
//                markerIcon = smallMarker8;
//                category = "attractions";
//
//                System.out.println("True");
//            } else if ("fastfoods".equals((widerArrays.get(i)).split(Pattern.quote("|"))[1])) {
//                markerIcon = smallMarker3;
//                category = "fastfoods";
//
//                System.out.println("True");
//            } else if ("food/dining".equals((widerArrays.get(i)).split(Pattern.quote("|"))[1])) {
//                markerIcon = smallMarker3;
//                category = "food/dining";
//
//                System.out.println("True");
//            } else if ("drinks".equals(((widerArrays.get(i)).split(Pattern.quote("|"))[1]))) {
//                markerIcon = smallMarker7;
//                category = "drinks";
//                System.out.println("True");
//            } else if ("movies".equals(((widerArrays.get(i)).split(Pattern.quote("|"))[1]))) {
//                markerIcon = smallMarker5;
//                category = "movies";
//                System.out.println("True");
//            }

            System.out.println("MARKERS about to print");
            System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[2]);
            System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[10]);
            System.out.println("MARKERS about to print");

//
//            // Upload our markers with all associated info
//            Marker marker = mMap.addMarker(new MarkerOptions()
//                    .title((((widerArrays.get(i)).split(Pattern.quote("|"))[2])))
//                    .position(new LatLng(Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7])), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6]))))
//                    .snippet(nd)
//                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon))
//            );


//            // Declare a variable for the cluster manager.
//             final ClusterManager<ClusterMarkerLocation> mClusterManager;
//
//            // Initialize the manager with the context and the map.
//            // (Activity extends context, so we can pass 'this' in the constructor.)
//            mClusterManager = new ClusterManager<>(MapsActivity.this,mMap);
////            mMap.setOnCameraIdleListener(clusterManager);


//            mClusterManager.addItem(new ClusterMarkerLocation(new LatLng(Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7])), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])))));
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                final SearchView sv = (SearchView) findViewById(R.id.sv);
                sv.setVisibility(View.GONE);

                Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                        R.anim.bottom_down);
                ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer2);
                if (hiddenPanel.getVisibility() == View.VISIBLE) {
                    hiddenPanel.startAnimation(bottomDown);
                    hiddenPanel.setVisibility(View.GONE);

                    mClusterManager.clearItems();
                    markers = mClusterManager.getMarkerCollection().getMarkers();
//            ArrayList markers2 = new ArrayList(markers);
//            System.out.println(markers2.get(1).toString());

                    for (int i = 0; i < widerArrays.size(); i++) {
                        try {
                            nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

                        } catch (Exception er) {
                            nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                            nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
                        }
                        try {
                            nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[1] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + widerArrays.get(i).split(Pattern.quote("|"))[0] + "|" + "ooo";

                        } catch (Exception er) {
                            nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

                        }


                        AppClusterItem offsetItem = new AppClusterItem((Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7]))), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6])), "title " + i + 1, nd);
                        mClusterManager.addItem(offsetItem);
                        mClusterManager.cluster();

                    }
                }
            }

        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {

                ScrollView scroll = (ScrollView) findViewById(R.id.scrolly);

                scroll.fullScroll(View.FOCUS_DOWN);
                Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
                        R.anim.bottom_down);
                ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer1);
                hiddenPanel.startAnimation(bottomDown);
                hiddenPanel.setVisibility(View.INVISIBLE);

                System.out.println(marker.getSnippet());
                TextView tv = (TextView) findViewById(R.id.retailerName);
                tv.setText(nd.split(Pattern.quote("|"))[9]);
                TextView tvr = (TextView) findViewById(R.id.retailerNamesy);
                tvr.setText(nd.split(Pattern.quote("|"))[0]);
                TextView tvn = (TextView) findViewById(R.id.retailerNamesy8);
                tvn.setText(nd.split(Pattern.quote("|"))[0]);

                final FrameLayout lin = (FrameLayout) findViewById(R.id.content2);
                FrameLayout lin2 = (FrameLayout) findViewById(R.id.content2z);
                FrameLayout lin3 = (FrameLayout) findViewById(R.id.content2d);


                lin.setVisibility(FrameLayout.VISIBLE);
                lin2.setVisibility(FrameLayout.VISIBLE);
                lin3.setVisibility(FrameLayout.VISIBLE);
                System.out.println(widerArrays.get(0).split(Pattern.quote("|"))[11]);
//                    System.out.println(marker.getSnippet().split(Pattern.quote("|"))[2]);


                ImageView catIcon = (ImageView) findViewById(R.id.iconImage);
                if (nd.split(Pattern.quote("|"))[8].equals("drinks")) {
                    catIcon.setImageResource(R.drawable.dealdrinks);
                    System.out.println("GAAAAAA");

                }
                if (nd.split(Pattern.quote("|"))[8].equals("attractions")) {
                    catIcon.setImageResource(R.drawable.dealactivities);
                    System.out.println("GAAAAAA");

                }
                if (nd.split(Pattern.quote("|"))[8].equals("accommodation")) {
                    catIcon.setImageResource(R.drawable.dealaccom);
                    System.out.println("GAAAAAA");

                }
                if (nd.split(Pattern.quote("|"))[8].equals("food/dining")) {
                    catIcon.setImageResource(R.drawable.dealhospitality);
                    System.out.println("GAAAAAA");
                }
                if (nd.split(Pattern.quote("|"))[8].equals("movies")) {
                    catIcon.setImageResource(R.drawable.dealentertainment);
                    System.out.println("GAAAAAA");
                }
                if (nd.split(Pattern.quote("|"))[8].equals("shopping")) {
                    catIcon.setImageResource(R.drawable.dealshopping);
                    System.out.println("GAAAAAA");
                }

                try {
                    startTimers(nd.split(Pattern.quote("|"))[5], nd.split(Pattern.quote("|"))[6], nd.split(Pattern.quote("|"))[7]);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

//                    try {
//                        if (timersInitialized != false){
//
//                        }else {
//
//                            for (int e = 0; e < widerArrays.size(); e++){
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


//                    for (int i=0;i<widerArrays.size();i++) {
//                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[1]);
//                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[2]);
//                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[3]);
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
//                    SlidingDrawer sdf = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
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
////                    SlidingDrawer sdg = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
//                    Animation bottomDown = AnimationUtils.loadAnimation(MapsActivity.this,
//                            R.anim.bottom_down);
//                    SearchView sv = (SearchView) findViewById(R.id.sv);
//                    sv.setVisibility(View.GONE);
//
//                    ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer2);
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
        for (int i = 0; i < widerArrays.size(); i++) {


            System.out.println("MARKERS about to print");
            System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[14]);
            System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[15]);
            System.out.println("MARKERS about to print");
            String nd = null;
            try {
                nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12];

            } catch (Exception er) {
                nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];
            }
            try {
                nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[10] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[12] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[13] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[3] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[14] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[15] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[2] + "|" + (widerArrays.get(i)).split(Pattern.quote("|"))[11];

            } catch (Exception er) {
                nd = (widerArrays.get(i)).split(Pattern.quote("|"))[8];

            }

//            markerManager.getCollection("1").addMarker(new MarkerOptions()
//                    .title((((widerArrays.get(i)).split(Pattern.quote("|"))[2])))
//                    .position(new LatLng(Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[7])), Double.parseDouble(((widerArrays.get(i)).split(Pattern.quote("|"))[6]))))
//                    .snippet(nd)
//                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon)));

            final String finalNd = nd;
            final int finalI = i;
            markerManager.getCollection("1").setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker marker) {

                    ScrollView scroll = (ScrollView) findViewById(R.id.scrolly);

                    scroll.fullScroll(View.FOCUS_DOWN);

                    System.out.println(marker.getSnippet());
                    System.out.println(widerArrays.get(finalI));
                    TextView tv = (TextView) findViewById(R.id.retailerName);
                    tv.setText(marker.getSnippet().split(Pattern.quote("|"))[8]);
                    TextView tvr = (TextView) findViewById(R.id.retailerNamesy);
                    tvr.setText(finalNd.split(Pattern.quote("|"))[0]);

                    final FrameLayout lin = (FrameLayout) findViewById(R.id.content2);
                    FrameLayout lin2 = (FrameLayout) findViewById(R.id.content2z);
                    FrameLayout lin3 = (FrameLayout) findViewById(R.id.content2d);


                    lin.setVisibility(FrameLayout.VISIBLE);
                    lin2.setVisibility(FrameLayout.VISIBLE);
                    lin3.setVisibility(FrameLayout.VISIBLE);
                    System.out.println(widerArrays.get(0).split(Pattern.quote("|"))[11]);
//                    System.out.println(marker.getSnippet().split(Pattern.quote("|"))[2]);


                    ImageView starIcon = (ImageView) findViewById(R.id.starsoverlay);
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

                    ImageView catIcon = (ImageView) findViewById(R.id.iconImage);
                    if (widerArrays.get(finalI).split(Pattern.quote("|"))[8].equals("drinks")) {
                        catIcon.setImageResource(R.drawable.dealdrinks);
                        System.out.println("GAAAAAA");

                    }
                    if (widerArrays.get(finalI).split(Pattern.quote("|"))[8].equals("attractions")) {
                        catIcon.setImageResource(R.drawable.dealactivities);
                        System.out.println("GAAAAAA");

                    }
                    if (widerArrays.get(finalI).split(Pattern.quote("|"))[8].equals("accommodation")) {
                        catIcon.setImageResource(R.drawable.dealaccom);
                        System.out.println("GAAAAAA");

                    }
                    if (widerArrays.get(finalI).split(Pattern.quote("|"))[8].equals("food/dining")) {
                        catIcon.setImageResource(R.drawable.dealhospitality);
                        System.out.println("GAAAAAA");
                    }
                    if (widerArrays.get(finalI).split(Pattern.quote("|"))[8].equals("movies")) {
                        catIcon.setImageResource(R.drawable.dealentertainment);
                        System.out.println("GAAAAAA");
                    }
                    if (widerArrays.get(finalI).split(Pattern.quote("|"))[8].equals("shopping")) {
                        catIcon.setImageResource(R.drawable.dealshopping);
                        System.out.println("GAAAAAA");
                    }

                    try {
                        startTimers(widerArrays.get(finalI).split(Pattern.quote("|"))[5], finalNd.split(Pattern.quote("|"))[6], finalNd.split(Pattern.quote("|"))[7]);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }

//                    try {
//                        if (timersInitialized != false){
//
//                        }else {
//
//                            for (int e = 0; e < widerArrays.size(); e++){
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


//                    for (int i=0;i<widerArrays.size();i++) {
//                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[1]);
//                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[2]);
//                        System.out.println((widerArrays.get(i)).split(Pattern.quote("|"))[3]);
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
                    ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer1);
                    hiddenPanel.startAnimation(bottomUp);
                    hiddenPanel.setVisibility(View.VISIBLE);
//
//                    scroll.fullScroll(View.FOCUS_DOWN);

//                    ScrollView scrollView = (ScrollView) findViewById(R.id.scrolly);
//                    scrollView.setFocusableInTouchMode(true);
//                    scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
//                    SlidingDrawer sdf = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
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


            mMap.setOnMarkerClickListener(markerManager);

        }
        System.out.println("off cat just printed");

        System.out.println(widerArrays.size());
        System.out.println(widerArrays.size());

        System.out.println("ENDTIME PRINTED");

    }

    private void startTimers(String offEndTime, String offEndTime2, String offEndTime3) throws ParseException {
        final TextView mTextField = (TextView) findViewById(R.id.countdown);
        final TextView mTextField2 = (TextView) findViewById(R.id.countdown2);
        final TextView mTextField3 = (TextView) findViewById(R.id.countdown3);
        mTextField.setText("");
        mTextField2.setText("");
        mTextField3.setText("");
        if (timer != null) {
            timer.cancel();
            mTextField.setTextColor(Color.parseColor("#ffffff"));
            mTextField.clearAnimation();
        }
        if (timer2 != null) {
            timer2.cancel();
            mTextField2.setTextColor(Color.parseColor("#ffffff"));
            mTextField2.clearAnimation();
        }
        if (timer3 != null) {
            timer.cancel();
            mTextField3.setTextColor(Color.parseColor("#ffffff"));
            mTextField3.clearAnimation();
        }
//        System.out.println((((widerArrays.get(0)).split(Pattern.quote("|"))[3])));
//        String offerTime = (((widerArrays.get(0)).split(Pattern.quote("|"))[3]));

//        offerTime = offerTime.substring(0,8);
        System.out.println(offEndTime);
        System.out.println(offEndTime2);
        System.out.println(offEndTime3);

        String str = "2013-09-29T18:46:19Z";
        String currentTime = "2014-09-29T18:46:19Z";


        SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat sde = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));
        sde.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Pacific/Auckland"));

        Calendar cal = Calendar.getInstance();
//        System.out.println(sdf.format(cal));
//        System.out.println(dateFormat);
//        System.out.println(dateFormat.format(cal));
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());

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

        Date dater = new Date(epochDif);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(dater);
        System.out.println(dateFormatted);

        int grey = R.drawable.greycircl;
        int orange = R.drawable.orangecircl;
        int green = R.drawable.greencircl;

//        timerBar.setImageResource(R.drawable.threequartertime);
        semiCircleProgressBarView = new SemiCircleProgressBarView(MapsActivity.this, orange);

        semiCircleProgressBarView = (SemiCircleProgressBarView) findViewById(R.id.iconImageBg3);
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
        firstBar.setProgress((int) timerBarValue);


        final long finalTimerBarValue = timerBarValue;
        final long finalTimerBarValue1 = timerBarValue;
        timer = new CountDownTimer(epochDif, 1000) {

            public void onTick(long millisUntilFinished) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                long x = epochDif / 60000;

//here you can have your logic to set text to edittext
                mTextField.setText("" + String.format(String.valueOf(FORMAT),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                long modulo = millisUntilFinished % 60000;
                int length = String.valueOf(modulo).length();

                if (length == 3) {

                    System.out.println(dateFormat.format(date));
                    firstBar.setProgress((int) finalTimerBarValue);

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
                            mTextField.startAnimation(anim);
                            mTextField.setTextColor(Color.parseColor("#ff0000"));
                            String colourBar = "orange";
                            semiCircleProgressBarView.setBitMap(colourBar);

                        } else if (millisUntilFinished < 900000) {
                            String colourBar = "orange";
                            semiCircleProgressBarView.setBitMap(colourBar);
                            firstBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));


//                    timerBar.setImageResource(R.drawable.quartertime);

                        }
                        if (millisUntilFinished > 900000) {
                            firstBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

                        }


                    }

                    // 1,200,000 millis is 20 minutes so display three quarter bar


                    // 300,000 millis is 5 minutes so display one quarter bar

                    semiCircleProgressBarView.setClipping(x);


                }
            }

            public void onFinish() {
                mTextField.clearAnimation();
//                timerBar.setImageResource(R.drawable.notime);

                mTextField.setTextColor(Color.parseColor("#ff0000"));
                mTextField.setText("Offer has ended");
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

            semiCircleProgressBarView2 = (SemiCircleProgressBarView) findViewById(R.id.semicirc2);
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


            secondBar.setProgress((int) timerBarValue2);
            timer2 = new CountDownTimer(epochDifOffr2, 1000) {

                public void onTick(long millisUntilFinished) {

                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    long x = epochDifOffr2 / 60000;

//here you can have your logic to set text to edittext
                    mTextField2.setText("" + String.format(String.valueOf(FORMAT),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    long modulo = millisUntilFinished % 60000;
                    int length = String.valueOf(modulo).length();

                    if (length == 3) {

                        System.out.println(dateFormat.format(date));
                        secondBar.setProgress((int) timerBarValue2);

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
                                mTextField2.startAnimation(anim);
                                mTextField2.setTextColor(Color.parseColor("#ff0000"));
                                String colourBar = "orange";
                                semiCircleProgressBarView2.setBitMap(colourBar);
                            } else if (millisUntilFinished < 900000) {
                                String colourBar = "orange";
                                semiCircleProgressBarView2.setBitMap(colourBar);
                                secondBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));
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
                    mTextField2.clearAnimation();
                    String colourBar = "grey";
                    semiCircleProgressBarView2.setClipping(0);
                    semiCircleProgressBarView2.setBitMap(colourBar);
                    mTextField2.setTextColor(Color.parseColor("#ff0000"));
                    mTextField2.setText("Offer has ended");
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

            semiCircleProgressBarView3 = (SemiCircleProgressBarView) findViewById(R.id.semicirc3);
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


            thirdBar.setProgress((int) timerBarValue3);

            timer3 = new CountDownTimer(epochDifOffr3, 1000) {

                public void onTick(long millisUntilFinished) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    long x = epochDifOffr3 / 60000;

//here you can have your logic to set text to edittext
                    mTextField3.setText("" + String.format(String.valueOf(FORMAT),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    long modulo = millisUntilFinished % 60000;
                    int length = String.valueOf(modulo).length();

                    if (length == 3) {

                        System.out.println(dateFormat.format(date));
                        thirdBar.setProgress((int) timerBarValue3);

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
                                mTextField3.startAnimation(anim);
                                mTextField3.setTextColor(Color.parseColor("#ff0000"));
                                String colourBar = "orange";
                                semiCircleProgressBarView3.setBitMap(colourBar);
                            } else if (millisUntilFinished < 900000) {
                                String colourBar = "orange";
                                thirdBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));
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
                    mTextField3.clearAnimation();
                    String colourBar = "grey";
                    semiCircleProgressBarView3.setBitMap(colourBar);
                    mTextField3.setTextColor(Color.parseColor("#ff0000"));
                    mTextField3.setText("Offer has ended");
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
        TextView retailerName = (TextView) findViewById(R.id.retailerName);
        TextView tvr = (TextView) findViewById(R.id.retailerNamesy);
        TextView tvf = (TextView) findViewById(R.id.retailerNamesz);
        TextView tvd = (TextView) findViewById(R.id.retailerNamesd);
        ePzl.putString("retailer", retailerName.getText().toString());
        ePzl.putString("offerDesc1", retailerName.getText().toString());
        System.out.println(tvr.getText().toString());
        intent.putExtra("retailer", retailerName.getText().toString());
        intent.putExtra("offerDesc1", tvr.getText().toString());
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
                    for (int i = 0; i < widerArrays.size(); i++) {
                        String retailerHash = widerArrays.get(i).split(Pattern.quote("|"))[4].substring(3, 10);

                        if (retailerHash.equals(nearHashes.get(0)) || retailerHash.equals(nearHashes.get(1))
                                || retailerHash.equals(nearHashes.get(2)) ||
                                retailerHash.equals(nearHashes.get(3)) ||
                                retailerHash.equals(nearHashes.get(4)) ||
                                retailerHash.equals(nearHashes.get(5)) ||
                                retailerHash.equals(nearHashes.get(6)) ||
                                retailerHash.equals(nearHashes.get(7)) ||
                                retailerHash.equals(nearHashes.get(8))) {
                            System.out.println(widerArrays.get(i));
                            System.out.println(widerArrays.size());

                            nearbyRetNames.add(count, widerArrays.get(i).split(Pattern.quote("|"))[2]);
                            offerDesc1.add(count, widerArrays.get(i).split(Pattern.quote("|"))[8]);
                            offerDesc2.add(count, widerArrays.get(i).split(Pattern.quote("|"))[10]);
                            offerDesc3.add(count, widerArrays.get(i).split(Pattern.quote("|"))[13]);

                            count++;

                        }
                    }

                    final FrameLayout contentNearby = (FrameLayout) findViewById(R.id.contentNearby);
                    final FrameLayout contentNearby2 = (FrameLayout) findViewById(R.id.contentNearby2);
                    final FrameLayout contentNearby3 = (FrameLayout) findViewById(R.id.contentNearby3);
                    final FrameLayout contentNearby4 = (FrameLayout) findViewById(R.id.contentNearby4);
                    final FrameLayout contentNearby5 = (FrameLayout) findViewById(R.id.contentNearby5);
                    final FrameLayout contentNearby6 = (FrameLayout) findViewById(R.id.contentNearby6);
                    final FrameLayout contentNearby7 = (FrameLayout) findViewById(R.id.contentNearby7);
                    final FrameLayout contentNearby8 = (FrameLayout) findViewById(R.id.contentNearby8);

                    final FrameLayout content2 = (FrameLayout) findViewById(R.id.content2);

                    final FrameLayout content3 = (FrameLayout) findViewById(R.id.content3);
                    final FrameLayout content4 = (FrameLayout) findViewById(R.id.content4);
                    final FrameLayout couponOffer1 = (FrameLayout) findViewById(R.id.content28s3);
                    final FrameLayout couponOffer2 = (FrameLayout) findViewById(R.id.content2z);
                    final FrameLayout couponOffer3 = (FrameLayout) findViewById(R.id.content2d);

                    final ImageView greyBack = (ImageView) findViewById(R.id.iconImageBg4);
                    final SemiCircleProgressBarView semiCirc = (SemiCircleProgressBarView) findViewById(R.id.iconImageBg3);
                    final SemiCircleProgressBarView semiCirc2 = (SemiCircleProgressBarView) findViewById(R.id.semicirc2);
                    final SemiCircleProgressBarView semiCirc3 = (SemiCircleProgressBarView) findViewById(R.id.semicirc3);
                    final ImageView starsOverlay = (ImageView) findViewById(R.id.starsoverlay);

                    ImageView starIcon = (ImageView) findViewById(R.id.starsoverlay);
                    final ImageView multiOffersStars = (ImageView) findViewById(R.id.multiOffersStars);
                    final ImageView multiOffersStars2 = (ImageView) findViewById(R.id.multiOffersStars2);
                    final ImageView multiOffersStars3 = (ImageView) findViewById(R.id.multiOffersStars3);

                    final TextView retailerNamesy8s3 =(TextView)findViewById(R.id.retailerNamesy8s3);
                    final TextView retailerNamesyzs3 =(TextView)findViewById(R.id.retailerNameszs3);
                    final TextView retailerNamesds3 =(TextView)findViewById(R.id.retailerNamesds3);
                    couponOffer1.setVisibility(View.GONE);
                    couponOffer2.setVisibility(View.GONE);
                    couponOffer3.setVisibility(View.GONE);
                    retailerNamesy8s3.setVisibility(View.GONE);
                    retailerNamesyzs3.setVisibility(View.GONE);
                    retailerNamesds3.setVisibility(View.GONE);
                    try {
                        retName.setText(nearbyRetNames.get(0).toString());

                        contentNearby.setVisibility(View.VISIBLE);
                        retailerNamesy8s3.setText(offerDesc1.get(0).toString());

                        contentNearby.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                greyBack.setVisibility(View.VISIBLE);
                                semiCirc.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);

//
                                semiCirc2.setVisibility(View.GONE);
                                semiCirc3.setVisibility(View.GONE);

                                contentNearby.setVisibility(View.GONE);
                                contentNearby2.setVisibility(View.GONE);
                                contentNearby3.setVisibility(View.GONE);
                                contentNearby4.setVisibility(View.GONE);
                                contentNearby5.setVisibility(View.GONE);
                                contentNearby6.setVisibility(View.GONE);
                                contentNearby7.setVisibility(View.GONE);
                                contentNearby8.setVisibility(View.GONE);
                                couponOffer1.setVisibility(View.VISIBLE);
                                retailerNamesy8s3.setVisibility(View.VISIBLE);
                                couponOffer2.setVisibility(View.GONE);
                                couponOffer3.setVisibility(View.GONE);
                                System.out.println("content2");


                            }
                        });
                    }
                    catch(Exception e) {
                        System.out.println(e);
                        contentNearby.setVisibility(View.GONE);
                    }
                    try {
                        retName2.setText(nearbyRetNames.get(1).toString());
                        contentNearby2.setVisibility(View.VISIBLE);
                        retailerNamesyzs3.setText(offerDesc1.get(0).toString());

                        contentNearby2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                greyBack.setVisibility(View.VISIBLE);
                                semiCirc.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);

//
                                semiCirc2.setVisibility(View.GONE);
                                semiCirc3.setVisibility(View.GONE);

                                contentNearby.setVisibility(View.GONE);
                                contentNearby2.setVisibility(View.GONE);
                                contentNearby3.setVisibility(View.GONE);
                                contentNearby4.setVisibility(View.GONE);
                                contentNearby5.setVisibility(View.GONE);
                                contentNearby6.setVisibility(View.GONE);
                                contentNearby7.setVisibility(View.GONE);
                                contentNearby8.setVisibility(View.GONE);
                                couponOffer1.setVisibility(View.GONE);
                                retailerNamesyzs3.setVisibility(View.VISIBLE);
                                couponOffer2.setVisibility(View.VISIBLE);
                                couponOffer3.setVisibility(View.GONE);
                                System.out.println("content2");


                            }
                    });
                    }
                    catch(Exception e) {
                        System.out.println(e);
                        contentNearby2.setVisibility(View.GONE);
                    }
                    try {
                        retName3.setText(nearbyRetNames.get(2).toString());
                        contentNearby3.setVisibility(View.VISIBLE);
                        retailerNamesds3.setText(offerDesc1.get(0).toString());

                        contentNearby3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                backStack = true;
                                greyBack.setVisibility(View.VISIBLE);
                                semiCirc.setVisibility(View.VISIBLE);
                                starsOverlay.setVisibility(View.VISIBLE);

//
                                semiCirc2.setVisibility(View.GONE);
                                semiCirc3.setVisibility(View.GONE);

                                contentNearby.setVisibility(View.GONE);
                                contentNearby2.setVisibility(View.GONE);
                                contentNearby3.setVisibility(View.GONE);
                                contentNearby4.setVisibility(View.GONE);
                                contentNearby5.setVisibility(View.GONE);
                                contentNearby6.setVisibility(View.GONE);
                                contentNearby7.setVisibility(View.GONE);
                                contentNearby8.setVisibility(View.GONE);
                                couponOffer1.setVisibility(View.GONE);
                                retailerNamesds3.setVisibility(View.VISIBLE);
                                couponOffer2.setVisibility(View.GONE);
                                couponOffer3.setVisibility(View.VISIBLE);
                                System.out.println("content2");


                            }
                        });

                    }
                    catch(Exception e) {
                        System.out.println(e);
                        contentNearby3.setVisibility(View.GONE);
                    }
                    try {
                        retName4.setText(nearbyRetNames.get(3).toString());
                        contentNearby4.setVisibility(View.VISIBLE);
                    }
                    catch(Exception e) {
                        System.out.println(e);
                        contentNearby4.setVisibility(View.GONE);
                    }
                    try {
                        retName5.setText(nearbyRetNames.get(4).toString());
                        contentNearby5.setVisibility(View.VISIBLE);
                    }
                    catch(Exception e) {
                        System.out.println(e);
                        contentNearby5.setVisibility(View.GONE);
                    }
                    try {
                        retName6.setText(nearbyRetNames.get(5).toString());
                        contentNearby6.setVisibility(View.VISIBLE);
                    }
                    catch(Exception e) {
                        System.out.println(e);
                        contentNearby6.setVisibility(View.GONE);
                    }
                    try {
                        retName7.setText(nearbyRetNames.get(6).toString());
                        contentNearby7.setVisibility(View.VISIBLE);
                    }
                    catch(Exception e) {
                        System.out.println(e);
                        contentNearby7.setVisibility(View.GONE);
                    }
                    try {
                        retName8.setText(nearbyRetNames.get(7).toString());
                        contentNearby8.setVisibility(View.VISIBLE);
                    }
                    catch(Exception e) {
                        System.out.println(e);
                        contentNearby8.setVisibility(View.GONE);
                    }

                    System.out.println(widerArrays.get(2).split(Pattern.quote("|"))[4].substring(3, 10));
                    System.out.println(widerArrays.get(3));
                    System.out.println(widerArrays.get(4));
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
        MyTask task = new MyTask();
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