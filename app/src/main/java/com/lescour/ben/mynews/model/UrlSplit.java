package com.lescour.ben.mynews.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by benja on 14/02/2019.
 */
public class UrlSplit implements Parcelable {

    private String period;
    private String section;
    private String query;
    private String filter_query;
    private String beginDate, endDate;
    private String sort;
    private String apiKey = "4cKaGJtqJJDtrVx14QNFiGbfQI6tqEP6";

    /**
     * Default constructor.
     */
    public UrlSplit() {
    }

    protected UrlSplit(Parcel in) {
        query = in.readString();
        filter_query = in.readString();
        beginDate = in.readString();
        endDate = in.readString();
        sort = in.readString();
        apiKey = in.readString();
    }

    /**
     * Generate instance of UrlSplit Parcelable.
     */
    public static final Creator<UrlSplit> CREATOR = new Creator<UrlSplit>() {
        /**
         * Create a new instance of the UrlSplit Parcelable class, instantiating it from the given
         * Parcel whose data had previously been written by writeToParcel().
         */
        @Override
        public UrlSplit createFromParcel(Parcel in) {
            return new UrlSplit(in);
        }

        /**
         * Create a new array of the UrlSplit Parcelable class.
         * @param size Size of array.
         */
        @Override
        public UrlSplit[] newArray(int size) {
            return new UrlSplit[size];
        }
    };

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setFilter_query(String filter_query) {
        this.filter_query = filter_query;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPeriod() {
        return period;
    }

    public String getSection() {
        return section;
    }

    public String getQuery() {
        return query;
    }

    public String getFilter_query() {
        return filter_query;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSort() {
        return sort;
    }

    public String getApiKey() {
        return apiKey;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable instance's
     * marshaled representation.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     * @param dest The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(query);
        dest.writeString(filter_query);
        dest.writeString(beginDate);
        dest.writeString(endDate);
        dest.writeString(sort);
        dest.writeString(apiKey);
    }
}
