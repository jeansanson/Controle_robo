package com.example.controle_robo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<Robo> robotList;
    private List<Categoria> categoryList;
    private List<Responsavel> responsibleList;
    private List<Localizacao> localizationList;
    private ListView robotListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        robotListView = findViewById(R.id.listRobos);

        robotList = new ArrayList<>();
        categoryList = new ArrayList<>();
        responsibleList = new ArrayList<>();
        localizationList = new ArrayList<>();

        DownloadDeDados downloadDeDados = new DownloadDeDados();

        try {
            downloadDeDados.execute("http://www.mocky.io/v2/5cf709a33200008a288cd582").get();
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
            String json = s;
            jsonReader.jsonToLists(json, robotList, categoryList, responsibleList, localizationList);
            teste();
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: " + strings[0]);
            JSONreader jsonReader = new JSONreader();
            String json = jsonReader.downloadJson(strings[0]);
            if (json == null) {
                Log.e(TAG, "doInBackground: Erro baixando JSON");
            }

            return json;
        }
    }

    private void teste() {
        RoboViewAdapter pokeListAdapter = new RoboViewAdapter(MainActivity.this,
                R.layout.list_robots, robotList);
        robotListView.setAdapter(pokeListAdapter);
    }
}
