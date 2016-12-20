package com.google.samples.quickstart.signin;

/**
 * Created by nathanhampshire on 2/11/16.
 */

public class TestClass {
    public String urlRequest;

    public TestClass(String n) {

        this.urlRequest = n;
        System.out.println(n);
        System.out.println("Test Class just printed");


    }

    public String returnResul(TestClass testClass){
            testClass.urlRequest = urlRequest;
        System.out.println(urlRequest);
        return urlRequest;



    }
}