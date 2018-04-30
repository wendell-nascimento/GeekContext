package com.pessoal.nascimento.geekcontext.Helper;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

public class CameraHelper {
    //Verificar se possui permissão para acessar a camera
    //Verificar se possui permissão para salvar no dispositivo

    private int LEITURA_PERMISSION_CODE=1;
    private int GRAVACAO_PERMISSION_CODE=1;
    private int CAMERA_PERMISSION_CODE=1;


    public void verificaPermissaoLEITURA(Context context, Activity activity){
        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            //Caso a permissão já exista exibe uma mensagem
            Toast.makeText(context,"Permissão autorizada", Toast.LENGTH_SHORT).show();
            verificaPermissaoGravacao(context,activity);
        }
        else {
            requestPermissaoLeitura(context, activity);
        }
    }

    public void verificaPermissaoGravacao(Context context, Activity activity){
        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            //Caso a permissão já exista exibe uma mensagem
            Toast.makeText(context,"Permissão autorizada", Toast.LENGTH_SHORT).show();
            verificaPermissaoCAMERA(context,activity);
        }
        else {
            requestPermissaoGravacao(context,activity);
        }
    }

    public void verificaPermissaoCAMERA(Context context, Activity activity){
        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
        {
            //Caso a permissão já exista exibe uma mensagem
            Toast.makeText(context,"Permissão autorizada", Toast.LENGTH_SHORT).show();
        }
        else {
            requestPermissaoCamera(context,activity);
        }
    }

    private void requestPermissaoLeitura(Context context, final Activity activity) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(context)
                    .setTitle("Permissão necessária:")
                    .setMessage("Permissão para leitura de arquivos no dispositivo")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},LEITURA_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},LEITURA_PERMISSION_CODE);
        }
    }

    private void requestPermissaoGravacao(Context context, final Activity activity) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(context)
                    .setTitle("Permissão necessária:")
                    .setMessage("Permissão para leitura de arquivos no dispositivo")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},GRAVACAO_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},GRAVACAO_PERMISSION_CODE);
        }
    }

    private void requestPermissaoCamera(Context context, final Activity activity) {
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA)){
            new AlertDialog.Builder(context)
                    .setTitle("Permissão necessária:")
                    .setMessage("Permissão para leitura de arquivos no dispositivo")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
        }
    }

    //continuar gravação de imagem no dispositivo

}
