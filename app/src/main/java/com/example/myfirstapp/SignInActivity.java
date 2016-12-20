package com.example.myfirstapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.samples.quickstart.signin.MapsActivity;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.fabric.sdk.android.Fabric;


/**
 * Activity to demonstrate basic retrieval of the Google user's ID, email address, and basic
 * profile.
 */
public class SignInActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    StringBuffer response = new StringBuffer();

    CallbackManager callbackManager;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;
    LoginButton loginButton;
    private TwitterLoginButton loginButton2;
    private Context mContext;
    View mLinearLayout;
    PopupWindow mPopupWindow;
    String g ="";
    String emailString ="";
    Boolean exists = null;

    StringBuilder sb = new StringBuilder();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    public class GameAnimationListener
            implements Animation.AnimationListener {

        public GameAnimationListener(SignInActivity signInActivity) {
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


    @Override
    @Nullable
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();


        TwitterAuthConfig authConfig =
                new TwitterAuthConfig("9u57HOuYQfeqenEjeNSGqSAtM",
                        "FPoyVBcbEVA6OJUSzOiMgOVfu1dQU0HOPn54MPL3Y4qPtzVDAm");

        Fabric.with(this, new TwitterCore(authConfig));
        setContentView(R.layout.activity_main);




        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        final LoginButton loginbutton = (LoginButton) findViewById(R.id.login_button);

        loginbutton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        System.out.println("Success");
                        Intent intent = new Intent(SignInActivity.this, MapsActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

// Get the application context
        mContext = getApplicationContext();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.email_layout,null);


        final Button createAccountButton = (Button) findViewById(R.id.createAccount);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
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

                        EditText email = (EditText) customView.findViewById(R.id.email);
                        EditText password = (EditText) customView.findViewById(R.id.password);
                        // Create data variable for sent values to server
                        String userEmail = email.getText().toString();
                        System.out.println(userEmail);
                        String userPassword = password.getText().toString();
                        System.out.println(password.getText().toString());
                        String data = null;

                        try {
                            data ="";
                            data += "{\"" + URLEncoder.encode("email", "UTF-8") + "\"" + ":"
                                    + "\"" + URLEncoder.encode(userEmail, "UTF-8") + "\"" ;
                            System.out.println("ha");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        try {
                            data += "," + "\"" + URLEncoder.encode("password", "UTF-8") + "\""
                                    + ":" + "\"" +URLEncoder.encode(userPassword, "UTF-8") + "\"" + "}";
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
                            my_json=jsonParser.parse(g);
                            JSONObject obj = new JSONObject(g);
                            emailString = obj.getString("email");
                            exists = obj.getBoolean("exists");
                            System.out.println(emailString);
                            System.out.println(exists);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (exists != false){
//                            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//
//                            // Inflate the custom layout/view
//                            View customView = inflater.inflate(R.layout.email_layout, null);

                            TextView validator = (TextView) customView.findViewById(R.id.validator);
                            validator.setVisibility(View.VISIBLE);

                        }
                        else {
                            mPopupWindow.dismiss();

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
                });
                mLinearLayout = (ScrollView) findViewById(R.id.main_layout);

                mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER, 0, 0);
                mPopupWindow.update(1300, 1000);
                mPopupWindow.setFocusable(true);
                mPopupWindow.update();
            }
        });


        Button myLoginButton = (Button) findViewById(R.id.myLoginButton);
        myLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton2 = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
                loginButton2.setCallback(new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        // The TwitterSession is also available through:
                        // Twitter.getInstance().core.getSessionManager().getActiveSession()
                        TwitterSession session = result.data;
                        // TODO: Remove toast and use the TwitterSession's userID
                        // with your app's user model
                        String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Log.d("TwitterKit", "Login with Twitter failure", exception);
                    }
                });
                loginButton2.performClick();
            }
        });
        loginButton2 = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton2.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
//            LoginButton loginButton = (LoginButton) view.findViewById(R.id.usersettings_fragment_login_button);
//            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() { ... });


        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    this.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Button clueBtn = (Button) findViewById(R.id.retailerButton);

        Animation animation =
                AnimationUtils.loadAnimation(this,
                        R.anim.slide_left);


        animation.setAnimationListener
                (new GameAnimationListener(this));

        clueBtn.startAnimation(animation);

        Button clueBtn2 = (Button) findViewById(R.id.login_button);

        Animation animation2 =
                AnimationUtils.loadAnimation(this,
                        R.anim.slid_up);
        animation2.setAnimationListener
                (new GameAnimationListener(this));

        clueBtn2.startAnimation(animation2);
        Button clueBtn3 = (Button) findViewById(R.id.twitter_login_button);

        Animation animation3 =
                AnimationUtils.loadAnimation(this,
                        R.anim.slid_up);
        animation3.setAnimationListener
                (new GameAnimationListener(this));

        clueBtn3.startAnimation(animation3);
        Button clueBtn4 = (Button) findViewById(R.id.myLoginButton);

        Animation animation4 =
                AnimationUtils.loadAnimation(this,
                        R.anim.slid_up);
        animation4.setAnimationListener
                (new GameAnimationListener(this));
        clueBtn4.startAnimation(animation4);
        SignInButton clueBtn5 = (SignInButton) findViewById(R.id.sign_in_button);

        Animation animation5 =
                AnimationUtils.loadAnimation(this,
                        R.anim.slid_up);
        animation5.setAnimationListener
                (new GameAnimationListener(this));

        clueBtn5.startAnimation(animation5);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);
//
//        ImageView imgFavorite = (ImageView) findViewById(R.id.shoppingBag);
//        imgFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        // Views
        mStatusTextView = (TextView) findViewById(R.id.status);

        // Button listeners
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.disconnect_button).setOnClickListener(this);

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        // [END build_client]

        // [START customize_button]
        // Customize sign-in button. The sign-in button can be displayed in
        // multiple sizes and color schemes. It can also be contextually
        // rendered based on the requested scopes. For example. a red button may
        // be displayed when Google+ scopes are requested, but a white button
        // may be displayed when only basic profile is requested. Try adding the
        // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
        // difference.
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        // [END customize_button]
    }


    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume");
    }


    @Override
    public void onStart() {
        super.onStart();
        System.out.println("onStart");

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

//    @Override
//    public View onCreateView(
//            LayoutInflater inflater,
//            ViewGroup container,
//            Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.third_layout, container, false);
//
//        loginButton = (LoginButton) view.findViewById(R.id.login_button);
//        loginButton.setReadPermissions("email");
//        // If using in a fragment
//        loginButton.setFragment(this);
//        // Other app specific specialization
//
//        // Callback registration
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//            }
//        });
//        return view;
//    }

    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        loginButton2.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            updateUI(true);

        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }
    // [END handleSignInResult]

    // [START signIn]


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]

    // [START revokeAccess]
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END revokeAccess]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText(R.string.signed_out);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    public void maps_activity(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void postRetailOffer(View view) {
        Intent intent = new Intent(SignInActivity.this, retailerSignUpLogin.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                signOut();
                break;
            case R.id.disconnect_button:
                revokeAccess();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


}
