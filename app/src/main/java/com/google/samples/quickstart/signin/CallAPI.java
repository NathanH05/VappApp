package com.google.samples.quickstart.signin;

import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nathanhampshire on 18/11/16.
 */

public class CallAPI extends AsyncTask<String, String, String> {

    public CallAPI() {
        //set context variables if required
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("call api on pre execute");

    }


    @Override
    protected String doInBackground(String... params) {

        System.out.println("call api do in backy execute");
        String urlString = params[0]; // URL to call

        String resultToDisplay = "";
        System.out.println(resultToDisplay);

        InputStream in = null;
        try {

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(urlConnection.getInputStream());


        } catch (Exception e) {

            System.out.println(e.getMessage());

            return e.getMessage();

        }

        try {
            resultToDisplay = IOUtils.toString(in, "UTF-8");
            System.out.println(resultToDisplay);

            //to [convert][1] byte stream to a string
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(resultToDisplay);
        return resultToDisplay;
    }


    @Override
    protected void onPostExecute(String result) {
        //Update the UI
        System.out.println("call api on post execute");

        System.out.println(result);

    }
}