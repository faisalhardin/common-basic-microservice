package com.ecomm.gallery.controller;

import com.ecomm.gallery.entity.Category;
import com.ecomm.gallery.entity.Gallery;
import com.ecomm.gallery.service.CategoryService;
import com.ecomm.gallery.service.GalleryService;
import com.ecomm.gallery.util.Response;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping(value = "gallery")
public class GalleryController {

    @Autowired
    GalleryService galleryService;

    @Autowired
    CategoryService categoryService;

    private String gallery = "Gallery";

    @PostMapping
    ResponseEntity<Response> create (@RequestBody @Validated Gallery gallery)
    {
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameOfCurrMethod);
        response.setMessage("Success New User Gallery");

        gallery.setCategory(categoryService.findById(gallery.getCategory().getId()));
        System.out.println("gallery : " + gallery.getCategory().getId());
        response.setData(galleryService.create(gallery));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<Response> update(@PathVariable ("id") Long id, @RequestBody @Validated Gallery gallery)
    {
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameOfCurrMethod);
        response.setMessage("Success Update User Gallery");

        response.setData(galleryService.update(id, gallery));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping(value = "/{id}")
    @HystrixCommand(fallbackMethod = "fallback")
    ResponseEntity<Response> getByID (@PathVariable("id") Long id)
    {
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameOfCurrMethod);
        response.setMessage("Success Find By ID");

        response.setData(galleryService.findById(id));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping
    ResponseEntity<Response> findAll()
    {
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameOfCurrMethod);
        response.setMessage("Success Show All Data");

        response.setData(galleryService.findAll());

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Response> deleteById (@PathVariable("id") Long id)
    {
        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        response.setService(this.getClass().getName() + nameOfCurrMethod);
        response.setMessage("Success Deletion");
        response.setData(galleryService.findById(id));

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

        Gallery gallery = new Gallery();
        gallery.setId(id);

        response.setData(gallery);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
