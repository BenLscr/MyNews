package com.lescour.ben.mynews.controller;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.adapter.PageAdapter;
import com.lescour.ben.mynews.controller.fragment.MostPopularFragment;
import com.lescour.ben.mynews.controller.fragment.TopStoriesFragment;
import com.lescour.ben.mynews.controller.fragment.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements
        TopStoriesFragment.OnListFragmentInteractionListener,
        MostPopularFragment.OnListFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ViewPager viewPager;

    private Fragment fragmentTopStories;
    private Fragment fragmentMostPopular;


    private static final int FRAGMENT_TOP_STORIES = 0;
    private static final int FRAGMENT_MOST_POPULAR = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolbar();
        this.configureViewPagerAndTabs();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    private void configureToolbar() {
        this.toolbar = findViewById(R.id.activity_main_toolbar);
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
                Toast.makeText(this, "Bouton 'search' non assigné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_notifications:
                Toast.makeText(this, "Bouton 'notifications' non assigné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_help:
                Toast.makeText(this, "Bouton 'help' non assigné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_about:
                Toast.makeText(this, "Bouton 'about' non assigné", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureViewPagerAndTabs(){
        this.viewPager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        // 2 - Set Adapter PageAdapter and glue it together
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()) {
        });
        TabLayout tabs = (TabLayout) findViewById(R.id.activity_main_tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
    }


//////////    MENU    //////////

    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * When a item is selected. Display the associate fragment in the ViewPager.
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.activity_main_drawer_top_stories :
                viewPager.setCurrentItem(0);
                break;
            case R.id.activity_main_drawer_most_popular:
                viewPager.setCurrentItem(1);
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
}
