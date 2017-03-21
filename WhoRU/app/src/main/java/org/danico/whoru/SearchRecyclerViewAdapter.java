package org.danico.whoru;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.RealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

/**
 * Created by nicomda on 1/3/17.
 */

public class SearchRecyclerViewAdapter extends RealmRecyclerViewAdapter<Teacher, SearchRecyclerViewAdapter.ViewHolder> {

    private final SearchFragment fragment;

    public SearchRecyclerViewAdapter(SearchFragment fragment, RealmResults<Teacher> data) {
        super(data, true);
        this.fragment = fragment;
    }

    @Override
    public SearchRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_teacher,parent, false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Teacher teacher = getItem(position);
        holder.cardText.setText(teacher.getName().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fragment.getContext(), teacher.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView cardText;
        public ImageView cardImage;

        public ViewHolder(View v) {
            super(v);
            cardText = (TextView) v.findViewById(R.id.cardview_search_name);
            cardImage = (ImageView) v.findViewById(R.id.cardView_search_image);
        }
    }
}
