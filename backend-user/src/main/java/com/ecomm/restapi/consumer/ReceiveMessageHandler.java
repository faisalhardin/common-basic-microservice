package com.ecomm.restapi.consumer;

import com.ecomm.restapi.config.ConfigureRabbitMq;
import com.ecomm.restapi.dto.AbstractResponse;
import com.ecomm.restapi.dto.UserRequest;
import com.ecomm.restapi.dto.UserResponse;
import com.ecomm.restapi.entity.User;
import com.ecomm.restapi.implement.UserServiceImpl;
import com.ecomm.restapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiveMessageHandler {

    @Autowired
    UserService userService;

    @Autowired
    RabbitTemplate rabbitTemplate;

//    @RabbitListener(queues = ConfigureRabbitMq.QUEUE_NAME_INQUIRY)
//    public UserResponse handleMessageInquiry(UserRequest user) {
//        log.info("Handle Message Inquiry!!!");
//        User backEndResponse = userService.findByEmail(user.getEmail());
//
//        UserResponse response = new UserResponse();
//        response.setEmail(backEndResponse.getEmail());
//        response.setId(backEndResponse.getId());
//        response.setName(backEndResponse.getName());
//        response.setRole(backEndResponse.getRole());
//
//        log.info(response.toString());
//
//        return  response;
//    }
//
//    @RabbitListener(queues = ConfigureRabbitMq.QUEUE_NAME_CREATE)
//    public UserResponse handleMessageCreate(UserRequest userRequest) {
//        log.info("Handle Message Modify!!!");
//
//        User user = new User();
//        user.setEmail(userRequest.getEmail());
//        user.setName(userRequest.getName());
//        user.setPassword(userRequest.getPassword());
//        user.setRole(userRequest.getRole());
//
//        User backEndResponse = userService.create(user);
//
//        UserResponse response = new UserResponse();
//        response.setEmail(backEndResponse.getEmail());
//        response.setId(backEndResponse.getId());
//        response.setName(backEndResponse.getName());
//        response.setRole(backEndResponse.getRole());
//
//        log.info(response.toString());
//
//        return  response;
//    }
//
//    @RabbitListener(queues = ConfigureRabbitMq.QUEUE_NAME_UPDATE)
//    public UserResponse handleMessageUpdate(UserRequest userRequest) {
//        log.info("Handle Message Modify!!!");
//        User user = new User();
//        user.setId(userRequest.getId());
//        user.setEmail(userRequest.getEmail());
//        user.setName(userRequest.getName());
//        user.setPassword(userRequest.getPassword());
//        user.setRole(userRequest.getRole());
//
//        User backEndResponse = userService.update(user.getId(), user);
//
//        UserResponse response = new UserResponse();
//        response.setEmail(backEndResponse.getEmail());
//        response.setId(backEndResponse.getId());
//        response.setName(backEndResponse.getName());
//        response.setRole(backEndResponse.getRole());
//
//        log.info(response.toString());
//
//        return  response;
//    }
    @RabbitListener(queues = ConfigureRabbitMq.QUEUE_NAME_INQUIRY)
    public UserResponse handleMessageInquiry(String email) {

    User user = userService.findByEmail(email);
    log.info(convertUserToResponse(user).toString());

    return  convertUserToResponse(user);
}

    @RabbitListener(queues = ConfigureRabbitMq.QUEUE_NAME_CREATE)
    public void  handleMessageCreate(UserRequest userRequest) {

        User user = userService.create(convertRequestToUser(userRequest));
        log.info(convertUserToResponse(user).toString());
    }

    @RabbitListener(queues = ConfigureRabbitMq.QUEUE_NAME_UPDATE)
    public void handleMessageUpdate(UserRequest userRequest) {

        User user = userService.update(userRequest.getId(), convertRequestToUser(userRequest));
        log.info(convertUserToResponse(user).toString());

    }

    public User convertRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());

        return user;
    }

    public UserResponse convertUserToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setRole(user.getRole());

        return userResponse;
    }

}
