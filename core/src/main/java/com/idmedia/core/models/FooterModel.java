package com.idmedia.core.models;

import com.idmedia.core.pojos.FooterMiddleNavigation;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = FooterModel.class)
public class FooterModel {
    @Inject
    List<FooterMiddleNavigation> middleNavigation;

    public List<FooterMiddleNavigation> getMiddleNavigation() {
        return middleNavigation;
    }
}


