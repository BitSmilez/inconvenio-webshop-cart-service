package com.bitsmilez.cartmicroservice.core.domain.service.impl;

import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartRepository;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.ICartService;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.IProductRepository;
import com.bitsmilez.cartmicroservice.port.dto.CartDTO;
import com.bitsmilez.cartmicroservice.port.dto.ProductDTO;
import com.bitsmilez.cartmicroservice.port.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    ICartRepository cartRepository;
    @Autowired
    IProductRepository productRepository;
    public CartServiceImpl(ICartRepository cartRepository, IProductRepository productRepository) {
        super();
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }



    private ProductDTO temp ( UUID productID, String productName, BigDecimal productPrice, BigDecimal productSalesPrice, String productImg, int quantity) {


        return new ProductDTO(productID, productName, productPrice, productSalesPrice, productImg, quantity,null);

    }
    @Override
    public CartDTO getProducts(String cartID) {
        CartDTO cart = new CartDTO();
        cart.setCartID(cartID);

        ProductDTO product= (temp(      UUID.nameUUIDFromBytes("9d94ae9f-3b8f-47ed-89ab-8bb808e94521".getBytes()), "fork", new BigDecimal(10), new BigDecimal(5), "https://ucarecdn.com/ac45e974-a77e-4f1c-86d1-f189204d18df", 10));
        ProductDTO product2=(temp(       UUID.nameUUIDFromBytes("1d56d360-5495-4dd2-96c1-a87b444bba56".getBytes()), "champa", new BigDecimal(10), null, "https://ucarecdn.com/9f95c6c4-ace3-4f59-a0da-e0c15591e002/", 3));
        ProductDTO product3=( temp(       UUID.nameUUIDFromBytes("3372a9ba-ca98-4718-a7c2-fa7272a011fd".getBytes()), "mug", new BigDecimal("7.5"), null, "https://ucarecdn.com/8f73e3b8-bc9c-46d1-a0f7-1bad02629692/", 5));
        cart.addItem(product);
        cart.addItem(product2);
        cart.addItem(product3);

        cartRepository.save(Mapper.toCart(cart));

        return cartRepository.findById(cartID).map(Mapper::toCartDTO).orElse(null);






    }

    @Override
    public void addProduct(String cartID, UUID productID, String productName, BigDecimal productPrice, BigDecimal productSalesPrice, String productImg, int quantity) {

    }


}
