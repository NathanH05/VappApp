package com.google.samples.quickstart.signin;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.couponActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class couponsDash extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    };
    private boolean mVisible;



    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_coupons_dash);
        final TextView retName = (TextView) findViewById(R.id.retailerStoredName);
        final TextView offerDetail = (TextView) findViewById(R.id.offerDetail);
        final TextView offerDetailb = (TextView) findViewById(R.id.offerDetailb);
        final TextView retNameb = (TextView) findViewById(R.id.retailerStoredNameb);
        final TextView offerDetailz = (TextView) findViewById(R.id.offerDetailz);
        final TextView retNamez = (TextView) findViewById(R.id.retailerStoredNamez);

        ImageView sharez = (ImageView)findViewById(R.id.sharez);
        ImageView sharex = (ImageView)findViewById(R.id.sharex);
        ImageView share = (ImageView)findViewById(R.id.share);

        ImageView qr = (ImageView)findViewById(R.id.qr);
        ImageView qrz = (ImageView)findViewById(R.id.qrz);
        ImageView qry = (ImageView)findViewById(R.id.qry);

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(couponsDash.this,couponActivity.class);
                intent.putExtra("retailer", retName.getText().toString());
                intent.putExtra("offerDesc1", offerDetail.getText().toString());
                startActivity(intent);
            }
        });
        qry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(couponsDash.this,couponActivity.class);
                intent.putExtra("retailer", retNameb.getText().toString());
                intent.putExtra("offerDesc1", offerDetailb.getText().toString());
                startActivity(intent);
            }
        });
        qrz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(couponsDash.this,couponActivity.class);
                intent.putExtra("retailer", retNamez.getText().toString());
                intent.putExtra("offerDesc1", offerDetailz.getText().toString());
                startActivity(intent);
            }
        });

        sharez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
//
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
                            intent.putExtra(Intent.EXTRA_TEXT, (retNamez.getText().toString() + "\n" + offerDetailz.getText().toString() + "\n" + offer2 + "\n" + offer3));
                        } else if (packageName.contains("facebook")) {
                            // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                            // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                            // will show the <meta content ="..."> text from that page with our link in Facebook.
                            intent.putExtra(Intent.EXTRA_TEXT, (retNamez.getText().toString() + "\n" + offerDetailz.getText().toString() + "\n" + offer2 + "\n" + offer3));
                        } else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                            intent.putExtra(Intent.EXTRA_SUBJECT, retNamez.getText().toString() + "\n" + offerDetailz.getText().toString() + "\n" + offer2 + "\n" + offer3);
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



 sharex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
//
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
                            intent.putExtra(Intent.EXTRA_TEXT, (retNameb.getText().toString() + "\n" + offerDetailb.getText().toString() + "\n" + offer2 + "\n" + offer3));
                        } else if (packageName.contains("facebook")) {
                            // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                            // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                            // will show the <meta content ="..."> text from that page with our link in Facebook.
                            intent.putExtra(Intent.EXTRA_TEXT, (retNameb.getText().toString() + "\n" + offerDetailb.getText().toString() + "\n" + offer2 + "\n" + offer3));
                        } else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                            intent.putExtra(Intent.EXTRA_SUBJECT, retNameb.getText().toString() + "\n" + offerDetailb.getText().toString() + "\n" + offer2 + "\n" + offer3);
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



 share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
//
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
                            intent.putExtra(Intent.EXTRA_TEXT, (retName.getText().toString() + "\n" + offerDetail.getText().toString() + "\n" + offer2 + "\n" + offer3));
                        } else if (packageName.contains("facebook")) {
                            // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                            // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                            // will show the <meta content ="..."> text from that page with our link in Facebook.
                            intent.putExtra(Intent.EXTRA_TEXT, (retName.getText().toString() + "\n" + offerDetail.getText().toString() + "\n" + offer2 + "\n" + offer3));
                        } else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                            intent.putExtra(Intent.EXTRA_SUBJECT, retName.getText().toString() + "\n" + offerDetail.getText().toString() + "\n" + offer2 + "\n" + offer3);
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


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    @SuppressLint("InlinedApi")
    private void show() {

    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
    }
}
