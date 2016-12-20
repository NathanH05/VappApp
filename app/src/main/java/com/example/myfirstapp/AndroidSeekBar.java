package com.example.myfirstapp;

/**
 * Created by nathanhampshire on 13/11/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class AndroidSeekBar extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_retailer_post_offer);

    SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
    final TextView seekBarValue = (TextView)findViewById(R.id.seekBarvalue);

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
        boolean fromUser) {
            // TODO Auto-generated method stub
            seekBarValue.setText(String.valueOf(progress));
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
}
}
