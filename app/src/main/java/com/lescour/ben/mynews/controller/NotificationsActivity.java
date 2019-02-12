package com.lescour.ben.mynews.controller;

import android.os.Bundle;

import com.lescour.ben.mynews.R;

import butterknife.ButterKnife;

/**
 * Created by benja on 11/02/2019.
 */
public class NotificationsActivity extends BaseCustomSearchAndCategories {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);
        ButterKnife.bind(this);
        this.configureToolbar();
    }

}
