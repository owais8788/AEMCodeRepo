package com.idmedia.core.service.impls;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.idmedia.core.pojos.ActorPojo;
import com.idmedia.core.service.ActorContentFragmentService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component(service=ActorContentFragmentService.class)
public class ActorContentFragmentServiceImpl implements ActorContentFragmentService {


    @Override
    public List<ActorPojo> getActorContentFragments(List<String> paths, ResourceResolver resourceResolver) {

        List<ActorPojo> actorContentFragmentsList = null;
        if (paths != null) {
            actorContentFragmentsList = new ArrayList<>();
            for (String path : paths) {
                ActorPojo actor = new ActorPojo();
                Resource resource = resourceResolver.getResource(path);
                ContentFragment contentFragment = resource.adaptTo(ContentFragment.class);
                actor.setFirstName(contentFragment.getElement("firstName").getContent().toString());
                actor.setLastName(contentFragment.getElement("lastName").getContent().toString());
                actor.setDob(contentFragment.getElement("dateOfBirth").getContent().toString());
                actor.setHeight(contentFragment.getElement("height").getContent().toString());
                actor.setImageUrl(contentFragment.getElement("image").getContent().toString());
                actor.setBiography(contentFragment.getElement("biography").getContent().toString());


                actorContentFragmentsList.add(actor);

            }


        }
        return actorContentFragmentsList;

    }

}
