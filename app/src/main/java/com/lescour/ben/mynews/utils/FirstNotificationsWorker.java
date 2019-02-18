package com.lescour.ben.mynews.utils;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Created by benja on 18/02/2019.
 */
public class FirstNotificationsWorker extends Worker {

    public FirstNotificationsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String myPersonalisedNotification = getInputData().getString("workUrlSplit");
        Data source = new Data.Builder()
                .putString("workUrlSplit", myPersonalisedNotification)
                .build();
        PeriodicWorkRequest.Builder notificationsBuilder = new PeriodicWorkRequest.Builder(NotificationsWorker.class, 1, TimeUnit.DAYS);
        notificationsBuilder.setInputData(source);
        PeriodicWorkRequest notificationsWork = notificationsBuilder.build();
        WorkManager.getInstance().enqueue(notificationsWork);
        return null;
    }
}
