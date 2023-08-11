package com.idmedia.core.mainmethods;


import com.adobe.cq.dam.cfm.ContentFragment;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class)
public class ReadingContentFragment {

    @Inject
    ResourceResolver resourceResolver;

    @Inject
    private String pathCfm;

    List<ReadingContentPojo> cfPojo = new ArrayList<>();

    @PostConstruct
    public void init() {
        ReadingContentPojo readingContentFragmentPojo = new ReadingContentPojo();
        Resource resource = resourceResolver.getResource(pathCfm);
        if (resource != null) {
            Iterable<Resource> childresources = resource.getChildren();
            for (Resource child : childresources) {
                ContentFragment contentFragment = child.adaptTo(ContentFragment.class);
                if (contentFragment != null) {
                    readingContentFragmentPojo.setTitle(contentFragment.getElement("faqTitle").getContent());
                    readingContentFragmentPojo.setDescription(contentFragment.getElement("faqDescription").getContent());
                }
            }
        }
    }

    public List<ReadingContentPojo> getCfPojo() {
        return cfPojo;
    }
}
