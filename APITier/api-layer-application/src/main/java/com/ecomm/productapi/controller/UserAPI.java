package com.ecomm.productapi.controller;

import com.ecomm.productapi.config.ConfigureRabbitMq;
import com.ecomm.productapi.dto.UserRequest;
import com.ecomm.productapi.dto.UserResponse;
import com.ecomm.productapi.util.Response;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserAPI {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public UserAPI(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<Response> sendMessageCreate(@RequestBody @Validated UserRequest userRequest) {

        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);

        rabbitTemplate.convertAndSend(
                ConfigureRabbitMq.USER_EXCHANGE,
                "api.user.create",
                userRequest
        );

        jres.setMessage("Success New User" + userRequest.getName());
        jres.setData(userRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> sendMessageModify(@PathVariable ("id")Long id, @RequestBody @Validated UserRequest userRequest) {

        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);
        userRequest.setId(id);

        rabbitTemplate.convertAndSend(
                ConfigureRabbitMq.USER_EXCHANGE,
                "api.user.update",
                userRequest
        );

        jres.setMessage("Success Edit User" + userRequest.getName());
        jres.setData(null);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);
    }


    @GetMapping("/get/{email}")
    public ResponseEntity<Response> sendMessageInquiry(@PathVariable("email") String email) {
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);

        UserResponse response = rabbitTemplate.convertSendAndReceiveAsType(
                ConfigureRabbitMq.USER_EXCHANGE,
                "api.user.inquire",
                email,
                new ParameterizedTypeReference<UserResponse>() {}
        );

        jres.setMessage("Success Get User " + email);
        jres.setData(response);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);
    }
}
