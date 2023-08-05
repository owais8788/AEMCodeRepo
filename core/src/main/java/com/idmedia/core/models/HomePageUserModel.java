package com.idmedia.core.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idmedia.core.service.SessionService;
import com.idmedia.core.service.UserProfileService;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HomePageUserModel {

    @SlingObject
    private SlingHttpServletRequest request;

    @OSGiService
    SessionService sessionService;

    @OSGiService
    UserProfileService userProfileService;

    String firstname;
    String lastname;

    @PostConstruct
    public void init() throws IOException {
        HttpEntity httpEntity = userProfileService.getUserProfile(sessionService.getSessionEmail(request));
        String responseContent = EntityUtils.toString(httpEntity);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseContent);
        JsonNode userProfileModel = rootNode.get("userProfileModel");
        firstname = userProfileModel.get("firstName").asText();
        lastname = userProfileModel.get("lastName").asText();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
