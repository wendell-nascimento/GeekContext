package com.pessoal.nascimento.geekcontext.Helper;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.pessoal.nascimento.geekcontext.Activity.CadastroUsuarioActivity;
import com.pessoal.nascimento.geekcontext.Activity.PrincipalActivity;
import com.pessoal.nascimento.geekcontext.Classes.Usuario;
import com.pessoal.nascimento.geekcontext.R;

public class CadastroUsuarioHelper {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText senhaRepita;
    private Usuario usuario;
    private RadioButton rbAdministrador;
    private RadioButton rbAtendente;


    public CadastroUsuarioHelper(CadastroUsuarioActivity activity){

        nome= activity.findViewById(R.id.edtNomeCompletoCad);
        email= activity.findViewById(R.id.edtEmailCad);
        senha= activity.findViewById(R.id.edtSenhaCad);
        senhaRepita= activity.findViewById(R.id.edtSenhaCad2);
        rbAdministrador= activity.findViewById(R.id.rbAdministrador);
        rbAtendente= activity.findViewById(R.id.rbAtendente);

        usuario=new Usuario();
    }

    public Usuario getUsuario(){
        usuario.setNome(nome.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setSenha1(senha.getText().toString());
        usuario.setSenha2(senhaRepita.getText().toString());

            if(rbAtendente.isChecked()){
                usuario.setTipoUsuario("Atendente");
            }else if (rbAdministrador.isChecked()){
                usuario.setTipoUsuario("Administrador");
            }

        Toast.makeText(CadastroUsuarioActivity.getCadastroContext(),"Usuario recebido", Toast.LENGTH_SHORT).show();
        CadastroUsuarioActivity.mainCadastro.finish();
        CadastroUsuarioActivity.getCadastroContext().startActivity(irActivityPrincipal());
        return usuario;
    }

   /* public void setUsuario(Usuario usuario){
        usuario.setNome(nome.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setSenha1(senha.getText().toString());
        usuario.setSenha2(senhaRepita.getText().toString());
    }*/

   public Intent irActivityPrincipal(){
       Intent i=new Intent(CadastroUsuarioActivity.getCadastroContext(), PrincipalActivity.class);
       return i;
   }
}
