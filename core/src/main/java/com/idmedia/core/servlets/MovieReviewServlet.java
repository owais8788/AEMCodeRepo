package com.idmedia.core.servlets;

import com.idmedia.core.constants.IDMediaConstant;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=/bin/infodales/moviereview"
        })
public class MovieReviewServlet extends SlingAllMethodsServlet {
        private static final long serialVersionUID = 1L;
        String date="null";

        private final Logger logger = LoggerFactory.getLogger(getClass());

        protected void doPost(final SlingHttpServletRequest request,
                              final SlingHttpServletResponse response)
                throws IOException,IOException
        {
                String email = request.getParameter(IDMediaConstant.EMAIL);
                String movieTitle = request.getParameter(IDMediaConstant.MOVIETITLE);
                String userMovieRating=request.getParameter(IDMediaConstant.USERMOVIERATING);
                String userMovieReview=request.getParameter(IDMediaConstant.USERMOVIEREVIEW);

                CloseableHttpClient httpClient = HttpClients.createDefault();
                JSONObject jsonRequestBody = new JSONObject();
                try {
                        jsonRequestBody.put(IDMediaConstant.EMAIL,email);
                        jsonRequestBody.put(IDMediaConstant.MOVIETITLE,movieTitle);
                        jsonRequestBody.put(IDMediaConstant.USERMOVIERATING,userMovieRating);
                        jsonRequestBody.put(IDMediaConstant.USERMOVIEREVIEW,userMovieReview);
                        jsonRequestBody.put("date",date);

                } catch (JSONException e) {
                        throw new RuntimeException(e);
                }

                logger.debug("JSON Object: " + jsonRequestBody);
                String url ="http://192.168.0.139:8080/review";
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Accept", "application/json");
                StringEntity entity = new StringEntity(jsonRequestBody.toString());
                httpPost.setEntity(entity);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                response.setContentType("application/json");
                String responseContent = EntityUtils.toString(httpEntity);

                if(httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_BAD_REQUEST){
                        response.getWriter().write("Invalid response");
                }else if(httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
                        response.getWriter().write("Success");
                }else if(httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                        response.getWriter().write("null");
                }
                httpClient.close();

                response.getWriter().write(jsonRequestBody.toString());

        }
}
