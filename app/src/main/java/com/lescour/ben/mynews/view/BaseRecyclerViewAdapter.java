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
    BaseFragment.OnListFragmentInteractionListener mListener;
    protected RequestManager glide;

    protected abstract void updateWithArticle(Article article, RequestManager glide, ViewHolder holder);
    protected abstract String getRawDate(Article article);

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent
     * an item. This new ViewHolder will be used to display items of the adapter. Since it will be re-used
     * to display different items in the data set.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_article, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method
     * update the contents of the ViewHolder to reflect the item at the given position.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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

    /**
     * Transform the date from api to a better visual date.
     * @param rawDate The date get by the API.
     * @return The date in a new format
     */
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

    /**
     * Returns the total number of articles in the data set held by the adapter.
     * @return The total number of articles in this adapter.
     */
    @Override
    public int getItemCount() {
        return articles.size();
    }
}
