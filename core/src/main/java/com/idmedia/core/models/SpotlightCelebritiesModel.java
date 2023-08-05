package com.idmedia.core.models;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.idmedia.core.pojos.ActorPojo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SpotlightCelebritiesModel {
    @Inject
    List<String> links;

    @Inject
    ResourceResolver resourceResolver;
    List<ActorPojo> actorList;

    @PostConstruct
    protected void init() {


        if (links != null) {
            actorList = new ArrayList<ActorPojo>();
            for (String path : links) {
                ActorPojo actor = new ActorPojo();
                Resource resource = resourceResolver.getResource(path);
                ContentFragment contentFragment = resource.adaptTo(ContentFragment.class);
                actor.setFirstName(contentFragment.getTitle());
                actor.setLastName(contentFragment.getElement("lastName").getContent());
                actor.setDob(contentFragment.getElement("dateOfBirth").getContent());
                actor.setHeight(contentFragment.getElement("height").getContent());
                actor.setImageUrl(contentFragment.getElement("image").getContent());
                actor.setBiography(contentFragment.getElement("biography").getContent());
                actorList.add(actor);
            }
        }


    }

    public List<ActorPojo> getActorList() {
        return actorList;
    }

}
