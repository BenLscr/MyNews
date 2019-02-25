package com.lescour.ben.mynews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benja on 29/01/2019.
 */
public class Multimedium {

    //SEARCH & TOP STORIES\\
    @SerializedName("url")
    @Expose
    private String url;
    //SEARCH\\
    @SerializedName("subtype")
    @Expose
    //TOP STORIES\\
    private String subType;
    @SerializedName("format")
    @Expose
    private String format;

    public String getUrl() {
        return url;
    }

    public String getSubType() {
        return subType;
    }

    public String getFormat() {
        return format;
    }
}
