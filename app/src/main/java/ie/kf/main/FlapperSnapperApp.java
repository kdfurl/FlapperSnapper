package ie.kf.main;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import ie.kf.db.DBManager;

public class FlapperSnapperApp extends Application {

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


    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v(TAG, "FlapperSnapper App Started");
        dbManager = new DBManager(this);
        dbManager.open();
        Log.v(TAG, "Realm Database Created & Opened");
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        dbManager.close();
    }

}
