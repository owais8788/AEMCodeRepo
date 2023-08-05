package com.idmedia.core.service;

import com.idmedia.core.pojos.ActorPojo;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.List;

public interface ActorContentFragmentService {
    public List<ActorPojo> getActorContentFragments(List<String> paths, ResourceResolver resourceResolver);
}
