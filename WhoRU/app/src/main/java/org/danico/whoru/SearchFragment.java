package org.danico.whoru;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lapism.searchview.SearchView;

import org.danico.whoru.API.APIConfigActivity;

import io.realm.Realm;
import io.realm.RealmResults;


public class SearchFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Realm realm;
    public SearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_search, container, false);
        setUpRecyclerView(rootView);
        SearchView search = (SearchView) rootView.findViewById(R.id.searchView);
        //To use search you have to install Google App.
        //search.setVoice(false);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RealmResults queryResults = realm.where(Teacher.class).beginsWith(Teacher.NAME, query).findAllSorted(Teacher.NAME);
                mAdapter = new SearchRecyclerViewAdapter(SearchFragment.this, queryResults);
                mRecyclerView.setAdapter(mAdapter);
                return false;
            }

            //DEV SETTINGS ACCESS
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.contains("apiconfig")) {
                    Intent i = new Intent(getActivity(), APIConfigActivity.class);
                    startActivity(i);
                }
                if (newText.contains("addteacher")) {
                    Intent i = new Intent(getActivity(), AddTeacherActivity.class);
                    startActivity(i);
                }
                if (newText.contains("realmpath")) {
                    Log.d("REALM", "path: " + realm.getPath());
                }
                return false;
            }
        });
        return rootView;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setUpRecyclerView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_search);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SearchRecyclerViewAdapter(this, realm.where(Teacher.class).findAll());
        mRecyclerView.setAdapter(mAdapter);
    }
}
