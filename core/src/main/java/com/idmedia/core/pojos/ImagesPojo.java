package com.idmedia.core.pojos;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


import javax.inject.Inject;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImagesPojo {

    @Inject
    private String fileReferenceBig;
    @Inject
    private String fileReference;

    public String getFileReferenceBig() {
        return fileReferenceBig;
    }

    public void setFileReferenceBig(String fileReferenceBig) {
        this.fileReferenceBig = fileReferenceBig;
    }

    public String getFileReference() {
        return fileReference;
    }

}
