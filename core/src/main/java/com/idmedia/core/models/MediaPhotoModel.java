package com.idmedia.core.models;


import com.adobe.cq.dam.cfm.ContentFragment;
import com.idmedia.core.pojos.MediaPhotoPojo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = MediaPhotoModel.class)
public class MediaPhotoModel {
    @Inject
    List<String> links;

    List<MediaPhotoPojo> mediaPhotoList;

    @Inject
    ResourceResolver resourceResolver;

    @PostConstruct
    public void init(){
        if(links != null){
            mediaPhotoList = new ArrayList<>();
            for(String link:links){
                MediaPhotoPojo mediaPhoto = new MediaPhotoPojo();
                Resource resource = resourceResolver.getResource(link);
                ContentFragment contentFragment = resource.adaptTo(ContentFragment.class);
                mediaPhoto.setImage(contentFragment.getElement("image").getContent());
                mediaPhotoList.add(mediaPhoto);

            }
        }
    }

    public List<MediaPhotoPojo> getMediaPhotoList() {
        return mediaPhotoList;
    }
}
