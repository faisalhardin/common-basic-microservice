package com.ecomm.productapi.controller;

import com.ecomm.productapi.config.ConfigureRabbitMq;
import com.ecomm.productapi.dto.AbstractResponse;
import com.ecomm.productapi.dto.ProductRequest;
import com.ecomm.productapi.dto.ProductResponse;
import com.ecomm.productapi.util.Response;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import org.apache.logging.slf4j;

@RestController
@RequestMapping(value = "product")
public class ProductAPI {

    private final RabbitTemplate rabbitTemplate;

    public ProductAPI(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send-create")
    public ResponseEntity<Response> sendMessageCreate(@RequestBody @Validated ProductRequest productRequest) {

        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);
        ProductResponse response = (ProductResponse) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
                "api.product.create", productRequest);
        jres.setMessage("Success New Product" + productRequest.getName());
        jres.setData(response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);

    }

    @PutMapping("/send-update/{id}")
    public ResponseEntity<Response> sendMessageModify(@PathVariable ("id")Long id, @RequestBody @Validated ProductRequest productRequest) {

        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);
        productRequest.setId(id);
        ProductResponse response = (ProductResponse) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
                "api.product.update", productRequest);
        jres.setMessage("Success Edit Product" + productRequest.getName());
        jres.setData(response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);

    }


    @GetMapping("/send-get/{id}")
    public ResponseEntity<Response> sendMessageInquiry(@PathVariable("id") Long id) {
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);
        AbstractResponse response = (AbstractResponse) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
                "api.product.get", id);

        jres.setMessage("Success Get User" + id);
        jres.setData(response);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);
    }
}
