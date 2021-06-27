package com.example.filepicker;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class FilePickerActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private String[] perms = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };
    private String [] cameraPermission={
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int RC_READ_WRITE = 001;
    private static final int RC_CAMERA = 002;
    private int REQUEST_CODE;
    private Intent intent;
    private String type;
    private boolean multiple;
    private boolean camera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        intent=getIntent();
        REQUEST_CODE=intent.getIntExtra("requestCode",101);
        type=intent.getStringExtra("type");
        multiple=intent.getBooleanExtra("multiple",false);
        camera=intent.getBooleanExtra("camera",false);
        if (camera){
            checkCameraPermissions();
        }else {
            checkPermissions();
        }
    }

    private void checkCameraPermissions() {
        if (EasyPermissions.hasPermissions(this,cameraPermission)){
            openCamera();
        }else {
            EasyPermissions.requestPermissions(this, getString(R.string.camera_permission),
                    RC_CAMERA, cameraPermission);
        }
    }

    private void checkPermissions() {
        if (!EasyPermissions.hasPermissions(this, perms)) {
            //permission not granted so far
            EasyPermissions.requestPermissions(this, getString(R.string.read_write_permissions),
                    RC_READ_WRITE, perms);

        }
        else {
            chooseFile();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(RC_CAMERA)
    private void openCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CODE);
    }

    @AfterPermissionGranted(RC_READ_WRITE)
    private void chooseFile() {
        Intent chooserIntent= FileUtils.createGetContentIntent(type,multiple);
        startActivityForResult(chooserIntent,REQUEST_CODE);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode==RC_READ_WRITE){
            chooseFile();
        }
        else if (requestCode==RC_CAMERA){
            openCamera();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

}
