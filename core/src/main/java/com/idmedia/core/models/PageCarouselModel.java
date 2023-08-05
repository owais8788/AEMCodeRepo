package com.idmedia.core.models;
import com.idmedia.core.pojos.PageCarouselPropertiesPojo;
import com.idmedia.core.service.MoviePagePropertiesService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageCarouselModel {

    @Inject
    List<String> pathfield;


    String path;
    List<PageCarouselPropertiesPojo> ppp;
    @Inject
    ResourceResolver resourceResolver;

    @OSGiService
    MoviePagePropertiesService moviePagePropertiesService;

    @PostConstruct
    public void init() {
        ppp = new ArrayList<>();
        ppp.addAll(moviePagePropertiesService.getMoviePageProperties(pathfield,resourceResolver));

    }

    public List<PageCarouselPropertiesPojo> getPpp() {
        return ppp;
    }


}
