package com.bitsmilez.cartmicroservice.port.MQAdapter;

import com.bitsmilez.cartmicroservice.config.CheckoutMessage;
import com.bitsmilez.cartmicroservice.config.MQConfig;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class CheckoutProducerTest {

    @Mock
    private RabbitTemplate checkoutTemplate;
    @Mock
    private ICartService cartService;
    @InjectMocks
    private CheckoutProducer checkoutProducer;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(checkoutProducer).build();
    }

    @Test
    public void testCreateCheckout() throws Exception {

        ObjectNode payload = generateSamplePayload();

        when(checkoutTemplate.convertSendAndReceive(eq(MQConfig.CHECKOUT_EXCHANGE), eq(MQConfig.CHECKOUT_TOPIC), any(CheckoutMessage.class)))
                .thenReturn(200);

        mockMvc.perform(post("/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload.toString()))
                .andExpect(status().isOk());

        verify(checkoutTemplate).convertSendAndReceive(eq(MQConfig.CHECKOUT_EXCHANGE), eq(MQConfig.CHECKOUT_TOPIC), any(CheckoutMessage.class));
        verify(cartService).removeAllProducts("1234");
    }


    private ObjectNode generateSamplePayload() {
        Map<String, Object> checkoutMap = new HashMap<>();
        checkoutMap.put("userID", "1234");
        checkoutMap.put("address", "");
        checkoutMap.put("city", "");
        checkoutMap.put("zip", "");
        checkoutMap.put("country", "");
        checkoutMap.put("paymentMethod", "");
        checkoutMap.put("shippingMethod", "hermes");
        checkoutMap.put("firstName", "John");
        checkoutMap.put("lastName", "Doe");
        checkoutMap.put("email", "johndoe@example.com");
        checkoutMap.put("phone", "");
        checkoutMap.put("orderTotal", 100);
        checkoutMap.put("orderItems", Collections.singletonMap("product123", 2));
        Map<String, Object> orderSummary = new HashMap<>();
        orderSummary.put("orderSummary", checkoutMap);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.set("orderSummary", mapper.valueToTree(orderSummary.get("orderSummary")));
        return objectNode;

    }
}


