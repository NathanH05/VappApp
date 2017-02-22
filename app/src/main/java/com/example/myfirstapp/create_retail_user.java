package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.samples.quickstart.signin.login_activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;

import io.fabric.sdk.android.services.concurrency.AsyncTask;

public class create_retail_user extends AppCompatActivity {

    String lat = "";
    String lng = "";
    String g = "";
    String emailString = "";
    Boolean exists = null;
    StringBuilder sb = new StringBuilder();
    Context mContext;
    String passw = "";
    String streetName = "";
    String strtName = "";
    Boolean retailerOrUser = false;
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    YourTask mTask;

    Boolean barsandclubsBool = false;
    Boolean shoppingBool = false;
    Boolean hospoBool = false;
    Boolean entertainmentBool = false;
    Boolean accomBool = false;
    Boolean activitiesBool = false;

    String businessTypeString = "|";


    String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_create_retail_user);
        final EditText website = (EditText)findViewById(R.id.website);

        ImageView save = (ImageView) findViewById(R.id.savebutton);
        website.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    website.setText("http://");
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RelativeLayout loadingPanel = (RelativeLayout) findViewById(R.id.loadingPanel);

                mTask = new YourTask();
                mTask.execute();


            }
        });

//
//        businessName.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);
//        businessType.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);
//        email.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);
//        address.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);
//        phone.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);
//        website.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);
//        password.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);
//        confirmpassword.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);
//        contactemail.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);
//        contactperson.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);
        final ImageView entertainment = (ImageView)findViewById(R.id.entertainment);
        entertainment.setAlpha(.5f);
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entertainmentBool) {
                    entertainment.setAlpha(.5f);
                    entertainmentBool = false;
                }
                else{
                    entertainment.setAlpha(1f);
                    entertainmentBool = true;
                }

            }
        });

        final ImageView shopping = (ImageView)findViewById(R.id.shopping);
        shopping.setAlpha(.5f);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shoppingBool) {
                    shopping.setAlpha(.5f);
                    shoppingBool = false;
                }
                else{
                    shopping.setAlpha(1f);
                    shoppingBool = true;
                }

            }
        });

        final ImageView activities = (ImageView)findViewById(R.id.activities);
        activities.setAlpha(.5f);
        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activitiesBool) {
                    activities.setAlpha(.5f);
                    activitiesBool = false;
                }
                else{
                    activities.setAlpha(1f);
                    activitiesBool = true;
                }

            }
        });

        final ImageView accomodation = (ImageView)findViewById(R.id.accom);
        accomodation.setAlpha(.5f);
        accomodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accomBool) {
                    accomodation.setAlpha(.5f);
                    accomBool = false;
                }
                else{
                    accomodation.setAlpha(1f);
                    accomBool = true;
                }

            }
        });

        final ImageView hospo = (ImageView)findViewById(R.id.hospo);
        hospo.setAlpha(.5f);
        hospo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hospoBool) {
                    hospo.setAlpha(.5f);
                    hospoBool = false;
                }
                else{
                    hospo.setAlpha(1f);
                    hospoBool = true;
                }

            }
        });

        final ImageView barsandclubs = (ImageView)findViewById(R.id.barsandclubs);
        barsandclubs.setAlpha(.5f);
        barsandclubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(barsandclubsBool) {
                    barsandclubs.setAlpha(.5f);
                    barsandclubsBool = false;
                }
                else{
                    barsandclubs.setAlpha(1f);
                    barsandclubsBool = true;
                }

            }
        });

        TextView returnToLogin = (TextView) findViewById(R.id.returnToLogin);
        returnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_retail_user.this, login_activity.class);
                startActivity(intent);
            }
        });

    }
    // Async Task to access the web
    class YourTask extends AsyncTask<String, Void, String> {
        RelativeLayout loadingPanel;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            System.out.println("do in the pre");
            loadingPanel = (RelativeLayout)findViewById(R.id.loadingPanel);

            super.onPreExecute();
            loadingPanel.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            System.out.println("do in the bg");
                    final EditText businessName = (EditText) findViewById(R.id.businessName);
                    final EditText businessType = (EditText) findViewById(R.id.businessType);
                    final EditText email = (EditText) findViewById(R.id.email);
                    final EditText address = (EditText) findViewById(R.id.address);
                    final EditText phone = (EditText) findViewById(R.id.phone);
                    final EditText password = (EditText) findViewById(R.id.password);
                    final EditText confirmpassword = (EditText) findViewById(R.id.confirmpassword);
                    final EditText contactperson = (EditText) findViewById(R.id.contactperson);
                    final EditText contactphone = (EditText) findViewById(R.id.contactphone);
                    final EditText contactemail = (EditText) findViewById(R.id.contactEmail);
                    final EditText website = (EditText)findViewById(R.id.website);
                    final String retId = "000rckq4mr" + randomString(5);



//stuff that updates ui
                    if(entertainmentBool){
                        businessTypeString+="entertainment|";
                    }
                    if(shoppingBool){
                        businessTypeString+="shopping|";
                    }
                    if(activitiesBool){
                        businessTypeString+="activities|";
                    }
                    if(accomBool){
                        businessTypeString+="accommodation|";
                    }
                    if(hospoBool){
                        businessTypeString+="hospitality|";
                    }
                    if(barsandclubsBool){
                        businessTypeString+="barsandclubs|";
                    }
                    System.out.println(businessTypeString);

                    if (!businessName.getText().toString().equals("") && businessTypeString.length()>1 &&
                            !email.getText().toString().equals("")
                            && !address.getText().toString().equals("") && !phone.getText().toString().equals("") &&
                            !website.getText().toString().equals("") && !password.getText().toString().equals("") && !confirmpassword.getText().equals("")
                            && !contactperson.getText().toString().equals("") && !contactphone.getText().toString().equals("") && !contactemail.getText().equals("")) {


                        if (isValidEmail(email.getText()) && isValidEmail(contactemail.getText().toString())) {
                            if (confirmpassword.getText().toString().equals(password.getText().toString())) {
                                if (phone.getText().toString().length() > 6 && contactphone.getText().toString().length() > 6 &&
                                        !phone.getText().toString().matches(".*[a-zA-Z]+.*") &&
                                        !contactphone.getText().toString().matches(".*[a-zA-Z]+.*")
                                        ) {
                                    try {
                                        URL u = new URL(website.getText().toString()); // this would check for the protocol
                                        u.toURI();


                                        System.out.println(email);
                                        String userPassword = password.getText().toString();
                                        System.out.println(password.getText().toString());
                                        String data = null;

                                        try {
                                            data = "";
                                            data += "{\"" + URLEncoder.encode("email", "UTF-8") + "\"" + ":"
                                                    + "\"" + email.getText().toString() + "\"";
                                            System.out.println("ha");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            data += "," + "\"" + URLEncoder.encode("businessName", "UTF-8") + "\"" + ":"
                                                    + "\"" + businessName.getText().toString() + "\"";
                                            System.out.println("ha");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            data += "," + "\"" + URLEncoder.encode("businessType", "UTF-8") + "\"" + ":"
                                                    + "\"" + businessTypeString + "\"";
                                            System.out.println("ha");
                                            businessTypeString="|";
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            data += "," + "\"" + URLEncoder.encode("phone", "UTF-8") + "\"" + ":"
                                                    + "\"" + phone.getText().toString() + "\"";
                                            System.out.println("ha");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            data += "," + "\"" + URLEncoder.encode("contactName", "UTF-8") + "\"" + ":"
                                                    + "\"" + contactperson.getText().toString() + "\"";
                                            System.out.println("ha");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            data += "," + "\"" + URLEncoder.encode("address", "UTF-8") + "\"" + ":"
                                                    + "\"" + address.getText().toString() + "\"";
                                            System.out.println("ha");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            data += "," + "\"" + URLEncoder.encode("website", "UTF-8") + "\"" + ":"
                                                    + "\"" + website.getText().toString() + "\"";
                                            System.out.println("ha");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            data += "," + "\"" + URLEncoder.encode("contactEmail", "UTF-8") + "\"" + ":"
                                                    + "\"" + contactemail.getText().toString() + "\"";
                                            System.out.println("ha");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            data += "," + "\"" + URLEncoder.encode("contactPerson", "UTF-8") + "\"" + ":"
                                                    + "\"" + contactperson.getText().toString() + "\"";
                                            System.out.println("ha");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            data += "," + "\"" + URLEncoder.encode("contactPhone", "UTF-8") + "\"" + ":"
                                                    + "\"" + contactphone.getText().toString() + "\"";
                                            System.out.println("ha");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            data += "," + "\"" + URLEncoder.encode("retailerID", "UTF-8") + "\"" + ":"
                                                    + "\"" + retId + "\"";
                                            System.out.println("ha");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        try {
                                            data += "," + "\"" + URLEncoder.encode("password", "UTF-8") + "\""
                                                    + ":" + "\"" + URLEncoder.encode(userPassword, "UTF-8") + "\"" + "}";
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
                                            URL url = new URL("https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/create-retailer-user");

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


                                            System.out.println(emailString);
                                            System.out.println(exists);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        if (exists) {
                                            runOnUiThread(new Runnable() {

                                                              @Override
                                                              public void run() {
                                                                  Toast.makeText(create_retail_user.this, "You are already registered, please login or use 'Forget Password", Toast.LENGTH_LONG).show();
                                                              }
                                                          });

                                            businessTypeString="|";

//                            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//
//                            // Inflate the custom layout/view
//                            View customView = inflater.inflate(R.layout.email_layout, null);
                                            if (!passw.equals(userPassword)) {
                                                businessTypeString="|";

                                            } else {
//                                        loadingPanel.setVisibility(View.GONE);

//                    RelativeLayout rl4 = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer);
//                    rl4.setVisibility(View.GONE);
//                    RelativeLayout rl5 = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer2);
//                    rl5.setVisibility(View.VISIBLE);

//                            Intent intent = new Intent(retailerSignUpLogin.this, retailerPostOffer.class);
//                            startActivity(intent);

                                            }
                                        } else {
                                            runOnUiThread(new Runnable() {

                                                @Override
                                                public void run() {
                                                    Toast.makeText(create_retail_user.this, "Thanks for joining, please login as " + email.getText().toString(), Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(create_retail_user.this, login_activity.class);
                                                    startActivity(intent);                                                }
                                            });


                                        }

                                    } catch (URISyntaxException |MalformedURLException e) {
                                        runOnUiThread(new Runnable() {

                                            @Override
                                            public void run() {
                                                Toast.makeText(create_retail_user.this, "Please enter valid website", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                        businessTypeString="|";
                                    }
                                }
                                else{
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            Toast.makeText(create_retail_user.this, "Please enter valid phone number", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    businessTypeString="|";

                                }
                            } else {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        Toast.makeText(create_retail_user.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                                    }
                                });
                                businessTypeString="|";

                            }
                        } else {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    Toast.makeText(create_retail_user.this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
                                }
                            });
                            businessTypeString="|";
                        }
                    } else {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(create_retail_user.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                            }
                        });
                        businessTypeString="|";

                    }


            //Things should do in, until progress bar close
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("on Post E");

            loadingPanel.setVisibility(View.GONE);
        }
    }

}
