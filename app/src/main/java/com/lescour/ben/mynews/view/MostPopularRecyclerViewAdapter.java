package com.lescour.ben.mynews.view;

import com.bumptech.glide.RequestManager;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.model.MediaMetadatum;

import java.util.List;

public class MostPopularRecyclerViewAdapter extends BaseRecyclerViewAdapter{

    /**
     * Default constructor.
     */
    public MostPopularRecyclerViewAdapter(List<Article> articles, RequestManager glide) {
        this.articles = articles;
        this.glide = glide;
    }

    @Override
    protected void updateWithArticle(Article article, RequestManager glide, ViewHolder holder) {
        if (article.getMedia().isEmpty()) {
            holder.articleImg.setImageResource(R.drawable.icon_75x75);
        } else {
            String imgUrl = findUrlOfImgArticle(article);
            glide.load(imgUrl).into(holder.articleImg);
        }
        holder.articleSectionSubsection.setText(article.getSection());
        holder.articleTitle.setText(article.getTitle());
    }

    private String findUrlOfImgArticle(Article article) {
        boolean imgFound = false;
        String imgUrl = "";
        int i = 0;
        do {
            MediaMetadatum temporaryMediaMetadatum = article.getMedia().get(0).getMediaMetadata().get(i);
            if (temporaryMediaMetadatum.getFormat().equals("Standard Thumbnail")) {
                imgUrl = article.getMedia().get(0).getMediaMetadata().get(i).getUrl();
                imgFound = true;
            }
            i++;
        } while (!imgFound);
        return imgUrl;
    }

    @Override
    protected String getRawDate(Article article) {
        return article.getPublishedDate();
    }
}
