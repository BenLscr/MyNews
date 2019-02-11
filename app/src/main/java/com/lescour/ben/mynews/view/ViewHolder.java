package com.lescour.ben.mynews.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.model.Article;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by benja on 10/02/2019.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public @BindView(R.id.img_article) ImageView articleImg;
    public @BindView(R.id.article_sectionSubsection) TextView articleSectionSubsection;
    public @BindView(R.id.article_date) TextView articleDate;
    public @BindView(R.id.article_title) TextView articleTitle;
    public Article article;

    public ViewHolder(View view) {
        super(view);
        mView = view;
        ButterKnife.bind(this, view);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + articleDate.getText() + "'";
    }
}
