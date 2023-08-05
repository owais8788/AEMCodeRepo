package com.idmedia.core.models;

import com.idmedia.core.pojos.ImagesPojo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;


@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImagesAndVideosModel {

    @Inject
    List<ImagesPojo> images;

    public List<ImagesPojo> getImages() {
        return images;
    }
}
