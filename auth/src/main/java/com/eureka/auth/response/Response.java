package com.eureka.auth.response;

import com.eureka.auth.response.UserResponse;

public class Response {

    String message, service;
    UserResponse data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public UserResponse getData() {
        return data;
    }

    public void setData(UserResponse data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", service='" + service + '\'' +
                ", data=" + data +
                '}';
    }
}
