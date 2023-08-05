package com.idmedia.core.service;

import org.apache.http.HttpEntity;

import java.io.IOException;

public interface UserProfileService {

    public HttpEntity getUserProfile(String email) throws IOException;


}
