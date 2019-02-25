package com.lescour.ben.mynews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by benja on 31/01/2019.
 */
public class Headline {

    //SEARCH\\
    @SerializedName("main")
    @Expose
    private String main;

    public String getMain() {
        return main;
    }
}
