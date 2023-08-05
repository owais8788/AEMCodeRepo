package com.idmedia.core.service;

import org.apache.sling.api.SlingHttpServletRequest;

public interface SessionService {
    public String getSessionEmail(SlingHttpServletRequest request);
    public String getSessionToken(SlingHttpServletRequest request);
}
