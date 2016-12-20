package com.google.samples.quickstart.signin;

/**
 * Created by nathanhampshire on 4/11/16.
 */

public class iconChecker {
    public static String title;
    public static int category;


    public static void setter(String titleName, int categoryName){
        title = titleName;
        category = categoryName;
    }

    public String titleGetter(){
        System.out.println(title);
        resultCard[0][5] = "d";
        resultCard[0][4] = "d";

        return title;
    }
    public int categoryGetter(){
        return category;
    }

    public static final String[][] resultCard =  new String[][]{
            {title}

    };
}
