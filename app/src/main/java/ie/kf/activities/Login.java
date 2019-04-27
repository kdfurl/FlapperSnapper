package ie.kf.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import ie.kf.R;
import ie.kf.main.FlapperSnapperApp;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    public FlapperSnapperApp app;
    private static final int RC_SIGN_IN = 1;

    SignInButton signInButton;
    Button disconnectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        app = (FlapperSnapperApp) getApplication();
        signInButton = findViewById(R.id.sign_in_button);
        disconnectButton = findViewById(R.id.disconnect_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(this);
        disconnectButton.setOnClickListener(this);

        app.gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        app.googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, app.gso)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(app.googleApiClient);
        if (opr.isDone()) {
            Log.d(app.TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    public void onClick(View v) {

        if (v.getId() == R.id.sign_in_button) {
            signIn();
        }
        else if (v.getId() == R.id.disconnect_button) {
            revokeAccess();
        }
    }

    private void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(app.googleApiClient);
        startActivityForResult(intent, RC_SIGN_IN);
    }

    private void revokeAccess() {
        app.dbManager.reset(app.googleId);
        Auth.GoogleSignInApi.revokeAccess(app.googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                startLoginScreen();
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Error signing in to Google " + connectionResult, Toast.LENGTH_LONG).show();
        Log.v(app.TAG, "ConnectionResult: " + connectionResult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(app.TAG, "handleSignInResult: " + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            app.googleId = account.getId();
            app.displayName = account.getDisplayName();
            app.firstName = account.getGivenName();
            app.lastName = account.getFamilyName();
            app.email = account.getEmail();
            if (account.getPhotoUrl() != null) {
                app.photoURL = account.getPhotoUrl();
            }

            Toast.makeText(this, "Signing in " + app.displayName + " with " + app.email, Toast.LENGTH_SHORT).show();
            startHomeScreen();
        } else {
            Toast.makeText(this, "Please sign in", Toast.LENGTH_SHORT).show();
        }
    }

    private void startHomeScreen() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void startLoginScreen() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}
