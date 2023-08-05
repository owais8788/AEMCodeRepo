package com.idmedia.core.servlets;

import com.idmedia.core.constants.IDMediaConstant;
import com.idmedia.core.service.MovieReviewService;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=/bin/infodales/getmoviereview"
        })
public class GetMovieReviewServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(GetMovieReviewServlet.class);

    @Reference
    public MovieReviewService movieReviewService;
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {

        String movieTitle = request.getParameter(IDMediaConstant.MOVIETITLE);

    }
}

