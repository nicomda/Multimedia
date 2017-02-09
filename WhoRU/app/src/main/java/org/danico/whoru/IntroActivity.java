package org.danico.whoru;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance(getString(R.string.appintro_title1), getString(R.string.appintro_description1), R.drawable.welcome, getResources().getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.appintro_title2), "", R.drawable.welcome, getResources().getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.appintro_title3), getString(R.string.appintro_description3), R.drawable.camera, getResources().getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.appintro_title4), getString(R.string.appintro_description4), R.drawable.check_tick, getResources().getColor(R.color.colorPrimary)));
        askForPermissions(new String[]{Manifest.permission.CAMERA}, 2);
    }
}
