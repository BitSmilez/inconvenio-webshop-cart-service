package com.bitsmilez.cartmicroservice.port.mapper;

import com.bitsmilez.cartmicroservice.core.domain.model.Cart;
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
        CartDTO cart = new CartDTO(message.getUserID());
        cart.setCartID(message.getUserID());
        return new ProductDTO(UUID.fromString(message.getProductID()), message.getProductName(), message.getProductPrice(), message.getProductSalesPrice(), message.getProductImg(), message.getQuantity(), cart);

    }

    public static Product toProduct(ProductDTO product) {

        return new ModelMapper().map(product, Product.class);

    }


    public static CartDTO toCartDTO(Cart cart) {
        ArrayList<ProductDTO> products = new ArrayList<>();
        for (Product product : cart.getItems()) {
            products.add(toProductDTO(product));
        }


        CartDTO dto = new ModelMapper().map(cart, CartDTO.class);
        dto.setItems(products);
        dto.calculateTotal();
        return dto;


    }

    public static Cart toCart(CartDTO cart) {
        ArrayList<Product> products = new ArrayList<>();
        for (ProductDTO product : cart.getItems()) {
            products.add(toProduct(product));
        }
        Cart entity = new ModelMapper().map(cart, Cart.class);
        entity.setItems(products);
        return entity;


    }

}
