package com.lescour.ben.mynews.view;

import com.bumptech.glide.RequestManager;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.model.Multimedium;

import java.util.List;

public class ArticleSearchRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private String imgUrl;

    /**
     * Default constructor.
     */
    public ArticleSearchRecyclerViewAdapter(List<Article> articles, RequestManager glide) {
        this.articles = articles;
        this.glide = glide;
    }

    @Override
    protected void updateWithArticle(Article article, RequestManager glide, ViewHolder holder) {
        if (article.getMultimedia().isEmpty()) {
            holder.articleImg.setImageResource(R.drawable.icon_75x75);
        } else {
            findUrlOfImgArticle(article);
            glide.load(imgUrl).into(holder.articleImg);
        }
        holder.articleSectionSubsection.setText(getSectionAndSubsection(article));
        holder.articleTitle.setText(article.getHeadline().getMain());
    }

    private void findUrlOfImgArticle(Article article) {
        boolean imgFound = false;
        int i = 0;
        do {
            Multimedium temporaryMultimedium = article.getMultimedia().get(i);
            if (temporaryMultimedium.getSubType().equals("thumbnail") || temporaryMultimedium.getSubType().equals("articleInline")) {
                imgUrl = "https://static01.nyt.com/" + article.getMultimedia().get(i).getUrl();
                imgFound = true;
            }
            i++;
        } while (!imgFound);
    }

    /**
     * Set section name of article. Is add subsection name if isn't null.
     * @param article The current article.
     * @return String with section (and subsection name if non-null)
     */
    public String getSectionAndSubsection(Article article) {
        String str;
        if (article.getSubsectoinName() == null) {
            str = article.getSectionName();
        } else {
            str = article.getSectionName() + " > " + article.getSubsectoinName();
        }
        return str;
    }

    @Override
    protected String getRawDate(Article article) {
        return article.getPubDate();
    }
}
