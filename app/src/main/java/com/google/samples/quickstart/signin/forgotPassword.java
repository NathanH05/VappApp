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

public class forgotPassword extends AppCompatActivity {
    @ColorInt
    final int GREEN1 = 0xFF91a832;

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
}
