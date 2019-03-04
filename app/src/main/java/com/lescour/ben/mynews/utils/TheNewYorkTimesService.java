package com.lescour.ben.mynews.utils;

import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by benja on 28/01/2019.
 */
public interface TheNewYorkTimesService {

    @GET("topstories/v2/{section}.json")
    Observable<TheNewYorkTimesResponse> getTopStories(@Path("section") String section,
                                                      @Query("api-key") String apiKey);

    @GET("mostpopular/v2/viewed/{period}.json")
    Observable<TheNewYorkTimesResponse> getMostPopular(@Path("period") String period,
                                                       @Query("api-key") String apiKey);

    @GET("search/v2/articlesearch.json")
    Observable<TheNewYorkTimesResponse> getArticleSearch(@Query("begin_date")String begin_date,
                                                         @Query("end_date")String end_date,
                                                         @Query("fq") String filter_query,
                                                         @Query("q") String query,
                                                         @Query("sort") String sort,
                                                         @Query("api-key") String apiKey);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

}
