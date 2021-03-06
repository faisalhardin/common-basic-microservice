package com.ecomm.restapi.controller;


import com.ecomm.restapi.entity.User;
import com.ecomm.restapi.service.UserService;
import com.ecomm.restapi.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService userService;

    private String service = "User";

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Validated User user)
    {
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameOfCurrMethod);
        response.setMessage("Success New User" + user.getEmail());

        response.setData(userService.create(user));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<Response> update(@PathVariable ("id")Long id, @RequestBody @Validated User user)
    {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameofCurrMethod);
        response.setMessage("Success Update User");

        response.setData(userService.update(id, user));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }


    @GetMapping(value = "/{id}")
    @HystrixCommand(fallbackMethod = "fallback")
    ResponseEntity<Response> getById (@PathVariable("id") Long id)
    {

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameofCurrMethod);
        response.setMessage("Success Find By ID");
        response.setData(userService.findById(id));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping
    ResponseEntity<Response> findAll()
    {

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameofCurrMethod);
        response.setMessage("Success Show All Data");


        response.setData(userService.findAll());

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Response> deleteById (@PathVariable("id") Long id)
    {

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameofCurrMethod);
        response.setMessage("Success Deletion");
        response.setData(userService.findById(id));

        userService.delete(id);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @GetMapping(value = "/email/{email}")
    ResponseEntity<Response> getByEmail (@PathVariable ("email")String email)
    {

        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameofCurrMethod);
        response.setMessage("Success Find By Email");


        response.setData(userService.findByEmail(email));

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping(value= "email")
    ResponseEntity<Response> getByEmail (@RequestBody @Validated User user)
    {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameofCurrMethod);
        response.setMessage("Success Post to get User by Email" + user.getEmail());

        response.setData(userService.findByEmail(user.getEmail()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    public ResponseEntity<Response> fallback(Long id, Throwable hystrixCommand) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameofCurrMethod);
        response.setMessage("No User by requested Id");

        User user = new User();
        user.setId(id);

        response.setData(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
