package com.idmedia.core.pojos;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,adapters = HeaderSubMenuLevelOne.class)
public class HeaderSubMenuLevelOne {

    @Inject
    private String label2;

    @Inject
    private String link2;

    public String getLabel2() {
        return label2;
    }

    public String getLink2() {
        return link2;
    }
}
