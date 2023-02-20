package com.bitsmilez.cartmicroservice.port.MQAdapter;

import com.bitsmilez.cartmicroservice.config.CheckoutMessage;
import com.bitsmilez.cartmicroservice.config.MQConfig;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CheckoutProducer {

    @Autowired
    private RabbitTemplate checkoutTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutProducer.class);

    @Autowired
    private final ICartService cartService;

    public CheckoutProducer(RabbitTemplate checkoutTemplate,ICartService cartService) {
        super();
        this.checkoutTemplate = checkoutTemplate;
        this.cartService = cartService;
    }


    @PostMapping("/checkout")
    public ResponseEntity<?> createCheckout(@RequestBody ObjectNode objectNode) {
        CheckoutMessage checkoutMessage = Mapper.toCheckoutMessage(objectNode);
        LOGGER.info(String.format("Sending Checkout Message to Queue -> %s", checkoutMessage));
        Integer response = (Integer) checkoutTemplate.convertSendAndReceive(MQConfig.CHECKOUT_EXCHANGE, MQConfig.CHECKOUT_TOPIC, checkoutMessage);
        LOGGER.info(String.format("Received Checkout ID -> %s", response));
        if (response!= null ||response == 200){
            cartService.removeAllProducts(checkoutMessage.getUserID());

        }
        return new ResponseEntity<>(HttpStatus.valueOf(response));

    }
}
