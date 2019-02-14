package com.lescour.ben.mynews.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by benja on 14/02/2019.
 */
public class UrlSplit implements Parcelable {

    private String query = "science";
    private String filter_query;
    private String beginDateForUrl, endDateForUrl;
    private String sort = "newest";
    private String apiKey = "4cKaGJtqJJDtrVx14QNFiGbfQI6tqEP6";

    public UrlSplit() {
    }

    protected UrlSplit(Parcel in) {
        query = in.readString();
        filter_query = in.readString();
        beginDateForUrl = in.readString();
        endDateForUrl = in.readString();
        sort = in.readString();
        apiKey = in.readString();
    }

    public static final Creator<UrlSplit> CREATOR = new Creator<UrlSplit>() {
        @Override
        public UrlSplit createFromParcel(Parcel in) {
            return new UrlSplit(in);
        }

        @Override
        public UrlSplit[] newArray(int size) {
            return new UrlSplit[size];
        }
    };

    public void setQuery(String query) {
        this.query = query;
    }

    public void setFilter_query(String filter_query) {
        this.filter_query = filter_query;
    }

    public void setBeginDateForUrl(String beginDateForUrl) {
        this.beginDateForUrl = beginDateForUrl;
    }

    public void setEndDateForUrl(String endDateForUrl) {
        this.endDateForUrl = endDateForUrl;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getQuery() {
        return query;
    }

    public String getFilter_query() {
        return filter_query;
    }

    public String getBeginDateForUrl() {
        return beginDateForUrl;
    }

    public String getEndDateForUrl() {
        return endDateForUrl;
    }

    public String getSort() {
        return sort;
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(query);
        dest.writeString(filter_query);
        dest.writeString(beginDateForUrl);
        dest.writeString(endDateForUrl);
        dest.writeString(sort);
        dest.writeString(apiKey);
    }
}
