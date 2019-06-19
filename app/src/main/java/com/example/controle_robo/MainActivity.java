package com.example.controle_robo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.controle_robo.obj.Categoria;
import com.example.controle_robo.obj.Localizacao;
import com.example.controle_robo.obj.Relacionamento;
import com.example.controle_robo.obj.Responsavel;
import com.example.controle_robo.obj.Robo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String REL = "relacionamento";
    private static final String RELLIST = "relacionamentoLista";
    private static final String SHARED_PREFS = "sharedPrefs";
    private List<Robo> robotList;
    private List<Categoria> categoryList;
    private List<Responsavel> responsibleList;
    private List<Localizacao> localizationList;
    public static List<Relacionamento> relationList;
    private EditText search;
    private ListView robotListView;
    private Button btUpdate;
    private Button btUpdateList;
    private Button btSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        robotListView = findViewById(R.id.listRobos);
        btUpdate = findViewById(R.id.btUpdate);
        btUpdateList = findViewById(R.id.btUpdateList);
        search = findViewById(R.id.searchText);
        btSearchList = findViewById(R.id.btSearch);
        robotList = new ArrayList<>();
        categoryList = new ArrayList<>();
        responsibleList = new ArrayList<>();
        localizationList = new ArrayList<>();
        relationList = new ArrayList<>();

        btAtualizarLista();
        btAtualizar();
        btProcurar();
        robotListViewOnItemClickListener();

    }

    private void robotListViewOnItemClickListener() {
        robotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailRobot.class);
                intent.putExtra(REL, relationList.get(position));
                //intent.putExtra(RELLIST, (Serializable) relationList);
                startActivity(intent);
            }
        });
    }

    private void btAtualizar() {
        btUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                download();
            }
        });
    }

    private void btAtualizarLista() {
        btUpdateList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showListView();
            }
        });
    }

    private void btProcurar() {
        btSearchList.setOnClickListener(new View.OnClickListener() {
            List<Relacionamento> tempRelList = new ArrayList<>();
            public void onClick(View v) {
                String aux = search.getText().toString();
                int tol=0;
                tempRelList.clear();

                Map<Integer,String> statusMap;
                MapaStatusRobo map = new MapaStatusRobo();
                statusMap = new HashMap<>();
                statusMap = map.loadStatus();

                for(int i=0;i<relationList.size();i++){
                    if (aux.compareTo(relationList.get(i).getRobName())==tol||
                            aux.compareTo(relationList.get(i).getRobCategory())>=-5&&
                            aux.compareTo(relationList.get(i).getRobCategory())<=0||
                            aux.compareTo(relationList.get(i).getResName())==tol
                    ){
                        Relacionamento r = new Relacionamento();
                        r=relationList.get(i);
                        tempRelList.add(r);
                    }
                }
                showListViewSearch(tempRelList);
            }
        });
    }

    private void showListView() {
        RoboViewAdapter roboListAdapter = new RoboViewAdapter(MainActivity.this,
                R.layout.list_robots, relationList);
        roboListAdapter.clear();
        robotListView.setAdapter(roboListAdapter);
    }

    private void showListViewSearch(List tempRelList) {
        RoboViewAdapter roboListAdapter = new RoboViewAdapter(MainActivity.this,
                R.layout.list_robots, tempRelList);
        roboListAdapter.clear();
        robotListView.setAdapter(roboListAdapter);
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
            jsonReader.jsonToLists(json, robotList, categoryList, responsibleList, localizationList, relationList);
            showListView();
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
