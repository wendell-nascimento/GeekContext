package com.pessoal.nascimento.geekcontext.DAO;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pessoal.nascimento.geekcontext.Activity.CadastroUsuarioActivity;
import com.pessoal.nascimento.geekcontext.Classes.Usuario;

/**
 * Created by Nascimento on 14/04/2018.
 */

public class ConfiguracaoFirebase {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacaoFirebase;

    public static DatabaseReference getReferenciaFirebase(){
        if(referenciaFirebase==null){
           referenciaFirebase= FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }

    public static FirebaseAuth getAutenticacaoFirebase(){
        if(autenticacaoFirebase==null){
            autenticacaoFirebase=FirebaseAuth.getInstance();
        }
        return autenticacaoFirebase;
    }

    public static void logoutUser(){
        autenticacaoFirebase.signOut();
    }

    public void cadastrarUsuario(Usuario usuario){
        autenticacaoFirebase=ConfiguracaoFirebase.getAutenticacaoFirebase();
        autenticacaoFirebase.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha1()).addOnCompleteListener(CadastroUsuarioActivity.mainCadastro, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });
    }
}
