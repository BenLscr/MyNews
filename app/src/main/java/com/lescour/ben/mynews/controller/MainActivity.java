package com.lescour.ben.mynews.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.adapter.PageAdapter;
import com.lescour.ben.mynews.controller.fragment.BaseFragment.OnListFragmentInteractionListener;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.model.UrlSplit;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        OnListFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.activity_main_drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
    @BindView(R.id.activity_main_nav_view) NavigationView navigationView;
    @BindView(R.id.activity_main_viewpager) ViewPager viewPager;
    @BindView(R.id.activity_main_tabs) TabLayout tabs;

    public static final String BUNDLE_EXTRA_URL = "BUNDLE_EXTRA_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.configureViewPagerAndTabs();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_activity_main_search:
                Intent searchActivityIntent = new Intent(MainActivity.this, SearchActivity.class);
                this.startActivity(searchActivityIntent);
                return true;
            case R.id.menu_activity_main_notifications:
                Intent notificationsActivityIntent = new Intent(MainActivity.this, NotificationsActivity.class);
                this.startActivity(notificationsActivityIntent);
                return true;
            case R.id.menu_activity_main_help:
                Intent helpActivityIntent = new Intent(MainActivity.this, HelpActivity.class);
                this.startActivity(helpActivityIntent);
                return true;
            case R.id.menu_activity_main_about:
                Intent aboutActivityIntent = new Intent(MainActivity.this, AboutActivity.class);
                this.startActivity(aboutActivityIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureViewPagerAndTabs(){
        // 2 - Set Adapter PageAdapter and glue it together
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()) {
        });
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onListFragmentInteraction(Article article) {
        Intent webViewActivity = new Intent(this, WebViewActivity.class);
        if (article.getUrl() != null) {
            webViewActivity.putExtra(BUNDLE_EXTRA_URL, article.getUrl());
        }
        else {
            webViewActivity.putExtra(BUNDLE_EXTRA_URL, article.getWebUrl());
        }
        this.startActivity(webViewActivity);
    }

//////////    MENU    //////////

    private void configureDrawerLayout(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void configureNavigationView(){
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * When a item is selected. Display the associate fragment in the ViewPager.
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        UrlSplit mUrlSplit = new UrlSplit();
        int id = item.getItemId();
        switch (id){
            case R.id.activity_main_drawer_top_stories :
                viewPager.setCurrentItem(0);
                break;
            case R.id.activity_main_drawer_most_popular:
                viewPager.setCurrentItem(1);
                break;
            case R.id.activity_main_drawer_science:
                viewPager.setCurrentItem(2);
                break;
            case R.id.activity_main_drawer_arts:
                mUrlSplit.setFilter_query("news_desk:(\"arts\")");
                this.launchCustomActivity(mUrlSplit);
                break;
            case R.id.activity_main_drawer_business:
                mUrlSplit.setFilter_query("news_desk:(\"business\")");
                this.launchCustomActivity(mUrlSplit);
                break;
            case R.id.activity_main_drawer_environment:
                mUrlSplit.setFilter_query("news_desk:(\"environment\")");
                this.launchCustomActivity(mUrlSplit);
                break;
            case R.id.activity_main_drawer_food:
                mUrlSplit.setFilter_query("news_desk:(\"food\")");
                this.launchCustomActivity(mUrlSplit);
                break;
            case R.id.activity_main_drawer_health:
                mUrlSplit.setFilter_query("news_desk:(\"health\")");
                this.launchCustomActivity(mUrlSplit);
                break;
            case R.id.activity_main_drawer_movies:
                mUrlSplit.setFilter_query("news_desk:(\"movies\")");
                this.launchCustomActivity(mUrlSplit);
                break;
            case R.id.activity_main_drawer_politics:
                mUrlSplit.setFilter_query("news_desk:(\"politics\")");
                this.launchCustomActivity(mUrlSplit);
                break;
            case R.id.activity_main_drawer_sports:
                mUrlSplit.setFilter_query("news_desk:(\"sports\")");
                this.launchCustomActivity(mUrlSplit);
                break;
            case R.id.activity_main_drawer_technology:
                mUrlSplit.setFilter_query("news_desk:(\"technology\")");
                this.launchCustomActivity(mUrlSplit);
                break;
            case R.id.activity_main_drawer_weather:
                mUrlSplit.setFilter_query("news_desk:(\"weather\")");
                this.launchCustomActivity(mUrlSplit);
                break;
            default:
                break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Close the NavigationDrawer with the button back
     */
    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void launchCustomActivity(UrlSplit mUrlSplit) {
        mUrlSplit.setSort("newest");
        Intent customActivity = new Intent(this, CustomActivity.class);
        customActivity.putExtra("SearchToCustom", mUrlSplit);
        this.startActivity(customActivity);
    }

}
