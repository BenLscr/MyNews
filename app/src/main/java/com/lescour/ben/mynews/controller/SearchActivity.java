package com.lescour.ben.mynews.controller;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lescour.ben.mynews.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by benja on 04/02/2019.
 */
public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
    @BindView(R.id.search_query_term) EditText editText;
    @BindView(R.id.select_begin_date) TextView selectBeginDate;
    @BindView(R.id.select_end_date) TextView selectEndDate;
    @BindView(R.id.checkbox_arts) CheckBox checkBoxArts;
    @BindView(R.id.checkbox_business) CheckBox checkBoxBusiness;
    @BindView(R.id.checkbox_entrepreneurs) CheckBox checkBoxEntreprenneurs;
    @BindView(R.id.checkbox_politics) CheckBox checkBoxPolitics;
    @BindView(R.id.checkbox_sports) CheckBox checkBoxSports;
    @BindView(R.id.checkbox_travel) CheckBox checkBoxTravel;
    @BindView(R.id.launch_search_button) Button launchSearchButton;
    private DatePickerDialog.OnDateSetListener beginDateSetListener, endDateSetListener;
    private Calendar calendar;
    private SimpleDateFormat visualFormat, urlFormat;
    private int year, month, day;
    private DatePickerDialog datePickerDialog;
    private String beginDateForUrl, endDateForUrl;
    private String arts, business, entreprenneurs, politics, sports, travel;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.initCalendar();

        beginDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(year, month, day);
                selectBeginDate.setText(visualFormat.format(calendar.getTime()));
                saveBeginDateInUrlFormat();
            }
        };

        endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(year, month, day);
                selectEndDate.setText(visualFormat.format(calendar.getTime()));
                saveEndDateInUrlFormat();
            }
        };
    }

    private void initCalendar() {
        calendar = Calendar.getInstance();
        urlFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        visualFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        visualFormat.setCalendar(calendar);
        year = calendar.get(calendar.YEAR);
        month = calendar.get(calendar.MONTH);
        day = calendar.get(calendar.DAY_OF_MONTH);
    }

//////////    Toolbar   //////////
    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

//////////    BeginDate   //////////

    @OnClick(R.id.select_begin_date)
    public void beginDate() {
        initCalendar();
        initBeginDatePickerDialog();
    }

    private void initBeginDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(
                SearchActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                beginDateSetListener,
                year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void saveBeginDateInUrlFormat() {
        urlFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        urlFormat.setCalendar(calendar);
        beginDateForUrl = urlFormat.format(calendar.getTime());
    }

//////////    EndDate   //////////

    @OnClick(R.id.select_end_date)
    public void endDate() {
        initCalendar();
        initEndDatePickerDialog();
    }

    private void initEndDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(
                SearchActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                endDateSetListener,
                year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void saveEndDateInUrlFormat() {
        urlFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        urlFormat.setCalendar(calendar);
        endDateForUrl = urlFormat.format(calendar.getTime());
    }

//////////    CheckBox   //////////

    public void checkCheckBox(View checkBox) {
        int id = checkBox.getId();
        switch (id){
            case R.id.checkbox_arts :
                if (checkBoxArts.isChecked()) {
                    arts = "arts";
                } else {
                    arts = null;
                }
                break;
            case R.id.checkbox_business :
                if (checkBoxBusiness.isChecked()) {
                    business = "business";
                } else {
                    business = null;
                }
                break;
            case R.id.checkbox_entrepreneurs :
                if (checkBoxEntreprenneurs.isChecked()) {
                    entreprenneurs = "entreprenneurs";
                } else {
                    entreprenneurs = null;
                }
                break;
            case R.id.checkbox_politics :
                if (checkBoxPolitics.isChecked()) {
                    politics = "politics";
                } else {
                    politics = null;
                }
                break;
            case R.id.checkbox_sports :
                if (checkBoxSports.isChecked()) {
                    sports = "sports";
                } else {
                    sports = null;
                }
                break;
            case R.id.checkbox_travel :
                if (checkBoxTravel.isChecked()) {
                    travel = "travel";
                } else {
                    travel = null;
                }
                break;
        }
    }

//////////    Search   //////////

    @OnClick(R.id.launch_search_button)
    public void launchPersonnaliSearch() {
        query = editText.getText().toString();
    }
}
