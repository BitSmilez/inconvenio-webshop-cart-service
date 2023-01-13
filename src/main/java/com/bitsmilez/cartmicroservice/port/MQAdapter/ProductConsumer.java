package com.bitsmilez.cartmicroservice.port.MQAdapter;

import com.bitsmilez.cartmicroservice.config.MQConfig.MQConfig;
import com.bitsmilez.cartmicroservice.config.MQConfig.ProductMessage;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.ProductDTO;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @Autowired
    private ICartService cartService;

    public ProductConsumer(ICartService cartService) {
        this.cartService = cartService;
    }

    @RabbitListener(queues = MQConfig.ADD_PRODUCT_QUEUE)
    public void receiveProductMessage(ProductMessage productMessage){
        System.out.println("Received Add message: " + productMessage);
        ProductDTO receivedProduct = Mapper.toProductDTO(productMessage);
        cartService.addProduct(receivedProduct);
    }

    @RabbitListener(queues = MQConfig.REMOVE_PRODUCT_QUEUE)
    public void receiveRemoveProductMessage(ProductMessage productMessage){
        System.out.println("Received Remove message: " + productMessage);
        cartService.removeProduct(productMessage.getUserID(), productMessage.getProductID());
    }

}
