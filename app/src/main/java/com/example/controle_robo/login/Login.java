package com.example.controle_robo.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controle_robo.MainActivity;
import com.example.controle_robo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText barraEmail;
    private EditText barraSenha;
    private Button btnLogin;
    private Button btnNovo;
    private ImageView imvLogo;
    private TextView tvResetSenha;

    private FirebaseAuth firebaseAuth;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inicializando elementos
        barraEmail = findViewById(R.id.barraEmail);
        barraSenha = findViewById(R.id.barraSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnNovo = findViewById(R.id.btnNovo);
        imvLogo = findViewById(R.id.imvLogo);
        tvResetSenha = findViewById(R.id.tvResetSenha);

        //Logo do grupo
        imvLogo.setImageResource(R.drawable.logo);


        //chamando o botão para o cadastro de um novo email
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(intent);
            }
        });
        //Botão para fazer o login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = barraEmail.getText().toString().trim();
                String senha = barraSenha.getText().toString().trim();
                login(email, senha);
            }
        });

        //Text Reset Senha
        tvResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ResetPassword.class);
                startActivity(intent);
            }
        });


        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            String email2 = bundle.getString("email");
            String senha2 = bundle.getString("senha");
            barraEmail.setText(email2.toString());
            barraSenha.setText(senha2.toString());
        }

        //barraEmail.setText(email2.toString());
//        barraSenha.setText(dados2.getString("senha").toString());
    }
    private void login(String email, String senha) {
        firebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){  //Se o login é valido
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this, "Login Invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexao.getFirebaseAuth();
    }
}
