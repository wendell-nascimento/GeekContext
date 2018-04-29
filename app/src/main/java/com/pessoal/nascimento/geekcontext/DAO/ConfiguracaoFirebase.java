package com.pessoal.nascimento.geekcontext.DAO;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pessoal.nascimento.geekcontext.Activity.CadastroUsuarioActivity;
import com.pessoal.nascimento.geekcontext.Classes.Usuario;

/**
 * Created by Nascimento on 08/04/2018.
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

    public void cadastrarUsuario(final Usuario usuario){
        autenticacaoFirebase=ConfiguracaoFirebase.getAutenticacaoFirebase();
        autenticacaoFirebase.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha1()).addOnCompleteListener(CadastroUsuarioActivity.mainCadastro, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    inseriUsuario(usuario);

                }else{
                    String erroExcecao="";

                    try{
                        throw task.getException();
                    }
                    catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao="A senha deve conter no minimo 8 caracteres contendo letras e números";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao="O email utilizado é inválido";
                    }
                    catch (FirebaseAuthUserCollisionException e){
                        erroExcecao="O email utilizado já está cadastrado";
                    } catch (Exception e) {
                        erroExcecao="Erro ao efetuar o cadastro";
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroUsuarioActivity.mainCadastro,"Erro "+erroExcecao,Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean inseriUsuario(Usuario usuario) {
        try{
            referenciaFirebase=ConfiguracaoFirebase.getReferenciaFirebase().child("usuarios");
            referenciaFirebase.push().setValue(usuario);
            Toast.makeText(CadastroUsuarioActivity.mainCadastro,"Usuário cadastrado com sucesso ",Toast.LENGTH_LONG).show();
            return true;

        }catch (Exception e){
            Toast.makeText(CadastroUsuarioActivity.mainCadastro,"Erro ao gravar ",Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
    }
}
