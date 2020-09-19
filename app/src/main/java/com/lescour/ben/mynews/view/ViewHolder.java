package com.lescour.ben.mynews.view;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.WebViewActivity;
import com.lescour.ben.mynews.model.Article;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by benja on 10/02/2019.
 */
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    Article article;
    public static final String BUNDLE_EXTRA_URL = "BUNDLE_EXTRA_URL";
    public @BindView(R.id.img_article) ImageView articleImg;
    public @BindView(R.id.article_sectionSubsection) TextView articleSectionSubsection;
    public @BindView(R.id.article_date) TextView articleDate;
    public @BindView(R.id.article_title) TextView articleTitle;

    ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent webViewActivity = new Intent(v.getContext(), WebViewActivity.class);
        if (article.getUrl() != null) {
            webViewActivity.putExtra(BUNDLE_EXTRA_URL, article.getUrl());
        }
        else {
            webViewActivity.putExtra(BUNDLE_EXTRA_URL, article.getWebUrl());
        }
        v.getContext().startActivity(webViewActivity);
    }
}
