package com.idmedia.core.pojos;

import javax.inject.Inject;

import org.apache.sling.models.annotations.Default;

public class IdMediaPageSliderPojo {

    @Inject@Default(values = "label")
    String movielabel="";
    @Inject@Default(values = "link")
    String moviepagelink ="";
    @Inject@Default(values = "poster")
    String movieposter="";
    @Inject@Default(values = "0.0")
    String rating="";

    String[] tags= new String[]{""};

    public String getMovielabel() {
        return movielabel;
    }

    public void setMovielabel(String movielabel) {
        this.movielabel = movielabel;
    }

    public String getMoviepagelink() {
        return moviepagelink;
    }

    public void setMoviepagelink(String moviepagelink) {
        this.moviepagelink = moviepagelink;
    }

    public String getMovieposter() {
        return movieposter;
    }

    public void setMovieposter(String movieposter) {
        this.movieposter = movieposter;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}