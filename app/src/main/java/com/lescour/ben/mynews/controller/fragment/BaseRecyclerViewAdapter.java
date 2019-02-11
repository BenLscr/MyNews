package com.lescour.ben.mynews.controller.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.view.ViewHolder;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by benja on 11/02/2019.
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    protected List<Article> articles;
    protected BaseFragment.OnListFragmentInteractionListener mListener;
    protected RequestManager glide;

    protected abstract void updateWithArticle(Article article, RequestManager glide, ViewHolder holder);
    protected abstract String getRawDate(Article article);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.article = articles.get(position);
        String rawDate = getRawDate(holder.article);
        holder.articleDate.setText(getDateWhitNewFormat(rawDate));
        this.updateWithArticle(holder.article, this.glide, holder);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected
                    mListener.onListFragmentInteraction(holder.article);
                }
            }
        });
    }

    private String getDateWhitNewFormat(String rawDate) {
        String year = rawDate.substring(0,4);
        String month = rawDate.substring(5,7);
        String day = rawDate.substring(8,10);
        return day + "/" + month + "/" + year;
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
