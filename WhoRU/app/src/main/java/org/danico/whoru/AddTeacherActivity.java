package org.danico.whoru;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mvc.imagepicker.ImagePicker;

import io.realm.Realm;

public class AddTeacherActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imgview;
    private Button button_send;
    private Button button_pickimg;
    private Bitmap bitmap;
    private EditText name;
    private EditText department;
    private EditText office;
    private EditText email;
    private EditText twitter;
    private EditText web;
    private EditText addinfo;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        toolbar = (Toolbar) findViewById(R.id.add_teacher_toolbar);
        toolbar.setTitle("Add teacher to Realm");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        imgview.setImageBitmap(bitmap);
        if (bitmap != null) {
            button_send.setEnabled(true);
        }
    }

    void setUpListeners() {
        imgview = (ImageView) findViewById(R.id.add_teacher_name);
        button_pickimg = (Button) findViewById(R.id.add_teacher_pickimg);
        button_send = (Button) findViewById(R.id.add_teacher_save);
        name = (EditText) findViewById(R.id.add_teacher_name);
        department = (EditText) findViewById(R.id.add_teacher_department);
        office = (EditText) findViewById(R.id.add_teacher_office);
        email = (EditText) findViewById(R.id.add_teacher_email);
        twitter = (EditText) findViewById(R.id.add_teacher_twitter);
        web = (EditText) findViewById(R.id.add_teacher_webpage);
        addinfo = (EditText) findViewById(R.id.add_teacher_addinfo);
        button_pickimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.pickImage(AddTeacherActivity.this, "Select your image: ");
            }
        });
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm = Realm.getDefaultInstance();
                final Teacher teacher = new Teacher(name.getText().toString(),
                        department.getText().toString(),
                        office.getText().toString(),
                        email.getText().toString(),
                        twitter.getText().toString(),
                        web.getText().toString(),
                        addinfo.getText().toString(),
                        bitmap);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(teacher);
                    }
                });
            }
        });


    }

}
