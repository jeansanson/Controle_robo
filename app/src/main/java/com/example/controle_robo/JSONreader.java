package com.example.controle_robo;

import android.util.Log;
import android.widget.Toast;

import com.example.controle_robo.obj.Categoria;
import com.example.controle_robo.obj.Relacionamento;
import com.example.controle_robo.obj.Robo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class JSONreader {

    public void jsonToLists (String jsonString, List robotList, List categoryList, List responsibleList, List localizationList, List relationList) {
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray robos = json.getJSONArray("robos");
            JSONArray categorias = json.getJSONArray("categorias");
            JSONArray responsaveis = json.getJSONArray("responsaveis");
            JSONArray localizacoes = json.getJSONArray("localizacoes");
            String TAG = "LeFaturasDeJSONString";

            for (int i = 0; i < robos.length(); i++) {
                JSONObject robo = robos.getJSONObject(i);
                Robo r = new Robo(
                        robo.getInt("id"),
                        robo.getString("nome"),
                        robo.getString("categoria")
                );
                robotList.add(r);
                Log.d(TAG, "leFaturasDeJSONString: Robo inserido em database id:"+r.getId());
            }

            for (int i = 0; i < categorias.length(); i++) {
                JSONObject categoria = categorias.getJSONObject(i);
                Categoria c = new Categoria(
                        categoria.getInt("id"),
                        categoria.getString("nome")
                );
                categoryList.add(c);
                Log.d(TAG, "leFaturasDeJSONString: Categoria inserida em database id:"+c.getId());
            }

            for (int i = 0; i < responsaveis.length(); i++) {
                JSONObject responsavel = responsaveis.getJSONObject(i);
                Categoria r = new Categoria(
                        responsavel.getInt("id"),
                        responsavel.getString("nome")
                        );
                responsibleList.add(r);
                Log.d(TAG, "leFaturasDeJSONString: Responsável inserido em database id:"+r.getId());
            }

            for (int i = 0; i < localizacoes.length(); i++) {
                JSONObject localizacao = localizacoes.getJSONObject(i);
                Categoria l = new Categoria(
                        localizacao.getInt("id"),
                        localizacao.getString("cidade")
                );
                localizationList.add(l);
                Log.d(TAG, "leFaturasDeJSONString: Localização inserida em database id:"+l.getId());
            }
            for (int i = 0; i < robos.length(); i++) {
                JSONObject robo = robos.getJSONObject(i);
                Relacionamento r = new Relacionamento(
                        robo.getInt("id"),
                        robo.getString("nome"),
                        robo.getString("categoria")
                );
                relationList.add(r);
                Log.d(TAG, "leFaturasDeJSONString: Robo inserido em database id:"+r.getId());
            }


        } catch (JSONException e) {
            System.err.println("Erro fazendo parse de String JSON: " + e.getMessage());
        }
    }

    public String downloadJson(String urlString){
        String TAG = "DownloadJSON";
        StringBuilder jsonString = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int resposta  = connection.getResponseCode();
            Log.e(TAG, "DownloadJSON: Código de resposta: "+resposta );

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            int charsLidos;
            char[] InputBuffer = new char[500];
            while(true){
                charsLidos = reader.read(InputBuffer);
                if(charsLidos<0){
                    break;
                }
                if(charsLidos>0){
                    jsonString.append(String.copyValueOf(InputBuffer, 0, charsLidos));
                }
            }
            reader.close();
            return jsonString.toString();
        }
        catch (MalformedURLException e){
            Log.e(TAG, "DownloadJSON: URL é invalido" + e.getMessage());

        } catch (IOException e) {
            Log.e(TAG, "DownloadJSON: Ocorreu um erro de IO ao baixar dados: "+e.getMessage() );

        }
        return null;
    }

}
