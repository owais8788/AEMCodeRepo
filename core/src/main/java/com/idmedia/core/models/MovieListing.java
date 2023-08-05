package com.idmedia.core.models;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.idmedia.core.pojos.PageCarouselPropertiesPojo;
import com.idmedia.core.service.MoviePagePropertiesService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables= SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,adapters = MovieListing.class)
public class MovieListing {
    @Inject
    SlingHttpServletRequest request;



    List<String> allPages;

    @OSGiService
    MoviePagePropertiesService moviePagePropertiesService;



    List<PageCarouselPropertiesPojo> moviesList;

    @PostConstruct
    protected void init() throws RepositoryException {
        allPages = new ArrayList<>();
        allPages.addAll(getPages());
        moviesList=new ArrayList<>();
        ResourceResolver resourceResolver= request.getResourceResolver();
        moviesList.addAll(moviePagePropertiesService.getMoviePageProperties(allPages, resourceResolver));





    }
    private List<String> getPages() throws RepositoryException {
        ResourceResolver resolver=request.getResourceResolver();
        QueryBuilder queryBuilder =resolver.adaptTo(QueryBuilder.class);
        Session session = resolver.adaptTo(Session.class);
      List<String>  pages = new ArrayList<>();
        if (queryBuilder!=null && session!=null){
            Map<String,String> predicates = new HashMap<>();
            predicates.put("path","/content/idmedia-aem/us/en/homepage/movie-listing");
            predicates.put("type","cq:Page");
            Query query = queryBuilder.createQuery(PredicateGroup.create(predicates), session);
            SearchResult result = query.getResult();
            for (Hit hit : result.getHits()) {
            Resource resource = hit.getResource();
                Page page = resource.adaptTo(Page.class);
                if (page != null) {
                    pages.add( page.getPath());
                }
            }
        }
        return pages;
    }




    public List<String> getAllPages() {
        return allPages;
    }
    public List<PageCarouselPropertiesPojo> getMoviesList() {
        return moviesList;
    }

}
