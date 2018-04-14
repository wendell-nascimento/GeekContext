package com.pessoal.nascimento.geekcontext.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pessoal.nascimento.geekcontext.R;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private Button btnCancelarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro_usuario);

        btnCancelarCadastro= findViewById(R.id.btnBotaoCancelarCad);

        btnCancelarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CadastroUsuarioActivity.this, PrincipalActivity.class );
                startActivity(i);
            }
        });
    }
}
