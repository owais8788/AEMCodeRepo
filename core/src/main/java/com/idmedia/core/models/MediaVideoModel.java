package com.idmedia.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = FooterModel.class)
public class MediaVideoModel {
@Inject
    List<MediaVideoModel> mediaVideo;

    public List<MediaVideoModel> getMediaVideo() {
        return mediaVideo;
    }
}
