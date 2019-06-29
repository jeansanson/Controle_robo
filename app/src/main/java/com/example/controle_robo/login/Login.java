package com.example.controle_robo.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener {

    private EditText barraEmail;
    private EditText barraSenha;
    private Button btnLogin;
    private Button btnNovo;
    private ImageView imvLogo;
    private TextView tvResetSenha;
    private CheckBox cbLembrarSenha;
    private SignInButton btnSignIn;

    private FirebaseAuth firebaseAuth;

    private GoogleApiClient googleApiClient;
    private FirebaseAuth.AuthStateListener authStateListener;

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
        btnSignIn = findViewById(R.id.btnSignIn);

        firebaseAuth = firebaseAuth.getInstance(); //Inicializa o firebase

        conectarGoogleApi();

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

                if (barraEmail.length() == 0 || barraSenha.length() == 0){  //Se algum campo estiver vazio aparece a mensagem
                    Toast.makeText(Login.this, "Campos solicitados vazios", Toast.LENGTH_SHORT).show();
                }

                else{
                    if (cbLembrarSenha.isChecked()) { //Se a checkbox esta clicada

                        Intent intent = new Intent(Login.this, TelaCarregamento.class);
                        startActivity(intent);

                        prefEditor.putString(getString(R.string.checkbox), "True"); //Vai abrir o app com a checkbox clicada
                        prefEditor.commit();

                        String email = barraEmail.getText().toString().trim();
                        prefEditor.putString(getString(R.string.email), email);    //com o ultimo login e senha salvo
                        prefEditor.commit();

                        String senha = barraSenha.getText().toString().trim();
                        prefEditor.putString(getString(R.string.password), senha);
                        prefEditor.commit();

                        login(email, senha);


                    } else {  //Se a checkbox não esta clicada apenas faz o login e nao salva nada

                        Intent intent = new Intent(Login.this, TelaCarregamento.class);
                        startActivity(intent);

                        SharedPreferences config =  PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                        SharedPreferences.Editor editor = config.edit();
                        editor.clear();   //Retira os dados guardados no SharedPreferences
                        editor.commit();  //Salva as configurações

                        //Toast.makeText(Login.this, "Dados salvos foram apagados", Toast.LENGTH_SHORT).show();

                        String email1 = barraEmail.getText().toString().trim();
                        String senha1 = barraSenha.getText().toString().trim();
                        login(email1, senha1);

                    }}

               /* String email = barraEmail.getText().toString().trim();
                String senha = barraSenha.getText().toString().trim();
                login(email, senha);*/

                }

        });

        /*btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences config =  PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = config.edit();
                editor.clear();   //Retira os dados guardados no SharedPreferences
                editor.commit();  //Salva as configurações
                Toast.makeText(Login.this, "Dados salvos foram apagados", Toast.LENGTH_SHORT).show();

                barraEmail.setText("");
                barraSenha.setText("");

                if (cbLembrarSenha.isChecked()){
                    cbLembrarSenha.setChecked(false);
                }


            }
        });*/

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
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

    private void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                loginFirebase(account);

            }
        }
        else{
            Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
        }
    }


    private void loginFirebase(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login realizado com sucesso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Login.this, "Deu ruim na autenticação", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void conectarGoogleApi() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
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

                    if (task.isSuccessful()) {  //Se o login é valido
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Login Invalido", Toast.LENGTH_SHORT).show();
                    }

                }




        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (barraEmail == null || barraSenha == null)
                Toast.makeText(Login.this, "Digite algo na barra", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override  //Bloqueia o Botão de voltar do celular
    public void onBackPressed() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexao.getFirebaseAuth();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Conexão falhou", Toast.LENGTH_SHORT).show();
    }
}
