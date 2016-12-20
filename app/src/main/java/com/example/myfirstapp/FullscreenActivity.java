package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.google.samples.quickstart.signin.MapsActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
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
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {

        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private boolean beverageIsClicked = false;
    private boolean gymIsClicked = false;
    private boolean moviesIsClicked = false;
    private boolean groceriesIsClicked = false;
    private boolean techIsClicked = false;
    private boolean coffeeIsClicked = false;
    private boolean shoppingIsClicked = false;
    private boolean fastfoodsIsClicked = false;
    private boolean restaurantsIsClicked = false;
public Boolean firstSubmit;
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

        setContentView(R.layout.activity_fullscreen);

            //The key argument here must match that used in the other activity

        Bundle extras = getIntent().getExtras();
        if (extras != null) {





             firstSubmit = extras.getBoolean("firstSubmit");
            System.out.println("The 1st submit when being get" +firstSubmit);






            if (firstSubmit == true){


                firstSubmit = extras.getBoolean("firstSubmit");

                firstSubmit = false;

            }
            else {
                groceriesIsClicked = extras.getBoolean("groceries");
                moviesIsClicked = extras.getBoolean("movies");
                beverageIsClicked = extras.getBoolean("beverages");
                restaurantsIsClicked = extras.getBoolean("restaurants");
                gymIsClicked = extras.getBoolean("gym");
                fastfoodsIsClicked = extras.getBoolean("fastfoods");
                techIsClicked = extras.getBoolean("tech");
                shoppingIsClicked = extras.getBoolean("shopping");
                coffeeIsClicked = extras.getBoolean("coffee");
                firstSubmit = extras.getBoolean("firstSubmit");


                        if (coffeeIsClicked == false){
                            ImageView goldBar = (ImageView) findViewById(R.id.goldbarCoffee);
                            goldBar.setVisibility(View.INVISIBLE);
                            coffeeIsClicked = true;
                        }
                        else {
                            ImageView goldBar = (ImageView) findViewById(R.id.goldbarCoffee);
                            goldBar.setVisibility(View.VISIBLE);
                            coffeeIsClicked = false;
                        }
                if (beverageIsClicked == false){
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarBeverages);
                    goldBar.setVisibility(View.INVISIBLE);
                    beverageIsClicked = true;
                }
                else {
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarBeverages);
                    goldBar.setVisibility(View.VISIBLE);
                    beverageIsClicked = false;
                }

                if (shoppingIsClicked == false){
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarShopping);
                    goldBar.setVisibility(View.INVISIBLE);
                    shoppingIsClicked = true;
                }
                else {
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarBeverages);
                    goldBar.setVisibility(View.VISIBLE);
                    shoppingIsClicked = false;
                }
                if (techIsClicked == false){
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarTech);
                    goldBar.setVisibility(View.INVISIBLE);
                    techIsClicked = true;
                }
                else {
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarTech);
                    goldBar.setVisibility(View.VISIBLE);
                    techIsClicked = false;
                }



                if (fastfoodsIsClicked == false){
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarFastfoods);
                    goldBar.setVisibility(View.INVISIBLE);
                    fastfoodsIsClicked = true;
                }
                else {
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarFastfoods);
                    goldBar.setVisibility(View.VISIBLE);
                    fastfoodsIsClicked = false;
                }


                        System.out.println("The 1st submit when being getelsed" +firstSubmit);

//                Toast toast = Toast.makeText(FullscreenActivity.this, "no extras", Toast.LENGTH_SHORT);
//                toast.show();

            }
        }
        else {

        }

        mVisible = true;
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.alert_blop);

        ImageView imgFavorite = (ImageView) findViewById(R.id.beverages);
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if (beverageIsClicked == false){
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarBeverages);
                    goldBar.setVisibility(View.INVISIBLE);
                    beverageIsClicked = true;
                }
                else {
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarBeverages);
                    goldBar.setVisibility(View.VISIBLE);
                    beverageIsClicked = false;
                }



                    v.startAnimation(AnimationUtils.loadAnimation(FullscreenActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });

        ImageView imgFavorite2 = (ImageView) findViewById(R.id.shopping);
        imgFavorite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();

                if (shoppingIsClicked == false){
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarShopping);
                    goldBar.setVisibility(View.INVISIBLE);
                    shoppingIsClicked = true;
                }
                else {
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarShopping);
                    goldBar.setVisibility(View.VISIBLE);
                    shoppingIsClicked = false;
                }
                v.startAnimation(AnimationUtils.loadAnimation(FullscreenActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });
        ImageView imgFavorite3 = (ImageView) findViewById(R.id.tech);
        imgFavorite3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
mp.start();
                if (techIsClicked == false){
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarTech);
                    goldBar.setVisibility(View.INVISIBLE);
                    techIsClicked = true;
                }
                else {
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarTech);
                    goldBar.setVisibility(View.VISIBLE);
                    techIsClicked = false;
                }

                v.startAnimation(AnimationUtils.loadAnimation(FullscreenActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });


        ImageView imgFavorite6 = (ImageView) findViewById(R.id.fastfoods);
        imgFavorite6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if (fastfoodsIsClicked == false){
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarFastfoods);
                    goldBar.setVisibility(View.INVISIBLE);
                    fastfoodsIsClicked = true;
                }
                else {
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarFastfoods);
                    goldBar.setVisibility(View.VISIBLE);
                    fastfoodsIsClicked = false;
                }

                v.startAnimation(AnimationUtils.loadAnimation(FullscreenActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });
        ImageView imgFavorite7 = (ImageView) findViewById(R.id.movies);
        imgFavorite7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if (moviesIsClicked == false){
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarMovies);
                    goldBar.setVisibility(View.INVISIBLE);
                    moviesIsClicked = true;
                }
                else {
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarMovies);
                    goldBar.setVisibility(View.VISIBLE);
                    moviesIsClicked = false;
                }


                v.startAnimation(AnimationUtils.loadAnimation(FullscreenActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });
        ImageView imgFavorite8 = (ImageView) findViewById(R.id.coffee);
        imgFavorite8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if (coffeeIsClicked == false){
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarCoffee);
                    goldBar.setVisibility(View.INVISIBLE);
                    coffeeIsClicked = true;
                }
                else {
                    ImageView goldBar = (ImageView) findViewById(R.id.goldbarCoffee);
                    goldBar.setVisibility(View.VISIBLE);
                    coffeeIsClicked = false;
                }


                v.startAnimation(AnimationUtils.loadAnimation(FullscreenActivity.this, R.anim.image_click));
//Your other coding if present

            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        Button filter = (Button) findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starting the previous Intent
                Intent previousScreen = new Intent(getApplicationContext(), MapsActivity.class);
                //Sending the data to Activity_A
//String f = previousScreen.getExtras().getString("d");
//
//                Toast toast = Toast.makeText(FullscreenActivity.this, f,Toast.LENGTH_SHORT);
//toast.show();
firstSubmit = false;

                previousScreen.putExtra("groceries",groceriesIsClicked);
                previousScreen.putExtra("tech",techIsClicked);
                previousScreen.putExtra("movies",moviesIsClicked);
                previousScreen.putExtra("fastfoods",fastfoodsIsClicked);
                previousScreen.putExtra("shopping",shoppingIsClicked);
                previousScreen.putExtra("gym",gymIsClicked);
                previousScreen.putExtra("beverages",beverageIsClicked);
                previousScreen.putExtra("coffee",coffeeIsClicked);
                previousScreen.putExtra("restaurant",restaurantsIsClicked);
                System.out.println("The 1st submit when being put" +firstSubmit);
                previousScreen.putExtra("firstSubmit",firstSubmit);

                setResult(1000, previousScreen);
                finish();


            }});

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

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
