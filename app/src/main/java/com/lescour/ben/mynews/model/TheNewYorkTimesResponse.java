package com.lescour.ben.mynews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benja on 28/01/2019.
 */
public class TheNewYorkTimesResponse {

    //TOP STORIES & MOST POPULAR\\
    @SerializedName("results")
    @Expose
    private List<Article> articles = null;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    //SEARCH\\
    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

}
