package com.google.samples.quickstart.signin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;

import com.example.myfirstapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.regex.Pattern;

import static com.facebook.FacebookSdk.getApplicationContext;

public class CustomClusterRenderer extends DefaultClusterRenderer<AppClusterItem> {
    private Context mContext;
    public String category ="";

    private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
    public CustomClusterRenderer(Context context, GoogleMap map,
                                 ClusterManager<AppClusterItem> clusterManager) {
        super(context, map, clusterManager);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );




    }


    @Override
    protected void onBeforeClusterItemRendered(AppClusterItem item, MarkerOptions markerOptions) {

        int width = 140;
        int height = 140;
//        setContentView(R.layout.activity_maps);
        String[] x = item.Opty.split(Pattern.quote("|"));
        System.out.println("OPTY ------------------");
        System.out.println(item.Opty.split(Pattern.quote("|"))[8]);
        System.out.println(item.Opty.split(Pattern.quote("|"))[1]);
        System.out.println(item.Opty.split(Pattern.quote("|"))[2]);



//
//        ScrollView scroll = (ScrollView) findViewById(R.id.scrolly);
//
//        scroll.fullScroll(View.FOCUS_DOWN);
//
//        TextView tv = (TextView) findViewById(R.id.retailerName);
//        tv.setText(item.Opty.split(Pattern.quote("|"))[9]);
//        TextView tvr = (TextView) findViewById(R.id.retailerNamesy);
//        tvr.setText(item.Opty.split(Pattern.quote("|"))[0]);
//
//        final FrameLayout lin = (FrameLayout) findViewById(R.id.content2);
//        FrameLayout lin2 = (FrameLayout) findViewById(R.id.content2z);
//        FrameLayout lin3 = (FrameLayout) findViewById(R.id.content2d);

        System.out.println("ON BEFORE CLUSTER");
        System.out.println(item.Opty);
        System.out.println(markerOptions.getSnippet());


        BitmapDrawable bitmapdraw9 = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.accommodation);
        Bitmap j = bitmapdraw9.getBitmap();
        Bitmap smallMarker9 = Bitmap.createScaledBitmap(j, width, height, false);


        BitmapDrawable bitmapdraw = (BitmapDrawable)mContext.getResources().getDrawable(R.drawable.shopping);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        Bitmap markerIcon = smallMarker;


        BitmapDrawable bitmapdraw3 = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.fooddining);
        Bitmap d = bitmapdraw3.getBitmap();
        Bitmap smallMarker3 = Bitmap.createScaledBitmap(d, width, height, false);


        BitmapDrawable bitmapdraw5 = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.movies);
        Bitmap e = bitmapdraw5.getBitmap();
        Bitmap smallMarker5 = Bitmap.createScaledBitmap(e, width, height, false);

        // Load all our marker image assets
        BitmapDrawable bitmapdraw7 = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.drinks);
        Bitmap g = bitmapdraw7.getBitmap();
        Bitmap smallMarker7 = Bitmap.createScaledBitmap(g, width, height, false);

        BitmapDrawable bitmapdraw8 = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.activities);
        Bitmap h = bitmapdraw8.getBitmap();
        Bitmap smallMarker8 = Bitmap.createScaledBitmap(h, width, height, false);


            if ("shopping".equals(x[8])) {
                markerIcon = smallMarker;
                category = "shopping";
                System.out.println("True");
            } else if ("attractions".equals(x[8])) {
                markerIcon = smallMarker8;
                category = "attractions";

                System.out.println("True");
            } else if ("accommodation".equals(x[8])) {
                markerIcon = smallMarker9;
                category = "accommodation";

                System.out.println("True");
            }  else if ("food/dining".equals(x[8])) {
                markerIcon = smallMarker3;
                category = "food/dining";

                System.out.println("True");
            } else if ("drinks".equals(x[8])) {
                markerIcon = smallMarker7;
                category = "drinks";
                System.out.println("True");
            } else if ("movies".equals(x[8])) {
                markerIcon = smallMarker5;
                category = "movies";
                System.out.println("True");
            }




        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(markerIcon));
//        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(markerDescriptor));



        // Load all our marker image assets

//        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(markerIcon));

  }

    @Override
    protected void onClusterItemRendered(AppClusterItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
    }
    @Override
    protected void onBeforeClusterRendered(Cluster<AppClusterItem> cluster, MarkerOptions markerOptions) {

//      super.onBeforeClusterRendered(cluster,markerOptions);
        final Drawable clusterIcon = mContext.getDrawable(R.drawable.nonum);
        Bitmap b = ((BitmapDrawable)clusterIcon).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 200, 200, false);
        BitmapDrawable clusterIcon2 = new BitmapDrawable(mContext.getResources(), bitmapResized);
//        mClusterIconGenerator.setBackground(clusterIcon);
//        final Drawable clusterIcon = context.getResources().getDrawable(R.drawable.drinks);
//        clusterIcon.setColorFilter(mContext.getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
//
        mClusterIconGenerator.setBackground(clusterIcon2);

        mClusterIconGenerator.setTextAppearance(mContext,R.style.iconGen);
//        mClusterIconGenerator.setT
//
        //modify padding for one or two digit numbers
        if (cluster.getSize() < 10) {
            mClusterIconGenerator.setContentPadding(82,50, 0, 0);
        }
        else {
            mClusterIconGenerator.setContentPadding(60,50, 0, 0);
        }

        Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));




        }

}