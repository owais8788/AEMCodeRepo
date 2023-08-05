package com.idmedia.core.servlets;

import com.idmedia.core.constants.IDMediaConstant;
import com.idmedia.core.pojos.ApiServletResponse;
import com.idmedia.core.service.ApiCallingService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=/bin/apiCalling"
        })
public class ApiCallingServlet extends SlingAllMethodsServlet {
    @Reference
    ApiCallingService apiCallingService;

    @Override
    protected void doPost(final SlingHttpServletRequest request,
                          final SlingHttpServletResponse response) throws ServletException,
            IOException {

        String email = request.getParameter(IDMediaConstant.EMAIL);
        String password = request.getParameter(IDMediaConstant.PASSWORD);
        ApiServletResponse servletResponse= apiCallingService.apiCall(request,response,"http://192.168.0.139:8080/login",email,password);
        response.getWriter().write(servletResponse.getMsg());
    }
}
