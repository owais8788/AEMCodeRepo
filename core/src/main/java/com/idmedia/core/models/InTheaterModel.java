package com.idmedia.core.models;

import com.idmedia.core.pojos.PageCarouselPropertiesPojo;
import com.idmedia.core.service.MoviePagePropertiesService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class InTheaterModel {

    @ValueMapValue
    List<String> popular;

    List<PageCarouselPropertiesPojo> popularList;

    @ValueMapValue
    List<String> comingSoon;
    List<PageCarouselPropertiesPojo> comingSoonList;

    @ValueMapValue
    List<String> topRated;
    List<PageCarouselPropertiesPojo> topratedList;

    @ValueMapValue
    List<String> mostReviewed;
    List<PageCarouselPropertiesPojo> mostReviewedList;

    @Inject
    ResourceResolver resourceResolver;

    @OSGiService
    MoviePagePropertiesService moviePagePropertiesService;

    @PostConstruct
    protected void init(){
        popularList = new ArrayList<>();
        comingSoonList = new ArrayList<>();
        topratedList =new ArrayList<>();
        mostReviewedList  = new ArrayList<>();

        popularList.addAll(moviePagePropertiesService.getMoviePageProperties(popular,resourceResolver));
        comingSoonList.addAll(moviePagePropertiesService.getMoviePageProperties(comingSoon,resourceResolver));
        topratedList.addAll(moviePagePropertiesService.getMoviePageProperties(topRated,resourceResolver));
        mostReviewedList.addAll(moviePagePropertiesService.getMoviePageProperties(mostReviewed,resourceResolver));
    }


    public List<PageCarouselPropertiesPojo> getPopularList() {
        return popularList;
    }

    public List<PageCarouselPropertiesPojo> getComingSoonList() {
        return comingSoonList;
    }

    public List<PageCarouselPropertiesPojo> getTopratedList() {
        return topratedList;
    }

    public List<PageCarouselPropertiesPojo> getMostReviewedList() {
        return mostReviewedList;
    }
}
