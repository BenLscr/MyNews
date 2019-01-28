package com.lescour.ben.mynews.utils;

import com.lescour.ben.mynews.model.TopStoriesJson;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by benja on 28/01/2019.
 */
public interface TopStoriesService {

    @GET("{section}.json?api-key=4cKaGJtqJJDtrVx14QNFiGbfQI6tqEP6")
    Observable<List<TopStoriesJson>> getTopStories(@Path("section") String section);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
