package com.ecomm.product.messagehandler;

import com.ecomm.product.config.ConfigureRabbitMq;
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
    ProductService productService;

    @RabbitListener(queues = ConfigureRabbitMq.QUEUE_NAME_CREATE)
    public ProductResponse handleMessageCreat

}
