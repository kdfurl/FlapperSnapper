package ie.kf.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
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

public class Edit extends Base implements AdapterView.OnItemSelectedListener {

    private String species, sex, age;
    private Bitmap bitmap;
    private ImageView birdPic;
    private EditText speciesET;
    private Spinner spinner;
    private RadioButton adultRB, juvenileRB;
    private Intent intent;
    private Bird bird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        birdPic = findViewById(R.id.editBirdPicView);
        speciesET = findViewById(R.id.editSpeciesET);
        spinner = findViewById(R.id.editSexSpinner);
        adultRB = findViewById(R.id.editAgeAdultRB);
        juvenileRB = findViewById(R.id.editAgeJuvenileRB);

        intent = getIntent();
        String birdId = intent.getStringExtra("birdId");

        bird = app.dbManager.get(birdId);

        species = bird.species;
        sex = bird.sex;
        age = bird.age;

        Log.v(app.TAG, "Edit: " + bird);

        bitmap = BitmapFactory.decodeByteArray(bird.byteArray, 0, bird.byteArray.length);
        birdPic.setImageBitmap(bitmap);

        speciesET.setText(species);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(sex));

        if (age.equals("Adult")) {
            adultRB.setChecked(true);
        } else if (age.equals("Juvenile")) {
            juvenileRB.setChecked(true);
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        sex = spinner.getSelectedItem().toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.editAgeAdultRB:
                if (checked)
                    age = "Adult";
                break;
            case R.id.editAgeJuvenileRB:
                if (checked)
                    age = "Juvenile";
                break;
        }
    }

    public void updateBird(View v) {

        species = speciesET.getText().toString();

        if ((species.length() > 0) && (age.length() > 0) && (sex.length() > 0)) {
            app.dbManager.update(bird, species, sex, age);
            Log.v(app.TAG, "Update: " + bird);
            startActivity(new Intent(this, Home.class));
        } else {
            Toast.makeText(this, "You must enter something for Species, Sex and Age", Toast.LENGTH_SHORT).show();
        }
    }

}
