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

    /**
     * An observable representing a data stream -for TopStories- that allows subscribers to
     * register and retrieve their data emissions.
     * subscribeOn() : Allow to execute the observable in a dedicated Thread.
     * observeOn() : Allow all subscribers to listen to the observable data stream on the Main Thread.
     * timeout() : If no data is emitted before the set time (here 10 seconds), the data emission will
     * be cut and a timeout error will be sent to Subscriber(s).
     */
    public static Observable<TheNewYorkTimesResponse> streamFetchTopStories(String section,
                                                                            String apiKey){
        TheNewYorkTimesService theNewYorkTimesService = TheNewYorkTimesService.retrofit.
                create(TheNewYorkTimesService.class);
        return theNewYorkTimesService.getTopStories(section, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    /**
     * An observable representing a data stream -for MostPopular- that allows subscribers to
     * register and retrieve their data emissions.
     * subscribeOn() : Allow to execute the observable in a dedicated Thread.
     * observeOn() : Allow all subscribers to listen to the observable data stream on the Main Thread.
     * timeout() : If no data is emitted before the set time (here 10 seconds), the data emission will
     * be cut and a timeout error will be sent to Subscriber(s).
     */
    public static Observable<TheNewYorkTimesResponse> streamFetchMostPopular(String period,
                                                                             String apiKey){
        TheNewYorkTimesService theNewYorkTimesService = TheNewYorkTimesService.retrofit
                .create(TheNewYorkTimesService.class);
        return theNewYorkTimesService.getMostPopular(period, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    /**
     * An observable representing a data stream -for ArticleSearch- that allows subscribers to
     * register and retrieve their data emissions.
     * subscribeOn() : Allow to execute the observable in a dedicated Thread.
     * observeOn() : Allow all subscribers to listen to the observable data stream on the Main Thread.
     * timeout() : If no data is emitted before the set time (here 10 seconds), the data emission will
     * be cut and a timeout error will be sent to Subscriber(s).
     */
    public static Observable<TheNewYorkTimesResponse> streamFetchArticleSearch(String begin_date,
                                                                               String end_date,
                                                                               String filter_query,
                                                                               String query,
                                                                               String sort,
                                                                               String apiKey){
        TheNewYorkTimesService theNewYorkTimesService = TheNewYorkTimesService.retrofit
                .create(TheNewYorkTimesService.class);
        return theNewYorkTimesService.getArticleSearch(begin_date, end_date, filter_query, query,
                sort, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
