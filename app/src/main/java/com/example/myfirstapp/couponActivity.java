package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class couponActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);


        Bundle p = getIntent().getExtras();
        try {

            String yourPreviousPzl =p.getString("retailer");
            yourPreviousPzl = yourPreviousPzl.replace("\n"," ");

            String yourPreviousPzl2 =p.getString("offerDesc1");
            TextView retailerName = (TextView) findViewById(R.id.retailerName);
            retailerName.setText(yourPreviousPzl);
            TextView offerDesc1 = (TextView) findViewById(R.id.offerDesc1);
            offerDesc1.setText(yourPreviousPzl2);
        }
        catch (Exception e){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
Intent intent = getIntent();

        if (intent.getExtras() != null){
            TextView retailerName = (TextView) findViewById(R.id.retailerName);
            TextView offerDesc1 = (TextView) findViewById(R.id.offerDesc1);
            String retailerNme = intent.getExtras().getString("retailer");
            String offerDsc1 = intent.getExtras().getString("offerDesc1");
            System.out.println(offerDsc1);
            offerDesc1.setText((intent.getExtras().getString("retailer")));
            retailerName.setText((intent.getExtras().getString("offerDesc1")));

        }
    }


}
