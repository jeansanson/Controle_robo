package com.example.controle_robo;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<Robo> robotList;
    private List<Categoria> categoryList;
    private List<Responsavel> responsibleList;
    private List<Localizacao> localizationList;
    private ListView robos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        robos = findViewById(R.id.listRobos);
        Button btnAdd = findViewById(R.id.btnShow);

        robotList = new ArrayList<>();
        categoryList = new ArrayList<>();
        responsibleList = new ArrayList<>();
        localizationList = new ArrayList<>();

        DownloadDeDados downloadDeDados = new DownloadDeDados();
        String aux = "";
        try {
            aux = downloadDeDados.execute("http://www.mocky.io/v2/5cf709a33200008a288cd582").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Log.d(TAG, "onCreate: " + cursor.getCount());
        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this,
        //        R.layout.paciente_layout, cursor, fields, ids, 0);

        //pacientes.setAdapter(adapter);
    }


    private class DownloadDeDados extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            JSONreader jsonReader = new JSONreader();
            String json=s;
            jsonReader.jsonToLists(json,robotList,categoryList,responsibleList,localizationList);
            teste();
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: "+strings[0]);
            JSONreader jsonReader = new JSONreader();
            String json = jsonReader.downloadJson(strings[0]);
            if (json == null){
                Log.e(TAG, "doInBackground: Erro baixando JSON");
            }

            return json;
        }
    }

    private void teste(){

        List<String> nomeRobo = new ArrayList<>();

        for(int i=1; i<robotList.size(); i++){
            nomeRobo.add(robotList.get(i).getNome());
        }
        ArrayAdapter<String> roboAdapter =
                new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, nomeRobo);
        robos.setAdapter(roboAdapter);
    }



}
