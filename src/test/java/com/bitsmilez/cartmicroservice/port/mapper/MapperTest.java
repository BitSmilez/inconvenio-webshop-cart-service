package com.bitsmilez.cartmicroservice.port.mapper;

import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.config.MQConfig.ProductMessage;
import com.bitsmilez.cartmicroservice.core.domain.model.ProductID;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.CartDTO;
import com.bitsmilez.cartmicroservice.core.domain.service.impl.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapperTest {

    UUID id = new UUID(0, 1);
    UUID productID = new UUID(0, 2);
    String productName = "Test";
    BigDecimal productPrice = new BigDecimal("2.99");
    BigDecimal productSalesPrice = new BigDecimal("1.99");
    String productImg = "TestPath";
    int quantity = 10;


    String cartID = id.toString();
    //CART
    CartDTO cartDTO = new CartDTO(cartID);

    //Product

    Product productEntity = new Product(new ProductID(productID, cartID), productName, productPrice, productSalesPrice, productImg, quantity);
    ProductDTO productDTO = new ProductDTO(productID,cartDTO.getCartID(), productName, productPrice, productSalesPrice, productImg, quantity);


    @Test
    void ProductEntityToDTO() {


        ProductDTO testDTO = Mapper.toProductDTO(productEntity);
        assertEquals(productDTO, testDTO);

    }

    @Test
    void ProductMessageToDTO() {
        ProductMessage message = new ProductMessage(id.toString(), productID.toString(), id.toString(), productName, productPrice, productSalesPrice, productImg, quantity);
        ProductDTO testDTO = Mapper.toProductDTO(message);
        assertEquals(productDTO, testDTO);


    }

    @Test
    void ProductDTOtoProductEntity() {
        Product testEntity = Mapper.toProduct(productDTO);
        assertEquals(productEntity, testEntity);
    }




}