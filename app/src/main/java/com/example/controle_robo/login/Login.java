package com.example.controle_robo.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private CheckBox cbLembrarSenha;

    private FirebaseAuth firebaseAuth;

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

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
        cbLembrarSenha = findViewById(R.id.cbLembrarSenha);

        //Logo do grupo de robotica
        imvLogo.setImageResource(R.drawable.logo);

        //SharedPreferences
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        prefEditor = pref.edit();


        checkSharedPreferences();



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

                if (cbLembrarSenha.isChecked()){ //Se a checkbox esta clicada
                    prefEditor.putString(getString(R.string.checkbox),"True"); //Vai abrir o app com a checkbox clicada
                    prefEditor.commit();

                    String email = barraEmail.getText().toString().trim();
                    prefEditor.putString(getString(R.string.email), email);    //com o ultimo login e senha salvo
                    prefEditor.commit();

                    String senha = barraSenha.getText().toString().trim();
                    prefEditor.putString(getString(R.string.password), senha);
                    prefEditor.commit();

                    login(email, senha);
                }
                else{  //Se a checkbox não esta clicada apenas faz o login e nao salva nada
                    String email1 = barraEmail.getText().toString().trim();
                    String senha1 = barraSenha.getText().toString().trim();
                    login(email1, senha1);
                }

               /* String email = barraEmail.getText().toString().trim();
                String senha = barraSenha.getText().toString().trim();
                login(email, senha);*/
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

    private void checkSharedPreferences(){
        String checkbox = pref.getString(getString(R.string.checkbox), "False");
        String email = pref.getString(getString(R.string.email), "");
        String password = pref.getString(getString(R.string.password), "");

        barraEmail.setText(email);
        barraSenha.setText(password);

        if (checkbox.equals("True")){
            cbLembrarSenha.setChecked(true);
        }
        else{
            cbLembrarSenha.setChecked(false);
        }
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
