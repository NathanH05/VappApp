package com.example.myfirstapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.UUID;

import ch.hsr.geohash.GeoHash;

public class retailerPostOffer extends AppCompatActivity {
    int year;
    int month;
    int day;
    int progress = 30;
    int progress2 = 50;
    String g = "";
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
    String streetName2 ="";
    String categoryString ="";
    String retailerName ="";
    private PopupWindow mPopupWindow;
    private GeoHash hash;


    public void hideMe(View v) {
        mPopupWindow.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_post_offer);
        setCurrentDateOnView();
        Bundle p = getIntent().getExtras();
        try {

             lat = p.getString("lat");
             streetName = p.getString("strtname");
            assert streetName != null;
            streetName2 = streetName.replace("+"," ");
            System.out.println(streetName);

            lng = p.getString("lng");
            retailerName = p.getString("retailer");
            categoryString = p.getString("category");
            System.out.println(lat);
            System.out.println(categoryString);
        }
        catch (Exception e){
            System.out.println(e);
        }




        // Get the application context
        mContext = getApplicationContext();

        // Get the activity
        mActivity = retailerPostOffer.this;

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer);
        mImageView = (ImageView) findViewById(R.id.share);
        mImageView2 = (ImageView) findViewById(R.id.coupon2);


        // Set a click listener for the text view
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(retailerPostOffer.this, R.anim.image_click));
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
                v.startAnimation(AnimationUtils.loadAnimation(retailerPostOffer.this, R.anim.image_click));

                Intent intent = new Intent(retailerPostOffer.this,couponActivity.class);
                startActivity(intent);

            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final TextView seekBarValue = (TextView) findViewById(R.id.seekBarvalue);
        seekBarValue.setText((String.valueOf(progress)));
//        seekBar.incrementProgressBy(15);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer);




            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBarValue.setText((String.valueOf(progress)));
                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideSoftKeyboard(retailerPostOffer.this);
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
                        hideSoftKeyboard(retailerPostOffer.this);
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

//        addListenerOnButton();
//        addListenerOnSpinnerItemSelection();
    }




    // display current date
    public void setCurrentDateOnView() {

        DatePicker dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) ;
        day = c.get(Calendar.DAY_OF_MONTH)+1;

        // set current date into textview
//        tvDisplayDate.setText(new StringBuilder()
//                // Month is 0 based, just add 1
//                .append(month + 1).append("-").append(day).append("-")
//                .append(year).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 98:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            DatePicker dpResult = (DatePicker) findViewById(R.id.dpResult);

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideSoftKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }
}
