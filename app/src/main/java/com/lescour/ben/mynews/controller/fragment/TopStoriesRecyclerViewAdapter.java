package com.lescour.ben.mynews.controller.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.TopStoriesFragment.OnListFragmentInteractionListener;
import com.lescour.ben.mynews.model.Multimedium;
import com.lescour.ben.mynews.model.Result;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TopStoriesRecyclerViewAdapter extends RecyclerView.Adapter<TopStoriesRecyclerViewAdapter.ViewHolder> {

    private final List<Result> resultsList;
    private List<Multimedium> multimediumsList;
    private final OnListFragmentInteractionListener mListener;
    private RequestManager glide;

    public TopStoriesRecyclerViewAdapter(List<Result> resultsList, OnListFragmentInteractionListener listener, RequestManager glide) {
        this.resultsList = resultsList;
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
        holder.result = resultsList.get(position);
        holder.articleImg.setImageResource(R.drawable.ic_launcher_background);
        holder.showArticleImg(holder.result, this.glide);
        if (holder.result.getMultimedia().isEmpty()) {
            Log.e("TAG","La list est vide");
        }
        holder.articleSectionSubsection.setText(getSectionAndSubsection(holder.result));
        holder.articleDate.setText(getDateWhitNewFormat(holder.result));
        holder.articleTitle.setText(holder.result.getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.result);
                }
            }
        });
    }

    private String getSectionAndSubsection(Result result) {
        return result.getSection() + " > " + result.getSubsection();
    }

    private String getDateWhitNewFormat(Result result) {
        String rawDate = result.getPublishedDate();
        String year = rawDate.substring(0,4);
        String month = rawDate.substring(5,7);
        String day = rawDate.substring(8,10);
        return day + "/" + month + "/" + year;
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.img_article) ImageView articleImg;
        @BindView(R.id.article_sectionSubsection) TextView articleSectionSubsection;
        @BindView(R.id.article_date) TextView articleDate;
        @BindView(R.id.article_title) TextView articleTitle;
        public Result result;
        public Multimedium multimedium;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        public void showArticleImg(Result result, RequestManager glide) {
            //glide.load(result.getMultimedia()multimedium.getUrl()).into(articleImg);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + articleDate.getText() + "'";
        }
    }
}
