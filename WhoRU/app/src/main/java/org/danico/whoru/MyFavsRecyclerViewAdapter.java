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

public class MyFavsRecyclerViewAdapter extends RecyclerView.Adapter<MyFavsRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView cardText;
        public ImageView cardImage;

        public ViewHolder(View v) {
            super(v);
            cardText=(TextView) v.findViewById(R.id.cardview_search_name);
            cardImage=(ImageView) v.findViewById(R.id.cardView_search_image);
        }
    }

    public MyFavsRecyclerViewAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyFavsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_teacher,parent, false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.cardText.setText(mDataset.get(position));
        holder.cardText.setOnClickListener(new View.OnClickListener() {
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
