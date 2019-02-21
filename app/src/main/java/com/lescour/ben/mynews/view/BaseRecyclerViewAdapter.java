package com.lescour.ben.mynews.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.BaseFragment;
import com.lescour.ben.mynews.model.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.article = articles.get(position);
        String rawDate = getRawDate(holder.article);
        holder.articleDate.setText(getDateWhitNewFormat(rawDate));
        this.updateWithArticle(holder.article, this.glide, holder);

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected
                mListener.onListFragmentInteraction(holder.article);
            }
        });
    }

    public String getDateWhitNewFormat(String rawDate) {
        SimpleDateFormat rawFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateWithNewFormat = null;
        try {
            Date date = rawFormat.parse(rawDate);
            dateWithNewFormat = newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateWithNewFormat;
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
