package com.lescour.ben.mynews.view;

import com.bumptech.glide.RequestManager;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.BaseFragment;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.model.MediaMetadatum;

import java.util.List;

public class MostPopularRecyclerViewAdapter extends BaseRecyclerViewAdapter{

    private String imgUrl;

    public MostPopularRecyclerViewAdapter(List<Article> articles, BaseFragment.OnListFragmentInteractionListener listener, RequestManager glide) {
        this.articles = articles;
        mListener = listener;
        this.glide = glide;
    }

    @Override
    protected void updateWithArticle(Article article, RequestManager glide, ViewHolder holder) {
        if (holder.article.getMedia().get(0).getMediaMetadata().isEmpty()) {
            holder.articleImg.setImageResource(R.drawable.icone_75x75);
        } else {
            findUrlOfImgArticle(holder.article);
            glide.load(imgUrl).into(holder.articleImg);
        }
        holder.articleSectionSubsection.setText(holder.article.getSection());
        holder.articleTitle.setText(holder.article.getTitle());
    }

    private void findUrlOfImgArticle(Article article) {
        boolean imgFound = false;
        int i = 0;
        do {
            MediaMetadatum temporaryMediaMetadatum = article.getMedia().get(0).getMediaMetadata().get(i);
            if (temporaryMediaMetadatum.getFormat().equals("Standard Thumbnail")) {
                imgUrl = article.getMedia().get(0).getMediaMetadata().get(i).getUrl();
                imgFound = true;
            }
            i++;
        } while (!imgFound);
    }

    @Override
    protected String getRawDate(Article article) {
        return article.getPublishedDate();
    }
}
