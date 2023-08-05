package com.idmedia.core.service.impls;

import com.idmedia.core.constants.IDMediaConstant;
import com.idmedia.core.pojos.ApiServletResponse;
import com.idmedia.core.service.ApiCallingService;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component(service = ApiCallingService.class)
public class ApiCallingServiceImpl implements ApiCallingService {
    @Override
    public ApiServletResponse apiCall(SlingHttpServletRequest request, SlingHttpServletResponse response, String api,String email,String password) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonRequestBody = new JSONObject();
        try {
            jsonRequestBody.put(IDMediaConstant.EMAIL,email);
            jsonRequestBody.put(IDMediaConstant.PASSWORD,password);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        HttpPost httpPost = new HttpPost(api);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(jsonRequestBody.toString());
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        response.setContentType("application/json");
        String responseContent = EntityUtils.toString(httpEntity);

        ApiServletResponse servletResponse = new ApiServletResponse();
        if(httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_BAD_REQUEST && responseContent.equals("Invalid Login Credentials")){

            servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletResponse.setMsg("Invalid email or password.");
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             response.getWriter().write("Invalid email or password.");
        }else if(httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
            String token = responseContent;
            HttpSession session=request.getSession();
            session.setAttribute(email, token);

            servletResponse.setStatus(HttpServletResponse.SC_OK);
            servletResponse.setMsg("Login successful!");

//         response.setStatus(HttpServletResponse.SC_OK);
//
//            response.getWriter().write("Login successful!");

        }else {
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            servletResponse.setMsg("something went wrong");

//            response.getWriter().write("something went wrong");
        }
        httpClient.close();
        return servletResponse;

    }
}
