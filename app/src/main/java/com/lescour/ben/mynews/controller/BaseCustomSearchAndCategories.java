package com.lescour.ben.mynews.controller;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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
    @BindView(R.id.search_query_term) EditText editText;
    @BindView(R.id.checkbox_arts) CheckBox checkBoxArts;
    @BindView(R.id.checkbox_business) CheckBox checkBoxBusiness;
    @BindView(R.id.checkbox_entrepreneurs) CheckBox checkBoxEntrepreneurs;
    @BindView(R.id.checkbox_politics) CheckBox checkBoxPolitics;
    @BindView(R.id.checkbox_sports) CheckBox checkBoxSports;
    @BindView(R.id.checkbox_travel) CheckBox checkBoxTravel;
    protected String arts, business, entrepreneurs, politics, sports, travel;
    protected StringBuilder compactCategoriesBuilder;
    protected String filter_query;
    public static final String BUNDLE_EXTRA_QUERY = "BUNDLE_EXTRA_QUERY";
    public static final String BUNDLE_EXTRA_FILTER_QUERY = "BUNDLE_EXTRA_FILTER_QUERY";

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

//////////    CheckBox   //////////

    protected void checkCheckBox(View checkBox) {
        switch (checkBox.getId()){
            case R.id.checkbox_arts :
                if (checkBoxArts.isChecked()) {
                    arts = "\"arts\"";
                } else {
                    arts = null;
                }
                break;
            case R.id.checkbox_business :
                if (checkBoxBusiness.isChecked()) {
                    business = "\"business\"";
                } else {
                    business = null;
                }
                break;
            case R.id.checkbox_entrepreneurs :
                if (checkBoxEntrepreneurs.isChecked()) {
                    entrepreneurs = "\"entrepreneurs\"";
                } else {
                    entrepreneurs = null;
                }
                break;
            case R.id.checkbox_politics :
                if (checkBoxPolitics.isChecked()) {
                    politics = "\"politics\"";
                } else {
                    politics = null;
                }
                break;
            case R.id.checkbox_sports :
                if (checkBoxSports.isChecked()) {
                    sports = "\"sports\"";
                } else {
                    sports = null;
                }
                break;
            case R.id.checkbox_travel :
                if (checkBoxTravel.isChecked()) {
                    travel = "\"travel\"";
                } else {
                    travel = null;
                }
                break;
        }
    }

    protected void buildCompactCategoriesBuilder() {
        compactCategoriesBuilder = new StringBuilder();
        if (arts != null || business != null || entrepreneurs != null || politics != null || sports != null || travel != null) {
            if (arts != null) {
                compactCategoriesBuilder.append(arts);
            }
            if (business != null) {
                compactCategoriesBuilder.append(business);
            }
            if (entrepreneurs != null) {
                compactCategoriesBuilder.append(entrepreneurs);
            }
            if (politics != null) {
                compactCategoriesBuilder.append(politics);
            }
            if (sports != null) {
                compactCategoriesBuilder.append(sports);
            }
            if (travel != null) {
                compactCategoriesBuilder.append(travel);
            }
        }
    }
}
