package com.lescour.ben.mynews.utils;

import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by benja on 28/01/2019.
 */
public class TheNewYorkTimesStreams {

    public static Observable<TheNewYorkTimesResponse> streamFetchTopStories(String section){
        TheNewYorkTimesService theNewYorkTimesService = TheNewYorkTimesService.retrofit.create(TheNewYorkTimesService.class);
        return theNewYorkTimesService.getTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<TheNewYorkTimesResponse> streamFetchMostPopular(String period){
        TheNewYorkTimesService theNewYorkTimesService = TheNewYorkTimesService.retrofit.create(TheNewYorkTimesService.class);
        return theNewYorkTimesService.getMostPopular(period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<TheNewYorkTimesResponse> streamFetchArticleSearch(String query, String sort){
        TheNewYorkTimesService theNewYorkTimesService = TheNewYorkTimesService.retrofit.create(TheNewYorkTimesService.class);
        return theNewYorkTimesService.getArticleSearch(query, sort)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
