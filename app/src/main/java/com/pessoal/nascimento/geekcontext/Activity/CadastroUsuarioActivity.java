package com.pessoal.nascimento.geekcontext.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pessoal.nascimento.geekcontext.Helper.CadastroUsuarioHelper;
import com.pessoal.nascimento.geekcontext.Helper.CameraHelper;
import com.pessoal.nascimento.geekcontext.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CadastroUsuarioActivity extends AppCompatActivity {


    private static Context CadastroContext;
    private static Activity CadastroActivity;
    private Button botaoCadastrarUsuario;
    private ImageView usuarioFoto;
    private CadastroUsuarioHelper cadastroUsuarioHelper;
    private CameraHelper cameraHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

       CadastroContext=getApplicationContext();
       CadastroActivity=CadastroUsuarioActivity.this;
       cadastroUsuarioHelper=new CadastroUsuarioHelper(this);
       cameraHelper=new CameraHelper();

       botaoCadastrarUsuario=findViewById(R.id.btnBotaoCad);
       usuarioFoto=findViewById(R.id.imgFotoCadastro);

       botaoCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               cadastroUsuarioHelper.getUsuario();
           }
       });

       usuarioFoto.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                         cameraHelper.verificaPermissaoLEITURA(CadastroUsuarioActivity.this, CadastroUsuarioActivity.this);
           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            usuarioFoto.setImageBitmap(bitmap);
        } catch (Exception e)
        {
            Toast.makeText(CadastroUsuarioActivity.this,"Cadastre sua Foto!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


    }

    public static Context getCadastroContext() {
        return CadastroContext;
    }

    public static Activity getCadastroActivity() {
        return CadastroActivity;
    }
}
