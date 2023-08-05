package com.idmedia.core.models;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.idmedia.core.pojos.ActorPojo;
import com.idmedia.core.pojos.CastAndCrew;
import com.idmedia.core.pojos.MoviePageSideTagPojo;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = {SlingHttpServletRequest.class,Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CastAndCrewModel {

    @ScriptVariable
    Page currentPage;

    @Inject
    SlingHttpServletRequest request;

    String currentPath;
    String movieTitle;


    @Inject
    ResourceResolver resourceResolver;

    List<CastAndCrew> actorList;
    List<CastAndCrew> directorList;
    List<CastAndCrew> writerList;
    List<CastAndCrew> actressList;



    @PostConstruct
    protected void init() throws RepositoryException {
        currentPath = currentPage.getPath();
        if (currentPath != null) {

            Resource resource = resourceResolver.getResource(currentPath);
            PageManager pm = resourceResolver.adaptTo(PageManager.class);
            Page containingPage = pm.getContainingPage(resource);
            ValueMap pageProperties = containingPage.getProperties();


            // Reading tag
            String[] directorTags = (String[]) pageProperties.get("cq:directortags");
            directorList = new ArrayList<>();
            directorList.addAll(getContent(getPathByTags(directorTags)));


            String[] writerTags = (String[]) pageProperties.get("cq:writertags");
            writerList = new ArrayList<>();
            writerList.addAll(getContent(getPathByTags(writerTags)));


            String[] actorsTags = (String[]) pageProperties.get("cq:actortags");
            actorList = new ArrayList<>();
            actorList.addAll(getContent(getPathByTags(actorsTags)));


            String[] actressTags = (String[]) pageProperties.get("cq:actresstags");
            actressList = new ArrayList<>();
            actressList.addAll(getContent(getPathByTags(actressTags)));


            movieTitle = pageProperties.get("jcr:title").toString();
        }
    }


    public List<String> getPathByTags(String[] tags) throws RepositoryException {

        QueryBuilder queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
        Session session = resourceResolver.adaptTo(Session.class);
        List<String> contentFragementlist = new ArrayList<>();
        if(queryBuilder!=null && session!=null){
            Map<String,String> predicates = new HashMap<>();
            predicates.put("path","/content/dam");
            predicates.put("type","cq:ContentFragment");
            predicates.put("property","cq:tags");
            for(int i=0; i< tags.length; i++){
                predicates.put("property."+i+"_value",tags[i]);
            }
            Query query = queryBuilder.createQuery(PredicateGroup.create(predicates),session);
            SearchResult result = query.getResult();
            for(Hit hit : result.getHits()){
                String path = hit.getPath().toString();
                String[] pathSegments = path.split("/jcr:content/data/master");
                String desiredPath = pathSegments[0];
                contentFragementlist.add(desiredPath);

            }
        }
      return contentFragementlist;
    }

    public List<CastAndCrew> getContent(List<String> links){
        ResourceResolver resolver = request.getResourceResolver();
        List<CastAndCrew> list = new ArrayList<>();
        if (links != null) {
            for (String path : links) {
                CastAndCrew cc = new CastAndCrew();
                 Resource resource = resolver.getResource(path);
                ContentFragment contentFragment = resource.adaptTo(ContentFragment.class);
                 cc.setName(contentFragment.getTitle());
                 cc.setImagePath(contentFragment.getElement("image").getContent().toString());
               list.add(cc);

            }
        }
     return list;
    }


    public List<CastAndCrew> getActorList() {
        return actorList;
    }

    public List<CastAndCrew> getDirectorList() {
        return directorList;
    }

    public List<CastAndCrew> getWriterList() {
        return writerList;
    }

    public List<CastAndCrew> getActressList() {
        return actressList;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}
