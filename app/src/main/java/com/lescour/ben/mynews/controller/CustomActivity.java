package com.lescour.ben.mynews.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.ArticleSearchFragment;
import com.lescour.ben.mynews.model.UrlSplit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by benja on 05/02/2019.
 */
public class CustomActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
    private UrlSplit mUrlSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_activity);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.checkIfAnIntentIsAvailable();
        setTitle(getCorrectLabel(mUrlSplit));
        this.addFragment();
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void checkIfAnIntentIsAvailable(){
        Intent intent = getIntent();
        if (intent != null) {
            mUrlSplit = intent.getParcelableExtra("SearchToCustom");
        }
    }

    /**
     * Decide what label to set. In priority the query is set if he isn't null.
     * @param mUrlSplit The current UrlSplit. It contains the filter_query and query.
     * @return The label to set.
     */
    public String getCorrectLabel(UrlSplit mUrlSplit) {
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
        ArticleSearchFragment articleSearchFragment = ArticleSearchFragment.newInstance();
        fragmentTransaction.add(R.id.custom_list_container, articleSearchFragment).commit();
        articleSearchFragment.setArguments(bundle);
    }
}
