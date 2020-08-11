package com.ecomm.product.messagehandler;

import com.ecomm.product.config.ConfigureRabbitMq;
import com.ecomm.product.dto.ProductRequest;
import com.ecomm.product.dto.ProductResponse;
import com.ecomm.product.entity.Product;
import com.ecomm.product.service.ProductService;
import com.ecomm.product.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

@Service
@Slf4j
public class ProductMessageHandler {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ProductServiceImpl productService;

    @RabbitListener(queues = ConfigureRabbitMq.QUEUE_NAME_CREATE)
    public void handleMessageCreate(ProductRequest productRequest) {

        Product product = productService.create(convertRequestToProduct(productRequest));
        log.info(convertProductToResponse(product).toString());

//        return convertProductToResponse(product);

    }

    @RabbitListener(queues =  ConfigureRabbitMq.QUEUE_NAME_INQUIRY)
    public ProductResponse handleMessageInquiry(Long id) {

        Product product = productService.findById(id);
        log.info(convertProductToResponse(product).toString());
        return convertProductToResponse(product);
    }

    @RabbitListener(queues =  ConfigureRabbitMq.QUEUE_NAME_UPDATE)
    public void handleMessageUpdate(ProductRequest productRequest) {

        Product product = productService.update(productRequest.getId(), convertRequestToProduct(productRequest));
        log.info(convertProductToResponse(product).toString());
//        return convertProductToResponse(product);
    }

    @RabbitListener(queues =  ConfigureRabbitMq.QUEUE_NAME_DELETE)
    public void handleMessageDelete(Long id) {

        productService.delete(id);
        log.info("Delete id : " + id);

    }

    public ProductResponse convertProductToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setTag( product.getTag());
        productResponse.setCreationTime(product.getCreationTime().toString());

        return productResponse;
//        return new ProductResponse(product.getId(),product.getName(), product.getImageUrl(), product.getTag(), product.getCreationTime().toString());
    }


    public Product convertRequestToProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setImageUrl(productRequest.getImageUrl());
        product.setTag(productRequest.getTag());

        return product;
    }

}
