package com.idmedia.core.servlets;

import com.idmedia.core.service.SessionService;
import com.idmedia.core.service.UserProfileService;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class,
property = {
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=/bin/userprofile"
})
public class HomePage extends SlingAllMethodsServlet {

    @Reference
    UserProfileService userProfileService;
    @Reference
    SessionService sessionService;
    @Override
    protected void doGet(final SlingHttpServletRequest request,
                          final SlingHttpServletResponse response) throws IOException,IOException {



        HttpEntity httpEntity = userProfileService.getUserProfile(sessionService.getSessionEmail(request));
//        response.setContentType("application/json");
        String responseContent = EntityUtils.toString(httpEntity);
        response.getWriter().write(responseContent.toString());


    }
}
