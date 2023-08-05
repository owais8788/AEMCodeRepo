package com.idmedia.core.beans;


import java.util.List;

public class MovieReviewPojo {

    private List<String> email;
    private List<String> movieTitle;
    private List<Integer> userMovieRating;
    private List<String> userMovieReview;
    private List<String> dateAndTime;

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(List<String> movieTitle) {
        this.movieTitle = movieTitle;
    }

    public List<Integer> getUserMovieRating() {
        return userMovieRating;
    }

    public void setUserMovieRating(List<Integer> userMovieRating) {
        this.userMovieRating = userMovieRating;
    }

    public List<String> getUserMovieReview() {
        return userMovieReview;
    }

    public void setUserMovieReview(List<String> userMovieReview) {
        this.userMovieReview = userMovieReview;
    }

    public List<String> getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(List<String> dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}