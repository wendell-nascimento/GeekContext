package com.pessoal.nascimento.geekcontext.Helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class CameraHelper {
    //Verificar se possui permissão para acessar a camera
    //Verificar se possui permissão para salvar no dispositivo

    Context context;
    Activity activity;

    public CameraHelper(Context context, Activity activity){
        this.context=context;
        this.activity=activity;
    }

    public void VerificaPermissaoGravacao(Context context){
        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            //Caso a permissão já exista exibe uma mensagem
            Toast.makeText(context,"Permissão autorizada", Toast.LENGTH_SHORT).show();
        }
        else {
            requestStoragePermission(activity);
        }
    }

    private void requestStoragePermission(Activity activity) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)){

        }
    }

    //continuar parte para salvar foto no dispositivo


}
