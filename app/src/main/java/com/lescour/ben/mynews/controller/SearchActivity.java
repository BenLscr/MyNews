package com.lescour.ben.mynews.controller;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

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
    @BindView(R.id.select_begin_date) TextView selectBeginDate;
    @BindView(R.id.select_end_date) TextView selectEndDate;
    private DatePickerDialog.OnDateSetListener beginDateSetListener;
    private DatePickerDialog.OnDateSetListener endDateSetListener;
    private Calendar calendar;
    private SimpleDateFormat visualFormat;
    private SimpleDateFormat urlFormat;
    private int year;
    private int month;
    private int day;
    private DatePickerDialog datePickerDialog;
    private String beginDateForUrl;
    private String endDateForUrl;

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
}
