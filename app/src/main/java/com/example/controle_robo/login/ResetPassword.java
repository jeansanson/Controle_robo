package com.example.controle_robo.login;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.controle_robo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private Button btnResetPassword;
    private EditText etEmail;
    private ImageView imvLogo1;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Toast.makeText(this, "Anota essa senha palhaço", Toast.LENGTH_SHORT).show();

        btnResetPassword = findViewById(R.id.btnResetPassword);
        etEmail = findViewById(R.id.etEmail);
        imvLogo1 = findViewById(R.id.imvLogo1);

        imvLogo1.setImageResource(R.drawable.logo);



        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                resetPassword(email);
            }
        });


    }

    private void resetPassword(String email) {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(ResetPassword.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ResetPassword.this, "Verifique sua caixa de Emails", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(ResetPassword.this, "Email não existe", Toast.LENGTH_SHORT).show();
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
