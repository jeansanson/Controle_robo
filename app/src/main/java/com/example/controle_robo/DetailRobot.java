package com.example.controle_robo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;



public class DetailRobot extends AppCompatActivity {

    private TextView robotId;
    private TextView robotName;
    private TextView robotCategory;
    private static final String TAG = "MostrarPokemonActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_robot);
        Log.d(TAG, "onCreate: ");

        robotId = findViewById(R.id.robotId);
        robotName = findViewById(R.id.robotName);
        robotCategory = findViewById(R.id.robotCategory);

        Intent intent = getIntent();

        robotId.setText(intent.getSerializableExtra("id").toString());
        robotName.setText(intent.getSerializableExtra("name").toString());
        robotCategory.setText(intent.getSerializableExtra("category").toString());
    }

}
