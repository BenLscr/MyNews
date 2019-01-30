package com.lescour.ben.mynews.controller.fragment;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.MostPopularFragment.OnListFragmentInteractionListener;
import com.lescour.ben.mynews.controller.fragment.dummy.DummyContent.DummyItem;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.model.Multimedium;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MostPopularRecyclerViewAdapter extends RecyclerView.Adapter<MostPopularRecyclerViewAdapter.ViewHolder> {

    private final List<Article> articles;
    private List<Multimedium> multimediumsList;
    private final OnListFragmentInteractionListener mListener;
    private RequestManager glide;

    public MostPopularRecyclerViewAdapter(List<Article> articles, OnListFragmentInteractionListener listener, RequestManager glide) {
        this.articles = articles;
        mListener = listener;
        this.glide = glide;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.article = articles.get(position);
        holder.articleImg.setImageResource(R.drawable.ic_launcher_background);
        holder.showArticleImg(holder.article, this.glide);
        holder.articleSectionSubsection.setText(holder.article.getSection());
        holder.articleDate.setText(getDateWhitNewFormat(holder.article));
        holder.articleTitle.setText(holder.article.getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.article);
                }
            }
        });
    }

    private String getDateWhitNewFormat(Article article) {
        String rawDate = article.getPublishedDate();
        String year = rawDate.substring(0,4);
        String month = rawDate.substring(5,7);
        String day = rawDate.substring(8,10);
        return day + "/" + month + "/" + year;
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.img_article) ImageView articleImg;
        @BindView(R.id.article_sectionSubsection) TextView articleSectionSubsection;
        @BindView(R.id.article_date) TextView articleDate;
        @BindView(R.id.article_title) TextView articleTitle;
        public Article article;
        public Multimedium multimedium;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        public void showArticleImg(Article article, RequestManager glide) {
            //glide.load(article.getMultimedia()multimedium.getUrl()).into(articleImg);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + articleDate.getText() + "'";
        }
    }
}
