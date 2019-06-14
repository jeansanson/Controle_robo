package com.example.controle_robo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.controle_robo.obj.Relacionamento;

import java.util.HashMap;
import java.util.Map;

import static com.example.controle_robo.MainActivity.REL;

public class UpdateRobot extends AppCompatActivity {


    private EditText robotName;
    private Spinner statusSpinner;
    private Button btSaveInfo;

    private Map<Integer,String> statusMap;




    private static final String TAG = "Detail Robot";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_robot);
        Log.d(TAG, "onCreate: ");

        MapaStatusRobo map = new MapaStatusRobo();

        statusMap = new HashMap<>();
        statusMap = map.loadStatus();

        robotName = findViewById(R.id.robotName);
        statusSpinner = findViewById(R.id.statusSpinner);
        btSaveInfo = findViewById(R.id.btSaveInfo);

        Intent intent = getIntent();

        Relacionamento r = (Relacionamento) intent.getSerializableExtra(REL);


        robotName.setText(r.getRobName());
        SaveRobotInfo(r);
    }

    private void SaveRobotInfo(final Relacionamento r) {
        btSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo(r);
                Intent intent = new Intent(UpdateRobot.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveInfo(Relacionamento r){
        r.setRobName(robotName.toString());
    }
}
