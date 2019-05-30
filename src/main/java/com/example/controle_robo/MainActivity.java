package com.example.controle_robo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView robos = findViewById(R.id.listRobos);

        DAL dal = new DAL(this);

        //String[] fields = new String[] {CreateDatabase.NOME, CreateDatabase.MORTALIDADE, CreateDatabase.IDADE};
        //int[] ids = {R.id.nomeIn, R.id.mortalidade, R.id.idade};

        //Log.d(TAG, "onCreate: " + cursor.getCount());
        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this,
        //        R.layout.paciente_layout, cursor, fields, ids, 0);

        //pacientes.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });

    }
}
