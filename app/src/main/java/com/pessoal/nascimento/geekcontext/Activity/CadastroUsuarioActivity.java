package com.pessoal.nascimento.geekcontext.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pessoal.nascimento.geekcontext.Classes.Usuario;
import com.pessoal.nascimento.geekcontext.Helper.CadastroUsuarioHelper;
import com.pessoal.nascimento.geekcontext.R;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private Button btnCancelarCadastro;
    private Button btnCadastrar;
    private CadastroUsuarioHelper helperCadastro;
    private Usuario usuario;

    private static Context cadastroContext;
    public static Activity mainCadastro;

    public static Context getCadastroContext(){
        return cadastroContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cadastroContext=getApplicationContext();
        mainCadastro=this;

        setContentView(R.layout.activity_cadastro_usuario);

        btnCancelarCadastro= findViewById(R.id.btnBotaoCancelarCad);

        helperCadastro=new CadastroUsuarioHelper(CadastroUsuarioActivity.this);

        btnCadastrar= findViewById(R.id.btnBotaoCad);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario=helperCadastro.getUsuario();

                if(usuario.getSenha1().toString().equals(usuario.getSenha2().toString())){

                }
                else {
                    Toast.makeText(CadastroUsuarioActivity.this,"Senhas não conferem",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CadastroUsuarioActivity.this, PrincipalActivity.class );
                startActivity(i);
                finish();
            }
        });
    }


}
