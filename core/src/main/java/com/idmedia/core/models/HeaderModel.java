package com.idmedia.core.models;

import com.idmedia.core.pojos.HeaderNavigation;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = HeaderModel.class)
public class HeaderModel {

    @Inject
    List<HeaderNavigation> headerNavigation;

    public List<HeaderNavigation> getHeaderNavigation() {
        return headerNavigation;
    }
}
