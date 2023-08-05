package com.idmedia.core.pojos;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = HeaderNavigation.class)
public class HeaderNavigation {

    @Inject
    private String label1;

    @Inject
    List<HeaderSubMenuLevelOne> headerSubMenuLevelOne;

    public String getLabel1() {
        return label1;
    }
    public List<HeaderSubMenuLevelOne> getHeaderSubMenuLevelOne() {
        return headerSubMenuLevelOne;
    }
}
