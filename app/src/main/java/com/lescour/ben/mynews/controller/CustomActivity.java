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
        setTitle(getCorrectLabel());
        this.addFragment();
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void checkIfIntentAnIntentIsAvailable(){
        Intent intent = getIntent();
        if (intent != null) {
            mUrlSplit = intent.getParcelableExtra("SearchToCustom");
        } else {
            mUrlSplit = new UrlSplit();
        }
    }

    public String getCorrectLabel() {
        if (mUrlSplit.getQuery() != null) {
            return mUrlSplit.getQuery();
        } else {
            switch (mUrlSplit.getFilter_query()) {
                case "news_desk:(\"arts\")":
                    return "Arts";
                case "news_desk:(\"business\")":
                    return "Business";
                case "news_desk:(\"environment\")":
                    return "Environment";
                case "news_desk:(\"food\")":
                    return "Food";
                case "news_desk:(\"health\")":
                    return "Health";
                case "news_desk:(\"movies\")":
                    return "Movies";
                case "news_desk:(\"politics\")":
                    return "Politics";
                case "news_desk:(\"sports\")":
                    return "Sports";
                case "news_desk:(\"technology\")":
                    return "Technology";
                case "news_desk:(\"weather\")":
                    return "Weather";
                default:
                    return "Custom Activity";
            }
        }
    }

    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable("CustomToArticleSearchFragment", mUrlSplit);
        ArticleSearchFragment articleSearchFragment = ArticleSearchFragment.newInstance(1);
        fragmentTransaction.add(R.id.custom_list_container, articleSearchFragment).commit();
        articleSearchFragment.setArguments(bundle);
    }

    @Override
    public void onListFragmentInteraction(Article article) {
        Intent webViewActivity = new Intent(this, WebViewActivity.class);
        webViewActivity.putExtra(BUNDLE_EXTRA_URL, article.getWebUrl());
        this.startActivity(webViewActivity);
    }
}
