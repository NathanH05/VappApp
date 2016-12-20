package com.google.samples.quickstart.signin;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.SearchView;

/**
 * Created by nathanhampshire on 2/12/16.
 */

public class n extends SearchView {

    public n(Context context) {
        super(context);
    }
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // Do your thing.
            System.out.println("hi");
            return true;  // So it is not propagated.
        }
        return super.dispatchKeyEvent(event);
    }
}
