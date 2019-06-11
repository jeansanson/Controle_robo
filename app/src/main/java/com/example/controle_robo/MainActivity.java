package com.example.controle_robo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.controle_robo.obj.Categoria;
import com.example.controle_robo.obj.Localizacao;
import com.example.controle_robo.obj.Responsavel;
import com.example.controle_robo.obj.Robo;

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

        download(); //operação assíncrona
        showListView();
        robotListViewOnItemClickListener();

    }

    private void robotListViewOnItemClickListener() {
        robotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Seu codigo aqui
                Intent intent = new Intent(MainActivity.this, DetailRobot.class);
                intent.putExtra("id", robotList.get(position).getId());
                intent.putExtra("name", robotList.get(position).getName());
                intent.putExtra("category", robotList.get(position).getCategory());
                startActivity(intent);
            }
        });
    }

    private void showListView() {
        RoboViewAdapter pokeListAdapter = new RoboViewAdapter(MainActivity.this,
                R.layout.list_robots, robotList);
        robotListView.setAdapter(pokeListAdapter);
    }

    private void download() {
        DownloadDeDados downloadDeDados = new DownloadDeDados();

        try {
            downloadDeDados.execute("http://www.mocky.io/v2/5cf709a33200008a288cd582").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class DownloadDeDados extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            JSONreader jsonReader = new JSONreader();
            String json = s;
            jsonReader.jsonToLists(json, robotList, categoryList, responsibleList, localizationList);
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
}
