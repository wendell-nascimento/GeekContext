package com.pessoal.nascimento.geekcontext.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.pessoal.nascimento.geekcontext.Classes.Usuario;
import com.pessoal.nascimento.geekcontext.Helper.MainHelper;
import com.pessoal.nascimento.geekcontext.R;

public class MainActivity extends AppCompatActivity {

    public static Activity Main;
  //Teste com o GITHUB
    private static Context MainActivityContext;
    private FirebaseAuth autenticacao;

    private Usuario usuario;
    private MainHelper helper;

    private Button button;

    public static Context getContext() {
        return MainActivityContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityContext = getApplicationContext(); //AQUI!
        Main=this;

        button = findViewById(R.id.btnLogin);

        helper = new MainHelper(MainActivity.this);

            button.setOnClickListener(new View.OnClickListener() {
                boolean valida;

                @Override
                public void onClick(View view) {

                    usuario = helper.getUsuario();
                    boolean verificaCampos;
                    boolean verificaSenhas;


                    verificaCampos=helper.verificaeInicia(MainActivity.this,
                            usuario.getEmail().toString(),
                            usuario.getSenha1().toString());

                    if (verificaCampos){
                        helper.validarLogin();
                        //helper.limparCampos();
                    }
                }
            });
        }
}
