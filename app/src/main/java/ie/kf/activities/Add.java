package ie.kf.activities;

import android.content.Intent;
import android.graphics.Bitmap;
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

import ie.kf.R;
import ie.kf.models.Bird;

public class Add extends Base implements AdapterView.OnItemSelectedListener {

    private String species, sex, age = "";
    private Bitmap bitmap;
    private ImageView birdPic;
    private EditText speciesET;
    private Spinner spinner;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

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
        sex = "";
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
            Bird b = new Bird(byteArray, species, sex, age);
            Log.v(app.TAG, "Add: " + b);
            app.dbManager.add(b);
            startActivity(new Intent(this, Home.class));
        } else {
            Toast.makeText(this, "You must enter something for Species, Sex and Age", Toast.LENGTH_SHORT).show();
        }
    }

}
