package ie.kf.main;

import android.app.Application;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import ie.kf.db.DBManager;

public class FlapperSnapperApp extends Application {

    private static FlapperSnapperApp instance;

    public String TAG = "FlapperSnapper";
    public DBManager dbManager;
    public GoogleApiClient googleApiClient;
    public GoogleSignInOptions gso;
    public String googleId;
    public String displayName;
    public String firstName;
    public String lastName;
    public String email;
    public Uri photoURL;
    public Bitmap photo;
    public Location mCurrentLocation;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "FlapperSnapper App Started");
        instance = this;
        dbManager = new DBManager(this);
        dbManager.open();
        Log.v(TAG, "Realm Database Created & Opened");
    }

    public static synchronized FlapperSnapperApp getInstance() {
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        dbManager.close();
    }

}
