package org.danico.whoru;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

/**
 * Created by nicomda on 1/3/17.
 */

public class MatchFaceRecyclerViewAdapter extends RealmRecyclerViewAdapter<Teacher, MatchFaceRecyclerViewAdapter.ViewHolder> {

    private final MatchFaceActivity activity;


    public MatchFaceRecyclerViewAdapter(MatchFaceActivity activity, RealmResults<Teacher> data) {
        super(data, true);
        this.activity = activity;
    }

    @Override
    public MatchFaceRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.match_face_cardview_social,parent, false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Teacher obj = getData().get(position);
        holder.cardText1.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView cardText1;
        public ImageView cardImage;
        public ImageView cardShare;

        public ViewHolder(View v) {
            super(v);
            cardText1 = (TextView) v.findViewById(R.id.profile_card_text1);
            cardImage = (ImageView) v.findViewById(R.id.profile_card_img);
            cardShare = (ImageView) v.findViewById(R.id.profile_card_share);
        }
    }
}
