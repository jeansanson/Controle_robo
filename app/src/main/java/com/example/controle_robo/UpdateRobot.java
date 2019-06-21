package com.example.controle_robo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;

import com.example.controle_robo.obj.Relacionamento;

import java.util.HashMap;
import java.util.Map;

import static com.example.controle_robo.MainActivity.relationList;


public class UpdateRobot extends AppCompatActivity {


    private EditText robotName;
    private EditText robotDescription;
    private Spinner categorySpinner;
    private Spinner statusSpinner;
    private Spinner responsibleSpinner;
    private Spinner localizationSpinner;
    private Button btSaveInfo;
    private Button btCategory;

    private Map<Integer,String> statusMap;

    private static final String REL = "relacionamento";
    private static final String TAG = "Detail Robot";
    private String responsible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_robot);
        Log.d(TAG, "onCreate: ");

        MapaStatusRobo map = new MapaStatusRobo();

        statusMap = new HashMap<>();
        statusMap = map.loadStatus();

        robotDescription = findViewById(R.id.descriptionRobo);
        robotName = findViewById(R.id.robotName);
        categorySpinner = findViewById(R.id.categorySpinner);
        statusSpinner = findViewById(R.id.statusSpinner);
        responsibleSpinner = findViewById(R.id.responsibleSpinner);
        localizationSpinner = findViewById(R.id.localizationSpinner);
        btSaveInfo = findViewById(R.id.btSaveInfo);
        btCategory = findViewById(R.id.btCategory);

        Intent intent = getIntent();
        Relacionamento r = (Relacionamento) intent.getSerializableExtra(REL);


        robotName.setText(r.getRobName());
        robotDescription.setText(r.getDescription());

        spinnerArrayAdapters();
        SaveRobotInfo(r);
        btCategoryClick(r);

    }


    private void SaveRobotInfo(final Relacionamento r) {
        btSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo(r);
                Log.d(TAG, "onClick: robo salvo");
                finish();
            }
        });
    }

    private void btCategoryClick(final Relacionamento r) {
        btCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(UpdateRobot.this,
                        R.array.category_array, android.R.layout.simple_spinner_item);

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateRobot.this);
                builder.setMessage("Categorias");


                builder.setItems(R.array.category_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void spinnerArrayAdapters(){
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> responsibleAdapter = ArrayAdapter.createFromResource(this,
                R.array.responsible_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> localizationAdapter = ArrayAdapter.createFromResource(this,
                R.array.localization_array, android.R.layout.simple_spinner_item);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        responsibleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localizationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorySpinner.setAdapter(categoryAdapter);
        statusSpinner.setAdapter(statusAdapter);
        responsibleSpinner.setAdapter(responsibleAdapter);
        localizationSpinner.setAdapter(localizationAdapter);

        categorySpinner.setOnItemSelectedListener(new SpinnerActivity());


    }

    private void saveInfo(Relacionamento r){
        int id=r.getId();
        for(int i=0;i<relationList.size();i++){
            if(id==relationList.get(i).getId()){
                relationList.get(i).setRobName(robotName.getText().toString().trim());
                relationList.get(i).setDescription(robotDescription.getText().toString().trim());
            }
        }
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            //owner = (String) parent.getItemAtPosition(pos);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

}
