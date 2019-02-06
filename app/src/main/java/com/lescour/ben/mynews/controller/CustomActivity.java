package com.lescour.ben.mynews.controller;

import android.content.Intent;
import android.os.Bundle;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.BaseFragment;
import com.lescour.ben.mynews.controller.fragment.ArticleSearchFragment;
import com.lescour.ben.mynews.model.Article;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lescour.ben.mynews.controller.MainActivity.BUNDLE_EXTRA_URL;
import static com.lescour.ben.mynews.controller.SearchActivity.BUNDLE_EXTRA_QUERY;

/**
 * Created by benja on 05/02/2019.
 */
public class CustomActivity extends AppCompatActivity implements BaseFragment.OnListFragmentInteractionListener {

    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
    private String query;
    private ArticleSearchFragment articleSearchFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_activity);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.addFragment();

    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        articleSearchFragment = ArticleSearchFragment.newInstance(1);
        fragmentTransaction.add(R.id.custom_list_container, articleSearchFragment);
        fragmentTransaction.commit();

        this.setTheCustomRequest();
    }

    private void setTheCustomRequest(){
        Intent customActivity = getIntent();
        if (customActivity.hasExtra(BUNDLE_EXTRA_QUERY)) {
            query = customActivity.getStringExtra(BUNDLE_EXTRA_QUERY);
            articleSearchFragment.setQuery(query);
        }
    }

    @Override
    public void onListFragmentInteraction(Article article) {
        Intent webViewActivity = new Intent(this, WebViewActivity.class);
        webViewActivity.putExtra(BUNDLE_EXTRA_URL, article.getWebUrl());
        this.startActivity(webViewActivity);
    }
}
