package com.example.controle_robo.login;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controle_robo.MainActivity;
import com.example.controle_robo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {

    private EditText barraEmail;
    private EditText barraSenha;
    private Button btnRegistrar;
    private Button btnVoltar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        barraEmail = findViewById(R.id.barraEmail);
        barraSenha = findViewById(R.id.barraSenha);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = barraEmail.getText().toString().trim(); //.trim()
                String senha = barraSenha.getText().toString().trim(); //.trim()
                CriarUsuario(email, senha);
            }
        });

    }

    private void CriarUsuario(final String email, final String senha) {
        firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(Cadastro.this, Login.class);  //Fez login vai para outra activity

                    intent.putExtra("email",email);
                    intent.putExtra("senha",senha);

                    Toast.makeText(Cadastro.this, "Cadastro realizado Com Sucesso", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(Cadastro.this, "Cadastro não deu boa kkk", Toast.LENGTH_SHORT).show();
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