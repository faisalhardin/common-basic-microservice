package com.eureka.auth.user;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public class UserService {
    String userUrl = "http://localhost:8080/user/user";

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
//    headers.set(MediaType.APPLICATION_JSON);

    public void getUser() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

}
