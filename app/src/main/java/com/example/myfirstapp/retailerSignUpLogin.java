package com.example.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.UUID;

import ch.hsr.geohash.GeoHash;

public class retailerSignUpLogin extends AppCompatActivity {

    String g = "";String l = "";
    String emailString = "";
    View mLinearLayout;
    Window window = null;
    Boolean b = null;

    Boolean exists = false;
    int year;

    int month;
    int day;
    int progress = 30;
    int progress2 = 50;
    StringBuilder sb = new StringBuilder();
    private Spinner spinner1, spinner2;
    private Context mContext;
    private Activity mActivity;
    private RelativeLayout mRelativeLayout;

    private ImageView mImageView;
    private ImageView mImageView2;
    String lat ="";
    String lng ="";
    String streetName ="";
    String strtName ="";
    String streetName2 ="";
    String passw="";

    String categoryString ="";
    String retailerName ="";
    private PopupWindow mPopupWindow;
    private GeoHash hash;

    @Override
    public void onBackPressed() {
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.rl2);
        Button signin = (Button) findViewById(R.id.retailerSignIn);
        Button signup = (Button) findViewById(R.id.retailerSignUp);
        RelativeLayout rl2 = (RelativeLayout)findViewById(R.id.activity_retailer_post_offer);
        RelativeLayout rl3 = (RelativeLayout)findViewById(R.id.activity_retailer_post_offer2);
        EditText busName = (EditText)findViewById(R.id.businessName);
        EditText email = (EditText)findViewById(R.id.email);
        EditText addr = (EditText)findViewById(R.id.address);
        EditText phnum = (EditText)findViewById(R.id.phoneNumber);
        EditText fname = (EditText)findViewById(R.id.firstName);
        EditText lname = (EditText)findViewById(R.id.lastName);
        EditText uname = (EditText)findViewById(R.id.username);
        EditText pword = (EditText)findViewById(R.id.password);
        CheckBox chk1 = (CheckBox)findViewById(R.id.checkBox1);
        TextView textvw = (TextView)findViewById(R.id.textView2);
        Button sub = (Button)findViewById(R.id.signUpSubmit);
        EditText reppword = (EditText)findViewById(R.id.repeatPassword);
        LinearLayout linlay = (LinearLayout)findViewById(R.id.linlay);

        Spinner sp = (Spinner)findViewById(R.id.businessType);


        if(signup.getVisibility() == View.VISIBLE){
            retailerSignUpLogin.this.finish();
        }
        else if(rl3.getVisibility() == View.VISIBLE || rl2.getVisibility() == View.VISIBLE || rl.getVisibility()==View.VISIBLE){
            rl3.setVisibility(View.GONE);
            mPopupWindow.dismiss();
            rl2.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
            linlay.setVisibility(View.VISIBLE);
            signin.setVisibility(View.VISIBLE);
            busName.setVisibility(View.INVISIBLE);
            signup.setVisibility(View.VISIBLE);
            sp.setVisibility(View.INVISIBLE);
            email.setVisibility(View.INVISIBLE);
            addr.setVisibility(View.INVISIBLE);
            phnum.setVisibility(View.INVISIBLE);
            pword.setVisibility(View.INVISIBLE);
            lname.setVisibility(View.INVISIBLE);
            fname.setVisibility(View.INVISIBLE);
            uname.setVisibility(View.INVISIBLE);
            reppword.setVisibility(View.INVISIBLE);
            chk1.setVisibility(View.INVISIBLE);
            sub.setVisibility(View.INVISIBLE);
            textvw.setVisibility(View.INVISIBLE);
        }
//        else if(signin.getVisibility() == View.GONE){
//            signin.setVisibility(View.VISIBLE);
//            signup.setVisibility(View.VISIBLE);
//            rl3.setVisibility(View.GONE);
//            rl.setVisibility(View.GONE);
//            rl2.setVisibility(View.GONE);
//
//        }

    }

    public void hideMe(View v) {
        mPopupWindow.dismiss();
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_sign_up_login);


        // Get the application context
        mContext = getApplicationContext();

        // Get the activity
        mActivity = retailerSignUpLogin.this;

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer);
        mImageView = (ImageView) findViewById(R.id.share);
        mImageView2 = (ImageView) findViewById(R.id.coupon2);
        ImageView mImageView3 = (ImageView) findViewById(R.id.share3);
        ImageView mImageView4 = (ImageView) findViewById(R.id.coupon3);

        mImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(retailerSignUpLogin.this, R.anim.image_click));
                // Initialize a new instance of LayoutInflater service
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                View customView = inflater.inflate(R.layout.custom_layout, null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
                // Initialize a new instance of popup window
                mPopupWindow = new PopupWindow(
                        customView,
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                // Set an elevation value for popup window
                // Call requires API level 21
                if (Build.VERSION.SDK_INT >= 21) {
                    mPopupWindow.setElevation(5.0f);
                }

                // Get a reference for the custom view close button
                Button closeButton = (Button) customView.findViewById(R.id.ib_close);

                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */
                // Finally, show the popup window at the center location of root relative layout
                TextView offerDsc = (TextView) customView.findViewById(R.id.OfferDescription);
                TextView strtDate = (TextView) customView.findViewById(R.id.startDate);
                TextView strtTime = (TextView) customView.findViewById(R.id.startTime);
                TextView timeLim = (TextView) customView.findViewById(R.id.timeLimit);
                TextView discount = (TextView) customView.findViewById(R.id.discount);

                DatePicker dpr = (DatePicker) findViewById(R.id.dpResult3);
                Integer monthInt = dpr.getMonth()+1;
                strtDate.setText(dpr.getDayOfMonth() + " " + monthInt.toString() + " " + dpr.getYear());
                String amOrPm = "pm";

                TimePicker tpr = (TimePicker) findViewById(R.id.timeResult3);
                if (tpr.getHour() > 11) {
                    amOrPm = "pm";
                } else{
                    amOrPm = "am";
                }
                Integer min = tpr.getMinute();
                String minuteString = min.toString();
                System.out.println(minuteString);
                if (min.equals(0) || min.equals(1)|| min.equals(2)|| min.equals(3)||  min.equals(4)||  min.equals(5)||  min.equals(6)||  min.equals(7)||  min.equals(8)||  min.equals(9)){
                    minuteString = "0"+minuteString;
                }

                strtTime.setText(tpr.getHour() + ":" + minuteString + amOrPm);

                TextView timeLmt = (TextView) findViewById(R.id.seekBarvalue3);
                timeLim.setText(timeLmt.getText().toString() + "m");

                TextView timeLmt2 = (TextView) findViewById(R.id.seekBarvalue4);
                discount.setText(timeLmt2.getText().toString() + "%");


                EditText offerD = (EditText) findViewById(R.id.offerDescr3);
                offerDsc.setText(offerD.getText().toString());
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                mPopupWindow.update(900, 1500);


                String data = null;
                EditText offerDescr = (EditText) findViewById(R.id.offerDescr3);
                DatePicker dpResult = (DatePicker) findViewById(R.id.dpResult3);
                TimePicker timeResult = (TimePicker) findViewById(R.id.timeResult3);

                EditText password = (EditText) customView.findViewById(R.id.password);
                // Create data variable for sent values to server
                System.out.println(offerDescr.getText());

                int hour = timeResult.getHour();
                int minute = timeResult.getMinute();
                String miny = Integer.toString(minute);
                int ampm = timeResult.getBaseline();
                String second = "00";
                System.out.println(ampm);
                System.out.println(hour);
                System.out.println(minute);
                if (miny.equals("0") || miny.equals("1")|| miny.equals("2")|| miny.equals("3")||  miny.equals("4")||  miny.equals("5")||  miny.equals("6")||  miny.equals("7")||  miny.equals("8")||  miny.equals("9")){
                    miny = "0"+miny;
                }
                System.out.println(second);

//                  2016  12    06   09   9   00
//                year  mnth  day  hour  min second
//
                int year = dpResult.getYear();
                int month = dpResult.getMonth()+1;
                int day = dpResult.getDayOfMonth();
                String dayy = Integer.toString(day);
                String monthy = Integer.toString(month);
                String houry = Integer.toString(hour);
                if (dayy.equals("0") || dayy.equals("1")|| dayy.equals("2")|| dayy.equals("3")||  dayy.equals("4")||  dayy.equals("5")||  dayy.equals("6")||  dayy.equals("7")||  dayy.equals("8")||  dayy.equals("9")){
                    dayy = "0"+dayy;
                    System.out.println("DAYY TRUE");

                }
                if (monthy.equals("0") || monthy.equals("1")|| monthy.equals("2")|| monthy.equals("3")||  monthy.equals("4")||  monthy.equals("5")||  monthy.equals("6")||  monthy.equals("7")||  monthy.equals("8")||  monthy.equals("9")){

                    monthy = "0"+monthy;
                    System.out.println("MONTHY TRUE");

                }
                if (houry.equals("0")  || houry.equals("1")|| houry.equals("2")|| houry.equals("3")||  houry.equals("4")||  houry.equals("5")||  houry.equals("6")||  houry.equals("7")||  houry.equals("8")||  houry.equals("9")){
                    houry = "0"+houry;
                    System.out.println("HOURY TRUE");
                }
                System.out.println(day);
                System.out.println(month);
                System.out.println(year);

                String dateString = Integer.toString(year) + monthy + dayy + houry + miny + second;
                String uuid = UUID.randomUUID().toString();
                System.out.println("uuid = " + uuid);
                String retailerID = uuid.replace("-","");
                String retailerID2 = retailerID.substring(0,retailerID.length()-17);
                System.out.println(dateString);



                hash = GeoHash.withCharacterPrecision(Float.parseFloat(lat), Float.parseFloat(lng), 12);
                System.out.println(hash.toBase32());
                retailerID2 = "000"+hash.toBase32();
                System.out.println(categoryString);
                System.out.println(retailerName);


                try {
                    data ="";
                    data += "{\"" + URLEncoder.encode("retailerID", "UTF-8") + "\"" + ":"
                            + "\"" + URLEncoder.encode(retailerID2, "UTF-8") + "\"" ;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("altitude", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode("100", "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("category", "UTF-8") + "\""
                            + ":" + "\"" + categoryString + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("duration", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode("50", "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("latitude", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode(lat, "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("longitude", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode(lng, "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("offerDescription", "UTF-8") + "\""
                            + ":" + "\"" +offerDescr.getText().toString() + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("offerEndTime", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode(dateString, "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("offerEndTime2", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode(dateString, "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("offerEndTime3", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode(dateString, "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("offerDescription2", "UTF-8") + "\""
                            + ":" + "\"" + "offer 2 empty" + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("offerDescription3", "UTF-8") + "\""
                            + ":" + "\"" + "offer 3 empty" + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("streetname", "UTF-8") + "\""
                            + ":" + "\"" + streetName + "\"";
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("retailerName", "UTF-8") + "\""
                            + ":" + "\"" + retailerName + "\"";
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("website", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode("www.google.com", "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("rating", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode("3", "UTF-8") + "\"" + "}";
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }



                String text = "";
                BufferedReader reader = null;

                // Send data
                try {
System.out.println("----------------------------------------------about to post");
                    // Defined URL  where to send data
                    URL url = new URL("https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/post-offers");

                    // Send POST data request

                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    // Get the server response

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    System.out.println("----------------------------------------------about to 2nd try");

                    line = reader.readLine();
                    // Append server response in string
                    sb.append(line + "\n");
                    String f = line;
                    g = f.replace("\\", "").trim();

                } catch (Exception ex) {
                    ex.printStackTrace();


                } finally {
                    try {

                        reader.close();
                        System.out.println("----------------------------------------------about to 3rd try");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });




        // Set a click listener for the text view
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(retailerSignUpLogin.this, R.anim.image_click));
                // Initialize a new instance of LayoutInflater service
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                View customView = inflater.inflate(R.layout.custom_layout, null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
                // Initialize a new instance of popup window
                mPopupWindow = new PopupWindow(
                        customView,
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                // Set an elevation value for popup window
                // Call requires API level 21
                if (Build.VERSION.SDK_INT >= 21) {
                    mPopupWindow.setElevation(5.0f);
                }

                // Get a reference for the custom view close button
                Button closeButton = (Button) customView.findViewById(R.id.ib_close);

                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */
                // Finally, show the popup window at the center location of root relative layout
                TextView offerDsc = (TextView) customView.findViewById(R.id.OfferDescription);
                TextView strtDate = (TextView) customView.findViewById(R.id.startDate);
                TextView strtTime = (TextView) customView.findViewById(R.id.startTime);
                TextView timeLim = (TextView) customView.findViewById(R.id.timeLimit);
                TextView discount = (TextView) customView.findViewById(R.id.discount);

                DatePicker dpr = (DatePicker) findViewById(R.id.dpResult);
                Integer monthInt = dpr.getMonth()+1;
                strtDate.setText(dpr.getDayOfMonth() + " " + monthInt.toString() + " " + dpr.getYear());
                String amOrPm = "pm";

                TimePicker tpr = (TimePicker) findViewById(R.id.timeResult);
                if (tpr.getHour() > 11) {
                    amOrPm = "pm";
                } else{
                    amOrPm = "am";
                }
                Integer min = tpr.getMinute();
                String minuteString = min.toString();
                System.out.println(minuteString);
                if (min.equals(0) || min.equals(1)|| min.equals(2)|| min.equals(3)||  min.equals(4)||  min.equals(5)||  min.equals(6)||  min.equals(7)||  min.equals(8)||  min.equals(9)){
                    minuteString = "0"+minuteString;
                }

                strtTime.setText(tpr.getHour() + ":" + minuteString + amOrPm);

                TextView timeLmt = (TextView) findViewById(R.id.seekBarvalue);
                timeLim.setText(timeLmt.getText().toString() + "m");

                TextView timeLmt2 = (TextView) findViewById(R.id.seekBarvalue2);
                discount.setText(timeLmt2.getText().toString() + "%");

                TextView timeLmt3 = (TextView) findViewById(R.id.seekBarvalue3);
                timeLim.setText(timeLmt.getText().toString() + "m");

                TextView timeLmt4 = (TextView) findViewById(R.id.seekBarvalue4);
                discount.setText(timeLmt2.getText().toString() + "%");


                EditText offerD = (EditText) findViewById(R.id.offerDescr);
                offerDsc.setText(offerD.getText().toString());
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                mPopupWindow.update(900, 1500);


                String data = null;
                EditText offerDescr = (EditText) findViewById(R.id.offerDescr);
                DatePicker dpResult = (DatePicker) findViewById(R.id.dpResult);
                TimePicker timeResult = (TimePicker) findViewById(R.id.timeResult);

                EditText password = (EditText) customView.findViewById(R.id.password);
                // Create data variable for sent values to server
                System.out.println(offerDescr.getText());

                int hour = timeResult.getHour();
                int minute = timeResult.getMinute();
                String miny = Integer.toString(minute);
                int ampm = timeResult.getBaseline();
                String second = "00";
                System.out.println(ampm);
                System.out.println(hour);
                System.out.println(minute);
                if (miny.equals("0") || miny.equals("1")|| miny.equals("2")|| miny.equals("3")||  miny.equals("4")||  miny.equals("5")||  miny.equals("6")||  miny.equals("7")||  miny.equals("8")||  miny.equals("9")){
                    miny = "0"+miny;
                }
                System.out.println(second);

//                  2016  12    06   09   9   00
//                year  mnth  day  hour  min second
//
                int year = dpResult.getYear();
                int month = dpResult.getMonth()+1;
                int day = dpResult.getDayOfMonth();
                String dayy = Integer.toString(day);
                String monthy = Integer.toString(month);
                String houry = Integer.toString(hour);
                if (dayy.equals("0") || dayy.equals("1")|| dayy.equals("2")|| dayy.equals("3")||  dayy.equals("4")||  dayy.equals("5")||  dayy.equals("6")||  dayy.equals("7")||  dayy.equals("8")||  dayy.equals("9")){
                    dayy = "0"+dayy;
                    System.out.println("DAYY TRUE");

                }
                if (monthy.equals("0") || monthy.equals("1")|| monthy.equals("2")|| monthy.equals("3")||  monthy.equals("4")||  monthy.equals("5")||  monthy.equals("6")||  monthy.equals("7")||  monthy.equals("8")||  monthy.equals("9")){

                    monthy = "0"+monthy;
                    System.out.println("MONTHY TRUE");

                }
                if (houry.equals("0")  || houry.equals("1")|| houry.equals("2")|| houry.equals("3")||  houry.equals("4")||  houry.equals("5")||  houry.equals("6")||  houry.equals("7")||  houry.equals("8")||  houry.equals("9")){
                    houry = "0"+houry;
                    System.out.println("HOURY TRUE");
                }
                System.out.println(day);
                System.out.println(month);
                System.out.println(year);

                String dateString = Integer.toString(year) + monthy + dayy + houry + miny + second;
                String uuid = UUID.randomUUID().toString();
                System.out.println("uuid = " + uuid);
                String retailerID = uuid.replace("-","");
                String retailerID2 = retailerID.substring(0,retailerID.length()-17);
                System.out.println(dateString);



                hash = GeoHash.withCharacterPrecision(Float.parseFloat(lat), Float.parseFloat(lng), 12);
                System.out.println(hash.toBase32());
                retailerID2 = "000"+hash.toBase32();

                try {
                    data ="";
                    data += "{\"" + URLEncoder.encode("retailerID", "UTF-8") + "\"" + ":"
                            + "\"" + URLEncoder.encode(retailerID2, "UTF-8") + "\"" ;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("altitude", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode("100", "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    Spinner category = (Spinner)findViewById(R.id.businessType);

                    String categoryString = category.getSelectedItem().toString().toLowerCase();
                    data += "," + "\"" + URLEncoder.encode("category", "UTF-8") + "\""
                            + ":" + "\"" + categoryString + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("duration", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode("50", "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("latitude", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode(lat, "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("longitude", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode(lng, "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("offerDescription", "UTF-8") + "\""
                            + ":" + "\"" +offerDescr.getText().toString() + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("offerEndTime", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode(dateString, "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("offerEndTime2", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode(dateString, "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("offerEndTime3", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode(dateString, "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("offerDescription2", "UTF-8") + "\""
                            + ":" + "\"" + "offer 2 empty" + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    data += "," + "\"" + URLEncoder.encode("offerDescription3", "UTF-8") + "\""
                            + ":" + "\"" + "offer 3 empty" + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("streetname", "UTF-8") + "\""
                            + ":" + "\"" + strtName + "\"";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    EditText businessNme = (EditText) findViewById(R.id.businessName);
                    data += "," + "\"" + URLEncoder.encode("retailerName", "UTF-8") + "\""
                            + ":" + "\"" + businessNme.getText().toString() + "\"";
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("website", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode("www.google.com", "UTF-8") + "\"" ;
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    data += "," + "\"" + URLEncoder.encode("rating", "UTF-8") + "\""
                            + ":" + "\"" +URLEncoder.encode("3", "UTF-8") + "\"" + "}";
                    System.out.println(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }



                String text = "";
                BufferedReader reader = null;

                // Send data
                try {

                    // Defined URL  where to send data
                    URL url = new URL("https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/post-offers");

                    // Send POST data request

                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();

                    // Get the server response

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;

                    line = reader.readLine();
                    // Append server response in string
                    sb.append(line + "\n");
                    String f = line;
                    g = f.replace("\\", "").trim();

                } catch (Exception ex) {

                } finally {
                    try {

                        reader.close();
                    } catch (Exception ex) {
                    }
                }

            }
        });
        mImageView2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(retailerSignUpLogin.this, R.anim.image_click));

                Intent intent = new Intent(retailerSignUpLogin.this,couponActivity.class);
                startActivity(intent);

            }
        });


        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        SeekBar seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        final TextView seekBarValue = (TextView) findViewById(R.id.seekBarvalue);
        seekBarValue.setText((String.valueOf(progress)));

        final TextView seekBarValue3 = (TextView) findViewById(R.id.seekBarvalue3);
        seekBarValue3.setText((String.valueOf(progress)));
//        seekBar.incrementProgressBy(15);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            RelativeLayout rly = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer);




            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBarValue.setText((String.valueOf(progress)));
                rly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideSoftKeyboard(retailerSignUpLogin.this);
                    }
                });
                int stepSize = 15;

                progress = (progress / stepSize) * stepSize;
                seekBar.setProgress(progress);

                seekBarValue.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            RelativeLayout rly = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer2);




            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBarValue3.setText((String.valueOf(progress)));
                rly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideSoftKeyboard(retailerSignUpLogin.this);
                    }
                });
                int stepSize = 15;

                progress = (progress / stepSize) * stepSize;
                seekBar.setProgress(progress);

                seekBarValue3.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        final TextView seekBarValue2 = (TextView) findViewById(R.id.seekBarvalue2);
        seekBarValue2.setText((String.valueOf(progress2)));
//        seekBar.incrementProgressBy(15);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            RelativeLayout rl2 = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer);


            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2,
                                          boolean fromUser2) {
                // TODO Auto-generated method stub
                seekBarValue2.setText((String.valueOf(progress2)));
                rl2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideSoftKeyboard(retailerSignUpLogin.this);
                    }
                });
                int stepSize2 = 10;

                progress2 = (progress2 / stepSize2) * stepSize2;
                seekBar2.setProgress(progress2);

                seekBarValue2.setText("" + progress2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {
                // TODO Auto-generated method stub
            }
        });


        SeekBar seekBar4 = (SeekBar) findViewById(R.id.seekBar4);
        final TextView seekBarValue4 = (TextView) findViewById(R.id.seekBarvalue4);
        seekBarValue4.setText((String.valueOf(progress2)));
//        seekBar.incrementProgressBy(15);
        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            RelativeLayout rl2 = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer2);


            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2,
                                          boolean fromUser2) {
                // TODO Auto-generated method stub
                seekBarValue4.setText((String.valueOf(progress2)));
                rl2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideSoftKeyboard(retailerSignUpLogin.this);
                    }
                });
                int stepSize2 = 10;

                progress2 = (progress2 / stepSize2) * stepSize2;
                seekBar2.setProgress(progress2);

                seekBarValue4.setText("" + progress2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar2) {
                // TODO Auto-generated method stub
            }
        });




    // Initialize a new instance of popup window
        final EditText email = (EditText) findViewById(R.id.email);


        CheckBox checkbox = (CheckBox) findViewById(R.id.checkBox1);
        TextView textView = (TextView) findViewById(R.id.textView2);


        checkbox.setText("");
        textView.setText(Html.fromHtml("I have read and agree to the " +
                "<a href='www.22wpc.com/files/terms-and-conditions.pdf'>TERMS AND CONDITIONS</a>"));
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        final Button retailerSignUp = (Button) findViewById(R.id.retailerSignUp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.actionbar));
        }

        retailerSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button signin = (Button) findViewById(R.id.retailerSignIn);
                signin.setVisibility(View.GONE);

                retailerSignUp.setVisibility(View.GONE);

                EditText businessNme = (EditText) findViewById(R.id.businessName);
                businessNme.setVisibility(View.VISIBLE);
                EditText email = (EditText) findViewById(R.id.email);
                email.setVisibility(View.VISIBLE);
                EditText address = (EditText) findViewById(R.id.address);
                address.setVisibility(View.VISIBLE);
                EditText phonenumber = (EditText) findViewById(R.id.phoneNumber);
                phonenumber.setVisibility(View.VISIBLE);
                EditText firstNme = (EditText) findViewById(R.id.firstName);
                firstNme.setVisibility(View.VISIBLE);
                EditText lastNme = (EditText) findViewById(R.id.lastName);
                lastNme.setVisibility(View.VISIBLE);
                EditText username = (EditText) findViewById(R.id.username);
                username.setVisibility(View.VISIBLE);
                EditText password = (EditText) findViewById(R.id.password);
                password.setVisibility(View.VISIBLE);
                EditText repeatpassword = (EditText) findViewById(R.id.repeatPassword);
                repeatpassword.setVisibility(View.VISIBLE);
                Spinner spinner = (Spinner) findViewById(R.id.businessType);
                spinner.setVisibility(View.VISIBLE);
                Button signUpSubmit = (Button) findViewById(R.id.signUpSubmit);
                signUpSubmit.setVisibility(View.VISIBLE);
                TextView circl1 = (TextView) findViewById(R.id.circle1);
                circl1.setVisibility(View.VISIBLE);
                TextView circl2 = (TextView) findViewById(R.id.circle2);
                circl2.setVisibility(View.VISIBLE);
                LinearLayout tsandcs = (LinearLayout) findViewById(R.id.linearLayout1);
                tsandcs.setVisibility(View.VISIBLE);
                ImageView dottdLine = (ImageView) findViewById(R.id.dottedLine);
                dottdLine.setVisibility(View.VISIBLE);


            }
        });
        final Context mContext;
        mContext = getApplicationContext();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        final View customView = inflater.inflate(R.layout.email_layout, null);

        Button signin = (Button) findViewById(R.id.retailerSignIn);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICKED");
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                final View customView = inflater.inflate(R.layout.email_layout, null);


                final EditText email = (EditText) customView.findViewById(R.id.email);
                email.clearFocus();
                email.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(email, InputMethodManager.SHOW_IMPLICIT);
                    }
                });
                final EditText password = (EditText) customView.findViewById(R.id.password);
                password.clearFocus();
                password.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(password, InputMethodManager.SHOW_IMPLICIT);
                    }
                });


//                    EditText email= (EditText) findViewById(R.id.email);
//                    InputMethodManager imm2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm2.showSoftInput(email, InputMethodManager.SHOW_IMPLICIT);

                // Initialize a new instance of popup window
                mPopupWindow = new PopupWindow(
                        customView,
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                // Set an elevation value for popup window
                // Call requires API level 21
                if (Build.VERSION.SDK_INT >= 21) {
                    mPopupWindow.setElevation(5.0f);
                }

                // Get a reference for the custom view close button
                Button closeButton = (Button) customView.findViewById(R.id.ib_close);


                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Boolean j = isValidEmail(email.getText().toString());

                        if (j == true) {
                            EditText email = (EditText) customView.findViewById(R.id.email);
                            EditText password = (EditText) customView.findViewById(R.id.password);
                            // Create data variable for sent values to server
                            String userEmail = email.getText().toString();
                            System.out.println(userEmail);
                            String userPassword = password.getText().toString();
                            System.out.println(password.getText().toString());
                            String data = null;

                            String retailerID2 ="thisISaLogin,RETID unknown ";

                            try {
                                data = "";
                                data += "{\"" + URLEncoder.encode("email", "UTF-8") + "\"" + ":"
                                        + "\"" + userEmail + "\"";
                                System.out.println("ha");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            try {
                                data += "," + "\"" + URLEncoder.encode("password", "UTF-8") + "\""
                                        + ":" + "\"" + URLEncoder.encode(userPassword, "UTF-8") + "\"" ;
                                System.out.println("ho");
                                System.out.println(data);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            try {
                                data += "," + "\"" + URLEncoder.encode("retailerID", "UTF-8") + "\""
                                        + ":" + "\"" + URLEncoder.encode(retailerID2, "UTF-8") + "\"" + "}";
                                System.out.println("ho");
                                System.out.println(data);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            String text = "";
                            BufferedReader reader = null;

                            // Send data
                            try {

                                // Defined URL  where to send data
                                URL url = new URL("https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/geo-email-login");

                                // Send POST data request

                                URLConnection conn = url.openConnection();
                                conn.setDoOutput(true);
                                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                                wr.write(data);
                                wr.flush();

                                // Get the server response

                                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                String line = null;

                                line = reader.readLine();
                                // Append server response in string
                                sb.append(line + "\n");
                                String f = line;
                                g = f.replace("\\", "").trim();


                            } catch (Exception ex) {

                            } finally {
                                try {
                                    reader.close();
                                } catch (Exception ex) {
                                }
                            }
                            System.out.println(g);
                            System.out.println(g);

                            Gson gson = new Gson();

                            JsonParser jsonParser = new JsonParser();
                            JsonElement my_json;


                            try {
                                my_json = jsonParser.parse(g);
                                JSONObject obj = new JSONObject(g);
                                emailString = obj.getString("email");
                                exists = obj.getBoolean("exists");
                                 passw = obj.getString("password");
                                try{
                                    String address = obj.getString("address");
                                    String urlAddress = address;
                                    String urlAddress2 = urlAddress.replace(" ", "+");
                                    System.out.println(urlAddress2);
                                    JSONObject json = readJsonFromUrl("http://maps.googleapis.com/maps/api/geocode/json?address=" + urlAddress2);
                                    System.out.println(json.toString());
                                    System.out.println(json.get("results"));
                                    JSONArray arr = json.getJSONArray("results");


                                    JSONObject startofAddrCmpo = arr.getJSONObject(0);

                                    JSONArray arr3 = startofAddrCmpo.getJSONArray("address_components");
//                                    JSONObject arr2 = arr.getJSONObject(0);
                                    JSONObject firstLngNmeSet = arr3.getJSONObject(0);
                                    String by2 = firstLngNmeSet.toString();
                                    System.out.println(firstLngNmeSet);

                                    JSONObject streetNameSection = arr3.getJSONObject(1);
                                    strtName = streetNameSection.getString("long_name");
                                    streetName = streetNameSection.getString("long_name");
                                    System.out.println(strtName);
                                    System.out.println("Street name printed, line 656");

                                    JSONObject geometry = startofAddrCmpo.getJSONObject("geometry");
                                    JSONObject location = geometry.getJSONObject("location");
                                    lat = location.getString("lat");
                                    lng = location.getString("lng");


                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                                System.out.println(emailString);
                                System.out.println(lat);
                                System.out.println(lng);
                                System.out.println(exists);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (exists != false) {
//                            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//
//                            // Inflate the custom layout/view
//                            View customView = inflater.inflate(R.layout.email_layout, null);
                                if(!passw.equals(userPassword)){
                                    TextView validator = (TextView) customView.findViewById(R.id.validator);
                                    validator.setVisibility(View.VISIBLE);
                                    System.out.println(passw);
                                    System.out.println(userPassword);
                                }
                                else {

                                    mPopupWindow.dismiss();
                                    RelativeLayout rl4 = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer);
                                    rl4.setVisibility(View.GONE);
                                    RelativeLayout rl5 = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer2);
                                    rl5.setVisibility(View.VISIBLE);
//                            Intent intent = new Intent(retailerSignUpLogin.this, retailerPostOffer.class);
//                            startActivity(intent);

                                }
                            } else {

                                TextView validator = (TextView) customView.findViewById(R.id.validator);
                                validator.setVisibility(View.VISIBLE);

                            }


//                        Gson gson = new Gson();
//                        JsonReader reader = new JsonReader(new StringReader(result1));
//                        reader.setLenient(true);
//                        Userinfo userinfo1 = gson.fromJson(reader, Userinfo.class);

//                        Object obj = jsonParser.parse(my_json);
//                        JSONArray array = (JSONArray)obj;
//
//                        System.out.println("The 2nd element of array");
//                        try {
//                            System.out.println(array.get(1));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }


//                        response.delete(0, response.length());
//                            //My task is called in the onRequestData method and passes it the url of our AWS GET request API
//                            String url = "https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/geo-email-login";
//                            URL obj = null;
//                            try {
//                                obj = new URL(url);
//                            } catch (MalformedURLException e) {
//                                e.printStackTrace();
//                            }
//                            HttpsURLConnection con = null;
//                            try {
//                                con = (HttpsURLConnection) obj.openConnection();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            final String userEmail = email.getText().toString();
//                            final String userPassword = password.getText().toString();
//                            //add request header
//                            try {
//                                con.setRequestMethod("POST");
//                            } catch (ProtocolException e) {
//                                e.printStackTrace();
//                            }
//                            con.setRequestProperty("User-Agent", "Mozilla/5.0");
//                            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//
//                           System.out.println("{\"email\": \"nathan.hampshire5@gmail.com\",\"password\": \"soccer05.\"}\n");
//                            String urlParameters = "{\"email\": " + "\""+userEmail+ "\"" + ",\"password\":" + "\""+ userPassword+ "\""+ "}";
//                            // Send post request
//                            con.setDoOutput(true);
//                            DataOutputStream wr = null;
//                            try {
//                                wr = new DataOutputStream(con.getOutputStream());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                wr.writeBytes(urlParameters);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                wr.flush();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                wr.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                            int responseCode = 0;
//                            try {
//                                responseCode = con.getResponseCode();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println("\nSending 'POST' request to URL : " + url);
//                            System.out.println("Post parameters : " + urlParameters);
//                            System.out.println("Response Code : " + responseCode);
//
//                            BufferedReader in = null;
//                            System.out.println("String printed");
//
//                            try {
//                                in = new BufferedReader(
//                                        new InputStreamReader(con.getInputStream()));
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            String inputLine;
//                            System.out.println("StringA printed");
//
//
//                            System.out.println("StringB printed");
//
//                            try {
//                                    while ((inputLine = in.readLine()) != null) {
//                                    response.append(inputLine);
//                                    System.out.println(response);
//                                    System.out.println(inputLine.toString());
//                                    System.out.println("input line just printed");
//                                        in.close();
//
//                                    if (response.equals(null) || response == null) {
//                                        System.out.println("it equalled null so window shouldn't dismiss");
//                                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//
//                                        // Inflate the custom layout/view
//                                        View customView = inflater.inflate(R.layout.email_layout, null);
//
//                                        TextView validator = (TextView) customView.findViewById(R.id.validator);
//                                        validator.setVisibility(View.VISIBLE);
//                                    }
//                                    else {
//                                        // Dismiss the popup window
//                                        mPopupWindow.dismiss();
//                                    }
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }

                            //print result
                        }
                        else{
                            Toast.makeText(retailerSignUpLogin.this,"Please enter a valid email address",Toast.LENGTH_SHORT).show();
                            email.setText("");
                        }

                    }
                });
                mLinearLayout = (LinearLayout) findViewById(R.id.activity_retailer_sign_up_login);

                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER, 0, 0);
                mPopupWindow.update(1300, 1000);
                mPopupWindow.setFocusable(true);
                mPopupWindow.update();

            }
        });


        Spinner spinner = (Spinner) findViewById(R.id.businessType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));


        Button submit = (Button) findViewById(R.id.signUpSubmit);

        // Set a click listener for the text view
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = null;
                String data2 = null;

                EditText businessNme = (EditText) findViewById(R.id.businessName);
                Spinner businessType = (Spinner) findViewById(R.id.businessType);
                EditText email = (EditText) findViewById(R.id.email);
                EditText address = (EditText) findViewById(R.id.address);
                EditText phoneNum = (EditText) findViewById(R.id.phoneNumber);
                EditText firstNme = (EditText) findViewById(R.id.firstName);
                EditText lastNme = (EditText) findViewById(R.id.lastName);
                EditText usernme = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);

                Spinner category = (Spinner)findViewById(R.id.businessType);


                categoryString = businessType.getSelectedItem().toString();
                retailerName = businessNme.getText().toString();

                String text = "";
                BufferedReader reader = null;
                CheckBox c_box = (CheckBox) findViewById(R.id.checkBox1);
                EditText repeatedPass = (EditText) findViewById(R.id.repeatPassword);
                final Boolean j = isValidEmail(email.getText().toString());

                if (j == true) {


                    if (password.getText().toString().equals(repeatedPass.getText().toString())) {
                        if(category.getSelectedItem()!=null) {

                            String categoryString = category.getSelectedItem().toString().toLowerCase();
                            System.out.println(category.toString());
                            if (c_box.isChecked()) {
                                System.out.println("cbox is checked");
                                if (!password.getText().toString().equals(repeatedPass.getText().toString()) || category.getSelectedItem()==null || !c_box.isChecked() || address.getText().toString().matches("") || businessNme.getText().toString().matches("") || phoneNum.getText().toString().matches("") || firstNme.getText().toString().matches("") || lastNme.getText().toString().matches("") || usernme.getText().toString().matches("") || password.getText().toString().matches("") || repeatedPass.getText().toString().matches("")) {
                                    Toast.makeText(retailerSignUpLogin.this, "Please enter in the missing fields", Toast.LENGTH_SHORT).show();
                                } else {
                                    try {


                                        String urlAddress = address.getText().toString();
                                        String urlAddress2 = urlAddress.replace(" ", "+");
                                        System.out.println(urlAddress2);
                                        JSONObject json = readJsonFromUrl("http://maps.googleapis.com/maps/api/geocode/json?address=" + urlAddress2);
                                        System.out.println(json.toString());
                                        System.out.println(json.get("results"));
                                        JSONArray arr = json.getJSONArray("results");


                                        JSONObject startofAddrCmpo = arr.getJSONObject(0);

                                        JSONArray arr3 = startofAddrCmpo.getJSONArray("address_components");
//                                    JSONObject arr2 = arr.getJSONObject(0);
                                        JSONObject firstLngNmeSet = arr3.getJSONObject(0);
                                        String by2 = firstLngNmeSet.toString();
                                        System.out.println(firstLngNmeSet);

                                        JSONObject streetNameSection = arr3.getJSONObject(1);
                                        strtName = streetNameSection.getString("long_name");
                                        System.out.println(strtName);
                                        System.out.println("Street name printed, line 656");

                                        JSONObject geometry = startofAddrCmpo.getJSONObject("geometry");
                                        JSONObject location = geometry.getJSONObject("location");
                                        lat = location.getString("lat");
                                        lng = location.getString("lng");


//                                        JSONObject oby = json.getJSONObject("results");
//                                        System.out.println(oby.toString());
//                                        JSONObject ob2 = oby.get("address_components");
//                                        System.out.println(oby.toString());


//                                    BufferedReader reader2 = null;
//                                    JsonElement my_json2;
//                                    JsonParser jsonParser2 = new JsonParser();
//                                    // Defined URL  where to send data
//                                    URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=12+Buckley+Road,+Epsom");
//
//                                    System.out.println(address.getText().toString());
//
//                                    // Send POST data request
//
//                                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                                    conn.setRequestMethod("GET");
//                                    int responseCode = conn.getResponseCode();
//                                    System.out.println("\nSending 'GET' request to URL : " + url);
//                                    System.out.println("Response Code : " + responseCode);
//
//                                    // Get the server response
//
//                                    reader2 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                                    String line = null;
//
//                                    while ((line = reader2.readLine()) != null) {
//                                        line = reader2.readLine();
//                                        // Append server response in string
//                                        sb2.append(line + "\n");
//                                    }
//                                    String f = sb2.toString();
//                                    System.out.println(f);
//                                    JSONObject obj2 = new JSONObject(f);
//                                    JSONObject obj3 = obj2.getJSONObject("results");
//
//                                    System.out.println(obj3.toString());
//
//                                    JSONArray results = obj2.getJSONArray("results");
//                                    JSONObject oby = results.getJSONObject(0);
//                                    System.out.println(oby.toString());
//                                     int n = results.length();
//                                     JSONObject person = results.getJSONObject(0);
//
//                                    System.out.println(person.getString("address_components"));
//
//
//
                                    }
                                    catch (Exception e){

                                    }
                                    finally {
                                        hash = GeoHash.withCharacterPrecision(Float.parseFloat(lat), Float.parseFloat(lng), 12);
                                        System.out.println(hash.toBase32());
                                        String retailerID2 ="";
                                        retailerID2 = "000"+hash.toBase32();



                                        try {
                                            data = "";
                                            data += "{\"" + URLEncoder.encode("address", "UTF-8") + "\"" + ":"
                                                    + "\"" + address.getText().toString() + "\"";
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try {

                                            data += "," + "\"" + URLEncoder.encode("businessName", "UTF-8") + "\"" + ":"
                                                    + "\"" + URLEncoder.encode(businessNme.getText().toString(), "UTF-8") + "\"";
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            data += "," + "\"" + URLEncoder.encode("businessType", "UTF-8") + "\""
                                                    + ":" + "\"" + businessType.getSelectedItem().toString() + "\"";
                                            System.out.println(data);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            data += "," + "\"" + URLEncoder.encode("contactFirstName", "UTF-8") + "\""
                                                    + ":" + "\"" + firstNme.getText().toString() + "\"";
                                            System.out.println(data);
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            data += "," + "\"" + URLEncoder.encode("contactLastName", "UTF-8") + "\""
                                                    + ":" + "\"" + URLEncoder.encode(lastNme.getText().toString(), "UTF-8") + "\"";
                                            System.out.println(data);
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            data += "," + "\"" + URLEncoder.encode("email", "UTF-8") + "\""
                                                    + ":" + "\"" + email.getText().toString() + "\"";
                                            System.out.println(data);
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            data += "," + "\"" + URLEncoder.encode("password", "UTF-8") + "\""
                                                    + ":" + "\"" + URLEncoder.encode(password.getText().toString(), "UTF-8") + "\"";
                                            System.out.println(data);
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            data += "," + "\"" + URLEncoder.encode("phoneNumber", "UTF-8") + "\""
                                                    + ":" + "\"" + URLEncoder.encode(phoneNum.getText().toString(), "UTF-8") + "\"";
                                            System.out.println(data);
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            data += "," + "\"" + URLEncoder.encode("username", "UTF-8") + "\""
                                                    + ":" + "\"" + URLEncoder.encode(usernme.getText().toString(), "UTF-8") + "\"";
                                            System.out.println(data);
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            data += "," + "\"" + URLEncoder.encode("retailerID", "UTF-8") + "\"" + ":"
                                                    + "\"" + URLEncoder.encode(retailerID2, "UTF-8") + "\""  + "}";
                                            System.out.println(data);
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try{
        //                                    reader2.close();

                                            // Defined URL  where to send data
                                            URL url = new URL("https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/load-retailer");

                                            // Send POST data request

                                            URLConnection conn = url.openConnection();
                                            conn.setDoOutput(true);
                                            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                                            wr.write(data);
                                            wr.flush();

                                            // Get the server response

                                            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                            String line = null;

                                            line = reader.readLine();
                                            // Append server response in string
                                            sb.append(line + "\n");
                                            String f = line;
                                            g = f.replace("\\", "").trim();
                                            reader.close();

                                        } catch (Exception ex) {
                                            System.out.println(ex);
                                            System.out.println("Some error");
                                        }
                                    }
                                    Intent intent = new Intent(retailerSignUpLogin.this, retailerPostOffer.class);
                                    intent.putExtra("lat", lat);
                                    intent.putExtra("lng", lng);
                                    intent.putExtra("retailer", businessNme.getText().toString());
                                    intent.putExtra("strtname", strtName);
                                    intent.putExtra("category", categoryString);

RelativeLayout rl = (RelativeLayout)findViewById(R.id.rl2);
                                    rl.setVisibility(View.GONE);
RelativeLayout rl2 = (RelativeLayout)findViewById(R.id.activity_retailer_post_offer);
                                    rl2.setVisibility(View.VISIBLE);
//                                    startActivity(intent);


                                }
                            } else {
                                Toast toast = Toast.makeText(retailerSignUpLogin.this, "Please agree to the terms and conditions before continuing", Toast.LENGTH_SHORT);
                                toast.show();

                            }
                        }
                        else{
                            Toast toast = Toast.makeText(retailerSignUpLogin.this, "Please select a business category", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(retailerSignUpLogin.this, "Passwords do not match, please retype", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast.makeText(retailerSignUpLogin.this,"Please enter a valid email address",Toast.LENGTH_SHORT).show();
                    email.setText("");
                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public Boolean itemClicked(View v) {
        Boolean n = null;
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {
            return n = true;

        } else {
            return n = false;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("retailerSignUpLogin Page") // TODO: Define a title for the content shown.
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
    }


    public class NothingSelectedSpinnerAdapter implements SpinnerAdapter, ListAdapter {

        protected static final int EXTRA = 1;
        protected SpinnerAdapter adapter;
        protected Context context;
        protected int nothingSelectedLayout;
        protected int nothingSelectedDropdownLayout;
        protected LayoutInflater layoutInflater;

        /**
         * Use this constructor to have NO 'Select One...' item, instead use
         * the standard prompt or nothing at all.
         *
         * @param spinnerAdapter        wrapped Adapter.
         * @param nothingSelectedLayout layout for nothing selected, perhaps
         *                              you want text grayed out like a prompt...
         * @param context
         */
        public NothingSelectedSpinnerAdapter(
                SpinnerAdapter spinnerAdapter,
                int nothingSelectedLayout, Context context) {

            this(spinnerAdapter, nothingSelectedLayout, -1, context);
        }

        /**
         * Use this constructor to Define your 'Select One...' layout as the first
         * row in the returned choices.
         * If you do this, you probably don't want a prompt on your spinner or it'll
         * have two 'Select' rows.
         *
         * @param spinnerAdapter                wrapped Adapter. Should probably return false for isEnabled(0)
         * @param nothingSelectedLayout         layout for nothing selected, perhaps you want
         *                                      text grayed out like a prompt...
         * @param nothingSelectedDropdownLayout layout for your 'Select an Item...' in
         *                                      the dropdown.
         * @param context
         */
        public NothingSelectedSpinnerAdapter(SpinnerAdapter spinnerAdapter,
                                             int nothingSelectedLayout, int nothingSelectedDropdownLayout, Context context) {
            this.adapter = spinnerAdapter;
            this.context = context;
            this.nothingSelectedLayout = nothingSelectedLayout;
            this.nothingSelectedDropdownLayout = nothingSelectedDropdownLayout;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public final View getView(int position, View convertView, ViewGroup parent) {
            // This provides the View for the Selected Item in the Spinner, not
            // the dropdown (unless dropdownView is not set).
            if (position == 0) {
                return getNothingSelectedView(parent);
            }
            return adapter.getView(position - EXTRA, null, parent); // Could re-use
            // the convertView if possible.
        }

        /**
         * View to show in Spinner with Nothing Selected
         * Override this to do something dynamic... e.g. "37 Options Found"
         *
         * @param parent
         * @return
         */
        protected View getNothingSelectedView(ViewGroup parent) {
            return layoutInflater.inflate(nothingSelectedLayout, parent, false);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            // Android BUG! http://code.google.com/p/android/issues/detail?id=17128 -
            // Spinner does not support multiple view types
            if (position == 0) {
                return nothingSelectedDropdownLayout == -1 ?
                        new View(context) :
                        getNothingSelectedDropdownView(parent);
            }

            // Could re-use the convertView if possible, use setTag...
            return adapter.getDropDownView(position - EXTRA, null, parent);
        }

        /**
         * Override this to do something dynamic... For example, "Pick your favorite
         * of these 37".
         *
         * @param parent
         * @return
         */
        protected View getNothingSelectedDropdownView(ViewGroup parent) {
            return layoutInflater.inflate(nothingSelectedDropdownLayout, parent, false);
        }

        @Override
        public int getCount() {
            int count = adapter.getCount();
            return count == 0 ? 0 : count + EXTRA;
        }

        @Override
        public Object getItem(int position) {
            return position == 0 ? null : adapter.getItem(position - EXTRA);
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position >= EXTRA ? adapter.getItemId(position - EXTRA) : position - EXTRA;
        }

        @Override
        public boolean hasStableIds() {
            return adapter.hasStableIds();
        }

        @Override
        public boolean isEmpty() {
            return adapter.isEmpty();
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            adapter.registerDataSetObserver(observer);
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            adapter.unregisterDataSetObserver(observer);
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            return position != 0; // Don't allow the 'nothing selected'
            // item to be picked.
        }

    }

}
