package com.idmedia.core.models;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.idmedia.core.pojos.RelatedMoviePojo;
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


@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, adapters = RelatedMovie.class)
public class RelatedMovie {
    @Inject
    private SlingHttpServletRequest request;

    @ScriptVariable
    private Page currentPage;
    @Inject
    private PageManager pageManager;
    @Inject
    private TagManager tagManager;
    @Inject
    private Resource resource;
    private String pageTitle;
    private List<String> pages;
    private List<String> tagList;
    private List<RelatedMoviePojo> relatedMovieList;
    @Inject
    private ResourceResolver resourceResolver;

    public RelatedMovie() {
    }

    @PostConstruct
    protected void init() throws RepositoryException {
        resource = resourceResolver.getResource(currentPage.getPath());

        PageManager pm = resourceResolver.adaptTo(PageManager.class);
        Page containingPage = pm.getContainingPage(resource);
        ValueMap pageProperties = containingPage.getProperties();

        pageTitle = (String) pageProperties.get("jcr:title");

        //Reading Genre tags
        String[] tags = (String[]) pageProperties.get("cq:generetags");
        tagManager = resourceResolver.adaptTo(TagManager.class);

        pages = new ArrayList<>();
        pages.addAll(getPagesByTags(tags));
        if (pages != null) {

            relatedMovieList = new ArrayList<>();
            for (String page : pages) {
                RelatedMoviePojo relatedMovie = new RelatedMoviePojo();
                relatedMovie.setPagePath(page);
                Resource resource1 = resourceResolver.getResource(page);
                PageManager pm1 = resourceResolver.adaptTo(PageManager.class);
                Page page1 = pm1.getContainingPage(resource1);
                ValueMap pageProperties2 = page1.getProperties();
                relatedMovie.setMovieName((String) pageProperties2.get("jcr:title"));
                relatedMovie.setReleaseDate((String) pageProperties2.get("movieReleaseDate"));
                relatedMovie.setMoviePoster((String) pageProperties2.get("bannerimagefield"));
                relatedMovie.setDescription((String) pageProperties2.get("movieDescription"));
                relatedMovie.setRuntime((String) pageProperties2.get("movieRunTime"));


                String inputString = ((String) pageProperties2.get("movieReleaseDate"));
                String[] parts = inputString.split(" ");
                String year = parts[2];

                relatedMovie.setYear(year);

                // Reading director tag
                String[] directorTags = (String[]) pageProperties2.get("cq:directortags");
                List<String> directorTagList = new ArrayList<>();
                for (String directorTag : directorTags) {
                    Tag tag = tagManager.resolve(directorTag);
                    if (tag != null) {
                        directorTagList.add(tag.getTitle());
                    }
                }
                relatedMovie.setDirector(directorTagList);


                // Reading actors tag
                String[] actorsTags = (String[]) pageProperties2.get("cq:actortags");
                List<String> actorsTagList = new ArrayList<>();
                for (String actorsTag : actorsTags) {
                    Tag tag = tagManager.resolve(actorsTag);
                    if (tag != null) {
                        actorsTagList.add(tag.getTitle());
                    }
                }
                relatedMovie.setActors(actorsTagList);

                // Reading actress tag
                String[] actressTags = (String[]) pageProperties2.get("cq:actresstags");
                List<String> actressTagList = new ArrayList<>();
                for (String actressTag : actressTags) {
                    Tag tag = tagManager.resolve(actressTag);
                    if (tag != null) {
                        actressTagList.add(tag.getTitle());
                    }
                }
                relatedMovie.setActress(actressTagList);


                relatedMovieList.add(relatedMovie);
            }

        }


    }

    private List<String> getPagesByTags(String[] tags) throws RepositoryException {
        ResourceResolver resolver = request.getResourceResolver();
        QueryBuilder queryBuilder = resolver.adaptTo(QueryBuilder.class);
        Session session = resolver.adaptTo(Session.class);
        pages = new ArrayList<>();
        if (queryBuilder != null && session != null) {
            Map<String, String> predicates = new HashMap<>();
            predicates.put("path", "/content/idmedia-aem");
            predicates.put("type", "cq:Page");
            predicates.put("1_property", "jcr:content/cq:generetags");
            for (int i = 0; i < tags.length; i++) {
                predicates.put("1_property.'" + i + "'_value", tags[i]);
            }
            Query query = queryBuilder.createQuery(PredicateGroup.create(predicates), session);
            SearchResult result = query.getResult();
            for (Hit hit : result.getHits()) {
                resource = hit.getResource();
                Page page = resource.adaptTo(Page.class);
                if (page != null) {
                    pages.add(page.getPath());
                }
            }
        }
        return pages;
    }

    public List<String> getPages() {
        return pages;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public List<RelatedMoviePojo> getRelatedMovieList() {
        return relatedMovieList;
    }
}
