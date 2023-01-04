package com.bitsmilez.cartmicroservice.core.domain.service.impl;

import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.core.domain.model.ProductID;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.cartmicroservice.port.dto.CartDTO;
import com.bitsmilez.cartmicroservice.port.dto.ProductDTO;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    IProductRepository productRepository;

    public CartServiceImpl(IProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }



    @Override
    public CartDTO getProducts(String cartID) {


        List<ProductDTO> products = productRepository.findByProductIDCartID(cartID).stream().map(Mapper::toProductDTO).toList();
        CartDTO cartDTO = new CartDTO(cartID);
        cartDTO.setItems(products);
        return cartDTO;


    }

    @Override
    public void addProduct(ProductDTO product) {
        System.out.println("Adding product to cart: " + product);
        ProductID id = new ProductID( product.getProductID(), product.getCartID());
        productRepository.findById(id).ifPresentOrElse(
                (p) -> {
                    p.setQuantity(p.getQuantity() + product.getQuantity());
                    productRepository.save(p);
                },
                () -> {
                    productRepository.save(Mapper.toProduct(product));
                }
        );

    }

}



