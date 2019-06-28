package com.example.controle_robo.SplashScreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.controle_robo.R;
import com.example.controle_robo.login.Login;

public class SplashScreen extends AppCompatActivity {

    private ImageView logoSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logoSplashScreen = findViewById(R.id.logoSplashScreen);

        logoSplashScreen.setImageResource(R.drawable.logo);


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                telaLogin();
            }
        }, 2000);


    }

    private void telaLogin() {
        Intent intent = new Intent(SplashScreen.this, Login.class);
        startActivity(intent);
    }
}
