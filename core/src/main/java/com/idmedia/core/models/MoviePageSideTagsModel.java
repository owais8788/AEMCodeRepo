package com.idmedia.core.models;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
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
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MoviePageSideTagsModel {

    @ScriptVariable
    Page currentPage;

    String currentPagePath;

    MoviePageSideTagPojo moviePageSideTag;

    @Inject
    ResourceResolver resourceResolver;

    @PostConstruct
    public void init() {
        currentPagePath = currentPage.getPath();

        if (currentPagePath != null) {

            Resource resource = currentPage.getContentResource();
            PageManager pm = resourceResolver.adaptTo(PageManager.class);
            Page containingPage = pm.getContainingPage(resource);
            ValueMap pageProperties = containingPage.getProperties();

            moviePageSideTag = new MoviePageSideTagPojo();


            // Reading director tag
            String[] directorTags = (String[]) pageProperties.get("cq:directortags");
            TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
            List<String> directorTagList = new ArrayList<>();
            for (String directorTag : directorTags) {
                Tag tag = tagManager.resolve(directorTag);
                if (tag != null) {
                    directorTagList.add(tag.getTitle());
                }
            }
            moviePageSideTag.setDirector(directorTagList);

            // Reading Writer tag
            String[] writerTags = (String[]) pageProperties.get("cq:writertags");
            List<String> writerTagList = new ArrayList<>();
            for (String writerTag : writerTags) {
                Tag tag = tagManager.resolve(writerTag);
                if (tag != null) {
                    writerTagList.add(tag.getTitle());
                }
            }
            moviePageSideTag.setWriter(writerTagList);

            // Reading actors tag
            String[] actorsTags = (String[]) pageProperties.get("cq:actortags");
            List<String> actorsTagList = new ArrayList<>();
            for (String actorsTag : actorsTags) {
                Tag tag = tagManager.resolve(actorsTag);
                if (tag != null) {
                    actorsTagList.add(tag.getTitle());
                }
            }
            moviePageSideTag.setActors(actorsTagList);

            // Reading actress tag
            String[] actressTags = (String[]) pageProperties.get("cq:actresstags");
            List<String> actressTagList = new ArrayList<>();
            for (String actressTag : actressTags) {
                Tag tag = tagManager.resolve(actressTag);
                if (tag != null) {
                    actressTagList.add(tag.getTitle());
                }
            }
            moviePageSideTag.setActress(actressTagList);

            // Reading genre tag
            String[] genreTags = (String[]) pageProperties.get("cq:generetags");
            List<String> genreTagList = new ArrayList<>();
            for (String genreTag : genreTags) {
                Tag tag = tagManager.resolve(genreTag);
                if (tag != null) {
                    genreTagList.add(tag.getTitle());
                }
            }
            moviePageSideTag.setGenre(genreTagList);

            moviePageSideTag.setReleaseData((String) pageProperties.get("movieReleaseDate"));
            moviePageSideTag.setRunTime((String) pageProperties.get("movieRunTime"));

        }
    }

    public MoviePageSideTagPojo getMoviePageSideTag() {
        return moviePageSideTag;
    }
}
