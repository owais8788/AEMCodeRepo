package com.idmedia.core.servlets;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;

@Component(service = Servlet.class,property ={
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.resource=/bin/infodales/userlogin"
})
public class HomePageServlet extends SlingAllMethodsServlet {

}
