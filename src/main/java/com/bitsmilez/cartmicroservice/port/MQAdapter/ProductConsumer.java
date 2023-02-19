package com.bitsmilez.cartmicroservice.port.MQAdapter;

import com.bitsmilez.cartmicroservice.config.MQConfig;
import com.bitsmilez.cartmicroservice.config.ProductMessage;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.ProductDTO;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    @Autowired
    private ICartService cartService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);

    public ProductConsumer(ICartService cartService) {
        this.cartService = cartService;
    }

    @RabbitListener(queues = MQConfig.ADD_PRODUCT_QUEUE)
    public void receiveProductMessage(ProductMessage productMessage){

        LOGGER.info(String.format("Add Message received -> %s", productMessage));

        ProductDTO receivedProduct = Mapper.toProductDTO(productMessage);
        cartService.addProduct(receivedProduct);
    }

    @RabbitListener(queues = MQConfig.REMOVE_PRODUCT_QUEUE)
    public void receiveRemoveProductMessage(ProductMessage productMessage){

        LOGGER.info(String.format("Remove Message received -> %s", productMessage));

        cartService.removeProduct(productMessage.getUserID(), productMessage.getProductID());
    }

    @RabbitListener(queues = MQConfig.UPDATE_PRODUCT_QUEUE)
    public void receiveUpdateProductMessage(ProductMessage productMessage){

        LOGGER.info(String.format("Update Message received -> %s", productMessage));

        cartService.updateProduct(productMessage.getUserID(), productMessage.getProductID(), productMessage.getQuantity());
    }

}
