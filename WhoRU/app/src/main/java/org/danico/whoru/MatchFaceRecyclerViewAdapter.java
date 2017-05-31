package org.danico.whoru;

import android.content.Context;
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

    private ArrayList<TeacherData> data;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView cardText1;
        public ImageView cardImage;
        public ImageView cardShare;

        public ViewHolder(View v) {
            super(v);
            cardText1 = (TextView) v.findViewById(R.id.profile_card_text1);
            cardImage = (ImageView) v.findViewById(R.id.profile_card_img);
        }
    }


    public MatchFaceRecyclerViewAdapter(Context context, ArrayList<TeacherData> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MatchFaceRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.match_face_cardview_social,parent, false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.cardText1.setText(data.get(position).getInfo());
        switch (data.get(position).getType()) {
            case DEPARTMENT:
                holder.cardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.profile_department));
                break;
            case OFFICE:
                holder.cardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.profile_office));
                break;
            case MAIL:
                holder.cardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.profile_card_email));
                break;
            case TWITTER:
                holder.cardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.profile_card_twitter));
                break;
            case WEBSITE:
                holder.cardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.profile_card_website));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
