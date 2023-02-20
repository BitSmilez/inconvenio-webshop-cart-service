package com.bitsmilez.cartmicroservice.port.mapper;

import com.bitsmilez.cartmicroservice.config.CheckoutMessage;
import com.bitsmilez.cartmicroservice.config.ProductMessage;
import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.ProductDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Mapper {
    public static ProductDTO toProductDTO(Product product) {

        ProductDTO dto = new ModelMapper().map(product, ProductDTO.class);
        dto.setProductID(product.getProductID().getProductID());
        return dto;
    }

    public static ProductDTO toProductDTO(ProductMessage message) {
        return new ProductDTO(UUID.fromString(message.getProductID()), message.getUserID(), message.getProductName(), message.getProductPrice(), message.getProductSalesPrice(), message.getProductImg(), message.getQuantity());
    }

    public static Product toProduct(ProductDTO product) {
        return new ModelMapper().map(product, Product.class);
    }

    public static CheckoutMessage toCheckoutMessage(JsonNode checkoutBody) {
        ObjectNode orderSummary = (ObjectNode) checkoutBody.get("orderSummary");
        JsonNode orderItemsNode = orderSummary.get("orderItems");
        Map<String, Integer> orderItems = new HashMap<>();
        orderItemsNode.fields().forEachRemaining(entry -> orderItems.put(entry.getKey(), entry.getValue().asInt()));
        return new CheckoutMessage(
                orderSummary.get("userID").asText(),
                orderSummary.get("address").asText(),
                orderSummary.get("city").asText(),
                orderSummary.get("zip").asText(),
                orderSummary.get("country").asText(),
                orderSummary.get("paymentMethod").asText(),
                orderSummary.get("shippingMethod").asText(),
                orderSummary.get("firstName").asText(),
                orderSummary.get("lastName").asText(),
                orderSummary.get("email").asText(),
                orderSummary.get("phone").asText(),
                orderSummary.get("orderTotal").decimalValue(),
                orderItems
        );
    }
}
