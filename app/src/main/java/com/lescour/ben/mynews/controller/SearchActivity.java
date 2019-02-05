package com.lescour.ben.mynews.controller;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
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

/**
 * Created by benja on 04/02/2019.
 */
public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
    @BindView(R.id.select_begin_date) TextView selectBeginDate;
    private DatePickerDialog.OnDateSetListener beginDateSetListener;
    private Calendar calendar;
    private SimpleDateFormat visualFormat;
    private SimpleDateFormat urlFormat;
    private int year;
    private int month;
    private int day;
    private DatePickerDialog datePickerDialog;
    private String beginDateForUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        this.configureToolbar();

        selectBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBeginCalendar();
                initBeginDatePickerDialog();
            }
        });

        beginDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(year, month, day);
                selectBeginDate.setText(visualFormat.format(calendar.getTime()));
                saveBeginDateInUrlFormat();
            }
        };
    }

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

    private void initBeginCalendar() {
        calendar = Calendar.getInstance();
        urlFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        visualFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        visualFormat.setCalendar(calendar);
        year = calendar.get(calendar.YEAR);
        month = calendar.get(calendar.MONTH);
        day = calendar.get(calendar.DAY_OF_MONTH);
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
}
