package com.google.samples.quickstart.signin;

import android.content.Context;
import android.view.KeyEvent;

public class BackAwareEditText extends android.widget.SearchView {

    private BackPressedListener mOnImeBack;

    public BackAwareEditText(Context context) {
        super(context);
    }

    /* constructors */

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (mOnImeBack != null) mOnImeBack.onImeBack(this);
        }
        return super.dispatchKeyEvent(event);
    }

    public void setBackPressedListener(BackPressedListener listener) {
        mOnImeBack = listener;
    }

    public interface BackPressedListener {
        void onImeBack(BackAwareEditText editText);
    }
}
