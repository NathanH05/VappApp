package com.google.samples.quickstart.signin;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Display;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Sample implementation of a locationProvider, feel free to polish this very basic approach (compare http://goo.gl/pvkXV )
 */
public abstract class LocationProvider extends Context implements ILocationProvider {

	/** location listener called on each location update */
	private final LocationListener locationListener;

	/** system's locationManager allowing access to GPS / Network position */
	public final LocationManager locationManager;

	/** location updates should fire approximately every second */
	private static final int LOCATION_UPDATE_MIN_TIME_GPS = 1000;

	/** location updates should fire, even if last signal is same than current one (0m distance to last location is OK) */
	private static final int LOCATION_UPDATE_DISTANCE_GPS = 0;

	/** location updates should fire approximately every second */
	private static final int LOCATION_UPDATE_MIN_TIME_NW = 1000;

	/** location updates should fire, even if last signal is same than current one (0m distance to last location is OK) */
	private static final int LOCATION_UPDATE_DISTANCE_NW = 0;

	/** to faster access location, even use 10 minute old locations on start-up */
	private static final int LOCATION_OUTDATED_WHEN_OLDER_MS = 1000 * 60 * 10;

	/** is gpsProvider and networkProvider enabled in system settings */
	private boolean gpsProviderEnabled, networkProviderEnabled;

	/** the context in which we're running */
	private final Context context;


	public LocationProvider(final Context context, LocationListener locationListener) {
		super();
		this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		this.locationListener = locationListener;
		this.context = context;
		this.gpsProviderEnabled = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		this.networkProviderEnabled = this.locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	}

	@Override
	public void onPause() {
		if (this.locationListener != null && this.locationManager != null && (this.gpsProviderEnabled || this.networkProviderEnabled)) {
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return;
			}
			this.locationManager.removeUpdates(this.locationListener);
		}
	}

	@Override
	public void onResume() {
		if (this.locationManager != null && this.locationListener != null) {

			// check which providers are available are available
			this.gpsProviderEnabled = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			this.networkProviderEnabled = this.locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			/** is GPS provider enabled? */
			if (this.gpsProviderEnabled) {
				final Location lastKnownGPSLocation = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if (lastKnownGPSLocation != null && lastKnownGPSLocation.getTime() > System.currentTimeMillis() - LOCATION_OUTDATED_WHEN_OLDER_MS) {
					locationListener.onLocationChanged(lastKnownGPSLocation);
				}
				if (locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) {
					this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_MIN_TIME_GPS, LOCATION_UPDATE_DISTANCE_GPS, this.locationListener);
				}
			}

			/** is Network / WiFi positioning provider available? */
			if (this.networkProviderEnabled) {
				final Location lastKnownNWLocation = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				if (lastKnownNWLocation != null && lastKnownNWLocation.getTime() > System.currentTimeMillis() - LOCATION_OUTDATED_WHEN_OLDER_MS) {
					locationListener.onLocationChanged(lastKnownNWLocation);
				}
				if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
					if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
						// TODO: Consider calling
						//    ActivityCompat#requestPermissions
						// here to request the missing permissions, and then overriding
						//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
						//                                          int[] grantResults)
						// to handle the case where the user grants the permission. See the documentation
						// for ActivityCompat#requestPermissions for more details.
						return;
					}
					this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_MIN_TIME_NW, LOCATION_UPDATE_DISTANCE_NW, this.locationListener);
				}
			}

			/** user didn't check a single positioning in the location settings, recommended: handle this event properly in your app, e.g. forward user directly to location-settings, new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS ) */
			if ( !this.gpsProviderEnabled || !this.networkProviderEnabled ) {
				Toast.makeText( this.context, "Please enable GPS and Network positioning in your Settings ", Toast.LENGTH_LONG ).show();
			}
		}
	}

	@Override
	public AssetManager getAssets() {
		return null;
	}

	@Override
	public Resources getResources() {
		return null;
	}

	@Override
	public PackageManager getPackageManager() {
		return null;
	}

	@Override
	public ContentResolver getContentResolver() {
		return null;
	}

	@Override
	public Looper getMainLooper() {
		return null;
	}

	@Override
	public Context getApplicationContext() {
		return null;
	}

	@Override
	public void setTheme(int resid) {

	}

	@Override
	public Resources.Theme getTheme() {
		return null;
	}

	@Override
	public ClassLoader getClassLoader() {
		return null;
	}

	@Override
	public String getPackageName() {
		return null;
	}

	@Override
	public ApplicationInfo getApplicationInfo() {
		return null;
	}

	@Override
	public String getPackageResourcePath() {
		return null;
	}

	@Override
	public String getPackageCodePath() {
		return null;
	}

	@Override
	public SharedPreferences getSharedPreferences(String name, int mode) {
		return null;
	}

	@Override
	public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
		return false;
	}

	@Override
	public boolean deleteSharedPreferences(String name) {
		return false;
	}

	@Override
	public FileInputStream openFileInput(String name) throws FileNotFoundException {
		return null;
	}

	@Override
	public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
		return null;
	}

	@Override
	public boolean deleteFile(String name) {
		return false;
	}

	@Override
	public File getFileStreamPath(String name) {
		return null;
	}

	@Override
	public File getDataDir() {
		return null;
	}

	@Override
	public File getFilesDir() {
		return null;
	}

	@Override
	public File getNoBackupFilesDir() {
		return null;
	}

	@Nullable
	@Override
	public File getExternalFilesDir(String type) {
		return null;
	}

	@Override
	public File[] getExternalFilesDirs(String type) {
		return new File[0];
	}

	@Override
	public File getObbDir() {
		return null;
	}

	@Override
	public File[] getObbDirs() {
		return new File[0];
	}

	@Override
	public File getCacheDir() {
		return null;
	}

	@Override
	public File getCodeCacheDir() {
		return null;
	}

	@Nullable
	@Override
	public File getExternalCacheDir() {
		return null;
	}

	@Override
	public File[] getExternalCacheDirs() {
		return new File[0];
	}

	@Override
	public File[] getExternalMediaDirs() {
		return new File[0];
	}

	@Override
	public String[] fileList() {
		return new String[0];
	}

	@Override
	public File getDir(String name, int mode) {
		return null;
	}

	@Override
	public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
		return null;
	}

	@Override
	public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
		return null;
	}

	@Override
	public boolean moveDatabaseFrom(Context sourceContext, String name) {
		return false;
	}

	@Override
	public boolean deleteDatabase(String name) {
		return false;
	}

	@Override
	public File getDatabasePath(String name) {
		return null;
	}

	@Override
	public String[] databaseList() {
		return new String[0];
	}

	@Override
	public Drawable getWallpaper() {
		return null;
	}

	@Override
	public Drawable peekWallpaper() {
		return null;
	}

	@Override
	public int getWallpaperDesiredMinimumWidth() {
		return 0;
	}

	@Override
	public int getWallpaperDesiredMinimumHeight() {
		return 0;
	}

	@Override
	public void setWallpaper(Bitmap bitmap) throws IOException {

	}

	@Override
	public void setWallpaper(InputStream data) throws IOException {

	}

	@Override
	public void clearWallpaper() throws IOException {

	}

	@Override
	public void startActivity(Intent intent) {

	}

	@Override
	public void startActivity(Intent intent, Bundle options) {

	}

	@Override
	public void startActivities(Intent[] intents) {

	}

	@Override
	public void startActivities(Intent[] intents, Bundle options) {

	}

	@Override
	public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {

	}

	@Override
	public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {

	}

	@Override
	public void sendBroadcast(Intent intent) {

	}

	@Override
	public void sendBroadcast(Intent intent, String receiverPermission) {

	}

	@Override
	public void sendOrderedBroadcast(Intent intent, String receiverPermission) {

	}

	@Override
	public void sendOrderedBroadcast(Intent intent, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {

	}

	@Override
	public void sendBroadcastAsUser(Intent intent, UserHandle user) {

	}

	@Override
	public void sendBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission) {

	}

	@Override
	public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {

	}

	@Override
	public void sendStickyBroadcast(Intent intent) {

	}

	@Override
	public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {

	}

	@Override
	public void removeStickyBroadcast(Intent intent) {

	}

	@Override
	public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {

	}

	@Override
	public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle user, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {

	}

	@Override
	public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {

	}

	@Nullable
	@Override
	public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
		return null;
	}

	@Nullable
	@Override
	public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler) {
		return null;
	}

	@Override
	public void unregisterReceiver(BroadcastReceiver receiver) {

	}

	@Nullable
	@Override
	public ComponentName startService(Intent service) {
		return null;
	}

	@Override
	public boolean stopService(Intent service) {
		return false;
	}

	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		return false;
	}

	@Override
	public void unbindService(ServiceConnection conn) {

	}

	@Override
	public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments) {
		return false;
	}

	@Override
	public Object getSystemService(String name) {
		return null;
	}

	@Override
	public String getSystemServiceName(Class<?> serviceClass) {
		return null;
	}

	@Override
	public int checkPermission(String permission, int pid, int uid) {
		return 0;
	}

	@Override
	public int checkCallingPermission(String permission) {
		return 0;
	}

	@Override
	public int checkCallingOrSelfPermission(String permission) {
		return 0;
	}

	@Override
	public int checkSelfPermission(String permission) {
		return 0;
	}

	@Override
	public void enforcePermission(String permission, int pid, int uid, String message) {

	}

	@Override
	public void enforceCallingPermission(String permission, String message) {

	}

	@Override
	public void enforceCallingOrSelfPermission(String permission, String message) {

	}

	@Override
	public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {

	}

	@Override
	public void revokeUriPermission(Uri uri, int modeFlags) {

	}

	@Override
	public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
		return 0;
	}

	@Override
	public int checkCallingUriPermission(Uri uri, int modeFlags) {
		return 0;
	}

	@Override
	public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
		return 0;
	}

	@Override
	public int checkUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags) {
		return 0;
	}

	@Override
	public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {

	}

	@Override
	public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {

	}

	@Override
	public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {

	}

	@Override
	public void enforceUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags, String message) {

	}

	@Override
	public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
		return null;
	}

	@Override
	public Context createConfigurationContext(Configuration overrideConfiguration) {
		return null;
	}

	@Override
	public Context createDisplayContext(Display display) {
		return null;
	}

	@Override
	public Context createDeviceProtectedStorageContext() {
		return null;
	}

	@Override
	public boolean isDeviceProtectedStorage() {
		return false;
	}
}
