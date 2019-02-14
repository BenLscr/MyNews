package com.lescour.ben.mynews.controller;

import android.content.Intent;
import android.os.Bundle;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.ArticleSearchFragment;
import com.lescour.ben.mynews.controller.fragment.BaseFragment;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.model.UrlSplit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lescour.ben.mynews.controller.MainActivity.BUNDLE_EXTRA_URL;

/**
 * Created by benja on 05/02/2019.
 */
public class CustomActivity extends AppCompatActivity implements BaseFragment.OnListFragmentInteractionListener {

    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
    private UrlSplit mUrlSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_activity);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.checkIfIntentAnIntentIsAvailable();
        this.addFragment();
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void checkIfIntentAnIntentIsAvailable(){
        Intent intent = getIntent();
        if (intent != null) {
            mUrlSplit = intent.getParcelableExtra("SearchToCustom");
        } else {
            mUrlSplit = new UrlSplit();
        }
    }

    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable("CustomToArticleSearchFragment", mUrlSplit);
        ArticleSearchFragment articleSearchFragment = ArticleSearchFragment.newInstance(1);
        fragmentTransaction.add(R.id.custom_list_container, articleSearchFragment);
        fragmentTransaction.commit();
        articleSearchFragment.setArguments(bundle);

    }

    @Override
    public void onListFragmentInteraction(Article article) {
        Intent webViewActivity = new Intent(this, WebViewActivity.class);
        webViewActivity.putExtra(BUNDLE_EXTRA_URL, article.getWebUrl());
        this.startActivity(webViewActivity);
    }
}
