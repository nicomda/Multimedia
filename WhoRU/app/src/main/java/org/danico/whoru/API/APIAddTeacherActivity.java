package org.danico.whoru.API;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.kairos.*;
import com.mvc.imagepicker.ImagePicker;

import org.danico.whoru.R;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;

public class APIAddTeacherActivity extends AppCompatActivity {
    private Kairos myKairos;
    private KairosListener kListener;
    private Toolbar toolbar;
    private ImageView imgView;
    private EditText editname;
    private Button button_pick;
    private Button button_send;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiadd_teacher);
        toolbar = (Toolbar) findViewById(R.id.api_add_toolbar);
        toolbar.setTitle("Add teacher");
        setSupportActionBar(toolbar);
        setUpListeners();
        setUpKairos();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        imgView.setImageBitmap(bitmap);
        if (bitmap != null) {
            button_send.setEnabled(true);
        }
    }

    void setUpListeners() {
        editname = (EditText) findViewById(R.id.api_add_name);
        imgView = (ImageView) findViewById(R.id.api_add_imgview);
        button_pick = (Button) findViewById(R.id.api_add_pickimg);
        button_send = (Button) findViewById(R.id.api_add_send);
        button_send.setEnabled(false);
        button_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.pickImage(APIAddTeacherActivity.this, "Select your image: ");
            }
        });
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    myKairos.enroll(bitmap, editname.getText().toString(), getString(R.string.kairos_gallery_id), null, null, null, kListener);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void setUpKairos() {
        myKairos = new Kairos();
        myKairos.setAuthentication(getApplicationContext(), getString(R.string.kairos_app_id), getString(R.string.kairos_api_key));
        kListener = new KairosListener() {
            @Override
            public void onSuccess(String s) {
                Log.d("KAIROS DEMO", s);
                button_send.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                button_send.setText("Success!!");
                button_send.setEnabled(false);

            }

            @Override
            public void onFail(String s) {
                Log.d("KAIROS DEMO", s);
                button_send.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                button_send.setText("Error");

            }
        };
    }
}
