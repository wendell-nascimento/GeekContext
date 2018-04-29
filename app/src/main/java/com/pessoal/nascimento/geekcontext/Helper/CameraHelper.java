package com.pessoal.nascimento.geekcontext.Helper;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.pessoal.nascimento.geekcontext.Activity.CadastroUsuarioActivity;

public class CameraHelper {
    //Verificar se possui permissão para acessar a camera
    //Verificar se possui permissão para salvar no dispositivo

    private int STORAGE_PERMISSION_CODE=1;
    private int WRITE_PERMISSION_CODE=1;
    private int CAMERA_PERMISSION_CODE=1;

    public void verificaPermissaoLEITURA(Context context,Activity activity){
        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            //Caso a permissão já exista exibe uma mensagem
            Toast.makeText(context,"Permissão de leitura autorizada", Toast.LENGTH_SHORT).show();
            verificaPermissaoGRAVACAO(CadastroUsuarioActivity.getCadastroContext(),CadastroUsuarioActivity.getCadastroActivity());
        }
        else {
            requestStoragePermission();
            //Toast.makeText(context,"Sem Permissão de LEITURA", Toast.LENGTH_SHORT).show();
        }
    }

    public void verificaPermissaoGRAVACAO(Context context,Activity activity){
        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            //Caso a permissão já exista exibe uma mensagem
            Toast.makeText(context,"Permissão de gravação autorizada", Toast.LENGTH_SHORT).show();
            verificaPermissaoCAMERA(CadastroUsuarioActivity.getCadastroContext(),CadastroUsuarioActivity.getCadastroActivity());
        }
        else {
            requestWritePermission();
            //Toast.makeText(context,"Sem Permissão de gravação", Toast.LENGTH_SHORT).show();
        }
    }

    public void verificaPermissaoCAMERA(Context context,Activity activity){

        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
        {
            //Caso a permissão já exista exibe uma mensagem
            Toast.makeText(context,"Permissão de CAMERA autorizada", Toast.LENGTH_SHORT).show();
            openCamera();
        }
        else {
            requestCAMERAPermission();
            //Toast.makeText(context,"Sem Permissão para CAMERA", Toast.LENGTH_SHORT).show();
        }
    }

    public void requestStoragePermission() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(CadastroUsuarioActivity.getCadastroActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)){

            //Duvida entre context e activity
            new AlertDialog.Builder(CadastroUsuarioActivity.getCadastroContext())
                    .setTitle("Permissão necessária")
                    .setMessage("A aplicação precisa salvar as fotos no dispositivo")
                    .setPositiveButton("Permitir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CadastroUsuarioActivity.getCadastroActivity(),
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                                    STORAGE_PERMISSION_CODE);
                            verificaPermissaoGRAVACAO(CadastroUsuarioActivity.getCadastroContext(),CadastroUsuarioActivity.getCadastroActivity());
                        }
                    })
                    .setNegativeButton("Negar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else{
            Log.i("Camera: ","Iniciando requisição");
            ActivityCompat.requestPermissions(CadastroUsuarioActivity.getCadastroActivity(),
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    STORAGE_PERMISSION_CODE);
        }
    }

    public void requestWritePermission(){

        if(ActivityCompat.shouldShowRequestPermissionRationale(CadastroUsuarioActivity.getCadastroActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            //Duvida entre context e activity
            new AlertDialog.Builder(CadastroUsuarioActivity.getCadastroContext())
                    .setTitle("Permissão necessária")
                    .setMessage("A aplicação precisa salvar as fotos no dispositivo")
                    .setPositiveButton("Permitir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CadastroUsuarioActivity.getCadastroActivity(),
                                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    WRITE_PERMISSION_CODE);
                            verificaPermissaoCAMERA(CadastroUsuarioActivity.getCadastroContext(),CadastroUsuarioActivity.getCadastroActivity());
                        }
                    })
                    .setNegativeButton("Negar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else{
            Log.i("Camera: ","Iniciando requisição");
            ActivityCompat.requestPermissions(CadastroUsuarioActivity.getCadastroActivity(),
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_PERMISSION_CODE);
        }
    }

    public void requestCAMERAPermission(){

        if(ActivityCompat.shouldShowRequestPermissionRationale(CadastroUsuarioActivity.getCadastroActivity(),
                Manifest.permission.CAMERA)){

            //Duvida entre context e activity
            new AlertDialog.Builder(CadastroUsuarioActivity.getCadastroContext())
                    .setTitle("Permissão necessária")
                    .setMessage("A aplicação precisa salvar as fotos no dispositivo")
                    .setPositiveButton("Permitir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CadastroUsuarioActivity.getCadastroActivity(),
                                    new String[] {Manifest.permission.CAMERA},
                                    CAMERA_PERMISSION_CODE);
                            openCamera();
                        }
                    })
                    .setNegativeButton("Negar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else{
            Log.i("Camera: ","Iniciando requisição");
            ActivityCompat.requestPermissions(CadastroUsuarioActivity.getCadastroActivity(),
                    new String[] {Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE);
        }
    }

    public void openCamera(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        CadastroUsuarioActivity.getCadastroActivity().startActivityForResult(intent,0);
    }

   /* public int getSTORAGE_PERMISSION_CODE() {
        return STORAGE_PERMISSION_CODE;

    }*/
}
