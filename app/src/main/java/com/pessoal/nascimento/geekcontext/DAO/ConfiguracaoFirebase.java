package com.pessoal.nascimento.geekcontext.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
}
