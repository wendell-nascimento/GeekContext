package com.pessoal.nascimento.geekcontext.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.pessoal.nascimento.geekcontext.R;


public class PrincipalActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        MainActivity.Main.finish();

        autenticacao=FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_administrador,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        switch (id){
            case R.id.menu_add_usuario:
                abrirTeladeCadastro();
                break;

            case R.id.menu_logoff_administrador:
                reiniciar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void abrirTeladeCadastro() {
        Intent intent=new Intent(PrincipalActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }

    private void reiniciar(){
        autenticacao.signOut();
        Intent i=new Intent(PrincipalActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}