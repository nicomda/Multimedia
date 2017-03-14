package org.danico.whoru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import io.realm.Realm;

public class MatchFaceActivity extends AppCompatActivity {

    private ImageView button_fav;
    private ImageView button_back;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_face);
        realm = Realm.getDefaultInstance();
        setUpRecyclerView();

    }

    public void setUpListeners(){
        button_fav=(ImageView)findViewById(R.id.imageView_matchface_fav);
        button_back=(ImageView)findViewById(R.id.imageView_matchface_back);
        button_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Implement fav
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });

    }

    private void setUpRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_matchface);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //TODO Modify query here to get just 1 teacher to show on RecyclerView
        mAdapter = new MatchFaceRecyclerViewAdapter(this, realm.where(Teacher.class).findAll());
        mRecyclerView.setAdapter(mAdapter);
    }
}
