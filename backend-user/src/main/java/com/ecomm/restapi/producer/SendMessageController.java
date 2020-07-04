package com.ecomm.restapi.producer;

import com.ecomm.restapi.config.ConfigureRabbitMq;
import com.ecomm.restapi.dto.AbstractResponse;
import com.ecomm.restapi.dto.UserRequest;
import com.ecomm.restapi.entity.User;
import com.ecomm.restapi.util.Response;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "message")
public class SendMessageController {


    private final RabbitTemplate rabbitTemplate;

    public SendMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send-update")
    public ResponseEntity<Response> sendMessageCreate(@RequestBody @Validated User user) {

        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);
        AbstractResponse response = (AbstractResponse) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
                "user.post.create", user);
        jres.setMessage("Success New User" + user.getEmail());
        jres.setData(response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);

    }

    @PutMapping("/send-update/{id}")
    public ResponseEntity<Response> sendMessageModify(@PathVariable ("id")Long id, @RequestBody @Validated User user) {

        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);
        user.setId(id);
        AbstractResponse response = (AbstractResponse) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
                "user.post.update", user);
        jres.setMessage("Success New User" + user.getEmail());
        jres.setData(response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);

    }


    @GetMapping("/send-get/{email}")
    public ResponseEntity<Response> sendMessageInquiry(@PathVariable("email") String email) {
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response jres = new Response();
        jres.setService(this.getClass().getName() + nameOfCurrMethod);
        UserRequest user = new UserRequest();
        user.setEmail(email);
        AbstractResponse response = (AbstractResponse) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
                "user.get.get", user);

        jres.setMessage("Success Get User" + user.getEmail());
        jres.setData(response);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jres);
    }
}
