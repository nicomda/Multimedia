package org.danico.whoru;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;
import io.realm.RealmResults;

public class MatchFaceActivity extends AppCompatActivity {

    private ImageView button_fav;
    private ImageView button_back;
    private CircleImageView profile_img;
    private TextView profile_name;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Realm realm;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_face);
        realm = Realm.getDefaultInstance();
        intent = getIntent();
        setUpViewsAndListeners();
        loadTeacherInfo();

    }

    public void setUpViewsAndListeners() {
        button_fav=(ImageView)findViewById(R.id.imageView_matchface_fav);
        button_back=(ImageView)findViewById(R.id.imageView_matchface_back);
        profile_img = (CircleImageView) findViewById(R.id.user_profile_photo);
        profile_name = (TextView) findViewById(R.id.textView_matchname);
        button_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String subject_name = intent.getStringExtra("subject_id");
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Teacher> db_results = realm.where(Teacher.class).equalTo("name", subject_name).findAll();
                        Teacher teacher = db_results.get(0);
                        if (teacher.isFav()) {
                            teacher.setFav(false);
                            button_fav.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.star_outline));
                        } else {
                            teacher.setFav(true);
                            button_fav.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.star));
                        }
                        realm.copyToRealmOrUpdate(teacher);
                    }
                });

            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });

    }

    private void setUpRecyclerView(ArrayList<TeacherData> dataset) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_matchface);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MatchFaceRecyclerViewAdapter(getApplicationContext(), dataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadTeacherInfo() {
        String subject_name = intent.getStringExtra("subject_id");
        RealmResults<Teacher> db_results = realm.where(Teacher.class).equalTo("name", subject_name).findAll();
        Teacher teacher = db_results.get(0);
        Bitmap bitmap = BitmapFactory.decodeByteArray(teacher.getImage(), 0, teacher.getImage().length);
        profile_img.setImageBitmap(bitmap);
        profile_name.setText(teacher.getName());
        if (teacher.isFav()) {
            button_fav.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.star));
        } else
            button_fav.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.star_outline));
        setUpRecyclerView(buildTeacherData(teacher));

    }

    private ArrayList<TeacherData> buildTeacherData(Teacher teacher) {
        ArrayList<TeacherData> dataset;
        dataset = new ArrayList<>();
        if (teacher.getDepartment() != null) {
            dataset.add(new TeacherData(teacher.getDepartment(), TeacherData.infoType.DEPARTMENT));
        }
        if (teacher.getOffice() != null) {
            dataset.add(new TeacherData(teacher.getOffice(), TeacherData.infoType.OFFICE));
        }
        if (teacher.getEmail() != null) {
            dataset.add(new TeacherData(teacher.getEmail(), TeacherData.infoType.MAIL));
        }
        if (teacher.getWeb() != null) {
            dataset.add(new TeacherData(teacher.getWeb(), TeacherData.infoType.WEBSITE));
        }
        if (teacher.getTwitter() != null) {
            dataset.add(new TeacherData(teacher.getTwitter(), TeacherData.infoType.TWITTER));
        }
        return dataset;
    }
}
