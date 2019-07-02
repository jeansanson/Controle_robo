package com.example.controle_robo.login;

import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.controle_robo.JSONreader;
import com.example.controle_robo.MainActivity;
import com.example.controle_robo.R;
import com.example.controle_robo.SplashScreen.SplashScreen;

public class TelaCarregamento extends AppCompatActivity {

    private ImageView gifRobo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_carregamento);

        gifRobo = findViewById(R.id.gifRobo);

       /* Glide.with(this)
                .load("https://media0.giphy.com/media/MkcgltZ9e1UI/giphy.gif")
                .into(gifRobo);*/

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                telaLogin();
            }
        }, 3000);

    }

    private void telaLogin() {
        Intent intent = new Intent(TelaCarregamento.this, MainActivity.class);
        startActivity(intent);


        finish();
    }

    @Override  //Bloqueia o Botão de voltar do celular
    public void onBackPressed() {

    }
}


