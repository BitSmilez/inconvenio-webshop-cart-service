package com.bitsmilez.cartmicroservice.port.mapper;

import com.bitsmilez.cartmicroservice.core.domain.model.Cart;
import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.port.MQ.ProductMessage;
import com.bitsmilez.cartmicroservice.port.dto.CartDTO;
import com.bitsmilez.cartmicroservice.port.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapperTest {

    UUID id = new UUID(0,1);
    String productName = "Test";
    BigDecimal productPrice = new BigDecimal("2.99");
    BigDecimal productSalesPrice = new BigDecimal("1.99");
    String productImg ="TestPath";
    int quantity = 10;



    String cartID="12345";
    //CART
    Cart cartEntity = new Cart(cartID);
    CartDTO cartDTO = new CartDTO(cartID);

    //Product

    Product productEntity = new Product(id,productName,productPrice,productSalesPrice,productImg,quantity,cartEntity);
    ProductDTO productDTO = new ProductDTO(id,productName,productPrice,productSalesPrice,productImg,quantity,cartDTO);

    @Test
    void ProductEntityToDTO() {


        ProductDTO testDTO = Mapper.toProductDTO(productEntity);
        assertEquals(productDTO,testDTO);

    }

    @Test
    void ProductMessageToDTO() {
        ProductMessage message = new ProductMessage(id,cartID,id,productName,productPrice,productSalesPrice,productImg,quantity);
        ProductDTO testDTO = Mapper.toProductDTO(message);
        assertEquals(productDTO,testDTO);


    }

    @Test
    void ProductDTOtoProductEntity() {
        Product testEntity = Mapper.toProduct(productDTO);
        assertEquals(productEntity,testEntity);
    }

    @Test
    void CartEntitytoCartDTO() {

        CartDTO testDTO = Mapper.toCartDTO(cartEntity);

        assertEquals(cartDTO,testDTO);
    }

    @Test
    void CartDTOtoCartEntity() {
        Cart testEntity= Mapper.toCart(cartDTO);
        assertEquals(cartEntity,testEntity);
    }
}