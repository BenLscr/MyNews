package com.lescour.ben.mynews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by benja on 28/01/2019.
 */
public class Article {

    //TOP STORIES & MOST POPULAR\\
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;

    //TOP STORIES\\
    @SerializedName("subsection")
    @Expose
    private String subsection;

    //TOP STORIES & SEARCH\\
    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia = null;

    //MOST POPULAR\\
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;

    //SEARCH\\
    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("headline")
    @Expose
    private Headline headline;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsectoinName")
    @Expose
    private String subsectoinName;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public Headline getHeadline() {
        return headline;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSubsectoinName() {
        return subsectoinName;
    }

    public void setSubsectoinName(String subsectoinName) {
        this.subsectoinName = subsectoinName;
    }

}
