package com.ecomm.productapi.controller;

import com.ecomm.productapi.config.ConfigureRabbitMq;
import com.ecomm.productapi.dto.ProductRequest;
import com.ecomm.productapi.dto.ProductResponse;
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
@RequestMapping(value = "product")
public class ProductAPI {

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    public ProductAPI(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<Response> sendMessageCreate(@RequestBody @Validated ProductRequest productRequest) {

        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);

        rabbitTemplate.convertAndSend(
            ConfigureRabbitMq.PRODUCT_EXCHANGE,
            "api.product.create",
            productRequest
        );

        jres.setMessage("Success New Product" + productRequest.getName());
        jres.setData(null);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> sendMessageModify(@PathVariable ("id")Long id, @RequestBody @Validated ProductRequest productRequest) {

        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);
        productRequest.setId(id);

        rabbitTemplate.convertAndSend(
                ConfigureRabbitMq.PRODUCT_EXCHANGE,
                "api.product.update",
                productRequest
        );

        jres.setMessage("Success Edit Product" + productRequest.getName());
        jres.setData(null);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Response> sendMessageInquiry(@PathVariable("id") Long id) {
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);

        ProductResponse response = rabbitTemplate.convertSendAndReceiveAsType(
                ConfigureRabbitMq.PRODUCT_EXCHANGE,
                "api.product.inquire",
                id,
                new ParameterizedTypeReference<ProductResponse>() {}
        );

        jres.setMessage("Success Get User " + id);
        jres.setData(response);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);
    }
}
