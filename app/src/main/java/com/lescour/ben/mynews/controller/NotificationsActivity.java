package com.lescour.ben.mynews.controller;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lescour.ben.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by benja on 11/02/2019.
 */
public class NotificationsActivity extends BaseCustomSearchAndCategories {

    @BindView(R.id.search_query_term) EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);
        ButterKnife.bind(this);
        this.configureToolbar();

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    return true;
                }
                return false;
            }
        });
    }

}
