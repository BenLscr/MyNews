package com.lescour.ben.mynews.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

    @Override
    public void onReceive(Context context, Intent intent) {
        this.setParams(intent);
        this.executeHttpRequestWithRetrofit();
    }

    private void setParams(Intent intent) {
        query = intent.getStringExtra(BUNDLE_EXTRA_QUERY);
        filter_query = intent.getStringExtra(BUNDLE_EXTRA_FILTER_QUERY);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        begin_date = format.format(calendar.getTime());
    }

    private void executeHttpRequestWithRetrofit() {
        this.disposable = TheNewYorkTimesStreams.streamFetchArticleSearch(begin_date, end_date, filter_query, query, "newest", apiKey).subscribeWith(new DisposableObserver<TheNewYorkTimesResponse>() {
            @Override
            public void onNext(TheNewYorkTimesResponse theNewYorkTimesResponse) {
                Log.e("TAG", "On Next");
                if (theNewYorkTimesResponse.getResponse().getArticles() != null) {
                    Log.e("BroadcastReceiver", "Des articles ont été trouvés");
                } else {
                    Log.e("BroadcastReceiver", "Rien n'a été trouvés");
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
}
