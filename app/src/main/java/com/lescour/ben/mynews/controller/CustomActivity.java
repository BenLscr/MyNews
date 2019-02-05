package com.lescour.ben.mynews.controller;

import android.os.Bundle;

import com.lescour.ben.mynews.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by benja on 05/02/2019.
 */
public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_activity);
    }
}
