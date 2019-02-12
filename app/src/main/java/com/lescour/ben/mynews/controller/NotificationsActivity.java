package com.lescour.ben.mynews.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.utils.AlarmReceiver;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by benja on 11/02/2019.
 */
public class NotificationsActivity extends BaseCustomSearchAndCategories {

    @BindView(R.id.notifications_switch) Switch notificationsSwitch;
    private PendingIntent pendingIntent;

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

        notificationsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                        configureAlarmManager();
                } else {
                    stopAlarm();
                }
            }
        });
    }

    private void configureAlarmManager() {
        String query = editText.getText().toString();
        this.buildCompactCategoriesBuilder();
        if (!query.equals("") && compactCategoriesBuilder.length() > 0) {
            Intent alarmIntent = new Intent(NotificationsActivity.this, AlarmReceiver.class);
            alarmIntent.putExtra(BUNDLE_EXTRA_QUERY, query);
            filter_query = "news_desk:(" + compactCategoriesBuilder + ")";
            alarmIntent.putExtra(BUNDLE_EXTRA_FILTER_QUERY, filter_query);
            sendBroadcast(alarmIntent);
            pendingIntent = PendingIntent.getBroadcast(NotificationsActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            startAlarm();
        }
        else if (query.equals("") &&  compactCategoriesBuilder.length() > 0) {
            Toast.makeText(this, "Please enter a query.", Toast.LENGTH_LONG).show();
            notificationsSwitch.setChecked(false);
        }
        else if (!query.equals("") && compactCategoriesBuilder.length() == 0) {
            Toast.makeText(this, "Please choose at least one category.", Toast.LENGTH_LONG).show();
            notificationsSwitch.setChecked(false);
        } else {
            Toast.makeText(this, "Please enter a query and choose at least one category.", Toast.LENGTH_LONG).show();
            notificationsSwitch.setChecked(false);
        }
    }

    private void startAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 30);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Alarm set !", Toast.LENGTH_SHORT).show();
    }

    private void stopAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled !", Toast.LENGTH_SHORT).show();
    }
}
