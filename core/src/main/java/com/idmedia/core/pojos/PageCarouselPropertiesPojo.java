package com.idmedia.core.pojos;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageCarouselPropertiesPojo {

    private String title;
    private String movieDescription;

    private String tileimagefield;

    private String bannerimagefield;

    List<String> genreList;

    String path;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getTileimagefield() {
        return tileimagefield;
    }

    public void setTileimagefield(String tileimagefield) {
        this.tileimagefield = tileimagefield;
    }

    public String getBannerimagefield() {
        return bannerimagefield;
    }

    public void setBannerimagefield(String bannerimagefield) {
        this.bannerimagefield = bannerimagefield;
    }

    public List<String> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<String> genreList) {
        this.genreList = genreList;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
