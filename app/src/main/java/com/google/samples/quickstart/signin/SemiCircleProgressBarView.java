package com.google.samples.quickstart.signin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.myfirstapp.R;

/**
 * Created by nathanhampshire on 15/12/16.
 */


public class SemiCircleProgressBarView extends View {

    private Path mClippingPath;
    private Context mContext;
    private Bitmap mBitmap;
    private float mPivotX;
    private float mPivotY;
    int g;

    public SemiCircleProgressBarView(Context context,int g) {
        super(context);
        mContext = context;
        this.g = g;
        initilizeImage();
    }

    public SemiCircleProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        initilizeImage();
    }

    private void initilizeImage() {
        mClippingPath = new Path();

        //Top left coordinates of image. Give appropriate values depending on the position you wnat image to be placed
        mPivotX = getScreenGridUnit();
        mPivotY = 0;

        //Adjust the image size to support different screen sizes
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.greencircl);

        int imageWidth = (int) (getScreenGridUnit() * 30 -2442);
        int imageHeight = (int) (getScreenGridUnit() * 30-2400);
        mBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false);
    }

    public void setClipping(float progress) {

        //Convert the progress in range of 0 to 100 to angle in range of 0 180. Easy math.
        float angle = (progress * 180) / 100;
        mClippingPath.reset();
        //Define a rectangle containing the image
        RectF oval = new RectF(mPivotX, mPivotY, mPivotX + mBitmap.getWidth(), mPivotY + mBitmap.getHeight());
        //Move the current position to center of rect
        mClippingPath.moveTo(oval.centerX(), oval.centerY());
        //Draw an arc from center to given angle
        mClippingPath.addArc(oval, 180, angle);
        //Draw a line from end of arc to center
        mClippingPath.lineTo(oval.centerX(), oval.centerY());
        //Redraw the canvas
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Clip the canvas
        canvas.clipPath(mClippingPath);
        canvas.drawBitmap(mBitmap, mPivotX, mPivotY, null);

    }

    private float getScreenGridUnit() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels / 32;
    }

     void setBitMap(String barColour) {
        Canvas canvas = new Canvas();
        //Clip the canvas
         if(barColour.equals("grey")){
             //Adjust the image size to support different screen sizes
             Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.greycircl);

             int imageWidth = (int) (getScreenGridUnit() * 30 -2442);
             int imageHeight = (int) (getScreenGridUnit() * 30-2400);
             mBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false);

         }
         else if(barColour.equals("orange")){
             //Adjust the image size to support different screen sizes
             Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.orangecircl);
             int imageWidth = (int) (getScreenGridUnit() * 30 -2442);
             int imageHeight = (int) (getScreenGridUnit() * 30-2400);
             mBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false);

         }
         else if(barColour.equals("green")){
             //Adjust the image size to support different screen sizes
             Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.greencircl);

             int imageWidth = (int) (getScreenGridUnit() * 30 -2442);
             int imageHeight = (int) (getScreenGridUnit() * 30-2400);
             mBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false);

         }
        canvas.clipPath(mClippingPath);
        canvas.drawBitmap(mBitmap, mPivotX, mPivotY, null);
    }

}