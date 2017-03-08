package org.danico.whoru;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MatchFaceActivity extends AppCompatActivity {

    private ImageView button_fav;
    private ImageView button_back;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_face);


        mRecyclerView=(RecyclerView) findViewById(R.id.recyclerview_matchface);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDataset=new ArrayList<>();
        mDataset.add("Twitter");
        mDataset.add("Facebook");
        mDataset.add("Youtube");
        mDataset.add("Facebook");
        mDataset.add("Youtube");
        mDataset.add("Facebook");
        mDataset.add("Youtube");
        mAdapter=new MatchFaceRecyclerViewAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

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
}
