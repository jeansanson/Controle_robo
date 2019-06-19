package com.example.controle_robo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.controle_robo.obj.Relacionamento;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DetailRobot extends AppCompatActivity {


    private TextView robotId;
    private TextView robotName;
    private TextView robotCategory;
    private TextView robotStatus;
    private TextView robotResponsible;
    private TextView robotLocalization;
    private TextView robotDescription;
    private Button btUpdateInfo;
    private Map<Integer,String> statusMap;
    private static final String TAG = "Detail Robot";
    private static final String REL = "relacionamento";
    private static final String RELLIST = "relacionamentoLista";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_robot);
        Log.d(TAG, "onCreate: ");

        MapaStatusRobo map = new MapaStatusRobo();

        statusMap = new HashMap<>();
        statusMap = map.loadStatus();

        robotId = findViewById(R.id.robotId);
        robotName = findViewById(R.id.robotName);
        robotCategory = findViewById(R.id.robotCategory);
        robotStatus = findViewById(R.id.robotStatus);
        robotResponsible = findViewById(R.id.robotResponsible);
        robotLocalization = findViewById(R.id.robotLocalization);
        robotDescription = findViewById(R.id.robotDescription);
        btUpdateInfo = findViewById(R.id.btUpdateInfo);
        Intent intent = getIntent();

        Relacionamento r = (Relacionamento) intent.getSerializableExtra(REL);
        List<Relacionamento> rl = (List) intent.getSerializableExtra(RELLIST);

        robotId.setText(String.valueOf(r.getId()));
        robotName.setText(r.getRobName());
        robotCategory.setText(r.getRobCategory());
        robotStatus.setText("Status: "+statusMap.get(r.getStatus()));
        robotResponsible.setText("Responsável: "+r.getResName());
        robotLocalization.setText("Local: "+r.getLocCity());
        robotDescription.setText(r.getDescription());

        updateRobotInfo(r,rl);
    }

    private void updateRobotInfo(final Relacionamento r, final List rl) {
        btUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailRobot.this, UpdateRobot.class);
                intent.putExtra(REL, r);
                //intent.putExtra(RELLIST, (Serializable) rl);
                startActivity(intent);
                finish();
            }
        });

    }

}
