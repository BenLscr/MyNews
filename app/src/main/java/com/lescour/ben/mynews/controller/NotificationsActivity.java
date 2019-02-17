package com.lescour.ben.mynews.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.model.UrlSplit;
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
    private SharedPreferences mSharedPreferences;
    private String myPersonalisedNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.setMyPersonalisedNotification();

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
                if (isChecked) {
                    configureAlarmManager();
                } else {
                    stopAlarm();
                    deleteNotificationsSave();
                }
            }
        });
    }

    private void setMyPersonalisedNotification() {
        mSharedPreferences = getSharedPreferences("myPersonalisedNotification", MODE_PRIVATE);
        myPersonalisedNotification = mSharedPreferences.getString("KEY_SAVE_NOTIFICATION", "{'apiKey':'4cKaGJtqJJDtrVx14QNFiGbfQI6tqEP6'}");
        Gson gson = new Gson();
        mUrlSplit = gson.fromJson(myPersonalisedNotification, UrlSplit.class);
        Log.e("checkSave", myPersonalisedNotification);

        if (mUrlSplit.getQuery() != null && mUrlSplit.getFilter_query() != null) {
            editText.setText(mUrlSplit.getQuery());
            if (mUrlSplit.getFilter_query().contains("arts")) {
                checkBoxArts.setChecked(true);
                arts = "\"arts\"";
            }
            if (mUrlSplit.getFilter_query().contains("business")) {
                checkBoxBusiness.setChecked(true);
                business = "\"business\"";
            }
            if (mUrlSplit.getFilter_query().contains("entrepreneurs")) {
                checkBoxEntrepreneurs.setChecked(true);
                entrepreneurs = "\"entrepreneurs\"";
            }
            if (mUrlSplit.getFilter_query().contains("politics")) {
                checkBoxPolitics.setChecked(true);
                politics = "\"politics\"";
            }
            if (mUrlSplit.getFilter_query().contains("sports")) {
                checkBoxSports.setChecked(true);
                sports = "\"sports\"";
            }
            if (mUrlSplit.getFilter_query().contains("travel")) {
                checkBoxTravel.setChecked(true);
                travel = "\"travel\"";
            }
            notificationsSwitch.setChecked(true);
        }

        if (mUrlSplit.getFilter_query() != null) {

        }
    }

    private void configureAlarmManager() {
        mUrlSplit.setQuery(editText.getText().toString());
        compactCategoriesBuilder = buildCompactCategoriesBuilder(arts, business, entrepreneurs, politics, sports, travel);
        if (!mUrlSplit.getQuery().equals("") && !compactCategoriesBuilder.equals("")) {
            Intent alarmIntent = new Intent(NotificationsActivity.this, AlarmReceiver.class);
            mUrlSplit.setFilter_query("news_desk:(" + compactCategoriesBuilder + ")");
            alarmIntent.putExtra("NotificationsToAlarm", mUrlSplit);
            sendBroadcast(alarmIntent);
            pendingIntent = PendingIntent.getBroadcast(NotificationsActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            this.saveMyPersonalisedNotification();
            startAlarm();
        }
        else if (mUrlSplit.getQuery().equals("") && !compactCategoriesBuilder.equals("")) {
            Toast.makeText(this, "Please enter a query.", Toast.LENGTH_LONG).show();
            notificationsSwitch.setChecked(false);
        }
        else if (!mUrlSplit.getQuery().equals("") && compactCategoriesBuilder.equals("")) {
            Toast.makeText(this, "Please choose at least one category.", Toast.LENGTH_LONG).show();
            notificationsSwitch.setChecked(false);
        } else {
            Toast.makeText(this, "Please enter a query and choose at least one category.", Toast.LENGTH_LONG).show();
            notificationsSwitch.setChecked(false);
        }
    }

    private void saveMyPersonalisedNotification() {
        Gson gson = new Gson();
        myPersonalisedNotification = gson.toJson(mUrlSplit);
        Log.e("gson", myPersonalisedNotification);
        mSharedPreferences = getSharedPreferences("myPersonalisedNotification", MODE_PRIVATE);
        mSharedPreferences.edit().putString("KEY_SAVE_NOTIFICATION", myPersonalisedNotification).apply();
    }

    private void startAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 30);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,0, 10000, pendingIntent);
        Toast.makeText(this, "Alarm set !", Toast.LENGTH_SHORT).show();
    }

    private void stopAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (pendingIntent == null) {
            Intent alarmIntent = new Intent(NotificationsActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(NotificationsActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled !", Toast.LENGTH_SHORT).show();
    }

    private void deleteNotificationsSave() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove("KEY_SAVE_NOTIFICATION");
        editor.apply();
    }
}
