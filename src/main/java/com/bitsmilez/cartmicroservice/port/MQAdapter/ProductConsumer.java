package com.bitsmilez.cartmicroservice.port.MQAdapter;

import com.bitsmilez.cartmicroservice.config.MQConfig;
import com.bitsmilez.cartmicroservice.config.ProductMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @RabbitListener(queues = MQConfig.PRODUCT_QUEUE)
    public void receiveProductMessage(ProductMessage productMessage){
        System.out.println("Received product message: " + productMessage);
    }
}
