package com.ecomm.product.messagehandler;

import com.ecomm.product.config.ConfigureRabbitMq;
import com.ecomm.product.dto.ProductRequest;
import com.ecomm.product.dto.ProductResponse;
import com.ecomm.product.entity.Product;
import com.ecomm.product.service.ProductService;
import com.ecomm.product.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductMessageHandler {
    @Autowired
    ProductServiceImpl productService;

    @RabbitListener(queues = ConfigureRabbitMq.QUEUE_NAME_CREATE)
    public ProductResponse handleMessageCreate(ProductRequest productRequest) {

        Product product = productService.create(convertRequestToProduct(productRequest));
        log.info(convertProductToResponse(product).toString());

        return convertProductToResponse(product);

    }

    @RabbitListener(queues =  ConfigureRabbitMq.QUEUE_NAME_INQUIRY)
    public ProductResponse handleMessageInquiry(Long id) {

        Product product = productService.findById(id);
        return convertProductToResponse(product);
    }

    @RabbitListener(queues =  ConfigureRabbitMq.QUEUE_NAME_UPDATE)
    public ProductResponse handleMessageUpdate(ProductRequest productRequest) {

        Product product = productService.update(productRequest.getId(), convertRequestToProduct(productRequest));
        return convertProductToResponse(product);

    }

    @RabbitListener(queues =  ConfigureRabbitMq.QUEUE_NAME_DELETE)
    public void handleMessageDelete(Long id) {

        productService.delete(id);

    }

    public ProductResponse convertProductToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setTag( product.getTag());
        productResponse.setCreationTime(product.getCreationTime().toString());

//        return productResponse;
        return new ProductResponse(product.getId(),product.getName(), product.getImageUrl(), product.getTag(), product.getCreationTime().toString());
    }


    public Product convertRequestToProduct(ProductRequest productRequest) {
        Product _product = new Product();
        _product.setName(productRequest.getName());
        _product.setImageUrl(productRequest.getImageUrl());
        _product.setTag(productRequest.getTag());

        return _product;
    }

}
