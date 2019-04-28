package ie.kf.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import ie.kf.R;
import ie.kf.fragments.MapsFragment;
import ie.kf.main.FlapperSnapperApp;

public class Map extends Base {

    public static FlapperSnapperApp app = FlapperSnapperApp.getInstance();
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        ft = getSupportFragmentManager().beginTransaction();

        MapsFragment fragment = MapsFragment.newInstance();
        ft.replace(R.id.mapFrame, fragment);
        ft.commit();
    }
}
