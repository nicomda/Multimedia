package org.danico.whoru;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.android.cameraview.CameraView;

public class IdentifyActivity extends AppCompatActivity {

    private CameraView camera;
    private ImageView button_recon;
    private ImageView button_camera_flip;
    private ImageView button_camera_flash;
    private final int PERMISSION_REQUEST_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        //TODO Disabled due permissions problems. We'll try to fix it later
        //launchTour();
        setUpButtonsListeners();
        askForCameraPermission();


    }

    //TODO MAYBE WE NEED A SPLASH SCREEN DUE TO HIGH LOAD OF FIRST LAUNCH (JUST IN THE FIRST LAUNCH)
    public void launchTour() {

        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());

        //  Create a new boolean and preference and set it to true
        boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

        //  If the activity has never started before...
        if (isFirstStart) {

            //  Launch app intro
            Intent i = new Intent(getApplicationContext(), IntroActivity.class);
            startActivity(i);

            //  Make a new preferences editor
            SharedPreferences.Editor e = getPrefs.edit();

            //  Edit preference to make it false because we don't want this to run again
            e.putBoolean("firstStart", false);

            //  Apply changes
            e.apply();
        }

    }

    public void setUpButtonsListeners() {

        //FAB Shoot camera listener
        button_recon = (ImageView) findViewById(R.id.identify_capture);
        button_recon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(i);
            }
        });

        //Flip camera button listener
        button_camera_flip = (ImageView) findViewById(R.id.identify_ic_camera_flip);
        button_camera_flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera.getFacing() == CameraView.FACING_FRONT) {
                    camera.setFacing(CameraView.FACING_BACK);
                } else camera.setFacing(CameraView.FACING_FRONT);
            }
        });
        //Flash camera button listener
        button_camera_flash = (ImageView) findViewById(R.id.identify_ic_camera_flash);
        button_camera_flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera.getFlash() == CameraView.FLASH_ON) {
                    camera.setFlash(CameraView.FLASH_OFF);
                    button_camera_flash.setImageDrawable(getResources().getDrawable(R.drawable.flash_disabled));
                } else if (camera.getFlash() == CameraView.FLASH_OFF) {
                    camera.setFlash(CameraView.FLASH_AUTO);
                    button_camera_flash.setImageDrawable(getResources().getDrawable(R.drawable.flash_auto));
                } else {
                    camera.setFlash(CameraView.FLASH_ON);
                    button_camera_flash.setImageDrawable(getResources().getDrawable(R.drawable.flash_enabled));
                }
            }
        });
    }

    public void askForCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
        } else initializeCamera();
    }

    //This function is also invoked when permissions are accepted. Refer to onRequestPermissionsResult, kinda listener.
    public void initializeCamera() {
        camera = (CameraView) findViewById(R.id.camera);
        camera.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CAMERA: { //Permissions.READ_CONTACTS is an int constant
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission granted
                    initializeCamera();
                }
            }
        }
    }
}
