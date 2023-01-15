package com.bitsmilez.cartmicroservice.config.MQConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String ADD_PRODUCT_QUEUE = "add-product-cart-queue";
    public static final String REMOVE_PRODUCT_QUEUE = "remove-product-cart-queue";

    public static final String PRODUCT_EXCHANGE = "product_exchange";

    public static final String PRODUCT_TOPIC_ADD_TO_CART = "productservice.add";
    public static final String PRODUCT_TOPIC_REMOVE_FROM_CART = "productservice.remove";



    @Bean
    Queue addProductQueue() {
        return new Queue(ADD_PRODUCT_QUEUE, false);
    }

    @Bean
    Queue removeProductQueue() {
        return new Queue(REMOVE_PRODUCT_QUEUE, false);
    }

    @Bean
    TopicExchange productExchange() {
        return new TopicExchange(PRODUCT_EXCHANGE);
    }

    @Bean
    Binding addItemBinding(Queue addProductQueue, TopicExchange productExchange) {
        return BindingBuilder.bind(addProductQueue).to(productExchange).with(PRODUCT_TOPIC_ADD_TO_CART);
    }

    @Bean
    Binding removeItemBinding(Queue removeProductQueue, TopicExchange productExchange) {
        return BindingBuilder.bind(removeProductQueue).to(productExchange).with(PRODUCT_TOPIC_REMOVE_FROM_CART);
    }


    @Bean
    public MessageConverter productMessageJsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate productTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(productMessageJsonConverter());
        return template;
    }


}
