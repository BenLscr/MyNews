package com.lescour.ben.mynews.utils;

import com.lescour.ben.mynews.model.TopStoriesJson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by benja on 28/01/2019.
 */
public class TopStoriesStreams {

    public static Observable<TopStoriesJson> streamFetchArticleTitle(String section){
        TopStoriesService topStoriesService = TopStoriesService.retrofit.create(TopStoriesService.class);
        return topStoriesService.getTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
