package com.lescour.ben.mynews.controller;

import android.os.Bundle;

import com.lescour.ben.mynews.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by benja on 05/02/2019.
 */
public class CustomActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_activity);
        ButterKnife.bind(this);

        this.configureToolbar();
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
