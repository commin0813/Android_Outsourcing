package com.commin.pro.exerciseproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.commin.pro.exerciseproject.page.list.Page2List;

public class Application extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_layout);
        getPermission();
        createGUI();
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(Application.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Application.this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(Application.this,
                        new String[]{Manifest.permission.CAMERA}, ApplicationProperty.MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case ApplicationProperty.MY_PERMISSIONS_REQUEST_CAMERA: {//CAMERA
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(Application.this, "카메라가 정상적으로 동작하지 않을 수 있습니다.", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

    private void createGUI() {
        Intent intent = new Intent(Application.this, Page2List.class);
        startActivity(intent);
    }

}
