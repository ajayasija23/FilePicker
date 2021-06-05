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

    private String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int RC_READ_WRITE = 001;
    public static final int FILE_REQUEST_CODE = 123;
    private Intent intent;
    private String type;
    private boolean multiple;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        intent=getIntent();
        type=intent.getStringExtra("type");
        multiple=intent.getBooleanExtra("multiple",false);
        checkPermissions();
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

    @AfterPermissionGranted(RC_READ_WRITE)
    private void chooseFile() {
        Intent chooserIntent= FileUtils.createGetContentIntent(type,multiple);
        startActivityForResult(chooserIntent,FILE_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        chooseFile();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

}
