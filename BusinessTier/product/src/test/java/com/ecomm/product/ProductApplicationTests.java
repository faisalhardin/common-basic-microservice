package com.ecomm.product;

import com.ecomm.product.config.ConfigureRabbitMq;
import com.ecomm.product.dto.AbstractResponse;
import com.ecomm.product.entity.Product;
import com.ecomm.product.service.ProductService;
import com.ecomm.product.service.ProductServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@DataJpaTest
class ProductApplicationTests {

    @Test
    void contextLoads() {
    }
//	@TestConfiguration
//	static class EmployeeServiceImplTestContextConfiguration {
//
//		@Bean
//		public ProductService productService() {
//			return new ProductServiceImpl();
//		}
//	}

//	@Autowired
//	private ProductService productService;

//	@Autowired
//	private final RabbitTemplate rabbitTemplate;

//	ProductApplicationTests(RabbitTemplate rabbitTemplate) {
//		this.rabbitTemplate = rabbitTemplate;
//	}

//	@Test
//	void sendMessageCreate() {
//
//		Product product1 = new Product();
//		product1.setName("product1");
//		product1.setTag("shirt");
//		product1.setImageUrl("google.com");
//
//		Product product2 = new Product();
//		product2.setName("product2");
//		product2.setTag("shirt");
//		product2.setImageUrl("google1.com");
//
//		Product product3 = new Product();
//		product3.setName("product3");
//		product3.setTag("cup");
//		product3.setImageUrl("google2.com");
//
//		Product productUpdate = new Product();
//		productUpdate.setId((long) 1);
//		productUpdate.setName("product2Update");
//		productUpdate.setTag("cup");
//		productUpdate.setImageUrl("google2.com");
//
//		Product response1 = (Product) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
//				"api.product.create", product1);
//
//		Product response2 = (Product) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
//				"api.product.create", product2);
//
//		Product response3 = (Product) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
//				"api.product.create", product3);
//
//		assertThat(response1.getName())
//				.isEqualTo(product1.getName());
//
//		Product responseFirst = (Product) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
//				"api.product.inquire", (long)1);
//
//		assertThat(responseFirst.getName())
//				.isEqualTo(product1.getName());
//
//		Product responseUpdate = (Product) rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
//				"api.product.update", productUpdate);
//
//		assertThat(responseUpdate.getName())
//				.isEqualTo(productUpdate.getName());
//
//		rabbitTemplate.convertSendAndReceive(ConfigureRabbitMq.EXCHANGE_NAME,
//				"api.product.delete", (long) 2);
//
//	}

}
