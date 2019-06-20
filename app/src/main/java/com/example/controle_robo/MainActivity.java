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

    }

    private void robotListViewOnItemClickListener(final List<Relacionamento> l) {
        robotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailRobot.class);
                intent.putExtra(REL, l.get(position));
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
                robotListViewOnItemClickListener(relationList);
                showListView(relationList);
            }
        });
    }

    private void btProcurar() {
        btSearchList.setOnClickListener(new View.OnClickListener() {
            List<Relacionamento> tempRelList = new ArrayList<>();
            public void onClick(View v) {
                String aux = search.getText().toString();
                tempRelList.clear();

                Map<Integer,String> statusMap;
                MapaStatusRobo map = new MapaStatusRobo();
                statusMap = new HashMap<>();
                statusMap = map.loadStatus();

                if (aux != null) {

                    aux = aux.trim();

                    if (aux.compareToIgnoreCase("bettle") == 0) {
                        aux = "Bettleweight";
                    }
                    if (aux.compareToIgnoreCase("hobby") == 0) {
                        aux = "Hobbyweight";
                    }
                    if (aux.compareToIgnoreCase("fetter") == 0) {
                        aux = "Fetterweight";
                    }
                    if (aux.compareToIgnoreCase("ant") == 0) {
                        aux = "Antweight";
                    }
                    if (aux.compareToIgnoreCase("jaraguá") == 0 || aux.compareToIgnoreCase("jaragua") == 0) {
                        aux = "Jaraguá do Sul";
                    }

                    if (aux.compareToIgnoreCase(("sumo")) == 0) {
                        for (int i = 0; i < relationList.size(); i++) {
                            if (("Sumo 500g".compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0) || ("Sumo 3kg".compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0)) {
                                Relacionamento r = new Relacionamento();
                                r = relationList.get(i);
                                tempRelList.add(r);
                            }
                        }
                    }

                    if (aux.compareToIgnoreCase("seguidor") == 0) {
                        for (int i = 0; i < relationList.size(); i++) {
                            if (("Seguidor de linha Pro".compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0) || ("Seguidor de Linha Junior".compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0)) {
                                Relacionamento r = new Relacionamento();
                                r = relationList.get(i);
                                tempRelList.add(r);
                            }
                        }
                    }

                    if (aux.compareToIgnoreCase("seguidor pro") == 0) {
                        for (int i = 0; i < relationList.size(); i++) {
                            if ("Seguidor de Linha Pro".compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0) {
                                Relacionamento r = new Relacionamento();
                                r = relationList.get(i);
                                tempRelList.add(r);
                            }
                        }
                    }

                    if (aux.compareToIgnoreCase("seguidor junior") == 0 || aux.compareToIgnoreCase("seguidor jr") == 0) {
                        for (int i = 0; i < relationList.size(); i++) {
                            if ("Seguidor de Linha Junior".compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0) {
                                Relacionamento r = new Relacionamento();
                                r = relationList.get(i);
                                tempRelList.add(r);
                            }
                        }
                    }

                    if (aux.compareToIgnoreCase("batalha") == 0) {
                        for (int i = 0; i < relationList.size(); i++) {
                            if (("Antweight".compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0) || ("Bettleweight".compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0) || ("Hobbyweight".compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0) || ("Fetterweight".compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0)) {
                                Relacionamento r = new Relacionamento();
                                r = relationList.get(i);
                                tempRelList.add(r);
                            }
                        }
                    } else {
                        for (int i = 0; i < relationList.size(); i++) {
                            if ((aux.compareToIgnoreCase(relationList.get(i).getRobName()) == 0) || (aux.compareToIgnoreCase(relationList.get(i).getRobCategory()) == 0) || (aux.compareToIgnoreCase(relationList.get(i).getResName()) == 0) || (aux.compareToIgnoreCase(relationList.get(i).getLocCity()) == 0)
                            ) {
                                Relacionamento r = new Relacionamento();
                                r = relationList.get(i);
                                tempRelList.add(r);
                            }
                        }
                    }
                    robotListViewOnItemClickListener(tempRelList);
                    showListView(tempRelList);
                }
            }
        });
    }

    private void showListView(final List<Relacionamento> l) {
        RoboViewAdapter roboListAdapter = new RoboViewAdapter(MainActivity.this,
                R.layout.list_robots, l);
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
            robotListViewOnItemClickListener(relationList);
            showListView(relationList);
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
