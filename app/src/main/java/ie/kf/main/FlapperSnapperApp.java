package ie.kf.main;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

import ie.kf.models.Bird;

public class FlapperSnapperApp extends Application {

    public ArrayList<Bird> birdlist = new ArrayList<>();

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("flappersnapper", "FlapperSnapper App Started");
    }

}
