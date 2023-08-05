package com.idmedia.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.idmedia.core.beans.MovieReviewPojo;
import com.idmedia.core.service.MovieReviewService;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = MovieReviewModel.class)
public class MovieReviewModel {

    @SlingObject
    SlingHttpServletRequest request;
    @OSGiService
    MovieReviewService movieReviewService;
    @Self
    Resource resource;
    @Inject
    PageManager pageManager;
    List<MovieReviewPojo>movieReviewPojoList= new ArrayList<>();
    String title;
    private final Logger logger = LoggerFactory.getLogger(MovieReviewModel.class);

    @PostConstruct
    protected void init() throws IOException, JSONException {

        Resource resource = request.getResource();
        Page page = pageManager.getContainingPage(resource);
        if (page != null) {
            title = page.getTitle();
        }
        try{
            movieReviewPojoList.addAll(movieReviewService.getReviews(title));
        }catch (Exception e){
            logger.info("error ocurr"+ e);
        }
    }

    public List<MovieReviewPojo> getMovieReviewPojoList() {
        return movieReviewPojoList;
    }

    public String getTitle() {
        return title;
    }

}
