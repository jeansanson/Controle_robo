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
import android.widget.TextView;

import com.example.controle_robo.dal.DalResponsible;
import com.example.controle_robo.obj.Localizacao;
import com.example.controle_robo.obj.Relacionamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.controle_robo.MainActivity.relationList;


public class UpdateRobot extends AppCompatActivity {


    private TextView robotName;
    private EditText robotDescription;
    private Spinner statusSpinner;
    private Spinner responsibleSpinner;
    private Spinner localizationSpinner;
    private Button btSaveInfo;
    private Button btCategory;

    private Map<Integer,String> statusMap;

    private static final String REL = "relacionamento";
    private static final String TAG = "Detail Robot";
    private static final String LOC = "localization";
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
        statusSpinner = findViewById(R.id.statusSpinner);
        responsibleSpinner = findViewById(R.id.responsibleSpinner);
        localizationSpinner = findViewById(R.id.localizationSpinner);
        btSaveInfo = findViewById(R.id.btSaveInfo);
        btCategory = findViewById(R.id.btCategory);

        Intent intent = getIntent();
        Relacionamento r = (Relacionamento) intent.getSerializableExtra(REL);
        robotName.setText(r.getRobName());
        robotDescription.setText(r.getDescription());

        //loadSpinnerDataResponsible();
        loadSpinnerDataStatus();

        SaveRobotInfo(r);

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


    private void loadSpinnerDataResponsible() {
        DalResponsible db = new DalResponsible(getApplicationContext());
        List<String> labels = db.getAllLabels();


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        responsibleSpinner.setAdapter(dataAdapter);
    }

    private void loadSpinnerDataStatus(){
        List<String> labels = new MapaStatusRobo().getStatusList();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        statusSpinner.setAdapter(dataAdapter);
    }

    private void loadSpinnerDataLocalization() {
        /*Intent intent = getIntent();
        List<Localizacao> localizationList = (List) intent.getSerializableExtra(LOC);

        List<String> labels = new ArrayList<>();
        for (int i=0;i<localizationList.size();i++){
            labels.add(localizationList.get(i).getCity());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        localizationSpinner.setAdapter(dataAdapter);*/
    }

    private void saveInfo(Relacionamento r){
        int id=r.getId();
        for(int i=0;i<relationList.size();i++){
            if(id==relationList.get(i).getId()){
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
