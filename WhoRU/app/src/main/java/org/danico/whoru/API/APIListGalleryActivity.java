package org.danico.whoru.API;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kairos.Kairos;
import com.kairos.KairosListener;

import org.danico.whoru.MyFavsRecyclerViewAdapter;
import org.danico.whoru.R;
import org.danico.whoru.Teacher;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class APIListGalleryActivity extends AppCompatActivity {
    private Gson gson;
    private Kairos myKairos;
    private APIList apiList;
    private KairosListener kListener;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apilist_gallery);
        toolbar = (Toolbar) findViewById(R.id.api_list_toolbar);
        toolbar.setTitle("Teachers on Kairos");
        setSupportActionBar(toolbar);
        gson = new Gson();
        setUpKairos();
        try {
            myKairos.listSubjectsForGallery(getString(R.string.kairos_gallery_id), kListener);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    void setUpKairos() {
        myKairos = new Kairos();
        myKairos.setAuthentication(getApplicationContext(), getString(R.string.kairos_app_id), getString(R.string.kairos_api_key));
        kListener = new KairosListener() {
            @Override
            public void onSuccess(String s) {
                Log.d("KAIROS DEMO", s);
                apiList = gson.fromJson(s, APIList.class);
                setUpRecyclerView();
                if (s.contains("successfully removed")) {
                    Toast.makeText(getApplicationContext(), "Deleted from Teachers", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFail(String s) {
                Toast.makeText(getApplicationContext(), "Response failed", Toast.LENGTH_SHORT).show();
                Log.d("KAIROS DEMO", s);
            }
        };
    }

    void setUpRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.api_list_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new APIListAdapter(kListener, apiList.getSubject_ids());
        mRecyclerView.setAdapter(mAdapter);
    }
}
