package com.idmedia.core.pojos;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


import javax.inject.Inject;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VideoPojo {

    @Inject
    private String fileReferenceVideo;
    @Inject
    private String linkVideo;

    public String getFileReferenceVideo() {
        return fileReferenceVideo;
    }

    public String getLinkVideo() {
        return linkVideo;
    }
}
