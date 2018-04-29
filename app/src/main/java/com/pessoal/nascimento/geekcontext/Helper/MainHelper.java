package com.pessoal.nascimento.geekcontext.Helper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pessoal.nascimento.geekcontext.Activity.CadastroUsuarioActivity;
import com.pessoal.nascimento.geekcontext.Activity.MainActivity;
import com.pessoal.nascimento.geekcontext.Activity.PrincipalActivity;
import com.pessoal.nascimento.geekcontext.Classes.Usuario;
import com.pessoal.nascimento.geekcontext.DAO.ConfiguracaoFirebase;
import com.pessoal.nascimento.geekcontext.R;

/**
 * Created by Nascimento on 08/04/2018.
 */

public class MainHelper {

    private Usuario usuario;

    private EditText emailUsuario;
    private EditText senhaUsuario;

    private FirebaseAuth autenticacao;

    public MainHelper(MainActivity activity){

        emailUsuario= activity.findViewById(R.id.edtEmail);
        senhaUsuario= activity.findViewById(R.id.edtSenha);


        usuario=new Usuario();
    }

    public Usuario getUsuario(){

        usuario.setEmail(emailUsuario.getText().toString());
        usuario.setSenha1(senhaUsuario.getText().toString());

        return usuario;
    }

    public void verificaeInicia(Context context,String a, String b){
        if(a.isEmpty() || b.isEmpty()){
            Toast.makeText(context,"Preencha todos os campos", Toast.LENGTH_SHORT ).show();

        }
        else{ //se estiver tudo ok, retorna true
            validarLogin();
        }
    }

    public void validarLogin(){

        autenticacao= ConfiguracaoFirebase.getAutenticacaoFirebase();

        Toast.makeText(MainActivity.getContext(),"ok",Toast.LENGTH_LONG);

        Log.i("Email: ", usuario.getEmail().toString());
        Log.i("Senha: ", usuario.getSenha1().toString());

        autenticacao.signInWithEmailAndPassword(usuario.getEmail().toString(),usuario.getSenha1().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.getContext(),"Tudo OK!", Toast.LENGTH_SHORT).show();
                        MainActivity.getContext().startActivity(administradorActivity());
                    }
                    else{
                        Toast.makeText(MainActivity.getContext(),"Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    public Intent administradorActivity(){
        Intent administrador=new Intent(MainActivity.getContext(), PrincipalActivity.class);
        return administrador;
    }
}