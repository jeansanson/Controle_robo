package com.example.controle_robo;

import android.util.Log;

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

    public static void jsonToSql(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray robos = json.getJSONArray("pessoas");

            String TAG = "LeFaturasDeJSONString";
            for (int i = 0; i < robos.length(); i++) {
                JSONObject robo = robos.getJSONObject(i);



                Log.d(TAG, "leFaturasDeJSONString: Robo inserido em database");

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
