package com.idmedia.core.service;

import com.idmedia.core.pojos.PageCarouselPropertiesPojo;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.List;

public interface MoviePagePropertiesService {

    public List<PageCarouselPropertiesPojo> getMoviePageProperties(List<String> list, ResourceResolver resourceResolver);
}
