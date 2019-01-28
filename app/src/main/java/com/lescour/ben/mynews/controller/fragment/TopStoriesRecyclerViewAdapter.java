package com.lescour.ben.mynews.controller.fragment;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.TopStoriesFragment.OnListFragmentInteractionListener;
import com.lescour.ben.mynews.controller.fragment.dummy.DummyContent.DummyItem;
import com.lescour.ben.mynews.model.Result;
import com.lescour.ben.mynews.model.TopStoriesJson;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TopStoriesRecyclerViewAdapter extends RecyclerView.Adapter<TopStoriesRecyclerViewAdapter.ViewHolder> {

    private final List<Result> results;
    private final OnListFragmentInteractionListener mListener;

    public TopStoriesRecyclerViewAdapter(List<Result> results, OnListFragmentInteractionListener listener) {
        this.results = results;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = results.get(position);
        holder.articleImg.setImageResource(R.drawable.ic_launcher_background);
        holder.articleGeoTag.setText("Disons politique");
        holder.articleDate.setText("08/01/2019");
        //holder.articleDescription.setText(holder.mItem.getTitle());
        holder.updateWithTopStoriesJson(this.results.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.img_article) ImageView articleImg;
        @BindView(R.id.article_geotag) TextView articleGeoTag;
        @BindView(R.id.article_date) TextView articleDate;
        @BindView(R.id.article_description) TextView articleDescription;
        public Result mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        public void updateWithTopStoriesJson(Result result){
            this.articleDescription.setText(result.getTitle());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + articleDate.getText() + "'";
        }
    }
}
