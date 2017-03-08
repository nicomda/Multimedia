package org.danico.whoru;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nicomda on 1/3/17.
 */

public class MatchFaceRecyclerViewAdapter extends RecyclerView.Adapter<MatchFaceRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView cardText1;
        public ImageView cardImage;
        public ImageView cardShare;

        public ViewHolder(View v) {
            super(v);
            cardText1=(TextView) v.findViewById(R.id.profile_card_text1);
            cardImage=(ImageView) v.findViewById(R.id.profile_card_img);
            cardShare=(ImageView) v.findViewById(R.id.profile_card_share);
        }
    }

    public MatchFaceRecyclerViewAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MatchFaceRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.match_face_cardview_social,parent, false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.cardText1.setText(String.valueOf(position));
        holder.cardText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,mDataset.get(position),Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
