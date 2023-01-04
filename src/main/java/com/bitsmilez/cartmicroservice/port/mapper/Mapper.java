package com.bitsmilez.cartmicroservice.port.mapper;

import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.config.MQConfig.ProductMessage;
import com.bitsmilez.cartmicroservice.port.dto.CartDTO;
import com.bitsmilez.cartmicroservice.port.dto.ProductDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
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






}
