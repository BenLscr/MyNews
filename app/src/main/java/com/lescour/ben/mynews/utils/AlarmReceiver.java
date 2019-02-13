package com.lescour.ben.mynews.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.MainActivity;
import com.lescour.ben.mynews.controller.NotificationsActivity;
import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.core.app.NotificationCompat;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.lescour.ben.mynews.controller.BaseCustomSearchAndCategories.BUNDLE_EXTRA_FILTER_QUERY;
import static com.lescour.ben.mynews.controller.BaseCustomSearchAndCategories.BUNDLE_EXTRA_QUERY;


/**
 * Created by benja on 12/02/2019.
 */
public class AlarmReceiver extends BroadcastReceiver {

    protected Disposable disposable;
    private String begin_date;
    private String end_date;
    private String query;
    private String filter_query;
    private String apiKey = "4cKaGJtqJJDtrVx14QNFiGbfQI6tqEP6";
    private boolean articles = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.setParams(intent);
        this.executeHttpRequestWithRetrofit();
        if (!articles) {
            this.showNotification(context);
        }
    }

    private void setParams(Intent intent) {
        query = intent.getStringExtra(BUNDLE_EXTRA_QUERY);
        filter_query = intent.getStringExtra(BUNDLE_EXTRA_FILTER_QUERY);
        Calendar calendar = Calendar.getInstance();
        begin_date = setYesterdayToBeginDate(calendar);
    }

    public String setYesterdayToBeginDate(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return format.format(calendar.getTime());
    }

    private void executeHttpRequestWithRetrofit() {
        this.disposable = TheNewYorkTimesStreams.streamFetchArticleSearch(begin_date, end_date, filter_query, query, "newest", apiKey).subscribeWith(new DisposableObserver<TheNewYorkTimesResponse>() {
            @Override
            public void onNext(TheNewYorkTimesResponse theNewYorkTimesResponse) {
                Log.e("TAG", "On Next");
                if (theNewYorkTimesResponse.getResponse().getArticles() != null) {
                    Log.e("BroadcastReceiver", "Articles found");
                    articles = true;
                } else {
                    Log.e("BroadcastReceiver", "No articles found");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
            }
        });
    }

    private void showNotification(Context context) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        Notification notification = new Notification.Builder(context)
                .setContentText("New articles of interest to you have been found.")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(contentIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .build();

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, notification);
    }
}
