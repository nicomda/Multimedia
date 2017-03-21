package org.danico.whoru.API;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kairos.Kairos;
import com.kairos.KairosListener;

import org.danico.whoru.R;
import org.danico.whoru.SearchRecyclerViewAdapter;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by nicomda on 21/3/17.
 */

public class APIListAdapter extends RecyclerView.Adapter<APIListAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    KairosListener kListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.cardview_api_list_name);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public APIListAdapter(KairosListener kListener, ArrayList<String> myDataset) {
        mDataset = myDataset;
        this.kListener = kListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View rootView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_api_list, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(rootView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final View view = holder.itemView.getRootView();
        holder.mTextView.setText(mDataset.get(position));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Kairos myKairos = new Kairos();
                myKairos.setAuthentication(v.getContext(), v.getContext().getString(R.string.kairos_app_id), v.getContext().getString(R.string.kairos_api_key));
                try {

                    myKairos.deleteSubject(mDataset.get(position), v.getContext().getString(R.string.kairos_gallery_id), kListener);
                    mDataset.remove(position);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
