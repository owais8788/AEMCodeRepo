package com.idmedia.core.pojos;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = FooterNavigation.class)

public class FooterNavigation {

    @Inject
    private String label;
    @Inject
    private String link;
    @Inject
    List<FooterSubMenuLevelOne> subMenuLevelOne;

    public String getLabel() {
        return label;
    }

    public String getLink() {
        return link;
    }

    public List<FooterSubMenuLevelOne> getSubMenuLevelOne() {
        return subMenuLevelOne;
    }
}
