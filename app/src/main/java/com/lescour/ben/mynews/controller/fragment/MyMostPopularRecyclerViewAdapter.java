package com.lescour.ben.mynews.controller.fragment;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.MostPopularFragment.OnListFragmentInteractionListener;
import com.lescour.ben.mynews.controller.fragment.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMostPopularRecyclerViewAdapter extends RecyclerView.Adapter<MyMostPopularRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyMostPopularRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
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
        holder.mItem = mValues.get(position);
        holder.articleImg.setImageResource(R.drawable.ic_launcher_background);
        holder.articleGeoTag.setText("Disons politique");
        holder.articleDate.setText("08/01/2019");
        holder.articleDescription.setText("Une très longue description");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView articleImg;
        public final TextView articleGeoTag;
        public final TextView articleDate;
        public final TextView articleDescription;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            articleImg = (ImageView) view.findViewById(R.id.img_article);
            articleGeoTag = (TextView) view.findViewById(R.id.article_geotag);
            articleDate = (TextView) view.findViewById(R.id.article_date);
            articleDescription = (TextView) view.findViewById(R.id.article_description);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + articleDate.getText() + "'";
        }
    }
}
