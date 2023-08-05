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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=/bin/infodales/userlogin"
        })
public class LoginAndCreateSessionServlet extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(final SlingHttpServletRequest request,
                          final SlingHttpServletResponse response) throws IOException,IOException {
        String email = request.getParameter(IDMediaConstant.EMAIL);
        String password = request.getParameter(IDMediaConstant.PASSWORD);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonRequestBody = new JSONObject();
        try {
            jsonRequestBody.put(IDMediaConstant.EMAIL,email);
            jsonRequestBody.put(IDMediaConstant.PASSWORD,password);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String url ="http:// 192.168.0.139:8080/login";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(jsonRequestBody.toString());
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        response.setContentType("application/json");
        String responseContent = EntityUtils.toString(httpEntity);
        if(httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_BAD_REQUEST && responseContent.equals("Invalid Login Credentials")){
//            response.getWriter().write("Invalid Login Credentials");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid email or password.");
        }else if(httpResponse.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
            String token = responseContent;
            HttpSession session=request.getSession();
            session.setAttribute("email",email);
            session.setAttribute("token",token);
//            response.getWriter().write("LogIn successfully");
//            response.sendRedirect("http://infodales:4502/system/console/slinglog");

            response.setStatus(HttpServletResponse.SC_OK);

            response.getWriter().write("Login successful!");

        }else {
            response.getWriter().write("something went wrong");
        }
        httpClient.close();
    }
}