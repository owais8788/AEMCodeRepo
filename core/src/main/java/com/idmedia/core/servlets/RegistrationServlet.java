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

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=/bin/infodales/registration"
        }
)
public class RegistrationServlet extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(final SlingHttpServletRequest request,
                          final SlingHttpServletResponse response) throws IOException {
        String uid = request.getParameter(IDMediaConstant.UID);
        String email=request.getParameter(IDMediaConstant.EMAIL);
        String password=request.getParameter(IDMediaConstant.PASSWORD);
        String firstName=request.getParameter(IDMediaConstant.FIRSTNAME);
        String lastName=request.getParameter(IDMediaConstant.LASTNAME);
        Long phoneNumber= Long.valueOf(request.getParameter(IDMediaConstant.PHONENUMBER));
        String gender=request.getParameter(IDMediaConstant.GENDER);
        String dob=request.getParameter(IDMediaConstant.DOB);
        String country=request.getParameter(IDMediaConstant.COUNTRY);
        int subscription_id=Integer.parseInt(request.getParameter(IDMediaConstant.SUBSCRIPTION_ID));
        CloseableHttpClient httpClient= HttpClients.createDefault();
        JSONObject jsonRequestBody= new JSONObject();
        try {
            jsonRequestBody.put(IDMediaConstant.UID, uid);
            jsonRequestBody.put(IDMediaConstant.EMAIL,email);
            jsonRequestBody.put(IDMediaConstant.PASSWORD,password);
            jsonRequestBody.put(IDMediaConstant.FIRSTNAME,firstName);
            jsonRequestBody.put(IDMediaConstant.LASTNAME,lastName);
            jsonRequestBody.put(IDMediaConstant.PHONENUMBER,phoneNumber);
            jsonRequestBody.put(IDMediaConstant.GENDER,gender);
            jsonRequestBody.put(IDMediaConstant.DOB,dob);
            jsonRequestBody.put(IDMediaConstant.COUNTRY,country);
            jsonRequestBody.put(IDMediaConstant.SUBSCRIPTION_ID,subscription_id);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String url ="http://192.168.0.139:8080/insert";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(jsonRequestBody.toString());
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        response.setContentType("application/json");
        String responseContent = EntityUtils.toString(httpEntity);
        if(httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_BAD_REQUEST&& responseContent.equals("Profile Already exist")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Profile Already Exist");
        }else if (httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
//            response.getWriter().write(responseContent);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(" Registration successful!");
        }else {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("something went wrong");
        }
        httpClient.close();
    }
}