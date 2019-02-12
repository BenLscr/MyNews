package com.lescour.ben.mynews.controller;

import com.lescour.ben.mynews.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

/**
 * Created by benja on 12/02/2019.
 */
public abstract class BaseCustomSearchAndCategories extends AppCompatActivity {

    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;

//////////    Toolbar   //////////
    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
