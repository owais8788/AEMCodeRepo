package com.idmedia.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.idmedia.core.pojos.IdMediaPageSliderPojo;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,adapters = IdMediaPageSlider.class)
public class IdMediaPageSlider {

    @ValueMapValue
    String facebookLink;

    @ValueMapValue
    String twitterLink;


    @ValueMapValue
    String instagramLink;
    @ValueMapValue
    String youtubeLink;

    @ValueMapValue
    String fileReference;

     @Inject
     Resource currentResource;

     List<IdMediaPageSliderPojo> pageSlidersList;


    @PostConstruct
    public void init() {

         pageSlidersList=new ArrayList<>();
         if(currentResource!=null){
             Resource childResource =currentResource.getChild("myMultifield");
             if(childResource!=null){
                 for (Resource child : childResource.getChildren()) {
                     IdMediaPageSliderPojo item=new IdMediaPageSliderPojo();
                     item.setMovielabel(child.getValueMap().get("movielabel",toString()));
                     item.setRating(child.getValueMap().get("rating",toString()));
                     item.setMovieposter(child.getValueMap().get("movieposter",toString()));
                     item.setMoviepagelink(child.getValueMap().get("moviepagelink",toString()));
                     pageSlidersList.add(item);

                 }
             }
         }


     }

    public String getFacebookLink() {
        return facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public String getFileReference() {
        return fileReference;
    }

    public List<IdMediaPageSliderPojo> getPageSlidersList() {
        return pageSlidersList;
    }




}
