package com.pessoal.nascimento.geekcontext.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pessoal.nascimento.geekcontext.Classes.Usuario;
import com.pessoal.nascimento.geekcontext.Helper.MainHelper;
import com.pessoal.nascimento.geekcontext.R;

public class MainActivity extends AppCompatActivity {

    private static Context MainActivityContext;
    public static Activity Main;

    private FirebaseAuth autenticacao;

    private Usuario usuario;
    private MainHelper helper;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityContext=getApplicationContext(); //AQUI!
        Main=MainActivity.this;

        button= findViewById(R.id.btnLogin);

        helper=new MainHelper(MainActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            boolean valida;

            @Override
            public void onClick(View view) {

                usuario=helper.getUsuario();

                helper.verificaeInicia(MainActivity.this,
                                            usuario.getEmail().toString(),
                                            usuario.getSenha1().toString());
            }
        });
    }

    public static Context getContext() {
        return MainActivityContext;
    }
}
