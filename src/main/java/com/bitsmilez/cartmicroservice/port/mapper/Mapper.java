package com.bitsmilez.cartmicroservice.port.mapper;

import com.bitsmilez.cartmicroservice.core.domain.model.Cart;
import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.port.MQ.ProductMessage;
import com.bitsmilez.cartmicroservice.port.dto.CartDTO;
import com.bitsmilez.cartmicroservice.port.dto.ProductDTO;
import org.modelmapper.ModelMapper;

public class Mapper {
    public static ProductDTO toProductDTO(Product product){

        return new ModelMapper().map(product,ProductDTO.class);

    }
    public static ProductDTO toProductDTO(ProductMessage message){
        CartDTO cart = new CartDTO(message.getUserID());
        cart.setCartID(message.getUserID());
        return new ProductDTO(message.getProductID(),message.getProductName(),message.getProductPrice(),message.getProductSalesPrice(),message.getProductImg(),message.getQuantity() ,cart);

    }
    public static Product toProduct(ProductDTO product){

        return new ModelMapper().map(product,Product.class);

    }


    public static CartDTO toCartDTO(Cart cart){

        return new ModelMapper().map(cart, CartDTO.class);

    }

    public static Cart toCart(CartDTO cart){

        return new ModelMapper().map(cart, Cart.class);

    }

}
