package com.idmedia.core.pojos;

import java.util.List;

public class MoviePageSideTagPojo {

    List<String> director;

    List<String> writer;

    List<String> actors;

    List<String> actress;

    List<String> genre;

    String releaseData;
    String runTime;

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<String> getWriter() {
        return writer;
    }

    public void setWriter(List<String> writer) {
        this.writer = writer;
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

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getReleaseData() {
        return releaseData;
    }

    public void setReleaseData(String releaseData) {
        this.releaseData = releaseData;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }
}
