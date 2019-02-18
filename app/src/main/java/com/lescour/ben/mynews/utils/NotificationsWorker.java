package com.lescour.ben.mynews.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.MainActivity;
import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;
import com.lescour.ben.mynews.model.UrlSplit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by benja on 18/02/2019.
 */
public class NotificationsWorker extends Worker {

    private Disposable disposable;
    private UrlSplit mUrlSplit;
    private boolean articles = false;

    public NotificationsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        this.setParams();
        this.executeHttpRequestWithRetrofit();
        /**if (!articles) {
            this.showNotification(getApplicationContext());
            Log.e("Check Worker", "Articles est true");
        }
        Log.e("Check Worker", "Articles est false");*/
        return Result.success();
    }

    private void setParams() {
        String myPersonalisedNotification = getInputData().getString("workUrlSplit");
        Gson gson = new Gson();
        mUrlSplit = gson.fromJson(myPersonalisedNotification, UrlSplit.class);
        Calendar calendar = Calendar.getInstance();
        mUrlSplit.setBeginDate(setYesterdayToBeginDate(calendar));
    }

    public String setYesterdayToBeginDate(Calendar calendar) {
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return format.format(calendar.getTime());
    }

    private void executeHttpRequestWithRetrofit() {
        this.disposable = TheNewYorkTimesStreams.streamFetchArticleSearch(mUrlSplit.getBeginDate(),
                mUrlSplit.getEndDate(), mUrlSplit.getFilter_query(), mUrlSplit.getQuery(),
                mUrlSplit.getSort(), mUrlSplit.getApiKey()).subscribeWith(new DisposableObserver<TheNewYorkTimesResponse>() {
            @Override
            public void onNext(TheNewYorkTimesResponse theNewYorkTimesResponse) {
                Log.e("TAG", "On Next");
                if (theNewYorkTimesResponse.getResponse().getArticles() != null) {
                    //articles = true;
                    showNotification(getApplicationContext());
                    Log.e("Check Worker", "Articles found");
                } else {
                    Log.e("Check Worker", "No articles found");
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
        Notification notification = new Notification.Builder(context)
                .setContentText("New articles of interest to you have been found.")
                .setSmallIcon(R.drawable.icone_75x75)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .build();

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, notification);
    }
}
