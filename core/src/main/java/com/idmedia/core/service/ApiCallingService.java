package com.idmedia.core.service;

import com.idmedia.core.pojos.ApiServletResponse;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;

public interface ApiCallingService {

    public ApiServletResponse apiCall(final SlingHttpServletRequest request,final SlingHttpServletResponse response, String api,String email,String password) throws IOException;
}
