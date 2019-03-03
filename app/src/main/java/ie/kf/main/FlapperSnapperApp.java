package ie.kf.main;

import android.app.Application;
import android.util.Log;

import ie.kf.db.DBManager;

public class FlapperSnapperApp extends Application {

    public DBManager dbManager;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("flappersnapper", "FlapperSnapper App Started");
        dbManager = new DBManager(this);
        dbManager.open();
        Log.v("flappersnapper", "Realm Database Created & Opened");
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        dbManager.close();
    }

}
