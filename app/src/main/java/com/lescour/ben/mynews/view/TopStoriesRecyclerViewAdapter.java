package com.lescour.ben.mynews.view;

import com.bumptech.glide.RequestManager;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.BaseFragment;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.model.Multimedium;

import java.util.List;

public class TopStoriesRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private String imgUrl;

    public TopStoriesRecyclerViewAdapter(List<Article> articles, BaseFragment.OnListFragmentInteractionListener listener, RequestManager glide) {
        this.articles = articles;
        mListener = listener;
        this.glide = glide;
    }

    @Override
    protected void updateWithArticle(Article article, RequestManager glide, ViewHolder holder) {
        if (holder.article.getMultimedia().isEmpty()) {
            holder.articleImg.setImageResource(R.drawable.icon_75x75);
        } else {
            findUrlOfImgArticle(holder.article);
            glide.load(imgUrl).into(holder.articleImg);
        }
        holder.articleSectionSubsection.setText(getSectionAndSubsection(holder.article));
        holder.articleTitle.setText(holder.article.getTitle());
    }

    private void findUrlOfImgArticle(Article article) {
        boolean imgFound = false;
        int i = 0;
        do {
            Multimedium temporaryMultimedium = article.getMultimedia().get(i);
            if (temporaryMultimedium.getFormat().equals("Standard Thumbnail")) {
                imgUrl = article.getMultimedia().get(i).getUrl();
                imgFound = true;
            }
            i++;
        } while (!imgFound);
    }

    private String getSectionAndSubsection(Article article) {
        String str;
        if (article.getSubsection().isEmpty()) {
            str = article.getSection();
        } else {
            str = article.getSection() + " > " + article.getSubsection();
        }
        return str;
    }

    @Override
    protected String getRawDate(Article article) {
        return article.getPublishedDate();
    }
}
