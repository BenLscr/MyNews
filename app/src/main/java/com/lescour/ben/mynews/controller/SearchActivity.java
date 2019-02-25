package com.lescour.ben.mynews.controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.model.UrlSplit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by benja on 04/02/2019.
 */
public class SearchActivity extends BaseCustomSearchAndCategories {

    @BindView(R.id.select_begin_date) TextView selectBeginDate;
    @BindView(R.id.select_end_date) TextView selectEndDate;
    @BindView(R.id.launch_search_button) Button launchSearchButton;
    private DatePickerDialog.OnDateSetListener beginDateSetListener, endDateSetListener;
    private Calendar calendar;
    private SimpleDateFormat visualFormat, urlFormat;
    private int year, month, day;
    private DatePickerDialog datePickerDialog;
    private String beginDateToShow, endDateToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        this.configureToolbar();
        mUrlSplit = new UrlSplit();
        this.initCalendar();

        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                launchPersonaliseSearch();
                return true;
            }
            return false;
        });

        beginDateSetListener = (view, year, month, day) -> {
            calendar.set(year, month, day);
            beginDateToShow = visualFormat.format(calendar.getTime());
            checkIfBeginDateIsCorrect(beginDateToShow);
        };

        endDateSetListener = (view, year, month, day) -> {
            calendar.set(year, month, day);
            endDateToShow = visualFormat.format(calendar.getTime());
            checkIfEndDateIsCorrect(endDateToShow);
        };
    }

    private void initCalendar() {
        calendar = Calendar.getInstance();
        urlFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        visualFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        visualFormat.setCalendar(calendar);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

//////////    BeginDate   //////////

    @OnClick(R.id.select_begin_date)
    public void beginDate() {
        initBeginDatePickerDialog();
    }

    private void initBeginDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(
                SearchActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                beginDateSetListener,
                year, month, day);
        Objects.requireNonNull(datePickerDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void checkIfBeginDateIsCorrect(String beginDateToShow){
        if (endDateToShow != null) {
            try {
                Date beginDate = visualFormat.parse(beginDateToShow);
                Date endDate = visualFormat.parse(endDateToShow);
                if (beginDate.before(endDate) || beginDate.equals(endDate)) {
                    showAndSaveBeginDateInUrlFormat(beginDateToShow);
                } else {
                    Toast.makeText(this, "Begin date can't be after end date.", Toast.LENGTH_LONG).show();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            showAndSaveBeginDateInUrlFormat(beginDateToShow);
        }
    }

    private void showAndSaveBeginDateInUrlFormat(String beginDateToShow) {
        selectBeginDate.setText(beginDateToShow);
        urlFormat.setCalendar(calendar);
        mUrlSplit.setBeginDate(urlFormat.format(calendar.getTime()));
    }

//////////    EndDate   //////////

    @OnClick(R.id.select_end_date)
    public void endDate() {
        initEndDatePickerDialog();
    }

    private void initEndDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(
                SearchActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                endDateSetListener,
                year, month, day);
        Objects.requireNonNull(datePickerDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void checkIfEndDateIsCorrect(String endDateToShow) {
        if (beginDateToShow != null) {
            try {
                Date beginDate = visualFormat.parse(beginDateToShow);
                Date endDate = visualFormat.parse(endDateToShow);
                if (beginDate.before(endDate) || beginDate.equals(endDate)) {
                    showAndSaveEndDateInUrlFormat(endDateToShow);
                } else {
                    Toast.makeText(this, "End date can't be before begin date.", Toast.LENGTH_LONG).show();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            showAndSaveEndDateInUrlFormat(endDateToShow);
        }
    }

    private void showAndSaveEndDateInUrlFormat(String endDateToShow) {
        selectEndDate.setText(endDateToShow);
        urlFormat.setCalendar(calendar);
        mUrlSplit.setEndDate(urlFormat.format(calendar.getTime()));
    }

//////////    Search   //////////

    @OnClick(R.id.launch_search_button)
    public void launchPersonaliseSearch() {
        mUrlSplit.setQuery(editText.getText().toString());
        compactCategoriesBuilder = buildCompactCategoriesBuilder(arts, business, entrepreneurs, politics, sports, travel);
        if (!mUrlSplit.getQuery().equals("") && !compactCategoriesBuilder.equals("")) {
            mUrlSplit.setFilter_query("news_desk:(" + compactCategoriesBuilder + ")");
            mUrlSplit.setSort("newest");
            Intent customActivity = new Intent(this, CustomActivity.class);
            customActivity.putExtra("SearchToCustom", mUrlSplit);
            this.startActivity(customActivity);
        }
        else if (mUrlSplit.getQuery().equals("") && !compactCategoriesBuilder.equals("")) {
            Toast.makeText(this, "Please enter a query.", Toast.LENGTH_LONG).show();
        }
        else if (!mUrlSplit.getQuery().equals("") && compactCategoriesBuilder.equals("")) {
            Toast.makeText(this, "Please choose at least one category.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please enter a query and choose at least one category.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void checkPreviousParam() {

    }
}