package com.idmedia.core.service.impls;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.idmedia.core.pojos.PageCarouselPropertiesPojo;
import com.idmedia.core.service.MoviePagePropertiesService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(service = MoviePagePropertiesService.class,immediate = true)
public class MoviePagePropertiesServiceImpl implements MoviePagePropertiesService {

    @Override
    public List<PageCarouselPropertiesPojo> getMoviePageProperties(List<String> paths, ResourceResolver resourceResolver) {
       List<PageCarouselPropertiesPojo> ppp = new ArrayList<>();
        for (String mp : paths) {
            if (mp != null) {

                Resource resource = resourceResolver.getResource(mp);
                PageCarouselPropertiesPojo pojo = new PageCarouselPropertiesPojo();

                PageManager pm = resourceResolver.adaptTo(PageManager.class);
                Page containingPage = pm.getContainingPage(resource);
                ValueMap pageProperties = containingPage.getProperties();

                //Title reading
                pojo.setTitle((String) pageProperties.get("jcr:title"));

                //Movie description reading
                pojo.setMovieDescription((String) pageProperties.get("movieDescription"));

                //Reading banner field
                pojo.setBannerimagefield((String) pageProperties.get("bannerimagefield"));

                pojo.setPath(mp);



                //Reading Genre tags
                String[] tags = (String[]) pageProperties.get("cq:generetags");
                TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
                List<String> tagList = new ArrayList<>();
                for (String tag : tags) {
                    Tag genreTag = tagManager.resolve(tag);
                    if (genreTag != null) {
                        tagList.add(genreTag.getTitle());
                    }
                }

                // Reading Title Image field
                pojo.setTileimagefield((String) pageProperties.get("tileimagefield"));
                pojo.setGenreList(tagList);
                ppp.add(pojo);
            }

        }
        return ppp;
    }
}
