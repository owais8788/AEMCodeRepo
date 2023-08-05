package com.idmedia.core.service.impls;

import com.idmedia.core.constants.IDMediaConstant;
import com.idmedia.core.service.UserProfileService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

@Component(service = UserProfileService.class)
public class UserProfileImpl implements UserProfileService{
    @Override
    public HttpEntity getUserProfile(String email) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonRequestBody = new JSONObject();
        try {
            jsonRequestBody.put(IDMediaConstant.EMAIL,email);
//            jsonRequestBody.put(IDMediaConstant.TOKEN,sessionToken);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String url ="http://192.168.0.139:8080/getprofile";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(jsonRequestBody.toString());
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return httpEntity;


//        String jsonString = responseContent;
//
//// Create a Gson object
//        Gson gson = new Gson();
//
//// Convert the JSON string to a JsonObject
//        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
//
//// Extract a single attribute from the JsonObject
//        String name = jsonObject.get("firstName").toString();
//        if(name!=null){
//            response.getWriter().write(name);
//        }else{
//            response.getWriter().write("name is empty");
//        }
    }
}
