package ie.kf.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import ie.kf.R;

public class Add extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String filePath, species, sex, age;
    private EditText speciesET;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        speciesET = findViewById(R.id.addSpeciesET);
        spinner = findViewById(R.id.addSexSpinner);

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

}
