package com.lescour.ben.mynews.controller;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.model.UrlSplit;

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
    protected UrlSplit mUrlSplit;
    protected String arts, business, entrepreneurs, politics, sports, travel;
    protected String compactCategoriesBuilder;

    protected abstract void checkPreviousCheckBox(String arts, String business, String entrepreneurs, String politics, String sports, String travel);

//////////    Toolbar   //////////

    protected void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * This method is called whenever the user chooses to navigate Up within your application's
     * activity hierarchy from the action bar.
     * @return True if Up navigation completed successfully and this Activity was finished.
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

//////////    CheckBox   //////////

    /**
     * When a checkbox is checked, set a string to a String variable.
     * When a checkbox isn't checked, set null to a String variable.
     * Call method checkPreviousCheckBox() when a change is operated.
     * @param checkBox The specified checkbox.
     */
    public void checkCheckBox(View checkBox) {
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
        checkPreviousCheckBox(arts, business, entrepreneurs, politics, sports, travel);
    }

    /**
     * A StringBuilder to create a part of the filter_query used in url of API.
     * Add every String variable if their non-null.
     * @param arts Correspond to the String variable arts.
     * @param business Correspond to the String variable arts business.
     * @param entrepreneurs Correspond to the String variable entrepreneurs.
     * @param politics Correspond to the String variable politics.
     * @param sports Correspond to the String variable sports.
     * @param travel Correspond to the String variable travel.
     * @return The new StringBuilder in String format.
     */
    public String  buildCompactCategoriesBuilder(String arts, String business,
                                                 String entrepreneurs, String politics,
                                                 String sports, String travel) {
        StringBuilder stringBuilder = new StringBuilder();
        if (arts != null || business != null || entrepreneurs != null ||
                politics != null || sports != null || travel != null) {
            if (arts != null) {
                stringBuilder.append(arts);
            }
            if (business != null) {
                stringBuilder.append(business);
            }
            if (entrepreneurs != null) {
                stringBuilder.append(entrepreneurs);
            }
            if (politics != null) {
                stringBuilder.append(politics);
            }
            if (sports != null) {
                stringBuilder.append(sports);
            }
            if (travel != null) {
                stringBuilder.append(travel);
            }
        }
        return stringBuilder.toString();
    }
}
