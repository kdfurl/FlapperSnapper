package ie.kf.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;

import ie.kf.R;
import ie.kf.models.Bird;

public class Add extends Base implements AdapterView.OnItemSelectedListener {

    private String userId, species, sex, age = "";
    private Bitmap bitmap;
    private ImageView birdPic;
    private EditText speciesET;
    private Spinner spinner;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        userId = app.googleId;

        birdPic = findViewById(R.id.addBirdPicView);
        speciesET = findViewById(R.id.addSpeciesET);
        spinner = findViewById(R.id.addSexSpinner);

        intent = getIntent();
        bitmap = intent.getParcelableExtra("imageBitmap");

        birdPic.setImageBitmap(bitmap);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        sex = spinner.getSelectedItem().toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.addAgeAdultRB:
                if (checked)
                    age = "Adult";
                    break;
            case R.id.addAgeJuvenileRB:
                if (checked)
                    age = "Juvenile";
                    break;
        }
    }

    public void addBird(View v) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        species = speciesET.getText().toString();

        if ((species.length() > 0) && (age.length() > 0) && (sex.length() > 0)) {
            Bird b = new Bird(userId, byteArray, species, sex, age, getAddressFromLocation(app.mCurrentLocation),
                    app.mCurrentLocation.getLatitude(),app.mCurrentLocation.getLongitude());
            Log.v(app.TAG, "Add: " + b);
            app.dbManager.add(b);
            startActivity(new Intent(this, Home.class));
        } else {
            Toast.makeText(this, "You must enter something for Species, Sex and Age", Toast.LENGTH_SHORT).show();
        }
    }

    private String getAddressFromLocation( Location location ) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        String strAddress = "";
        Address address;
        try {
            address = geocoder
                    .getFromLocation( location.getLatitude(), location.getLongitude(), 1 )
                    .get( 0 );
            strAddress = address.getAddressLine(0) +
                    " " + address.getAddressLine(1) +
                    " " + address.getAddressLine(2);
        }
        catch (IOException e ) {
        }

        return strAddress;
    }

}
