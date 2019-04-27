package ie.kf.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import ie.kf.R;
import ie.kf.main.FlapperSnapperApp;

public class Base extends AppCompatActivity {

    public FlapperSnapperApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (FlapperSnapperApp) getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void menuHome(MenuItem m) {
        startActivity(new Intent(this, Home.class));
    }

    public void menuClear(MenuItem m) {
        app.dbManager.reset();
        startActivity(new Intent(this, Home.class));
    }

    public void menuSignOut(MenuItem m) {

        app.googleApiClient.connect();
        app.googleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {

                if (app.googleApiClient.isConnected()) {
                    Auth.GoogleSignInApi.signOut(app.googleApiClient).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.v(app.TAG, "User logged out");
                                Intent intent = new Intent(Base.this, Login.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }

            @Override
            public void onConnectionSuspended(int i) {
                Toast.makeText(Base.this, "Google API Client Connection Suspended", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
