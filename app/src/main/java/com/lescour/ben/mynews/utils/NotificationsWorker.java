package com.lescour.ben.mynews.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by benja on 18/02/2019.
 */
public class NotificationsWorker extends Worker {

    private UrlSplit mUrlSplit;
    private Disposable disposable;

    public NotificationsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        this.setParams();
        this.executeHttpRequestWithRetrofit();
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
        disposable = TheNewYorkTimesStreams.streamFetchArticleSearch(mUrlSplit.getBeginDate(),
                mUrlSplit.getEndDate(), mUrlSplit.getFilter_query(), mUrlSplit.getQuery(),
                mUrlSplit.getSort(), mUrlSplit.getApiKey()).subscribeWith(new DisposableObserver<TheNewYorkTimesResponse>() {
            @Override
            public void onNext(TheNewYorkTimesResponse theNewYorkTimesResponse) {
                Log.e("TAG", "On Next");
                if (theNewYorkTimesResponse.getResponse().getArticles().size() > 0) {
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

    @Override
    public void onStopped() {
        super.onStopped();
        this.disposeWhenStopped();
    }

    private void disposeWhenStopped(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    private void showNotification(Context context) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon_75x75_transparent)
                .setContentText("New articles of interest to you have been found.");

        notificationManager.notify(1, notificationBuilder.build());
    }
}
