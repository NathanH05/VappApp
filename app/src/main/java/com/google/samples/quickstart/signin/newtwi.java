package com.google.samples.quickstart.signin;

import android.content.Context;
import android.util.AttributeSet;

import com.example.myfirstapp.R;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by nathanhampshire on 11/02/17.
 */

public class newtwi extends TwitterLoginButton {
    public newtwi(Context context) {
        super(context);
        init();
    }

    public newtwi(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public newtwi(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (isInEditMode()) {
            return;
        }
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, (getResources().getDrawable(R.drawable
                .twitter)));
        setTextSize(0);
        setPadding(0, 0, 0, 200);
//        setTypeface(App.getInstance().getTypeface());
        setBackgroundResource(R.drawable.twitter);

    }
}
