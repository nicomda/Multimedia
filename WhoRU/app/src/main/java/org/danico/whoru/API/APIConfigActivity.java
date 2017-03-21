package org.danico.whoru.API;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.danico.whoru.R;

public class APIConfigActivity extends AppCompatActivity {
    private Button add_teacher;
    private Button list_gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiconfig);

        add_teacher = (Button) findViewById(R.id.api_button_add);
        list_gallery = (Button) findViewById(R.id.api_button_list);
        add_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(APIConfigActivity.this, APIAddTeacherActivity.class);
                startActivity(i);
            }
        });

        list_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(APIConfigActivity.this, APIListGallery.class);
                startActivity(i);
            }
        });

    }

}
