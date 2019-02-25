package com.lescour.ben.mynews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benja on 30/01/2019.
 */
public class MediaMetadatum {

    //MOST POPULAR\\
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("format")
    @Expose
    private String format;

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }
}
