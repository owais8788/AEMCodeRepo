package com.idmedia.core.service;

import com.idmedia.core.beans.MovieReviewPojo;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface MovieReviewService {
   public List<MovieReviewPojo> getReviews(String movieTitle) throws IOException, JSONException;

}
