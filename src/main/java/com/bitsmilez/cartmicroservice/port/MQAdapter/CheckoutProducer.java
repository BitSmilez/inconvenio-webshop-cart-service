package com.bitsmilez.cartmicroservice.port.MQAdapter;

import com.bitsmilez.cartmicroservice.config.CheckoutMessage;
import com.bitsmilez.cartmicroservice.config.MQConfig;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CheckoutProducer {

    @Autowired
    private RabbitTemplate checkoutTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutProducer.class);

    public CheckoutProducer(RabbitTemplate checkoutTemplate) {
        super();
        this.checkoutTemplate = checkoutTemplate;
    }


    @PostMapping("/checkout")
    public void createCheckout(@RequestBody ObjectNode objectNode) {
        CheckoutMessage checkoutMessage = Mapper.toCheckoutMessage(objectNode);
        LOGGER.info(String.format("Sending Checkout Message to Queue -> %s", checkoutMessage));
        checkoutTemplate.convertAndSend(MQConfig.CHECKOUT_EXCHANGE, MQConfig.CHECKOUT_TOPIC, checkoutMessage);
    }
}
