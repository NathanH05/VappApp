package com.google.samples.quickstart.signin;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.mail.MessagingException;

import io.fabric.sdk.android.services.concurrency.AsyncTask;

public class forgotPassword extends AppCompatActivity {
    @ColorInt
    final int GREEN1 = 0xFF91a832;
    RelativeLayout loadingPanel;
    Context mContext;
    String g = "";
    StringBuilder sb = new StringBuilder();
    Boolean exists;
    String userPasswordResponse;
    String emailString;


    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();

        final ImageView usernameCheckerImage = (ImageView)findViewById(R.id.usernameCheckerImage);
        final EditText username = (EditText)findViewById(R.id.username);

        RelativeLayout relLay = (RelativeLayout)findViewById(R.id.activity_forgot_password);
        relLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().equals("")) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    usernameCheckerImage.setImageResource(R.drawable.tickgreen);
                }
                else{
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });



        username.getBackground().setColorFilter(GREEN1, PorterDuff.Mode.SRC_OUT);

        TextView returntologin = (TextView)findViewById(R.id.returntologin);
        returntologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forgotPassword.this,login_activity.class);
                startActivity(intent);
            }
        });

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if(!username.getText().toString().equals("")){
                        usernameCheckerImage.setImageResource(R.drawable.tickgreen);
                    }
                    else{
                        usernameCheckerImage.setImageResource(R.drawable.crossorange);

                    }
                } else {
                    System.out.println(username.getText().toString());

                    if(!username.getText().toString().equals("")){
                        usernameCheckerImage.setImageResource(R.drawable.tickgreen);
                    }
                    else{
                        usernameCheckerImage.setImageResource(R.drawable.crossorange);

                    }
                }
            }
        });


        username.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE && !username.getText().toString().equals("") && isValidEmail(username.getText())) {

                    usernameCheckerImage.setImageResource(R.drawable.tickgreen);
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    isValidEmail(username.getText());

                    return true;
                }
                else {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return false;
                }
            }



        });

        ImageView send = (ImageView)findViewById(R.id.savebutton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().equals("")){
                    if(isValidEmail(username.getText())){
                        Intent intent = new Intent(forgotPassword.this,login_activity.class);
                        startActivity(intent);
                        String useremail = username.getText().toString();

                        userFgPass userFgPass = new userFgPass();
                        userFgPass.execute();

                        Toast.makeText(forgotPassword.this,"Password sent to " + useremail,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(forgotPassword.this,"Please enter a valid email address",Toast.LENGTH_LONG).show();
                    }

                }
                Toast.makeText(forgotPassword.this,"Please enter email address",Toast.LENGTH_LONG).show();



            }
        });

    }

    // Async Task to access the web
    class userFgPass extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            loadingPanel = (RelativeLayout) findViewById(R.id.loadingPanel);
            loadingPanel.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            final EditText userEmail = (EditText) findViewById(R.id.username);

            System.out.println(userEmail);

            final Boolean j = isValidEmail(userEmail.getText().toString());


            loadingPanel.setVisibility(View.VISIBLE);
            mContext = getApplicationContext();

            if (j == true) {
                // Create data variable for sent values to server
                System.out.println(userEmail);
final String userPassword ="d";
                String data = null;

                try {
                    data = "";
                    data += "{\"" + URLEncoder.encode("email", "UTF-8") + "\"" + ":"
                            + "\"" + userEmail.getText().toString() + "\"";
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
                    System.out.println("Error");
                    e.printStackTrace();
                }
                System.out.println("Error2");

                String text = "";
                BufferedReader reader = null;

                // Send data
                try {

                    // Defined URL  where to send data
                    URL url = new URL("https://9ex1ark3n8.execute-api.us-west-2.amazonaws.com/test/standard-user-login");

                    // Send POST data request
                    System.out.println("Error3");

                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write(data);
                    wr.flush();
                    System.out.println("Error4");

                    // Get the server response

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    System.out.println("Error5");


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
                    userPasswordResponse = obj.getString("password");
                    exists = obj.getBoolean("exists");
                    System.out.println(emailString);
                    System.out.println(exists);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (exists) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            loadingPanel.setVisibility(View.GONE);
                        }
                    });


//                            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//
//                            // Inflate the custom layout/view
//                            View customView = inflater.inflate(R.layout.email_layout, null);

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                String[] toppings = new String[3];
                                toppings[0] = userEmail.getText().toString();
                                toppings[1] = userPasswordResponse;
                                ComposeAndSendMIMEEmail cm = new ComposeAndSendMIMEEmail();
                                try {
                                    cm.main(toppings);
                                } catch (MessagingException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Intent intent = new Intent(forgotPassword.this, login_activity.class);
                                startActivity(intent);
                                Toast.makeText(forgotPassword.this, "Your password has been emailed to  " + userEmail.getText().toString(), Toast.LENGTH_LONG).show();
                                userEmail.setText("");
                            }
                        });


//                    RelativeLayout rl4 = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer);
//                    rl4.setVisibility(View.GONE);
//                    RelativeLayout rl5 = (RelativeLayout) findViewById(R.id.activity_retailer_post_offer2);
//                    rl5.setVisibility(View.VISIBLE);

//                            Intent intent = new Intent(retailerSignUpLogin.this, retailerPostOffer.class);
//                            startActivity(intent);


                } else {

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            TextView validator = (TextView) findViewById(R.id.validator);
                            validator.setVisibility(View.VISIBLE);

                        }
                    });

//                TextView validator = (TextView) findViewById(R.id.validator);
//                validator.setVisibility(View.VISIBLE);

                }


            } else {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(forgotPassword.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                        userEmail.setText("");
                    }
                });


            }
            //Things should do in, until progress bar close
            return null;

        }

        @Override
        protected void onPostExecute(String result) {

            loadingPanel.setVisibility(View.GONE);
        }
    }
}
