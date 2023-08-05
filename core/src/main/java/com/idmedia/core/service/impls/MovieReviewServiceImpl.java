package com.idmedia.core.service.impls;

import com.idmedia.core.beans.MovieReviewPojo;
import com.idmedia.core.constants.IDMediaConstant;
import com.idmedia.core.service.MovieReviewService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component(service = MovieReviewService.class, immediate = true)
public class MovieReviewServiceImpl implements MovieReviewService {

    @Override
    public List<MovieReviewPojo> getReviews(String movieTitle) throws IOException, JSONException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonRequestBody = new JSONObject();
        try {
            jsonRequestBody.put(IDMediaConstant.MOVIETITLE, movieTitle);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String url = "http://192.168.0.139:8080/getReview";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(jsonRequestBody.toString());
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String responseContent = EntityUtils.toString(httpEntity);

        JSONObject jsonObject = new JSONObject(responseContent);
        JSONArray jsonArray=jsonObject.getJSONArray("reviewModels");
        List<MovieReviewPojo> movieReviewPojoList=new ArrayList<MovieReviewPojo>();

        if (jsonArray !=null) {
            for(int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject1 =jsonArray.getJSONObject(i) ;
                List<Integer> userMovieRating = new ArrayList<Integer>();
                List<String> userMovieReview  = new ArrayList<String>();
                List<String> movie  = new ArrayList<String>();
                List<String> email  = new ArrayList<String>();
                List<String> dateAndTime=new ArrayList<String>();

                int rating = jsonObject1.optInt("userMovieRating");
                String review = jsonObject1.optString("userMovieReview");
                String title = jsonObject1.optString("movieTitle");
                String Email = jsonObject1.optString("email");
                String dateTime=jsonObject1.optString("dateAndTime");

                if (rating != 0) {
                    userMovieRating.add(rating);
                }
                if (review != null) {
                    userMovieReview.add(review);
                }
                if (title != null) {
                    movie.add(title);
                }
                if (Email != null) {
                    email.add(Email);
                }
                if (dateTime != null) {
                    dateAndTime.add(dateTime);
                }
//                userMovieRating.add(jsonObject1.getInt("userMovieRating"));
//                userMovieReview.add(jsonObject1.getString("userMovieReview"));
//                movieTitles.add(jsonObject1.getString("movieTitle"));

                MovieReviewPojo movieReviewPojo=new MovieReviewPojo();
                movieReviewPojo.setUserMovieRating(userMovieRating);
                movieReviewPojo.setUserMovieReview(userMovieReview);
                movieReviewPojo.setMovieTitle(movie);
                movieReviewPojo.setEmail(email);
                movieReviewPojo.setDateAndTime(dateAndTime);

                movieReviewPojoList.add(movieReviewPojo);
            }
        }
        return movieReviewPojoList;
    }
}
