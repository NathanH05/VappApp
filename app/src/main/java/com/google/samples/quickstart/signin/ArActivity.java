
        package com.google.samples.quickstart.signin;

        import android.Manifest;
        import android.annotation.TargetApi;
        import android.app.FragmentManager;
        import android.content.ComponentName;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.LabeledIntent;
        import android.content.pm.PackageManager;
        import android.content.pm.ResolveInfo;
        import android.content.res.Configuration;
        import android.content.res.Resources;
        import android.graphics.Color;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.location.Address;
        import android.location.Geocoder;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.media.MediaPlayer;
        import android.media.Ringtone;
        import android.media.RingtoneManager;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.preference.ListPreference;
        import android.preference.Preference;
        import android.preference.PreferenceFragment;
        import android.preference.PreferenceManager;
        import android.preference.RingtonePreference;
        import android.provider.Settings;
        import android.support.annotation.NonNull;
        import android.support.design.widget.NavigationView;
        import android.support.v4.app.ActivityCompat;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.Html;
        import android.text.TextUtils;
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
        import android.webkit.JavascriptInterface;
        import android.widget.Button;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.PopupWindow;
        import android.widget.RelativeLayout;
        import android.widget.ScrollView;
        import android.widget.SlidingDrawer;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.myfirstapp.FullscreenActivity;
        import com.example.myfirstapp.R;
        import com.example.myfirstapp.SettingsActivity;
        import com.example.myfirstapp.SignInActivity;
        import com.example.myfirstapp.couponActivity;
        import com.example.myfirstapp.help;
        import com.example.myfirstapp.paymentscreen;
        import com.google.android.gms.appindexing.Action;
        import com.google.android.gms.appindexing.AppIndex;
        import com.google.android.gms.appindexing.Thing;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.wikitude.architect.ArchitectView;
        import com.wikitude.architect.StartupConfiguration;

        import java.io.IOException;
        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.List;
        import java.util.Locale;
        import java.util.TimeZone;
        import java.util.concurrent.TimeUnit;
        import java.util.regex.Pattern;

        import static com.example.myfirstapp.R.layout.loggedout;

public class ArActivity extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener, ArchitectView.ArchitectUrlListener, NavigationView.OnNavigationItemSelectedListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10;
    Button btnslidedown;
    Animation animationslidedown;
    ImageView img1;
    ArchitectView.ArchitectUrlListener urlListener;
    String name = "";
    SensorManager sensorManager;
    Sensor sensor;
    List<MyTask> tasks;
    boolean orientationScape = true;
    Animation animationSlideDown;
    boolean cameFromButton = false;
    int cameFromCounter =1;
    Boolean firstOrLast;
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
    CountDownTimer timer;
    CountDownTimer timer2;
    CountDownTimer timer3;
    SemiCircleProgressBarView semiCircleProgressBarView;
    long epochDif;
    int counter = 0;
    String website ="";
    String deal ="";

    @Override
    public boolean urlWasInvoked(String s) {
        Toast.makeText(this, "dfedfdf", Toast.LENGTH_SHORT).show();
        return false;
    }
    public void logout(View view){
        Intent intent = new Intent(ArActivity.this,SignInActivity.class);
        startActivity(intent);

    }    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.settings) {
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new FirstFragment()).commit();
            Intent intent = new Intent(ArActivity.this, SettingsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.lefter, R.anim.lefter);
        } else if (id == R.id.coupons) {
            Intent intent = new Intent(ArActivity.this, couponsDash.class);
            startActivity(intent);
        } else if (id == R.id.help) {
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new ThirdFragment()).commit();
            Intent intent = new Intent(ArActivity.this, help.class);
            startActivity(intent);
        } else if (id == R.id.loginlogout) {
            RelativeLayout mLinearLayout = (RelativeLayout) findViewById(R.id.activity_main2);
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

            Button remove=(Button) customView.findViewById(R.id.ib_close);

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
            Intent intent = new Intent(ArActivity.this, paymentscreen.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class JavaScriptInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void showToast() {
            Toast.makeText(ArActivity.this, "hrh", Toast.LENGTH_SHORT).show();
        }
    }

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
            int orientation = getResources().getConfiguration().orientation;


            if ((int) x > 1 && orientation == 1) {
                Intent intent = new Intent(ArActivity.this, MapsActivity.class);
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


    private ArchitectView architectView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private float x1, x2;
    static final int MIN_DISTANCE = 150;
    SlidingDrawer slidingdrawer;
    Button SlidingButton;
/*
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("NewApi")
    */

    public void hideSlider(View view) {
        Toast.makeText(ArActivity.this, "Slide draw func ran in hideSlider()", Toast.LENGTH_LONG).show();

    }

    public void hello(String s) {
        Toast.makeText(ArActivity.this, s, Toast.LENGTH_LONG).show();

    }


    public void openRetailerSite(View view) {
//        TextView website = (TextView) findViewById(R.id.website);
//        String url = "http://" + website.getText().toString();
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(url));
//        startActivity(i);
    }


    public ArchitectView.ArchitectUrlListener getUrlListener() {
        return new ArchitectView.ArchitectUrlListener() {

            @Override
            public boolean urlWasInvoked(String uriString) {
                System.out.println("URL INVOKED");

                Uri invokedUri = Uri.parse(uriString);
                if(!uriString.split(Pattern.quote("|"))[1].equals("worldclicked")){
                    System.out.println(invokedUri);
                    String retailerName = uriString.split(Pattern.quote("|"))[1];
                    deal = uriString.split(Pattern.quote("|"))[2];
                    String category = uriString.split(Pattern.quote("|"))[3];
                    String endTme = uriString.split(Pattern.quote("|"))[4];
                    String duration = uriString.split(Pattern.quote("|"))[5];
                     website = uriString.split(Pattern.quote("|"))[6];
                    String offerDescription2 = uriString.split(Pattern.quote("|"))[7];
                    String offerDescription3 = uriString.split(Pattern.quote("|"))[8];
                    String rating = uriString.split(Pattern.quote("|"))[9];

                    String offerEndTime2 = uriString.split(Pattern.quote("|"))[10];
                    String offerEndTime3 = uriString.split(Pattern.quote("|"))[11];

                    ImageView iconImage = (ImageView)findViewById(R.id.ofsdIconImage);
                    if( category.equals("drinks")){
                        iconImage.setImageResource(R.drawable.drinks);

                    }
                    else if(category.equals("shopping")){
                        iconImage.setImageResource(R.drawable.shopping);

                    }
                    else if(category.equals("food/dining")){
                        iconImage.setImageResource(R.drawable.fooddining);

                    }
                    else if(category.equals("accomodation")){
                        iconImage.setImageResource(R.drawable.accommodation);

                    }
                    else if(category.equals("movies")){
                        iconImage.setImageResource(R.drawable.movies);

                    }
                    else if(category.equals("attractions")){
                        iconImage.setImageResource(R.drawable.activities);
                    }

                    System.out.println(offerEndTime2);
                    System.out.println(offerEndTime3);
                    try {
                        startTimers(endTme, offerEndTime2, offerEndTime3);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    TextView tv = (TextView) findViewById(R.id.retailerName);
                    tv.setText(retailerName);
                    TextView tvr = (TextView) findViewById(R.id.retailerNamesy);
                    tvr.setText(deal);
//                    TextView tvm = (TextView) findViewById(R.id.retailerNamesz);
//                    tvm.setText(offerDescription2);
//                    TextView tvn = (TextView) findViewById(R.id.retailerNamesd);
//                    tvn.setText(offerDescription3);
//                    ImageView stars = (ImageView) findViewById(R.id.stars);
//                    if (rating.equals("1")) {
//                        stars.setImageResource(R.drawable.onestar);
//                    }
//                    if (rating.equals("2")) {
//                        stars.setImageResource(R.drawable.twostar);
//                    }
//                    if (rating.equals("3")) {
//                        stars.setImageResource(R.drawable.threestar);
//                    }
//                    if (rating.equals("4")) {
//                        stars.setImageResource(R.drawable.fourstar);
//                    }
//                    if (rating.equals("5")) {
//                        stars.setImageResource(R.drawable.fivestar);
//                    }

//                    TextView websiteTV = (TextView) findViewById(R.id.website);
//                    websiteTV.setText(website);
//
//                    FrameLayout offerBox2 = (FrameLayout) findViewById(R.id.content2z);
//                    FrameLayout offerBox3 = (FrameLayout) findViewById(R.id.content2d);

//                    if (offerDescription2.equals("offer 2 empty")) {
//                        System.out.println("true2");
//                        offerBox2.setVisibility(FrameLayout.GONE);
//                    } else {
//                        offerBox2.setVisibility(FrameLayout.VISIBLE);
//                    }
//
//                    if (offerDescription3.equals("offer 3 empty")) {
//                        System.out.println("true3");
//                        offerBox3.setVisibility(FrameLayout.GONE);
//                    } else {
//                        offerBox3.setVisibility(FrameLayout.VISIBLE);
//                    }


//                System.out.println(marker.getSnippet().split(Pattern.quote("|"))[2]);
////                    TextView tvf = (TextView) findViewById(R.id.retailerNamesz);
////                    tvf.setText(marker.getSnippet().split(Pattern.quote("|"))[1]);
////                    TextView tvd = (TextView) findViewById(R.id.retailerNamesd);
//                LinearLayout lin = (LinearLayout) findViewById(R.id.content2d);
//                LinearLayout lin2 = (LinearLayout) findViewById(R.id.content2z);
//                LinearLayout lin3 = (LinearLayout) findViewById(R.id.content2d);
//                lin.setVisibility(LinearLayout.VISIBLE);
//                lin2.setVisibility(LinearLayout.VISIBLE);


//                        if (marker.getSnippet().split(Pattern.quote("|"))[4].equals("offer 3 empty")) {
//                            lin.setVisibility(LinearLayout.INVISIBLE);
//                    System.out.println("offer3 is empty");
//                        }
//                        if (marker.getSnippet().split(Pattern.quote("|"))[1].equals("offer 2 empty")){
//                            lin2.setVisibility(LinearLayout.INVISIBLE);
//                                            System.out.println("offer2 is empty");
//                }
//                if (marker.getSnippet().split(Pattern.quote("|"))[1].equals("offer 2 empty")){
//                    lin2.setVisibility(LinearLayout.INVISIBLE);
//                }
//                else{
//                    TextView tvf = (TextView) findViewById(R.id.retailerNamesz);
//                    tvf.setText(marker.getSnippet().split(Pattern.quote("|"))[1]);
//
//                }
//                if (marker.getSnippet().split(Pattern.quote("|"))[4].equals("All scones, half price!")){
//                    lin3.setVisibility(LinearLayout.INVISIBLE);
//                }
//                else{
//                    TextView tvd = (TextView) findViewById(R.id.retailerNamesd);
//                    tvd.setText(marker.getSnippet().split(Pattern.quote("|"))[4]);
//                }
//                if (marker.getSnippet().split(Pattern.quote("|"))[4].equals("offer 3 empty")){
//                    lin3.setVisibility(LinearLayout.INVISIBLE);
//                }
//                else{
//                    TextView tvd = (TextView) findViewById(R.id.retailerNamesd);
//                    tvd.setText(marker.getSnippet().split(Pattern.quote("|"))[4]);
//                }
//
//
//                TextView tvn = (TextView) findViewById(R.id.website);
//                tvn.setText(marker.getSnippet().split(Pattern.quote("|"))[3]);
//                ImageView tvg = (ImageView) findViewById(R.id.stars);
//                if (marker.getSnippet().split(Pattern.quote("|"))[2].equals("1")){
//                    tvg.setImageResource(R.drawable.onestar);
//                }
//                if (marker.getSnippet().split(Pattern.quote("|"))[2].equals("2")){
//                    tvg.setImageResource(R.drawable.twostar);
//                }
//                if (marker.getSnippet().split(Pattern.quote("|"))[2].equals("3")){
//                    tvg.setImageResource(R.drawable.threestar);
//                }
//                if (marker.getSnippet().split(Pattern.quote("|"))[2].equals("4")){
//                    tvg.setImageResource(R.drawable.fourstar);
//                }
//                if (marker.getSnippet().split(Pattern.quote("|"))[2].equals("5")){
//                    tvg.setImageResource(R.drawable.fivestar);
//                }



                    Animation bottomDown = AnimationUtils.loadAnimation(ArActivity.this,
                            R.anim.bottom_down);
                    Animation bottomUp = AnimationUtils.loadAnimation(ArActivity.this,
                            R.anim.bottom_up);
                    ScrollView scrolly = (ScrollView)findViewById(R.id.scrolly);
                    scrolly.fullScroll(View.FOCUS_DOWN);
                    ViewGroup hiddenPanel = (ViewGroup)findViewById(R.id.slidingDrawer2);
                    if(hiddenPanel.getVisibility() == View.VISIBLE) {
                        hiddenPanel.startAnimation(bottomDown);
                        hiddenPanel.setVisibility(View.GONE);
                    }

                    ViewGroup hiddenPanel2 = (ViewGroup)findViewById(R.id.slidingDrawer1);
                    if(hiddenPanel2.getVisibility() == View.VISIBLE) {
                        hiddenPanel2.startAnimation(bottomDown);
                        hiddenPanel2.setVisibility(View.GONE);
                    }
                    else{

                        hiddenPanel2.startAnimation(bottomUp);
                        hiddenPanel2.setVisibility(View.VISIBLE);
                    }
                }

                else {
                    Animation bottomDown = AnimationUtils.loadAnimation(ArActivity.this,
                            R.anim.bottom_down);
                    ViewGroup hiddenPanel = (ViewGroup)findViewById(R.id.slidingDrawer2);
                    if(hiddenPanel.getVisibility() == View.VISIBLE) {
                        firstOrLast = false;
                        String first = "firstAndLastFilt(" + firstOrLast +")";
                        architectView.callJavascript(first);                        hiddenPanel.startAnimation(bottomDown);
                        hiddenPanel.setVisibility(View.GONE);
                    }
                    cameFromButton = false;
                    String streetName = "filterByStreet(" + '"' + name + '"' + "," + cameFromButton + ")";
                    System.out.println(streetName);
                    architectView.callJavascript(streetName);
                }

                return true;

            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main2);
        ImageView nodPhone = (ImageView) findViewById(R.id.nodPhone);
        nodPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+64273581288"));
                if (ActivityCompat.checkSelfPermission(ArActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }
        });

        ImageView email = (ImageView) findViewById(R.id.nodEmail);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String website2 = null;
                TextView messagetv = (TextView) findViewById(R.id.nodSingleCouponOffer1Text);
                String message = deal;

                if (website.substring(website.length() - 4, website.length()) == "com") {
                    website2 = website.substring(4, (website.length() - 4));
                    System.out.println(website2);
                } else {
                    website2 = website.substring(4, website.length() - 6);
                    System.out.println(website2);
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Find My Deal, Special Offer");
                intent.putExtra(Intent.EXTRA_TEXT, message);
                intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{website2 + "@gmail.com"});
                Intent mailer = Intent.createChooser(intent, null);
                startActivity(mailer);
            }
        });
        final ImageView websitey = (ImageView) findViewById(R.id.nodWebsitey);
        websitey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://" + website;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        View myView = findViewById(R.id.toolbar2);
        myView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public static final String DEBUG_TAG = "error";

            public void onSwipeRight() {
                Intent intent = new Intent(ArActivity.this, MapsActivity.class);
                startActivity(intent);
            }

        });
        ScrollView scroll = (ScrollView)findViewById(R.id.scrolly);
        scroll.fullScroll(View.FOCUS_DOWN);
        ImageView closeButtn = (ImageView) findViewById(R.id.closeButtn);

        final Button streetFilt = (Button)findViewById(R.id.streetFilt);
        streetFilt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameFromButton = true;
                cameFromCounter ++;
                streetFilt.setBackgroundColor(getResources().getColor(R.color.blue_grey_500));
                if(cameFromCounter % 2 == 0) {
                    cameFromButton = true;
                    String streetName = "filterByStreet(" + '"' + name + '"' + "," + cameFromButton + ")";
                    System.out.println(streetName);
                    architectView.callJavascript(streetName);
                }
                else{
                    streetFilt.setBackgroundColor(getResources().getColor(R.color.white));
                    cameFromButton = false;
                    String streetName = "filterByStreet(" + '"' + name + '"' + "," + cameFromButton + ")";
                    System.out.println(streetName);
                    architectView.callJavascript(streetName);
                }
            }
        });

        ImageView shareButton = (ImageView) findViewById(R.id.nodShare);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvy = (TextView) findViewById(R.id.retailerNamesy);
//                TextView tvz = (TextView) findViewById(R.id.retailerNamesz);
//                TextView tvd = (TextView) findViewById(R.id.retailerNamesd);
                TextView retName = (TextView) findViewById(R.id.retailerName);
                String offer2 = null;
                String offer3 = null;
//                if (tvz.getText().toString().equals("offer 2 empty")) {
//                    offer2 = "";
//
//                } else {
//                    offer2 = tvz.getText().toString();
//
//                }
//                if (tvd.getText().toString().equals("offer 3 empty")) {
//                    offer3 = "";
//
//                } else {
//                    offer3 = tvd.getText().toString();

//                }

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
        closeButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bottomDown = AnimationUtils.loadAnimation(ArActivity.this,
                        R.anim.bottom_down);
                ViewGroup hiddenPanel = (ViewGroup)findViewById(R.id.slidingDrawer1);
                hiddenPanel.startAnimation(bottomDown);
                hiddenPanel.setVisibility(View.GONE);
            }
        });
        this.architectView = (ArchitectView) this.findViewById(R.id.architectView);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
        MyTask task = new MyTask();
        task.execute();

        getUrlListener();

        final ImageView imgFavorite = (ImageView) findViewById(R.id.drinks);
        final ImageView imgFavorite2 = (ImageView) findViewById(R.id.shopping);
        final ImageView imgFavorite3 = (ImageView) findViewById(R.id.accommodation);
        final ImageView imgFavorite6 = (ImageView) findViewById(R.id.fooddining);
        final ImageView imgFavorite7 = (ImageView) findViewById(R.id.movies);
        final ImageView imgFavorite8 = (ImageView) findViewById(R.id.attractions);

        imgFavorite.getBackground().setAlpha(255);
        imgFavorite2.getBackground().setAlpha(255);
        imgFavorite3.getBackground().setAlpha(255);
        imgFavorite6.getBackground().setAlpha(255);
        imgFavorite7.getBackground().setAlpha(255);
        imgFavorite8.getBackground().setAlpha(255);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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


        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if (drinksIsClicked == false) {
                    imgFavorite.setBackgroundResource(R.drawable.drinks);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarDrinks);
//                    goldBar.setVisibility(View.INVISIBLE);
                    drinksIsClicked = true;
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);
                } else {
                    imgFavorite.setBackgroundResource(R.drawable.drinksgrey);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarDrinks);
//                    goldBar.setVisibility(View.VISIBLE);
                    drinksIsClicked = false;
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);
                }

                System.out.println(drinksIsClicked);

                v.startAnimation(AnimationUtils.loadAnimation(ArActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });

        imgFavorite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();

                if (shoppingIsClicked == false) {
                    imgFavorite2.setBackgroundResource(R.drawable.shopping);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarShopping);
//                    goldBar.setVisibility(View.INVISIBLE);
                    System.out.println("goldbar shpng is clicked fired and was false");
                    shoppingIsClicked = true;
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);
                } else {
                    imgFavorite2.setBackgroundResource(R.drawable.shoppinggrey);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarShopping);
//                    goldBar.setVisibility(View.VISIBLE);
                    shoppingIsClicked = false;
                    System.out.println("goldbar shpng is clicked fired and was true");
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);

                }
                v.startAnimation(AnimationUtils.loadAnimation(ArActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });
        imgFavorite3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                if (accomodationIsClicked == false) {
                    imgFavorite3.setBackgroundResource(R.drawable.accommodation);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarAccomodation);
//                    goldBar.setVisibility(View.INVISIBLE);
                    accomodationIsClicked = true;
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);
                } else {
                    imgFavorite3.setBackgroundResource(R.drawable.accommodationgrey);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarAccomodation);
//                    goldBar.setVisibility(View.VISIBLE);
                    accomodationIsClicked = false;
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);
                }

                v.startAnimation(AnimationUtils.loadAnimation(ArActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });


        imgFavorite6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if (foodDiningIsClicked == false) {
                    imgFavorite6.setBackgroundResource(R.drawable.fooddining);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarFoodDining);
//                    goldBar.setVisibility(View.INVISIBLE);
                    foodDiningIsClicked = true;
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);

                } else {
                    imgFavorite6.setBackgroundResource(R.drawable.fooddininggrey);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarFoodDining);
//                    goldBar.setVisibility(View.VISIBLE);
                    foodDiningIsClicked = false;

//        try {
//            this.architectView.load("file:///android_asset/../js/multiplepois.js");
//        }catch (IOException e){
//        }

                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);
                }

                v.startAnimation(AnimationUtils.loadAnimation(ArActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });
        imgFavorite7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if (moviesIsClicked == false) {
                    imgFavorite7.setBackgroundResource(R.drawable.movies);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarMovies);
//                    goldBar.setVisibility(View.INVISIBLE);
                    moviesIsClicked = true;
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);
                } else {
                    imgFavorite7.setBackgroundResource(R.drawable.moviesgrey);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarMovies);
//                    goldBar.setVisibility(View.VISIBLE);
                    moviesIsClicked = false;
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);
                }


                v.startAnimation(AnimationUtils.loadAnimation(ArActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });
        imgFavorite8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                System.out.println("Image Fav fired");
                if (attractionsIsClicked == false) {
                    imgFavorite8.setBackgroundResource(R.drawable.activities);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarAttractions);
//                    goldBar.setVisibility(View.INVISIBLE);
                    System.out.println("attractions is clicked was false");

                    attractionsIsClicked = true;
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);

                } else {
                    imgFavorite8.setBackgroundResource(R.drawable.activitiesgrey);
//                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarAttractions);
//                    goldBar.setVisibility(View.VISIBLE);
                    System.out.println("attractions is clicked was true");

                    attractionsIsClicked = false;
                    String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
                    ArActivity.this.architectView.callJavascript(passedBools);
                }


                v.startAnimation(AnimationUtils.loadAnimation(ArActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });


// ArchitectView.ArchitectUrlListener listener;
//        listener = this.getUrlListener();
//        architectView.callJavascript("cat");
//        ArchitectView architectViewy = (ArchitectView) findViewById(R.id.architectView);
//
//        architectViewy.setOnClickListener(new ArchitectView.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ArActivity.this, "architectView", Toast.LENGTH_LONG).show();
//
//                hideSlider(v);
//            }
//        });
//showToast();

//            slidingdrawer = (SlidingDrawer)findViewById(R.id.slidingDrawer1);
//            SlidingButton = (Button)findViewById(R.id.handle);
//
//            slidingdrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
//
//                @Override
//                public void onDrawerOpened() {
//
//                    Toast.makeText(ArActivity.this, "Sliding drawer open", Toast.LENGTH_LONG).show();
//                    ((ScrollView) findViewById(R.id.content)).post(new Runnable() {
//                        public void run() {
//                            ((ScrollView) findViewById(R.id.content)).fullScroll(View.FOCUS_UP);
//                        }
//                    });
//
//
//                    ScrollView scrollyView = (ScrollView) findViewById(R.id.content);
//                    scrollyView.setFocusableInTouchMode(true);
//                    scrollyView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
//                }
//            });
//
//            slidingdrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
//
//                public void onDrawerClosed() {
//
//                    Toast.makeText(ArActivity.this, "Sliding drawer close", Toast.LENGTH_LONG).show();
//
//                }
//
//            });
//
//        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.activity_main2);
//        rlayout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ArActivity.this, "Slide draw func ran", Toast.LENGTH_LONG).show();
//                slidingdrawer.open();
//
//            }
//
//        });
//
//
//
//        System.out.println("onCreate");


        // Set the activity main2 as the

/*
        //btnslidedown = (Button) findViewById(R.id.btnslidedown);
        final boolean[] chk = {false};
        animationslidedown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slid_down);
        animationslidedown.setAnimationListener(this);
        btnslidedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk[0] == false){
                    final TextView textviewy = (TextView)findViewById(R.id.textyView);
                    textviewy.setVisibility(View.VISIBLE);
                    chk[0] = true;
                }
                else{
                    final TextView textviewy = (TextView)findViewById(R.id.textyView);
                    textviewy.setVisibility(View.GONE);
                    chk[0] = false;
                }
            }
        });
        */
        Toast toast = Toast.makeText(this, "Be careful crossing the road with Vapp app", Toast.LENGTH_SHORT);
        toast.show();
        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(ArActivity.this,
                Manifest.permission.WRITE_CALENDAR);

// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(ArActivity.this,
                Manifest.permission.INTERNET
        )
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ArActivity.this,
                    Manifest.permission.CAMERA)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(ArActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.LOCATION_HARDWARE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        // Assign the button in the layout activity to the button variable
        // button = (Button) findViewById(R.id.button);
        // Assign the text view in the layout activity to the textView variable

//        public Boolean ArchitectUrlListener getUrlListener(){
//
//        };

        // Assign the wikitude architect view to the architect view component in the layout
        this.architectView = (ArchitectView) this.findViewById(R.id.architectView);
        ArchitectView.ArchitectUrlListener getUrlListener = getUrlListener();


        // Give the startup config your wikitude trial key and pass it to the architect views onCreate method
        final StartupConfiguration config = new StartupConfiguration("JoK+MBE+CPtbFenDtGchsR1qjtegkEHhbyTuotIa95ghRFJRIRnvbMDOlzMD8vX8cZfSPnko6+AGNm9XkDY52ve8e9sIEoCvQKwQfmboxucVcSubwYWA8AqJfrpPHhVZv1IxGhLlT94T5pcmrIQ0DYW8XQmzXwWYBD5a/Jnkl7FTYWx0ZWRfX2JOSZLxA6++14+MtsYewjnixVEAzr2yjVsSQhzAcaXG9YLHRLHEdT+ebqp+494Yyf5POMx/Rnepa681k3kR9pQuaxbz8bJrkKhymvqePB/V1ACHk0LmOhrDH8CWpHaJuzpooQ0YgnPxrjJSDqVX3cdHe1EoQ+wp7FWW5JoE/629llgzcYIyuMiePWajDbq9J1B+gKq4AeI/ZCjI5kTFL7xVV85DY9MMULXilPdKgCRcpJU2cEX+46eHiitFy1GrjdAFRu4dquFAM15xCvuEHdUJMJY8gpW7urXPE3euDUrlJPtJsBR0DDPgP1Ey0Jzeqi0K8jMk3OswVbRQwmYPK/Kpsr840RCNFWlJPZkevsHvCe4HmC+g18WA9Zm1i2tc/3VAj5kvBhQB6FbR3x5Wa8sbYyQJAczw2P4VciGvhSA0CLDs49DpFQpbqysZAot0WGsK7yKJlc80FWioLwLJwGfvI0Hl/+XEzrMlLySdZeTxOnWd1TJY8zs=");
        this.architectView.onCreate(config);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        // If the current permissions in the android manifest of this app
        // do not already grant the location and internet permissions
        // set the required permissions during run time
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
            return;
        } else {
            // If the permissions are set already, than configure the button to get location updates
            // when regularly

//            Activity mActivity;
//            mActivity = ArActivity.this;


        }

        architectView.registerUrlListener(getUrlListener());

        View va = (View) findViewById(R.id.activity_main2);
        va.setOnTouchListener(new OnSwipeTouchListener(ArActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(ArActivity.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                Intent intent = new Intent(ArActivity.this, MapsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.righter, R.anim.righter);

            }

            public void onSwipeLeft() {
                Intent intent = new Intent(ArActivity.this, SettingsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.lefter, R.anim.lefter);

            }

            public void onSwipeBottom() {
                Toast.makeText(ArActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });





    }

    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Animation bottomDown = AnimationUtils.loadAnimation(ArActivity.this,
                R.anim.bottom_down);

        ViewGroup hiddenPanel = (ViewGroup) findViewById(R.id.slidingDrawer2);
        ViewGroup hiddenPanel2 = (ViewGroup) findViewById(R.id.slidingDrawer1);


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(hiddenPanel.getVisibility() == View.VISIBLE){
            firstOrLast = false;
            String first = "firstAndLastFilt(" + firstOrLast +")";
            architectView.callJavascript(first);

            hiddenPanel.startAnimation(bottomDown);
            hiddenPanel.startAnimation(bottomDown);
            hiddenPanel.setVisibility(View.GONE);

        } else if(hiddenPanel2.getVisibility() == View.VISIBLE){

            hiddenPanel2.startAnimation(bottomDown);
            hiddenPanel2.startAnimation(bottomDown);
            hiddenPanel2.setVisibility(View.GONE);


        }
        else {
            super.onBackPressed();
        }


    }


    public void openQR(View v) {
        Bundle ePzl = new Bundle();
        Intent intent = new Intent(ArActivity.this, couponActivity.class);
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


    public void armode(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(ArActivity.this, R.anim.image_click));

        Intent intent = new Intent(ArActivity.this, MapsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.lefter, R.anim.lefter);
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
        semiCircleProgressBarView = new SemiCircleProgressBarView(ArActivity.this, orange);

        semiCircleProgressBarView = (SemiCircleProgressBarView) findViewById(R.id.ofsdSemiCirc1);
        semiCircleProgressBarView.setVisibility(View.VISIBLE);

        ImageView ofsdSemiCircBg = (ImageView)findViewById(R.id.ofsdSemiCircBg);
        ofsdSemiCircBg.setVisibility(View.VISIBLE);
        semiCircleProgressBarView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        semiCircleProgressBarView.setClipping(100);
        long timerBarValue = epochDif / 60000;

        if (epochDif == 0 || epochDif <0) {
            String colourBar = "grey";
            timerBarValue = 0;
            semiCircleProgressBarView.setBitMap(colourBar);
        }
        else if (epochDif < 900000) {
            String colourBar = "orange";
            semiCircleProgressBarView.setBitMap(colourBar);
        }  else if (epochDif > 90000) {
            String colourBar = "green";
            semiCircleProgressBarView.setBitMap(colourBar);
        }
        semiCircleProgressBarView.setClipping(timerBarValue);

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
                System.out.println(length);

                if (length == 3) {

                    System.out.println(dateFormat.format(date));

                    System.out.println(millisUntilFinished);
                    if (x == 0) {
                        String colourBar = "grey";
                        semiCircleProgressBarView.setBitMap(colourBar);
                    } else {
                        x--;
                        if(millisUntilFinished < 360000){
                            Animation anim = new AlphaAnimation(0.0f, 1.0f);
                            anim.setDuration(200); //You can manage the time of the blink with this parameter
                            anim.setStartOffset(20);
                            anim.setRepeatMode(Animation.REVERSE);
                            anim.setRepeatCount(Animation.INFINITE);
                            mTextField.startAnimation(anim);
                            mTextField.setTextColor(Color.parseColor("#ff0000"));
                            String colourBar = "orange";
                            semiCircleProgressBarView.setBitMap(colourBar);
                        }
                        else if (millisUntilFinished < 900000) {
                            String colourBar = "orange";
                            semiCircleProgressBarView.setBitMap(colourBar);

//                    timerBar.setImageResource(R.drawable.quartertime);

                        }


                    }

                    // 1,200,000 millis is 20 minutes so display three quarter bar


                    // 300,000 millis is 5 minutes so display one quarter bar

                    semiCircleProgressBarView.setClipping(x);


                }      }

            public void onFinish() {
                mTextField.clearAnimation();

                mTextField.setTextColor(Color.parseColor("#ff0000"));
                mTextField.setText("Offer has ended");

            }

        }.start();
        if (offEndTime2 != "offer 2 empty") {
            System.out.println("OFFER 2 AINT EMPTY DAWG");

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

            long epochDifOffr2 = epochOffr2 - epoch3Offr2;
            System.out.println(epochDifOffr2);

            Date daterOffr2 = new Date(epochDif);
            DateFormat formatterOffr2 = new SimpleDateFormat("HH:mm:ss:SSS");
            String dateFormattedOffr2 = formatterOffr2.format(daterOffr2);
            System.out.println(dateFormattedOffr2);

            timer2 = new CountDownTimer(epochDifOffr2, 1000) {

                public void onTick(long millisUntilFinished) {

                    mTextField2.setText("" + String.format(String.valueOf(FORMAT),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    if (millisUntilFinished < 300000) {

                        Animation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(200); //You can manage the time of the blink with this parameter
                        anim.setStartOffset(20);
                        anim.setRepeatMode(Animation.REVERSE);
                        anim.setRepeatCount(Animation.INFINITE);
                        mTextField2.startAnimation(anim);

                        mTextField2.setTextColor(Color.parseColor("#ff0000"));
                    }

                }

                public void onFinish() {
                    mTextField2.clearAnimation();

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

            long epochDifOffr3 = epochOffr3 - epoch3Offr3;
            System.out.println(epochDifOffr3);

            Date daterOffr3 = new Date(epochDif);
            DateFormat formatterOffr3 = new SimpleDateFormat("HH:mm:ss:SSS");
            String dateFormattedOffr3 = formatterOffr3.format(daterOffr3);
            System.out.println(dateFormattedOffr3);

            timer3 = new CountDownTimer(epochDifOffr3, 1000) {

                public void onTick(long millisUntilFinished) {

                    mTextField3.setText("" + String.format(String.valueOf(FORMAT),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                    if (millisUntilFinished < 300000) {

                        Animation anim = new AlphaAnimation(0.0f, 1.0f);
                        anim.setDuration(200); //You can manage the time of the blink with this parameter
                        anim.setStartOffset(20);
                        anim.setRepeatMode(Animation.REVERSE);
                        anim.setRepeatCount(Animation.INFINITE);
                        mTextField3.startAnimation(anim);

                        mTextField3.setTextColor(Color.parseColor("#ff0000"));
                    }

                }

                public void onFinish() {
                    mTextField3.clearAnimation();

                    mTextField3.setTextColor(Color.parseColor("#ff0000"));
                    mTextField3.setText("Offer has ended");
                }

            }.start();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    public void openFilterrr(View view) {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
        mp.start();
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
//         System.out.println("sending from maps, 1st submit is: " + firstSubmit);

//            startActivityForResult(intent,1000);


        Animation bottomUp = AnimationUtils.loadAnimation(ArActivity.this,
                R.anim.bottom_up);
        ViewGroup hiddenPanel = (ViewGroup)findViewById(R.id.slidingDrawer2);
        hiddenPanel.startAnimation(bottomUp);
        hiddenPanel.setVisibility(View.VISIBLE);
        overridePendingTransition(R.anim.slid_up, R.anim.slide_out);
        firstOrLast = true;
        String first = "firstAndLastFilt(" + firstOrLast +")";
        architectView.callJavascript(first);


    }


    public void filterIcons(View v) {
        this.architectView = (ArchitectView) this.findViewById(R.id.architectView);
//        try {
//            this.architectView.load("file:///android_asset/../js/multiplepois.js");
//        }catch (IOException e){
//        }

        String passedBools = "filterMarkers(" + drinksIsClicked + "," + accomodationIsClicked + "," + attractionsIsClicked + "," + foodDiningIsClicked + "," + moviesIsClicked + "," + shoppingIsClicked + ")";
        System.out.println(passedBools);
        this.architectView.callJavascript(passedBools);
        Animation bottomDown = AnimationUtils.loadAnimation(ArActivity.this,
                R.anim.bottom_down);
        ViewGroup hiddenPanel = (ViewGroup)findViewById(R.id.slidingDrawer2);
        hiddenPanel.startAnimation(bottomDown);
        hiddenPanel.setVisibility(View.GONE);

//        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
//        v.startAnimation(AnimationUtils.loadAnimation(ArActivity.this, R.anim.image_click));
//
//        SlidingDrawer sd2 = (SlidingDrawer)findViewById(R.id.slidingDrawer2);
//        sd2.close();
//        hideAttractions();
//        hideMovies();
//        hideShopping();
//        hideAccomodation();
//        hideDrinks();
//        hideFoodDining();


        v.startAnimation(AnimationUtils.loadAnimation(ArActivity.this, R.anim.image_click));
    }

    public void switchToMaps(View v) {

        Intent intent = new Intent(ArActivity.this, MapsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.righter, R.anim.righter);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            int orientation = this.getResources().getConfiguration().orientation;
            setContentView(R.layout.activity_main2);

        }
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT && orientationScape == false) {
            orientationScape = true;
            System.out.println("ORIENTATION CHANGED");
            int orientation = this.getResources().getConfiguration().orientation;

            try {
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                System.out.println("didnt work");

            }


        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int orientation = this.getResources().getConfiguration().orientation;
            orientationScape = true;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)


                    return;
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        architectView.onPostCreate();

//        try {
//            // When the app is created, load the index, JS and css files from the
//            // wikitude Vapp multiple pois files in the assets directory
//            this.architectView.load("file:///android_asset/demo6/index.html");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private boolean isChecked = false;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }


    protected void onResume() {
        super.onResume();

        System.out.println("onResume");

        sensorManager.registerListener(gyroListener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        architectView.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        architectView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        architectView.onPause();
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

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();

        super.onStop();
        sensorManager.unregisterListener(gyroListener);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == animationSlideDown) {

        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void toastMsg(View view) {
        Intent intent = new Intent(this, FullscreenActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slid_up, R.anim.slide_out);
    }

    @Override
    public void onClick(View v) {

    }


    public class MyUtils {

        public void SlideUP(View view, Context context) {
            view.startAnimation(AnimationUtils.loadAnimation(context,
                    R.anim.slid_down));
        }

        public void SlideDown(View view, Context context) {
            view.startAnimation(AnimationUtils.loadAnimation(context,
                    R.anim.slid_up));
        }


    }


    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }


    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    /**
     * {@inheritDoc}
     /**
     * {@inheritDoc}
     */


    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || SettingsActivity.GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || SettingsActivity.DataSyncPreferenceFragment.class.getName().equals(fragmentName)
                || SettingsActivity.NotificationPreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("example_text"));
            bindPreferenceSummaryToValue(findPreference("example_list"));


        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows notification preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class NotificationPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notification);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DataSyncPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_data_sync);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
    protected void updateDisplay(String message){
        System.out.println(message + "\n");

    }
    private class MyTask extends AsyncTask<String, String, String> {    // Declare an instance of the Location Manager and Listener for retrieving the user's current
        // latitude and longitude
        private LocationManager locationManager;
        private LocationListener locationListener;
        private ArchitectView architectView;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            updateDisplay("starting task");

            // Create the instance of the location listener for obtaining the users current location
            updateDisplay("on pre done");


        }
        @Override
        protected String doInBackground(String... params) {
            updateDisplay("Do in background started");

            // Call the location service of the LocationManager
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            // Create the instance of the location listener for obtaining the users current location
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    locationManager.requestLocationUpdates("gps", 40000, 0, locationListener);

                    // When the location does change, grab the lon, lat from the location passed into it by the device + LocationManager
                    //Set the architectView location with the users current lat,lon and altitude.
                    // Note: this fires the onLocationChanged function of the multiplepois.js
                    architectView.setLocation(location.getLatitude(), location.getLongitude(), 100);
                    System.out.println(String.valueOf(location.getLatitude()));


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

            // If the permissions are set already, than configure the button to get location updates
            // when regularly



            // Call the location service of the LocationManager
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    locationManager.requestLocationUpdates("gps", 40000, 0, locationListener);
                    int dig = 10;
                    counter ++;

                    // When the location does change, grab the lon, lat from the location passed into it by the device + LocationManager
                    //Set the architectView location with the users current lat,lon and altitude.
                    // Note: this fires the onLocationChanged function of the multiplepois.js
                    architectView.setLocation(location.getLatitude(), location.getLongitude(), 100);
                    if (counter % 10 == 0 || counter == 1){
                        Geocoder gc = new Geocoder(ArActivity.this, Locale.getDefault());
                        List<Address> addresses = null;
                        try {
                            addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (addresses.size() == 1) {
                            name = addresses.get(0).getThoroughfare();
                            cameFromButton = false;
                            System.out.println("PRINTING ADDRESS");
                            String streetName = "filterByStreet(" +'"' + name +'"' + ","+  cameFromButton +")";
                            System.out.println(streetName);
                            architectView.callJavascript(streetName);

                        } else {
                        }
                    }




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

            return "task complete";
        };

        @Override
        protected void onPostExecute(String result) {
            updateDisplay(result);
            locationManager.requestLocationUpdates("gps", 40000, 0, locationListener);
            try {
//                setContentView(R.layout.activity_main2);
                this.architectView = (ArchitectView)findViewById(R.id.architectView);
                architectView.load("file:///android_asset/demo6/index.html");
            } catch (IOException e) {
                e.printStackTrace();
            }

            updateDisplay("on post done");
        }



    }

}