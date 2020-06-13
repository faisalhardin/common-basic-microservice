package com.ecomm.gallery.controller;

import com.ecomm.gallery.entity.Category;
import com.ecomm.gallery.entity.Gallery;
import com.ecomm.gallery.service.CategoryService;
import com.ecomm.gallery.service.GalleryService;
import com.ecomm.gallery.util.Response;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(value = "gallery")
public class GalleryController {

    @Autowired
    GalleryService galleryService;

    @Autowired
    CategoryService categoryService;

    private String gallery = "Gallery";

    private Sort.Direction getSortDirection(boolean desc) {
        if (desc) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

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

    @GetMapping(value = "/u/{id}")
    @HystrixCommand(fallbackMethod = "fallback")
    ResponseEntity<Response> getAllByUserId (@RequestParam(name="page") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "date") String sortBy, @RequestParam(defaultValue = "true") boolean desc, @PathVariable("id") Long id )
    {

        String nameOfCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        Response response = new Response();
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(getSortDirection(desc), sortBy));
        response.setService(this.getClass().getName() + nameOfCurrMethod);
        response.setMessage("Success Find By ID");
        Pageable paging = PageRequest.of(page, size, Sort.by(orders));
        Page<Gallery> galleryPage = galleryService.findByUserId(id, paging);
        List<Gallery> galleryPageContent = galleryPage.getContent();

        if ( galleryPageContent.isEmpty() ) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        HashMap<String, Object> res = new HashMap<>();
        res.put("response", galleryPageContent);
        res.put("currentPage", galleryPage.getNumber());
        res.put("totalItems", galleryPage.getTotalElements());
        res.put("totalPages", galleryPage.getTotalPages());

        response.setData(res);

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
