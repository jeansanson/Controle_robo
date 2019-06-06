package com.example.controle_robo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*
public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAL dal = new DAL(InsertActivity.this);
                EditText etNome = findViewById(R.id.etNome);
                EditText etIdade = findViewById(R.id.etIdade);
                EditText etLeucocito = findViewById(R.id.leucocitoIn);
                EditText etGlicemia = findViewById(R.id.glicemiaIn);
                EditText etAstTgo = findViewById(R.id.astTgoIn);
                EditText etLdh = findViewById(R.id.ldhIn);
                TextView pontuacaoOut = findViewById(R.id.pontuacaoOut);
                TextView mortalidadeOut = findViewById(R.id.mortalidadeOut);
                CheckBox litiaseIn = findViewById(R.id.checkBox);

                String nome = etNome.getText().toString();
                Integer idade = Integer.parseInt(etIdade.getText().toString());
                Integer leucocitos = Integer.parseInt(etLeucocito.getText().toString());
                Integer glicemia = Integer.parseInt(etGlicemia.getText().toString());
                Integer astTgo = Integer.parseInt(etAstTgo.getText().toString());
                Integer ldh = Integer.parseInt(etLdh.getText().toString());
                Integer pontuacao = 0;
                Integer mortalidade = 0;
                Boolean litiase;


                if(litiaseIn.isChecked()){
                    litiase = true;
                    if(idade>55)pontuacao++;
                    if(leucocitos>1600)pontuacao++;
                    if(glicemia>11)pontuacao++;
                    if(astTgo>250)pontuacao++;
                    if(ldh>350)pontuacao++;
                }else {
                    litiase = false;
                    if(idade>70)pontuacao++;
                    if(leucocitos>18000)pontuacao++;
                    if(glicemia>12)pontuacao++;
                    if(astTgo>250)pontuacao++;
                    if(ldh>400)pontuacao++;
                }
                if(pontuacao==0 && pontuacao<=2){mortalidade = 2;}
                if(pontuacao==3 || pontuacao==4){mortalidade = 15;}
                if(pontuacao==5 || pontuacao==6){mortalidade = 40;}
                if(pontuacao==7 || pontuacao==8){mortalidade = 100;}

                pontuacaoOut.setText(pontuacao.toString());
                mortalidadeOut.setText(mortalidade.toString()+"%");

                if (dal.insert(nome, idade, leucocitos, glicemia, astTgo, ldh, litiase, pontuacao, mortalidade)) {
                    Toast.makeText(InsertActivity.this,
                            "Registro Inserido com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(InsertActivity.this,
                            "Erro ao inserir registro!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
*/