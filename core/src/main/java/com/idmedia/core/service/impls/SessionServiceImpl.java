package com.idmedia.core.service.impls;

import com.idmedia.core.service.SessionService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpSession;

@Component(service = SessionService.class)
public class SessionServiceImpl implements SessionService{
    @Override
    public String getSessionEmail(SlingHttpServletRequest request) {
        String sessionEmail = "";

        HttpSession session = request.getSession();
        if (session != null) {
            Object sessionEmailObject = session.getAttribute("email");

            if (sessionEmailObject!= null) {
                sessionEmail = sessionEmailObject.toString();

            } else {
                sessionEmail = "session is empty";
            }
        } else {
            sessionEmail = "Session is not created";
        }
        return sessionEmail;
    }

    @Override
    public String getSessionToken(SlingHttpServletRequest request) {
        String sessionToken="";

        HttpSession session = request.getSession();
        if (session != null) {
            Object sessionTokenObject = session.getAttribute("token");
            if (sessionTokenObject != null) {
                sessionToken = sessionTokenObject.toString();
            } else {
                sessionToken = "session is empty";
            }
        } else {
            sessionToken ="session is not created";
        }
        return sessionToken;
    }
}
