package com.idmedia.core.pojos;

import java.util.List;

public class RelatedMoviePojo {
    String pagePath;
    String movieName;
    String year;
    int rating;
    String description;
    String runtime;
    String releaseDate;
    List<String> Director;
    List<String> actors;
    List<String> actress;
    String moviePoster;

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getDirector() {
        return Director;
    }

    public void setDirector(List<String> director) {
        Director = director;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getActress() {
        return actress;
    }

    public void setActress(List<String> actress) {
        this.actress = actress;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    @Override
    public String toString() {
        return "RelatedMoviePojo{" +
                "pagePath='" + pagePath + '\'' +
                ", movieName='" + movieName + '\'' +
                ", year='" + year + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", runtime='" + runtime + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", Director=" + Director +
                ", actors=" + actors +
                ", actress=" + actress +
                ", moviePoster='" + moviePoster + '\'' +
                '}';
    }
}